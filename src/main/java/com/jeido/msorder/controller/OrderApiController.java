package com.jeido.msorder.controller;

import com.jeido.msorder.dto.OrderDtoReceive;
import com.jeido.msorder.dto.OrderDtoSend;
import com.jeido.msorder.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/order/")
public class OrderApiController {

    private final OrderService orderService;

    @Autowired
    public OrderApiController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDtoSend> createOrder(@Validated @RequestBody OrderDtoReceive orderDtoReceive) {
        OrderDtoSend orderDtoSend = orderService.create(orderDtoReceive);
        return ResponseEntity.status(HttpStatus.CREATED).body(orderDtoSend);
    }

    @GetMapping
    public ResponseEntity<List<OrderDtoSend>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAll());
    }

    @GetMapping("{id}")
    public ResponseEntity<OrderDtoSend> getOrderById(@PathVariable Long id) {
        OrderDtoSend orderDtoSend = orderService.get(id);
        return ResponseEntity.ok(orderDtoSend);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<List<OrderDtoSend>> getUserOrders(@PathVariable Long id) {
        return ResponseEntity.ok(orderService.getByUser(id));
    }

    @PutMapping("{id}")
    public ResponseEntity<OrderDtoSend> updateOrder(@PathVariable Long id,
                                                    @Validated @RequestBody OrderDtoReceive orderDtoReceive) {
        OrderDtoSend orderDtoSend = orderService.update(id, orderDtoReceive);
        return ResponseEntity.ok(orderDtoSend);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<List<OrderDtoSend>> deleteOrder(@PathVariable Long id) {
        if (orderService.delete(id)) {
            return ResponseEntity.ok(orderService.getAll());
        }
        return ResponseEntity.noContent().build();
    }


}
