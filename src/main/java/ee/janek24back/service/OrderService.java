package ee.janek24back.service;

import ee.janek24back.controller.inbox.dto.UsernameSendRequest;
import ee.janek24back.controller.order.dto.OrderDto;
import ee.janek24back.infrastructure.exception.PrimaryKeyNotFoundException;
import ee.janek24back.persistence.order.Order;
import ee.janek24back.persistence.order.OrderMapper;
import ee.janek24back.persistence.order.OrderRepository;
import ee.janek24back.persistence.providerservice.ProviderService;
import ee.janek24back.persistence.providerservice.ProviderServiceRepository;
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
    private final ProviderServiceRepository providerServiceRepository;
    private final InboxService inboxService;

    @Transactional
    public void addOrder(Integer userId, OrderDto orderDto) {
        Order order = createOrder(userId, orderDto);
        Order saved = orderRepository.save(order);
        notifyInbox(saved);
    }

    private Order createOrder(Integer userId, OrderDto orderDto) {
        User user = getValidUser(userId);
        Order order = orderMapper.toOrder(orderDto);
        order.setUser(user);
        Integer psId = orderDto.getProviderServiceId();
        if (psId == null) throw new PrimaryKeyNotFoundException("providerServiceId", null);
        ProviderService ps = providerServiceRepository.findById(psId)
                .orElseThrow(() -> new PrimaryKeyNotFoundException("providerServiceId", psId));
        order.setProviderService(ps);
        return order;
    }

    private User getValidUser(Integer userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new PrimaryKeyNotFoundException("userId", userId));
    }

    @Transactional(readOnly = true)
    public List<OrderDto> getUserOrders(Integer userId) {
        List<Order> orders = orderRepository.findByUserId(userId);
        return orders.stream()
                .map(order -> {
                    OrderDto dto = orderMapper.toOrderDto(order);
                    dto.setServiceName(order.getProviderService().getName());
                    dto.setUnitCost(order.getProviderService().getUnitCost());
                    dto.setProviderName(order.getProviderService().getUser().getFullName());
                    return dto;
                })
                .toList();
    }

    private void notifyInbox(Order order) {
        User buyer = order.getUser();
        ProviderService ps = order.getProviderService();
        User provider = ps.getUser();

        String subject = "Order " + order.getId();
        String buyerBody = "Your order " + order.getId() + " for " + ps.getName() + " was created on " + order.getDate() + ".";
        String providerBody = "New order " + order.getId() + " from " + buyer.getFullName() + " for " + ps.getName() + " on " + order.getDate() + ".";

        UsernameSendRequest toBuyer =
                new UsernameSendRequest(provider.getUsername(), buyer.getUsername(), null, subject, buyerBody);
        inboxService.composeByUsernames(toBuyer);

        UsernameSendRequest toProvider =
                new UsernameSendRequest(buyer.getUsername(), provider.getUsername(), null, subject, providerBody);
        inboxService.composeByUsernames(toProvider);
    }
}
