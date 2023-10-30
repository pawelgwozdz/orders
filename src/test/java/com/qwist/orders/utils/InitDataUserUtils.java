package com.qwist.orders.utils;

import com.qwist.orders.entity.User;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class InitDataUserUtils {

    public static User getAdminUser() {
        return User.builder()
                .userType("admin")
                .orders(new ArrayList<>())
                .email("John@gmail.com")
                .firstName("John")
                .lastName("White")
                .login("admin")
                .build();
    }

    public static User getUser1() {
        return User.builder()
                .userType("customer")
                .orders(new ArrayList<>())
                .email("MarcoPolo@gmail.com")
                .firstName("Marco")
                .lastName("Polo")
                .login("user1")
                .build();
    }

    public static User getUser2() {
        return User.builder()
                .userType("customer")
                .orders(new ArrayList<>())
                .email("Arya@outlook.com")
                .firstName("Arya")
                .lastName("Stark")
                .login("user2")
                .build();
    }

    public static List<User> getDefaultUsers() {
        List<User> users = new LinkedList<>();
        users.add(getAdminUser());
        users.add(getUser1());
        users.add(getUser2());
        return users;
    }
}
