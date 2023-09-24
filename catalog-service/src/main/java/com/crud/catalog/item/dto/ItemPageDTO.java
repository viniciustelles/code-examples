package com.crud.catalog.item.dto;

import java.util.List;

public record ItemPageDTO(List<ItemDTO> items, long totalElements, long totalPages) {}
