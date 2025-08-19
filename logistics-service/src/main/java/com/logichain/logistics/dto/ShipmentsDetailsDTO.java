package com.logichain.logistics.dto;

import java.time.LocalDateTime;

import com.logichain.logistics.model.Shipments.CarrierName;
import com.logichain.logistics.model.Shipments.CurrentStatus;
import com.logichain.order_purchase.model.Order.OrderStatus;
import com.logichain.order_purchase.model.Order.OrderType;

public class ShipmentsDetailsDTO {
    private String shipmentId;
    private CurrentStatus currentStatus;
    private CarrierName carrierName;
    private String orderId;
    private String customerName;
    private String customerEmail;
    private String productName;
    private Integer quantity;
    private LocalDateTime createdAt;
    private OrderType orderType;
    private OrderStatus orderStatus;

    public ShipmentsDetailsDTO(String shipmentId, CurrentStatus currentStatus, CarrierName carrierName, String orderId,
            String customerName, String customerEmail, String productName, Integer quantity, LocalDateTime createdAt,
            OrderType orderType, OrderStatus orderStatus) {
        this.shipmentId = shipmentId;
        this.currentStatus = currentStatus;
        this.carrierName = carrierName;
        this.orderId = orderId;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.productName = productName;
        this.quantity = quantity;
        this.createdAt = createdAt;
        this.orderType = orderType;
        this.orderStatus = orderStatus;
    }

    public String getShipmentId() {
        return shipmentId;
    }

    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }

    public CurrentStatus getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(CurrentStatus currentStatus) {
        this.currentStatus = currentStatus;
    }

    public CarrierName getCarrierName() {
        return carrierName;
    }

    public void setCarrierName(CarrierName carrierName) {
        this.carrierName = carrierName;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
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
