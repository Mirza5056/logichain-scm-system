package com.logichain.order_purchase.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import com.logichain.common.model.Product;
import com.logichain.common.model.Users;
import com.logichain.common.repository.ProductRepository;
import com.logichain.common.repository.UserRepository;
import com.logichain.order_purchase.dto.OrderDetailsDTO;
import com.logichain.order_purchase.dto.OrderRequestDTO;
import com.logichain.order_purchase.model.Order;
import com.logichain.order_purchase.model.OrderItem;
import com.logichain.order_purchase.repository.OrderItemRepository;
import com.logichain.order_purchase.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public Order createOrder(OrderRequestDTO orderRequest) {
        if (orderRequest.getItems() == null || orderRequest.getItems().isEmpty()) {
            throw new RuntimeException("Order must contain at least one item");
        }

        // Fetch customer
        Users customer = userRepository.findById(orderRequest.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Invalid customer id"));

        Random random = new Random();
        String numberId = "LOGI"+(100000 + random.nextInt(900000));
        Order order = new Order();
        order.setOrderId(numberId);
        order.setUsers(customer);
        order.setOrderType(Order.OrderType.PURCHASE);
        order.setOrderStatus(Order.OrderStatus.CREATED);
        order.setCreatedAt(LocalDateTime.now());

        // Build order items from request DTOs
        List<OrderItem> items = orderRequest.getItems().stream().map(itemReq -> {
            if (itemReq.getProductId() == null) {
                throw new RuntimeException("Product ID must not be null");
            }

            Product product = productRepository.findById(itemReq.getProductId())
                    .orElseThrow(() -> new RuntimeException("Invalid product ID: " + itemReq.getProductId()));

            OrderItem item = new OrderItem();
            item.setProduct(product);
            item.setQuantity(itemReq.getQuantity());
            item.setPrice(product.getPrice()); // Always use DB price
            item.setOrder(order);
            return item;
        }).toList();

        order.setItems(items);

        // Calculate total
        BigDecimal total = items.stream()
                .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        order.setTotalAmount(total);

        return orderRepository.save(order);
    }

    // public Order updateOrderStatus(Long orderId, Order.OrderStatus status) {
    // Order order = getOrderById(orderId);
    // order.setOrderStatus(status);
    // return orderRepository.save(order);
    // }

    public List<OrderDetailsDTO> getOrderById(String orderId) {
        List<Order> orders = orderRepository.findOrderByOrderId(orderId);
        if (orders == null || orders.isEmpty()) {
            throw new RuntimeException("Invalid order id");
        }
        return orders.stream()
                .flatMap(order -> order.getItems().stream().map(item -> new OrderDetailsDTO(
                        order.getOrderId(),
                        order.getUsers().getUsername(),
                        order.getUsers().getEmail(),
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getPrice(),
                        order.getTotalAmount(),
                        order.getCreatedAt(),
                        order.getOrderType(),
                        order.getOrderStatus()
                        )))
                .collect(Collectors.toList());
    }

    public List<OrderDetailsDTO> getAllOrderDetails() {
        List<Order> orders = orderRepository.findAll();

        return orders.stream()
                .flatMap(order -> order.getItems().stream().map(item -> new OrderDetailsDTO(
                        order.getOrderId(),
                        order.getUsers().getUsername(),
                        order.getUsers().getEmail(),
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getPrice(),
                        order.getTotalAmount(),
                        order.getCreatedAt(),
                        order.getOrderType(),
                        order.getOrderStatus()
                        )))
                .collect(Collectors.toList());
    }
}
