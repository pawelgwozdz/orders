package com.qwist.orders.dto;

import com.qwist.orders.entity.ItemParameter;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class ItemRespDto {

    private String name;

    private BigDecimal price;

    private String Type;

    private List<ItemParameterRespDto> itemParameters;
}
