package com.orderSrvice.oderService.repository;


import com.orderSrvice.oderService.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}