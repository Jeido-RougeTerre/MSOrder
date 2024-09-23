package com.jeido.msorder.dto;

import com.jeido.msorder.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDtoSend {
    private long orderId;
    private UserDto user;
    private String product;

    public static OrderDtoSend of(Order order, UserDto user) {
        return new OrderDtoSend(order.getId(), user, order.getProduct());
    }

    public Order get() {
        return new Order(orderId, user.getId(), product);
    }
}
