package com.qwist.orders.util;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * Util methods for security roles
 */
public class RoleUtils {

    /**
     * Check if authentication class contains chosen role
     * @param role Role to check
     * @param authentication Authentication data
     * @return true if contains role
     */
    public static boolean hasRole(String role, Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return authorities.stream().anyMatch(authority -> authority.getAuthority().equals(role));
    }

}
