package com.qwist.orders.dto;

import com.qwist.orders.entity.Order;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.List;

@Data
public class UserRespDto {

    private String id;

    private String userType;

    private String login;

    private String firstName;

    private String lastName;

    private String email;

    private List<OrderRespDto> orders;
}
