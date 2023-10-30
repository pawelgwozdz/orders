package com.qwist.orders.util;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.Authentication;

import static org.junit.jupiter.api.Assertions.*;

class RoleUtilsTest {

    @Test
    void hasRoleShouldReturnTrue() {
        //GIVEN
        Authentication mockAuthentication = TestUtils.mockAuth("user1", "ROLE_CUSTOMER");

        //WHEN & THEN
        assertTrue(RoleUtils.hasRole("ROLE_CUSTOMER", mockAuthentication));
    }

    @Test
    void hasRoleShouldReturnFalse() {
        //GIVEN
        Authentication mockAuthentication = TestUtils.mockAuth("user1", "ROLE_CUSTOMER");

        //WHEN & THEN
        assertFalse(RoleUtils.hasRole("ROLE_ADMIN", mockAuthentication));
    }

}