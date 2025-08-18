package com.logichain.order_purchase.repository;

import java.util.*;

import org.springframework.data.jpa.repository.JpaRepository;
import com.logichain.order_purchase.model.Order;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findOrderByOrderId(String orderId); 
    //List<Order> findByCustomerId(Long orderId);
}
