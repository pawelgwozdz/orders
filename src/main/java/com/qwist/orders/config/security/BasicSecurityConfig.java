package com.qwist.orders.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Configuration for endpoints security
 */
@Configuration
@EnableWebSecurity
@EnableMethodSecurity
//Usually better way here is to use some OAuth2 or LDAP, but it is more time-consuming, or for example basic but with bcrypt in database
public class BasicSecurityConfig {

    /**
     * Creates users for spring security
     * @param encoder password encoder
     * @return Returns user details for Spring
     */
    @Bean
    public UserDetailsService userDetailsManager(PasswordEncoder encoder) {

        UserDetails user1 = User.builder()
                .username("user1")
                .password(encoder.encode("test"))
                .roles("CUSTOMER")
                .build();

        UserDetails user2 = User.builder()
                .username("user2")
                .password(encoder.encode("test"))
                .roles("CUSTOMER")
                .build();

        UserDetails admin = User.builder()
                .username("admin")
                .password(encoder.encode("test"))
                .roles("CUSTOMER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(user1, user2, admin);
    }

    /**
     * Specifies which endpoints should be secured or not
     * @param http Configuration data for http requests
     * @return Security filter chain with rules that applies to specified requests
     * @throws Exception handles exceptions from csrf
     */
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(authorize ->
                        authorize.requestMatchers(
                                "/swagger-ui.html","/swagger-ui/**",
                                "/v3/api-docs/**" //<<---(1)
                        ).permitAll())
                .authorizeHttpRequests(authorize ->
                        authorize.anyRequest().authenticated()

                ).httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
