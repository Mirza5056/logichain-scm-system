package com.logichain.auth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

import com.logichain.order_purchase.dto.OrderDetailsDTO;
import com.logichain.order_purchase.dto.OrderRequestDTO;
import com.logichain.order_purchase.model.Order;
import com.logichain.order_purchase.service.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/createOrder")
    public ResponseEntity<?> createOrder(@RequestBody OrderRequestDTO order) {
        try {
            return ResponseEntity.ok(orderService.createOrder(order));
        } catch (RuntimeException r) {
            return ResponseEntity.badRequest().body(r.getMessage());
        }
    }

    // @PostMapping("/updateStatus")
    // public ResponseEntity<?> updateStatus(@RequestParam Long orderId, @RequestParam Order.OrderStatus status) {
    //     try {
    //         return ResponseEntity.ok(orderService.updateOrderStatus(orderId, status));
    //     } catch (RuntimeException r) {
    //         return ResponseEntity.badRequest().body(r.getMessage());
    //     }
    // }

    // @GetMapping("/getOrderById")
    // public ResponseEntity<?> getOrderById(@RequestParam Long orderId) {
    //     try {
    //         return ResponseEntity.ok(orderService.getOrderById(orderId));
    //     } catch (RuntimeException r) {
    //         return ResponseEntity.badRequest().body(r.getMessage());
    //     }
    // }

    @GetMapping("/details")
    public ResponseEntity<List<OrderDetailsDTO>> getAllOrderDetails() {
        return ResponseEntity.ok(orderService.getAllOrderDetails());
    }

    @GetMapping("/getOrderById/{id}")
    public ResponseEntity<?> getOrderById(@PathVariable Long id) {
        try
        {
            return ResponseEntity.ok(orderService.getOrderById(id));
        }catch(RuntimeException r) {
            return ResponseEntity.badRequest().body(r.getMessage());
        }
    }
}
