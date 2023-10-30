package com.qwist.orders.dto;

import com.qwist.orders.entity.Item;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderRespDto {

    private String id;

    private LocalDateTime orderDate;

    private BigDecimal price;

    private String currency;

    private String shopName;

    private List<ItemRespDto> items;
}
