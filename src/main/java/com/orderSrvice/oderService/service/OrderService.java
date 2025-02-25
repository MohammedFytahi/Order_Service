package com.orderSrvice.oderService.service;

import com.orderSrvice.oderService.entity.Order;
import com.orderSrvice.oderService.event.OrderCreatedEvent; // Add this import
import com.orderSrvice.oderService.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private KafkaTemplate<String, Object> kafkaTemplate;

    public Order createOrder(Long userId, Long productId, Integer quantity) {
        Double price = 10.0; // Hardcoded for testing

        Order order = new Order();
        order.setUserId(userId);
        order.setProductId(productId);
        order.setQuantity(quantity);
        order.setTotalPrice(price * quantity);
        order.setStatus("PENDING");
        order = orderRepository.save(order);

        // Re-enable Kafka
        OrderCreatedEvent event = new OrderCreatedEvent();
        event.setOrderId(order.getId());
        event.setUserId(userId);
        event.setTotalPrice(order.getTotalPrice());
        kafkaTemplate.send("order-created-topic", event);

        return order;
    }

    public void updateOrderStatus(Long orderId, String status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
        order.setStatus(status);
        orderRepository.save(order);

        // Re-enable Kafka
        if ("SHIPPED".equals(status)) {
            kafkaTemplate.send("order-shipped-topic", orderId.toString());
        }
    }
}