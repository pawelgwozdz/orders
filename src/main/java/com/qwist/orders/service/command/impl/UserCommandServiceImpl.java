package com.qwist.orders.service.command.impl;

import com.qwist.orders.dao.UserRepository;
import com.qwist.orders.dto.CreateOrderDto;
import com.qwist.orders.dto.CreateOrderRespDto;
import com.qwist.orders.dto.DeleteOrderRespDto;
import com.qwist.orders.entity.Order;
import com.qwist.orders.entity.User;
import com.qwist.orders.enums.ExceptionMessage;
import com.qwist.orders.enums.OrderStatus;
import com.qwist.orders.exceptions.OrderNotFoundException;
import com.qwist.orders.exceptions.UserNotFoundException;
import com.qwist.orders.mapper.OrderMapper;
import com.qwist.orders.mapper.UserMapper;
import com.qwist.orders.service.command.UserCommandService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

/**
 * Service for transactions on orders
 */
@RequiredArgsConstructor
@Service
//We could use annotation @ConditionalOnProperty, if we would have multiple servers, and we would like to separate servers for writing and reading
public class UserCommandServiceImpl implements UserCommandService {

    private final UserRepository userRepository;
    private final OrderMapper orderMapper;

    /**
     * Creates new order
     * @param createOrderDto create order DTO class
     * @param principal Principals of user that invoked endpoint
     * @return response with order id and status
     */
    @Override
    public CreateOrderRespDto createOrder(CreateOrderDto createOrderDto, Principal principal) {
        User user = userRepository.findByLogin(principal.getName())
                .orElseThrow(() -> new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND, principal.getName()));

        Order order = orderMapper.orderDtoToOrder(createOrderDto);

        user.getOrders().add(order);
        userRepository.save(user);
        return CreateOrderRespDto.builder()
                .OrderId(order.getId())
                .orderStatus(OrderStatus.CREATED)
                .build();
    }

    /**
     * Deletes chosen order
     * @param orderId Id of order that should be removed
     * @return response with status of removed order
     */
    @Override
    public DeleteOrderRespDto deleteOrder(String orderId) {

        User userWithOrder = userRepository.findByOrdersId(orderId).orElseThrow(() -> new OrderNotFoundException(ExceptionMessage.ORDER_NOT_FOUND, orderId));

        Order orderFound = userWithOrder.getOrders().stream().filter(order -> order.getId().equals(orderId)).findFirst().get();
        userWithOrder.getOrders().remove(orderFound);

        userRepository.save(userWithOrder);

        return DeleteOrderRespDto.builder()
                .orderStatus(OrderStatus.REMOVED)
                .build();
    }
}
