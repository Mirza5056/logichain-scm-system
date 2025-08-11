package com.logichain.order_purchase.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logichain.order_purchase.model.Order;
import com.logichain.order_purchase.model.OrderItem;
import java.util.*;
public interface OrderItemRepository extends JpaRepository<OrderItem,Long> {
    List<OrderItem> findByOrderId(Long orderId); 
    //boolean existsByOrderItem(Long id);   
}
