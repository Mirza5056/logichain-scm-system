package com.logichain.auth.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.logichain.common.dto.MessageResponse;
import com.logichain.logistics.model.ShipmentsTrackingEvents;
import com.logichain.logistics.service.ShipmentTrackingService;
import org.springframework.web.bind.annotation.CrossOrigin;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/tracking")
public class ShipmentTrackingController {
    @Autowired
    private ShipmentTrackingService shipmentTrackingService;

    @PostMapping("/addShipmentTracking")
    public ResponseEntity<?> addTrackingEvent(@RequestBody ShipmentsTrackingEvents shipmentsTrackingEvents) {
        try {
            shipmentTrackingService.addTrackingEvents(shipmentsTrackingEvents);
            return ResponseEntity.ok(new MessageResponse(true, "shipment added successfully."));
        } catch (RuntimeException r) {
            return ResponseEntity.badRequest().body(new MessageResponse(false, r.getMessage()));
        }
    }

    @GetMapping("/{shipmentId}/tracking")
    public ResponseEntity<List<ShipmentsTrackingEvents>> getTrackingEvents(@PathVariable Long shipmentId) {
        return ResponseEntity.ok(shipmentTrackingService.getTrackingEventByShipmentId(shipmentId));
    }

    @GetMapping("/{shipmentId}/tracking/latest")
    public ResponseEntity<ShipmentsTrackingEvents> getLatestTrackingEvent(@PathVariable Long shipmentId) {
        return shipmentTrackingService.getLatestTrackingEvent(shipmentId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}
