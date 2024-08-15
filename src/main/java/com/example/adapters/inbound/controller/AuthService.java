package com.example.adapters.inbound.controller;

import com.example.adapters.inbound.controller.dto.UserLogin;
import com.example.application.JwtService;
import com.example.application.UserEntityService;
import com.example.domain.exception.UnauthorizationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AuthService {

    private final UserEntityService userEntityService;
    private final JwtService jwtService;

    public String login(UserLogin userLogin) {
        return userEntityService
                .findByUsername(userLogin.getUsername())
                .map(user -> {
                    if (user.getPassword().equalsIgnoreCase(userLogin.getPassword())) {
                        return jwtService.generateToken(user.getUsername());
                    }
                    throw new UnauthorizationException("Usuario no autorizado");
                })
                .orElseThrow(() -> new UnauthorizationException("Usuario o contraseña incorrecto"));
    }
}
