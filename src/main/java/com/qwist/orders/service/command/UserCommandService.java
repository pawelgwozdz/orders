package com.qwist.orders.service.command;

import com.qwist.orders.dto.CreateOrderDto;
import com.qwist.orders.dto.CreateOrderRespDto;
import com.qwist.orders.dto.DeleteOrderRespDto;

import java.security.Principal;

public interface UserCommandService {

    CreateOrderRespDto createOrder(CreateOrderDto createOrderDto, Principal principal);

    DeleteOrderRespDto deleteOrder(String orderId);
}
