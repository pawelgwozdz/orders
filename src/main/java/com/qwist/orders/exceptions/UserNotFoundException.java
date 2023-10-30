package com.qwist.orders.exceptions;

import com.qwist.orders.enums.ExceptionMessage;
import lombok.Getter;
import lombok.ToString;

/**
 * Exception that occurs when User is not found
 */
@ToString
public class UserNotFoundException extends RuntimeException {

    @Getter
    private final ExceptionMessage exceptionMessage;
    private final String userName;

    /**
     * Exception that occurs when User is not found
     * @param exceptionMessage Exception message
     * @param userName Username that was not found
     */
    public UserNotFoundException(ExceptionMessage exceptionMessage, String userName) {
        super(exceptionMessage.getTitle() + " UserName: " + userName);
        this.exceptionMessage = exceptionMessage;
        this.userName = userName;
    }
}
