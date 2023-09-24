package com.crud.catalog.item.service;

import com.crud.catalog.item.Item;
import com.crud.catalog.item.dto.ItemDTO;
import com.crud.catalog.item.dto.ItemPageDTO;
import com.crud.catalog.item.dto.ItemRequestDTO;
import com.crud.catalog.item.dto.mapper.ItemMapper;
import com.crud.catalog.item.exception.EntityNotFoudException;
import com.crud.catalog.item.repository.ItemRepository;
import jakarta.validation.constraints.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class ItemService {

    private final ItemRepository itemRepository;
    private final ItemMapper itemMapper;

    public ItemService(ItemRepository itemRepository, ItemMapper itemMapper) {
        this.itemRepository = itemRepository;
        this.itemMapper = itemMapper;
    }

    public ItemDTO create(@Validated ItemRequestDTO itemRequestDTO) {
        Item item = itemMapper.toModel(itemRequestDTO);
        return itemMapper.toDTO(itemRepository.save(item));
    }

    public ItemDTO update(@NotNull String uuid, @Validated ItemRequestDTO itemRequestDTO) {
        Item item = itemRepository.findById(uuid).orElseThrow(() -> new EntityNotFoudException(uuid));

        item.setName(itemRequestDTO.name());
        item.setPrice(itemRequestDTO.price());

        return itemMapper.toDTO(itemRepository.save(item));
    }

    public void delete(@NotNull @NotBlank String id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new EntityNotFoudException(id));

        itemRepository.delete(item);
    }

    public ItemDTO findById(@NotNull @NotBlank String id) {
        Item item = itemRepository.findById(id).orElseThrow(() -> new EntityNotFoudException(id));

        return itemMapper.toDTO(item);
    }

    public ItemPageDTO findAll(@PositiveOrZero Integer page, @Positive @Max(1000) Integer pageSize) {
        final var itemsPage = itemRepository.findAll(PageRequest.of(page, pageSize));
        final var items = itemsPage.getContent().stream().map(itemMapper::toDTO).toList();
        return new ItemPageDTO(items, itemsPage.getTotalElements(), itemsPage.getTotalElements());
    }
}
