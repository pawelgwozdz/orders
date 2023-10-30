package com.qwist.orders.service.query.impl;

import com.qwist.orders.dao.UserRepository;
import com.qwist.orders.dto.UserRespDto;
import com.qwist.orders.entity.User;
import com.qwist.orders.enums.ExceptionMessage;
import com.qwist.orders.exceptions.UserNotFoundException;
import com.qwist.orders.exceptions.WrongGetOrdersRequestException;
import com.qwist.orders.mapper.OrderMapper;
import com.qwist.orders.mapper.UserMapper;
import com.qwist.orders.service.query.UserQueryService;
import com.qwist.orders.util.RoleUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service for querying orders
 */
@RequiredArgsConstructor
@Service
public class UserQueryServiceImpl implements UserQueryService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    /**
     * Returns orders of chosen user
     * @param login Login of chosen user, if was given in request
     * @param authentication Authentication data of user that invoked request
     * @return List of users with their orders
     */
    @Override
    // This method is against Solid single responsibility rule, but it is because I was restricted to create only one endpoint for both customers and admins
    public List<UserRespDto> getOrders(Optional<String> login, Authentication authentication) {
        List<User> users = null;

        boolean role_admin = RoleUtils.hasRole("ROLE_ADMIN", authentication);

        if (login.isEmpty() && role_admin) {
            //We could also use pagination here if amount of orders would be too large
            users = getAllOrders();
        } else if (login.isPresent() && role_admin) {
            users = getUserOrders(login.get());
        } else if (RoleUtils.hasRole("ROLE_CUSTOMER", authentication)){
            users = getUserOrders(authentication.getName());
        } else {
            throw new WrongGetOrdersRequestException(ExceptionMessage.WRONG_GET_ORDERS_REQUEST);
        }

        return userMapper.userListToUserRespDtoList(users);
    }

    public List<User> getAllOrders() {
        return userRepository.findAll();
    }

    public List<User> getUserOrders(String login) {
        User user = userRepository.findByLogin(login)
                .orElseThrow(() -> new UserNotFoundException(ExceptionMessage.USER_NOT_FOUND, login));
        return List.of(user);
    }

}
