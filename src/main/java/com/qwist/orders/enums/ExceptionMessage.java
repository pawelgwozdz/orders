package com.qwist.orders.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

/**
 * Represents exceptions responses
 */
@AllArgsConstructor
@Getter @ToString
public enum ExceptionMessage {

    //Usually there would be path something like "user.not.found" and it would return "User not found" from properties
    USER_NOT_FOUND("User not found", "User not found", "Specified username was not found in database"),
    WRONG_GET_ORDERS_REQUEST("Request was wrong", "Request was wrong", "Request was wrong, check api documentation for more information or contact system administrator"),
    ORDER_NOT_FOUND("Order not found", "Order not found", "Specified Order was not found in database");

    private final String title;
    private final String name;
    private final String reason;
}
