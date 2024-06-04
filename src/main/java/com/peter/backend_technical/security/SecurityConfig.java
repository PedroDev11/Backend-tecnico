package com.peter.backend_technical.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.Customizer;


@Configuration
public class SecurityConfig {

	@Bean
    public InMemoryUserDetailsManager userDetailsManager() {
        // Creating users
        UserDetails pedro = User.builder()
            .username("pedro")
            .password("{noop}test123")
            .roles("LECTURA")
            .build();

        UserDetails victor = User.builder()
            .username("victor")
            .password("{noop}test123")
            .roles("ESCRITURA")
            .build();

        UserDetails alex = User.builder()
            .username("alex")
            .password("{noop}test123")
            .roles("ADMIN")
            .build();

        // Return an instance of this memory user details manager
        return new InMemoryUserDetailsManager(pedro, victor, alex);
	} 
	

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Setting authorization roles
        http.authorizeHttpRequests(configurer -> 
            configurer
                .requestMatchers(HttpMethod.GET, "/api/v1/publico/getUsuarios").hasRole("LECTURA")
                .requestMatchers(HttpMethod.GET, "/api/v1/publico/getUsuarios/**").hasRole("LECTURA")
                .requestMatchers(HttpMethod.POST, "/api/v1/publico/addUsuario").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/v1/publico/updateUsuario/**").hasRole("ESCRITURA")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/publico/deleteUsuario/**").hasRole("ADMIN")                
        );

        // Use HTTP Basic authentication
        http.httpBasic(Customizer.withDefaults());

       
        http.csrf(csrf -> csrf.disable());

        // return that given information
        return http.build();
    }  
}
