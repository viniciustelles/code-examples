package com.crud.catalog.item.dto.mapper;

import com.crud.catalog.item.Item;
import com.crud.catalog.item.dto.ItemDTO;
import com.crud.catalog.item.dto.ItemRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class ItemMapper {

    public ItemDTO toDTO(final Item item) {
        return new ItemDTO(item.getId(), item.getName(), item.getPrice());
    }

    public Item toModel(final ItemRequestDTO itemRequest) {
        return new Item(itemRequest.name(), itemRequest.price());
    }
}
