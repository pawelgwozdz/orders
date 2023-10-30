package com.qwist.orders.utils;

import com.qwist.orders.dto.ItemParameterDto;

import java.util.List;

public class InitDataItemParameterUtils {

    public static ItemParameterDto getItemParameter1() {
        return new ItemParameterDto("screen", "5.7 Inch");
    }

    public static ItemParameterDto getItemParameter2() {
        return new ItemParameterDto("screen", "17 Inch");
    }

    public static ItemParameterDto getItemParameter3() {
        return new ItemParameterDto("weight", "12 KG");
    }

    public static ItemParameterDto getItemParameter4() {
        return new ItemParameterDto("weight", "100 gram");
    }

    public static ItemParameterDto getItemParameter5() {
        return new ItemParameterDto("Power output", "100 Wat");
    }

    public static ItemParameterDto getItemParameter6() {
        return new ItemParameterDto("Color", "red");
    }

    public static List<ItemParameterDto> getItemParameterList1() {
        return List.of(getItemParameter1(), getItemParameter4());
    }

    public static List<ItemParameterDto> getItemParameterList2() {
        return List.of(getItemParameter2(), getItemParameter3());
    }

    public static List<ItemParameterDto> getItemParameterList3() {
        return List.of(getItemParameter2(), getItemParameter5(), getItemParameter6());
    }

    public static List<ItemParameterDto> getItemParameterList4() {
        return List.of();
    }


}
