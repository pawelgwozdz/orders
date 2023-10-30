package com.qwist.orders.dto;

import com.qwist.orders.enums.OrderStatus;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeleteOrderRespDto {

    OrderStatus orderStatus;

}
