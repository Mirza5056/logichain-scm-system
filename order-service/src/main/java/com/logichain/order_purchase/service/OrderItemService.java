package com.logichain.order_purchase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logichain.order_purchase.model.Order;
import com.logichain.order_purchase.model.OrderItem;
import com.logichain.order_purchase.repository.OrderItemRepository;
import com.logichain.order_purchase.repository.OrderRepository;

@Service
public class OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private OrderRepository orderRepository;

    public OrderItem addItemToOrder(Long orderId, OrderItem orderItem) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));

        orderItem.setOrder(order);
        return orderItemRepository.save(orderItem);
    }
}
