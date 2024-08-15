package com.example.adapters.inbound.controller.mapper;

import com.example.adapters.inbound.controller.dto.OrderDto;
import com.example.adapters.outbound.repository.entities.Order;
import java.util.List;
import java.util.stream.Collectors;
import lombok.experimental.UtilityClass;

@UtilityClass
public class OrderMapper {

    public OrderDto map(Order order) {
        return OrderDto.builder()
                .id(order.getId())
                .number(order.getNumber())
                .creationDate(order.getCreationDate())
                .dateReceived(order.getDateReceived())
                .total(order.getTotal())
                .userDto(UserMapper.toDto(order.getUserEntity()))
                .product(order.getProduct())
                .build();
    }

    public List<OrderDto> map(List<Order> orders) {
        return orders.stream().map(OrderMapper::map).collect(Collectors.toList());
    }
}
