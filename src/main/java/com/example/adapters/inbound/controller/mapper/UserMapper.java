package com.example.adapters.inbound.controller.mapper;

import com.example.adapters.inbound.controller.dto.UserDto;
import com.example.adapters.inbound.controller.dto.UserParamsDto;
import com.example.adapters.outbound.repository.entities.UserEntity;
import java.util.List;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;

@UtilityClass
public class UserMapper {

    public UserDto toDto(UserEntity userEntity) {
        return UserDto.builder()
                .id(userEntity.getId())
                .name(userEntity.getName())
                .username(userEntity.getUsername())
                .build();
    }

    public UserEntity toDomain(UserDto userDto) {
        return UserEntity.builder()
                .id(userDto.getId())
                .username(userDto.getUsername())
                .build();
    }

    public UserEntity toDomain(UserParamsDto userParamsDto) {
        return UserEntity.builder()
                .name(userParamsDto.getName())
                .username(userParamsDto.getUsername())
                .password(userParamsDto.getPassword())
                .email(userParamsDto.getEmail())
                .address(userParamsDto.getAddress())
                .phone(userParamsDto.getPhone())
                .type(userParamsDto.getType())
                .build();
    }

    public List<UserDto> map(List<UserEntity> userEntities) {
        return userEntities.stream().map(UserMapper::toDto).collect(Collectors.toList());
    }
}
