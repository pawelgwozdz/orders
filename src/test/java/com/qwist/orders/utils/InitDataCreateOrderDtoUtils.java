package com.qwist.orders.utils;

import com.qwist.orders.dto.CreateOrderDto;
import com.qwist.orders.entity.Order;

import java.time.LocalDateTime;

public class InitDataCreateOrderDtoUtils {

    public static CreateOrderDto getOrder1() {
        return CreateOrderDto.builder()
                .orderDate(LocalDateTime.now())
                .currency("DOL")
                .shopName("Amazon")
                .items(InitDataItemDtoUtils.getItemList1())
                .build();
    }

    public static CreateOrderDto getOrder2() {
        return CreateOrderDto.builder()
                .orderDate(LocalDateTime.now())
                .currency("EUR")
                .shopName("Amazon")
                .items(InitDataItemDtoUtils.getItemList2())
                .build();
    }

    public static CreateOrderDto getOrder3() {
        return CreateOrderDto.builder()
                .orderDate(LocalDateTime.now())
                .currency("PLN")
                .shopName("Allegro")
                .items(InitDataItemDtoUtils.getItemList3())
                .build();
    }

    public static CreateOrderDto getOrder4() {
        return CreateOrderDto.builder()
                .orderDate(LocalDateTime.now())
                .currency("DOL")
                .shopName("Ebay")
                .items(InitDataItemDtoUtils.getItemList4())
                .build();
    }

}
