package com.jeido.msorder.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import com.jeido.msorder.entity.Order;

@Data
@AllArgsConstructor
public class OrderDtoReceive {

    @NotNull
    @Positive
    private long userId;

    @NotBlank
    private String product;

    public Order get() {
        return Order.builder()
                .userId(userId)
                .product(product)
                .build();
    }
}
