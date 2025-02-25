package com.orderSrvice.oderService.controller;

import com.orderSrvice.oderService.entity.Order;
import com.orderSrvice.oderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @PostMapping
    public Order createOrder(@RequestParam Long userId,
                             @RequestParam Long productId,
                             @RequestParam Integer quantity) {
        return orderService.createOrder(userId, productId, quantity);
    }

    @PutMapping("/{id}/status")
    public void updateOrderStatus(@PathVariable Long id, @RequestParam String status) {
        orderService.updateOrderStatus(id, status);
    }
}