package com.qwist.orders.exceptions;

import com.qwist.orders.enums.ExceptionMessage;
import lombok.Getter;
import lombok.ToString;

/**
 * Exception that occurs when order is not found
 */
@ToString
public class OrderNotFoundException extends RuntimeException {

    @Getter
    private final ExceptionMessage exceptionMessage;
    private final String orderId;

    /**
     * Exception that occurs when order is not found
     * @param exceptionMessage Exception message
     * @param orderId Order id that was not found
     */
    public OrderNotFoundException(ExceptionMessage exceptionMessage, String orderId) {
        super(exceptionMessage.getTitle() + " UserName: " + orderId);
        this.exceptionMessage = exceptionMessage;
        this.orderId = orderId;
    }
}
