package com.qwist.orders.exceptions;

import com.qwist.orders.enums.ExceptionMessage;
import lombok.Getter;
import lombok.ToString;

/**
 * Exception that occurs when get orders request is wrong
 */
@ToString
public class WrongGetOrdersRequestException extends RuntimeException {

    @Getter
    private final ExceptionMessage exceptionMessage;

    /**
     * Exception that occurs when get orders request is wrong
     * @param exceptionMessage Exception message
     */
    public WrongGetOrdersRequestException(ExceptionMessage exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }
}
