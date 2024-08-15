package com.example.adapters.inbound.controller.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserParamsDto {
    private String name;
    private String username;
    private String password;
    private String email;
    private String address;
    private String phone;
    private String type;
}
