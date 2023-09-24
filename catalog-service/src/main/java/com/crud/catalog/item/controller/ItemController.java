package com.crud.catalog.item.controller;

import com.crud.catalog.item.dto.ItemDTO;
import com.crud.catalog.item.dto.ItemPageDTO;
import com.crud.catalog.item.dto.ItemRequestDTO;
import com.crud.catalog.item.service.ItemService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Validated
@RestController
@RequestMapping("/api/items")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public ItemPageDTO findAll(
            @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int pageSize) {
        return itemService.findAll(page, pageSize);
    }

    @GetMapping("/{id}")
    public ItemDTO findById(@PathVariable @NotNull @NotBlank String id) {
        return itemService.findById(id);
    }

    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public ItemDTO create(@RequestBody @Valid ItemRequestDTO item) {
        return itemService.create(item);
    }

    @PutMapping(value = "/{id}")
    public ItemDTO update(@PathVariable @NotNull @NotBlank String id, @RequestBody @Valid ItemRequestDTO item) {
        return itemService.update(id, item);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @NotNull @NotBlank String id) {
        itemService.delete(id);
    }
}
