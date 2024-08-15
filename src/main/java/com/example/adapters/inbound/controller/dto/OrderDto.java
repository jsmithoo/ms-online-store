package com.example.adapters.inbound.controller.dto;

import com.example.adapters.outbound.repository.entities.Product;
import java.time.LocalDateTime;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDto {

    private Long id;
    private String number;
    private LocalDateTime creationDate;
    private LocalDateTime dateReceived;
    private Float total;
    private UserDto userDto;
    private Product product;
}
