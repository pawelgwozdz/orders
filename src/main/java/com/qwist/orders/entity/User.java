package com.qwist.orders.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * represents user collection
 */
/*Basically another option here would be to create separate collections for users, orders and items, but I taught that for now
better idea is just to create one collection as volumes of data for now are small, and maybe separate it in feature as volumes would grow larger if needed.
If we created separate collections, there would be multiple repositories, multiple services and probably some facade design patter would be useful*/
@Document(collection = "user")
@Getter @Setter @Builder
public class User {

    @Id
    private String id;

    private String userType;

    @Indexed(unique = true)
    private String login;

    private String firstName;

    private String lastName;

    @Indexed(unique = true)
    private String email;

    private List<Order> orders;

}
