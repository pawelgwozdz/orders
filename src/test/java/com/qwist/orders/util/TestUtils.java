package com.qwist.orders.util;

import org.mockito.Mockito;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

public class TestUtils {

    public static Authentication mockAuth(String userName, String role) {
        Authentication mockAuthentication = Mockito.mock(Authentication.class);
        Collection<GrantedAuthority> grantedAuthorities = List.of(new SimpleGrantedAuthority(role));
        Mockito.when(mockAuthentication.getAuthorities()).thenAnswer(unused -> grantedAuthorities);
        Mockito.when(mockAuthentication.getName()).thenReturn(userName);
        return mockAuthentication;
    }

}
