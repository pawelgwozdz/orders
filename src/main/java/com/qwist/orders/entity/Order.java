package com.qwist.orders.entity;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Represents order sub document
 */
@Getter @Setter
public class Order {

    @Indexed(unique = true)
    private String id;

    private LocalDateTime orderDate;

    @Getter(AccessLevel.NONE)
    @Transient
    private BigDecimal price;

    private String currency;

    private String shopName;

    private List<Item> items;

    @Builder
    public Order() {
        id = new ObjectId().toString();
    }

    public BigDecimal getPrice() {
        price = BigDecimal.ZERO;

        if (items != null && items.size() > 0) {
            price = items.stream()
                    .map(Item::getPrice)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        }

        return price;
    }
}
