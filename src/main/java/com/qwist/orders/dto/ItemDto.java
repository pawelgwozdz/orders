package com.qwist.orders.dto;

import com.qwist.orders.entity.ItemParameter;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
public class ItemDto {

    @NotBlank
    private String name;

    @NotNull
    private BigDecimal price;

    @NotBlank
    private String Type;

    private List<ItemParameterDto> itemParameters;
}
