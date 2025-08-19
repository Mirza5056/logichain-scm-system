package com.logichain.logistics.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logichain.logistics.model.Shipments;

public interface ShipmentRepository extends JpaRepository<Shipments,Long> {
    Shipments findByTrackingNumber(String trackingNumber);   
    Optional<Shipments> findByShipmentId(String shipmentId);
}   
