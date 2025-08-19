package com.logichain.auth.controller;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;
import com.logichain.common.dto.MessageResponse;
import com.logichain.logistics.dto.ShipmentsDetailsDTO;
import com.logichain.logistics.model.Shipments;
import com.logichain.logistics.service.ShipmentService;

@RestController
@RequestMapping("/shipments")
public class ShipmentController {
    @Autowired
    private ShipmentService shipmentService;

    @PostMapping("/addShipment")
    public ResponseEntity<?> addShipment(@RequestBody Shipments shipments) {
        try {
            shipmentService.addShipment(shipments);
            return ResponseEntity.ok(new MessageResponse(true, "shipment added successfully."));
        } catch (RuntimeException r) {
            return ResponseEntity.badRequest().body(new MessageResponse(false, r.getMessage()));
        }
    }

    @GetMapping("/getAllShipmentList")
    public ResponseEntity<List<ShipmentsDetailsDTO>> getAllShipmentList() {
        return ResponseEntity.ok(shipmentService.getAllShipmentDetails());
    }
}
