package com.logichain.logistics.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logichain.logistics.model.Shipments;
import com.logichain.logistics.model.ShipmentsTrackingEvents;
import com.logichain.logistics.repository.ShipmentRepository;
import com.logichain.logistics.repository.ShipmentTrackingRepository;

@Service
public class ShipmentTrackingService {
    @Autowired
    private ShipmentTrackingRepository shipmentTrackingRepository;
    @Autowired
    private ShipmentRepository shipmentRepository;

    public ShipmentsTrackingEvents addTrackingEvents(ShipmentsTrackingEvents request) {
        Shipments shipments = shipmentRepository.findByShipmentId(request.getShipments().getShipmentId())
                .orElseThrow(() -> new RuntimeException("shipment not found with id"));

        ShipmentsTrackingEvents events = new ShipmentsTrackingEvents();
        events.setShipments(shipments);
        events.setLocation(request.getLocation());
        events.setStatus(request.getStatus());
        events.setTimestamp(LocalDateTime.now());
        return shipmentTrackingRepository.save(events);
    }

    public List<ShipmentsTrackingEvents> getTrackingEventByShipmentId(Long shipmentId) {
        return shipmentTrackingRepository.findByShipmentsIdOrderByTimestampAsc(shipmentId);
    }

    public Optional<ShipmentsTrackingEvents> getLatestTrackingEvent(Long shipmentId) {
        return shipmentTrackingRepository.findTopByShipmentsIdOrderByTimestampDesc(shipmentId);
    }
}
