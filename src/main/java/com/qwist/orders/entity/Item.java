package com.qwist.orders.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

/**
 * Represents item sub document
 */
@Getter @Setter
@AllArgsConstructor
public class Item {

    private String name;

    private BigDecimal price;

    private String Type;

    private List<ItemParameter> itemParameters;
}
