package com.qwist.orders.dao;

import com.qwist.orders.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Data access object for user collection
 */
@Repository
public interface UserRepository extends MongoRepository<User, String> {

    /**
     * Finds user by unique login
     * @param login User login
     * @return User if found
     */
    Optional<User> findByLogin(String login);

    /**
     * Finds user by one of his order id's
     * @param orderId Order Id
     * @return User if found
     */
    Optional<User> findByOrdersId(String orderId);

}
