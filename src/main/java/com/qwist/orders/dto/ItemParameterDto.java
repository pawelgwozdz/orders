package com.qwist.orders.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemParameterDto {

    @NotBlank
    private String name;

    @NotBlank
    private String value;
}
