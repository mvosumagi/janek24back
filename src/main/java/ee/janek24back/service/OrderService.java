package ee.janek24back.service;

import ee.janek24back.controller.order.dto.OrderDto;
import ee.janek24back.infrastructure.exception.PrimaryKeyNotFoundException;
import ee.janek24back.persistence.order.Order;
import ee.janek24back.persistence.order.OrderMapper;
import ee.janek24back.persistence.order.OrderRepository;
import ee.janek24back.persistence.user.User;
import ee.janek24back.persistence.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final UserRepository userRepository;

    @Transactional
    public void addOrder(Integer userId, OrderDto orderDto) {
        Order order = createOrder(userId, orderDto);
        orderRepository.save(order);
    }

    private Order createOrder(Integer userId, OrderDto orderDto) {
        User user = getValidUser(userId);
        Order order = orderMapper.toOrder(orderDto);
        order.setUser(user);
        return order;
    }
    private User getValidUser(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new PrimaryKeyNotFoundException("userId", userId));
    }

    public List<OrderDto> getUserOrders(Integer userId) {
        List<Order> orders = orderRepository.findBy(userId);
        return orders.stream()
                .map(order -> {
                    OrderDto dto = orderMapper.toOrderDto(order);
                    return dto;
                })
                .toList();
    }



}
