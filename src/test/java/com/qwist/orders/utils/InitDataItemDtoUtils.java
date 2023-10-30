package com.qwist.orders.utils;

import com.qwist.orders.dto.ItemDto;
import com.qwist.orders.entity.Item;

import java.math.BigDecimal;
import java.util.List;

public class InitDataItemDtoUtils {

    public static ItemDto getItem1() {
        return new ItemDto("IPHONE", new BigDecimal("800"), "Smartphone", InitDataItemParameterUtils.getItemParameterList1());
    }

    public static ItemDto getItem2() {
        return new ItemDto("Surface Pro", new BigDecimal("1300"), "Laptop", InitDataItemParameterUtils.getItemParameterList2());
    }

    public static ItemDto getItem3() {
        return new ItemDto("Dell Alienware", new BigDecimal("1800"), "PC", InitDataItemParameterUtils.getItemParameterList3());
    }

    public static ItemDto getItem4() {
        return new ItemDto("Wooden desk", new BigDecimal("333"), "Furniture", InitDataItemParameterUtils.getItemParameterList4());
    }

    public static List<ItemDto> getItemList1() {
        return List.of(getItem1(), getItem2());
    }

    public static List<ItemDto> getItemList2() {
        return List.of(getItem2(), getItem4());
    }

    public static List<ItemDto> getItemList3() {
        return List.of(getItem1(), getItem3(), getItem4());
    }

    public static List<ItemDto> getItemList4() {
        return List.of(getItem2());
    }
}
