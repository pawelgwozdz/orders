package com.qwist.orders.mapper;

import com.qwist.orders.dto.CreateOrderDto;
import com.qwist.orders.dto.ItemDto;
import com.qwist.orders.entity.Item;
import com.qwist.orders.entity.Order;
import com.qwist.orders.utils.InitDataCreateOrderDtoUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderMapperTest {

    @Autowired
    OrderMapper orderMapper;

    @Test
    void shouldReturnCorrectOrder() {
        //GIVEN
        CreateOrderDto orderDto4 = InitDataCreateOrderDtoUtils.getOrder4();

        //WHEN
        Order order = orderMapper.orderDtoToOrder(orderDto4);

        //THEN
        assertEquals(orderDto4.getOrderDate(), order.getOrderDate());
        assertEquals(orderDto4.getCurrency(), order.getCurrency());
        assertEquals(orderDto4.getShopName(), order.getShopName());
        assertEquals(orderDto4.getItems().size(), 1);
        assertEquals(order.getItems().size(), 1);
        ItemDto itemDto = orderDto4.getItems().get(0);
        Item item = order.getItems().get(0);
        assertEquals(itemDto.getName(), item.getName());
        assertEquals(itemDto.getPrice(), item.getPrice());
        assertEquals(itemDto.getType(), item.getType());
        assertEquals(itemDto.getItemParameters().size(), item.getItemParameters().size());
    }

}