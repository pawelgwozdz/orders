package com.qwist.orders.dto;

import com.qwist.orders.enums.OrderStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateOrderRespDto {

    private OrderStatus orderStatus;

    private String OrderId;

}
