package com.crud.catalog.item.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import org.hibernate.validator.constraints.Length;

public record ItemRequestDTO(@NotBlank @NotNull @Length(min = 5, max = 200) String name, @NotNull BigDecimal price) {}
