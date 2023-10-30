package com.qwist.orders.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Represents item parameter sub document
 */
@Getter @Setter
@AllArgsConstructor
public class ItemParameter {

    private String name;

    private String value;
}
