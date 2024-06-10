package com.peter.backend_technical.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private JwtAuthFilter jwtAuthFilter;

	/*@Bean
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
	} */
	
	/*@Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        // Setting authorization roles
        http.authorizeHttpRequests(configurer -> 
            configurer
                .requestMatchers(HttpMethod.GET, "/api/v1/publico/getUsuarios").hasAnyAuthority("ADMINISTRATOR")
                .requestMatchers(HttpMethod.GET, "/api/v1/publico/getUsuarios/**").hasAnyAuthority("ADMINISTRATOR")
                .requestMatchers(HttpMethod.POST, "/api/v1/publico/addUsuario").hasRole("ADMIN")
                .requestMatchers(HttpMethod.PUT, "/api/v1/publico/updateUsuario/**").hasRole("ESCRITURA")
                .requestMatchers(HttpMethod.DELETE, "/api/v1/publico/deleteUsuario/**").hasRole("ADMIN")
                .requestMatchers(HttpMethod.POST, "/v1.0/oauth/client_credential/accessToken/**").permitAll()
        );
        
        http.addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        // Use HTTP Basic authentication
        http.httpBasic(Customizer.withDefaults());

       
        http.csrf(csrf -> csrf.disable());

        // return that given information
        return http.build();
    }*/
}
	
	
	/* @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(AbstractHttpConfigurer::disable)
                .addFilterBefore(jwtAuthFilter, BasicAuthenticationFilter.class)
                .sessionManagement(customizer -> customizer.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(request ->
                        request
                        .requestMatchers(HttpMethod.POST, "/v1.0/oauth/client_credential/accessToken").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/publico/getUsuarios").permitAll()
                                .anyRequest().authenticated()

                );
        return http.build();
    } */

