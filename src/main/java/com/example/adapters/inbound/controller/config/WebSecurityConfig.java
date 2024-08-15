package com.example.adapters.inbound.controller.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final JWTAuthorizationFilter jwtAuthorizationFilter;

    private static final String[] AUTH_WHITELIST = {
        "/v2/api-docs",
        "/configuration/ui",
        "/swagger-resources/**",
        "/configuration/security",
        "/swagger-ui.html",
        "/webjars/**",
        "/health",
        "/refresh",
        "/api/v1.0/auth/login",
        "/api/v1.0/auth/validate",
        "/swagger-ui/index.html",
        "/swagger-ui/**",
        "/v3/api-docs/**"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeRequests((authorize) -> authorize
                        .requestMatchers(AUTH_WHITELIST)
                        .permitAll() // Exclude login and token endpoints from authentication
                        .anyRequest()
                        .authenticated());
        http.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    /* @Bean
     PasswordEncoder passwordEncoder(){
         return new BCryptPasswordEncoder();
     }

     @Bean
    public AuthenticationManager authenticationManager(HttpSecurity httpSecurity, PasswordEncoder passwordEncoder)throws Exception{
         return httpSecurity.getSharedObject(AuthenticationManagerBuilder.class)
                 .userDetailsService(userEntityService)
                 .passwordEncoder(passwordEncoder())
                 .and().build();
     }*/
}
