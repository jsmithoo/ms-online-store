package com.example.adapters.inbound.controller;

import com.example.adapters.inbound.controller.dto.OrderDto;
import com.example.adapters.inbound.controller.mapper.OrderMapper;
import com.example.adapters.outbound.repository.entities.Order;
import com.example.application.OrderService;
import com.example.application.paramas.OrderUpdateParams;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping(value = "/api/v1.0/orders")
@RestController
public class OrderController {

    public final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> findById(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.findById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteByIdOrder(@PathVariable Long id) {
        orderService.deleteByIdOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // TODO la order la crea el usuario por lo tanto va estar e el servicio del usuario

    @PutMapping("/{id}")
    public ResponseEntity<OrderDto> updateOrder(
            @PathVariable Long id, @RequestBody OrderUpdateParams orderUpdateParams) {
        Order order = orderService.updateOrder(id, orderUpdateParams);
        return ResponseEntity.ok(OrderMapper.map(order));
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> findAllOrders(int offset, int limit) {
        List<Order> orders = orderService.findAllOrders(offset, limit);
        return ResponseEntity.ok(OrderMapper.map(orders));
    }
}
