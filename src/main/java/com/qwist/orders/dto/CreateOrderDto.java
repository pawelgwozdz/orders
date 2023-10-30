package com.qwist.orders.dto;

import com.qwist.orders.entity.Item;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
public class CreateOrderDto {

    @NotNull
    private LocalDateTime orderDate;

    @NotBlank
    private String currency;

    @NotBlank
    private String shopName;

    @NotNull
    private List<ItemDto> items;
}
