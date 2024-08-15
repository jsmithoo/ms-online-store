package com.example.adapters.inbound.controller;

import com.example.adapters.inbound.controller.dto.UserLogin;
import com.example.application.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(value = "*")
@RequestMapping(value = "/api/v1.0/auth")
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/login")
    public String login(@RequestBody UserLogin userLogin) {
        return authService.login(userLogin);
    }

    /*@GetMapping("/validate")
    public String validateToken(@RequestHeader("Authorization") String token) {
        token = token.replace("Bearer ", ""); // Remove Bearer prefix
        boolean isValid = jwtService.validateToken(token);
        return isValid ? "Token is valid" : "Token is invalid";
    }*/
}
