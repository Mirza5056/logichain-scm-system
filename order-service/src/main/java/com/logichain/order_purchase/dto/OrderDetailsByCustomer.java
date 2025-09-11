package com.logichain.order_purchase.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.logichain.order_purchase.model.Order.OrderStatus;
import com.logichain.order_purchase.model.Order.OrderType;

public class OrderDetailsByCustomer {
    private String orderId;
    private String productName;
    private Integer quantity;
    private BigDecimal price;
    private BigDecimal totalAmount;
    private LocalDateTime createdAt;
    private OrderType orderType;
    private OrderStatus orderStatus;
    public OrderDetailsByCustomer(String orderId, String productName, Integer quantity, BigDecimal price,
            BigDecimal totalAmount, LocalDateTime createdAt, OrderType orderType, OrderStatus orderStatus) {
        this.orderId = orderId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
        this.totalAmount = totalAmount;
        this.createdAt = createdAt;
        this.orderType = orderType;
        this.orderStatus = orderStatus;
    }
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) {
        this.productName = productName;
    }
    public Integer getQuantity() {
        return quantity;
    }
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    public BigDecimal getPrice() {
        return price;
    }
    public void setPrice(BigDecimal price) {
        this.price = price;
    }
    public BigDecimal getTotalAmount() {
        return totalAmount;
    }
    public void setTotalAmount(BigDecimal totalAmount) {
        this.totalAmount = totalAmount;
    }
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
    public OrderType getOrderType() {
        return orderType;
    }
    public void setOrderType(OrderType orderType) {
        this.orderType = orderType;
    }
    public OrderStatus getOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
