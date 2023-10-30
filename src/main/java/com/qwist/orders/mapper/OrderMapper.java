package com.qwist.orders.mapper;

import com.qwist.orders.dto.CreateOrderDto;
import com.qwist.orders.dto.ItemDto;
import com.qwist.orders.dto.ItemParameterDto;
import com.qwist.orders.dto.OrderRespDto;
import com.qwist.orders.entity.Item;
import com.qwist.orders.entity.ItemParameter;
import com.qwist.orders.entity.Order;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Takes care of mapping order, createOrderDto and OrderRespDto
 */
@Mapper(uses = ItemMapper.class)
public interface OrderMapper {

    /**
     * Maps CreateOrderDto to Order
     * @param createOrderDto Create order DTO
     * @return Order
     */
    Order orderDtoToOrder(CreateOrderDto createOrderDto);

    /**
     * Maps Order to OrderRespDto
     * @param order Order
     * @return Order resp DTO
     */
    OrderRespDto orderToOrderRespDto(Order order);
}
