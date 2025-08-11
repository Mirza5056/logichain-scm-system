package com.logichain.order_purchase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import com.logichain.order_purchase.model.Order;
import com.logichain.order_purchase.repository.OrderItemRepository;
import com.logichain.order_purchase.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Transactional
    public Order createOrder(Order order) {
        if(order.getItems() == null || order.getItems().isEmpty()) {
            throw new RuntimeException("Order must be contain at one item");
        }
        BigDecimal total = order.getItems().stream().map(item -> 
            item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))).reduce(BigDecimal.ZERO,BigDecimal::add);

        order.setTotalAmount(total);
        order.setCreatedAt(LocalDateTime.now());
        order.setOrderStatus(Order.OrderStatus.CREATED);
        // Set the back-reference in eah item
        order.getItems().forEach(item -> item.setOrder(order));
        return orderRepository.save(order);
    }

    public Order updateOrderStatus(Long orderId, Order.OrderStatus status) {
        Order order = getOrderById(orderId);
        order.setOrderStatus(status);
        return orderRepository.save(order);
    }
    public Order getOrderById(Long id) {
        return orderRepository.findOrderById(id)
                .orElseThrow(() -> new RuntimeException("Invalid order id"));
    }

    public List<Order> getAllOrderList() {
        return orderRepository.findAll();
    }
}
