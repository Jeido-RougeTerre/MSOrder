package com.jeido.msorder.service;

import com.jeido.msorder.dto.OrderDtoReceive;
import com.jeido.msorder.dto.OrderDtoSend;
import com.jeido.msorder.dto.UserDto;
import com.jeido.msorder.entity.Order;
import com.jeido.msorder.repository.OrderRepository;
import com.jeido.msorder.util.RestClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public OrderDtoSend create(OrderDtoReceive orderDtoReceive) {
        RestClient<UserDto> userDtoRestClient = new RestClient<>("http://localhost:8081/api/user/" + orderDtoReceive.getUserId());
        UserDto userDto = userDtoRestClient.getRequest(UserDto.class);
        if (userDto == null) {
            throw new NoSuchElementException("User not found");
        }
        return OrderDtoSend.of(orderRepository.save(orderDtoReceive.get()), userDto);
    }

    public OrderDtoSend get(Long id) {
        return orderToOrderDtoSend(orderRepository.findById(id).orElseThrow());
    }

    public List<OrderDtoSend> getByUser(long userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream().map(this::orderToOrderDtoSend).toList();

    }

    public List<OrderDtoSend> getAll() {
        List<Order> orders = (List<Order>) orderRepository.findAll();

        return orders.stream().map(this::orderToOrderDtoSend).toList();
    }

    public OrderDtoSend update(Long id, OrderDtoReceive orderDtoReceive) {

        Order order = get(id).get();
        order.setUserId(orderDtoReceive.getUserId());
        order.setProduct(orderDtoReceive.getProduct());

        return orderToOrderDtoSend(orderRepository.save(order));
    }

    public boolean delete(Long id) {
        if (orderRepository.existsById(id)) {

            orderRepository.deleteById(id);
            return !orderRepository.existsById(id);
        }
        return false;
    }

    private OrderDtoSend orderToOrderDtoSend (Order order) {
        RestClient<UserDto> userDtoRestClient = new RestClient<>("http://localhost:8081/api/user/" + order.getUserId());
        UserDto userDto = userDtoRestClient.getRequest(UserDto.class);
        if (userDto == null) {
            throw new NoSuchElementException("User not found");
        }
        return OrderDtoSend.of(order, userDto);
    }

}
