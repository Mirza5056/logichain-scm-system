package com.logichain.logistics.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.logichain.logistics.model.ShipmentsTrackingEvents;

public interface ShipmentTrackingRepository extends JpaRepository<ShipmentsTrackingEvents,Long> {
    List<ShipmentsTrackingEvents> findByShipmentsIdOrderByTimestampAsc(Long shipmentId);
    Optional<ShipmentsTrackingEvents> findTopByShipmentsIdOrderByTimestampDesc(Long shipmentId);
}
