package com.logichain.logistics.model;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import com.logichain.order_purchase.model.Order;
import com.logichain.order_purchase.model.OrderItem;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "shipments")
public class Shipments {
    public enum CarrierName {
        Maersk, MSC, CMA, CGM, FedEx, Freight, XPO, Old
    }
    public enum CurrentStatus {
        Tranist,Delivered,Pending,Processed
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "shipment_id", unique = true)
    private String shipmentId;
    @ManyToOne
    @JoinColumn(name = "order_id",nullable = false,referencedColumnName = "id")
    private Order order;
    @Column(name = "carrier_name",nullable = false)
    private CarrierName carrierName;
    @Column(name = "carrier_status",nullable = false)
    private CurrentStatus currentStatus; 
    @Column(name = "tracking_number",nullable = false,unique = true)
    private String trackingNumber;
    @Column(name = "expected_delivery",nullable = false)
    private Date expectedDelivery;
    @OneToMany(mappedBy = "shipments",cascade = CascadeType.ALL,orphanRemoval = true, fetch = FetchType.EAGER)
    private List<ShipmentsTrackingEvents> shipmentsTrackingEvents;

    public List<ShipmentsTrackingEvents> getShipmentsTrackingEvents() {
        return shipmentsTrackingEvents;
    }
    public void setShipmentsTrackingEvents(List<ShipmentsTrackingEvents> shipmentsTrackingEvents) {
        this.shipmentsTrackingEvents = shipmentsTrackingEvents;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getShipmentId() {
        return shipmentId;
    }
    public void setShipmentId(String shipmentId) {
        this.shipmentId = shipmentId;
    }
    public Order getOrder() {
        return order;
    }
    public void setOrder(Order order) {
        this.order = order;
    }
    public CarrierName getCarrierName() {
        return carrierName;
    }
    public void setCarrierName(CarrierName carrierName) {
        this.carrierName = carrierName;
    }
    public CurrentStatus getCurrentStatus() {
        return currentStatus;
    }
    public void setCurrentStatus(CurrentStatus currentStatus) {
        this.currentStatus = currentStatus;
    }
    public String getTrackingNumber() {
        return trackingNumber;
    }
    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }
    public Date getExpectedDelivery() {
        return expectedDelivery;
    }
    public void setExpectedDelivery(Date expectedDelivery) {
        this.expectedDelivery = expectedDelivery;
    } 
}
