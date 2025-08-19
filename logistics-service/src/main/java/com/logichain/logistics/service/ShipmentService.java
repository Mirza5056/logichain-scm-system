package com.logichain.logistics.service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logichain.logistics.dto.ShipmentsDetailsDTO;
import com.logichain.logistics.model.Shipments;
import com.logichain.logistics.model.Shipments.CarrierName;
import com.logichain.logistics.model.Shipments.CurrentStatus;
import com.logichain.logistics.repository.ShipmentRepository;
import com.logichain.order_purchase.model.Order;
import com.logichain.order_purchase.repository.OrderRepository;

@Service
public class ShipmentService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ShipmentRepository shipmentRepository;

    public Shipments addShipment(Shipments shipments) {
        if (shipments.getOrder() == null || shipments.getOrder().getOrderId() == null) {
            throw new RuntimeException("Shipment must contain a valid Order Id.");
        }
        Order order = orderRepository.findByOrderId(shipments.getOrder().getOrderId())
                .orElseThrow(() -> new RuntimeException("Invalid order Id."));
        Random random = new Random();
        String shipmentId = "LOGISHIP" + (100000 + random.nextInt(900000));
        String LogiTrac = "LOGITRA" + (100000 + random.nextInt(900000));
        shipments.setShipmentId(shipmentId);
        shipments.setTrackingNumber(LogiTrac);
        shipments.setOrder(order);

        if (shipments.getCarrierName() == null)
            shipments.setCarrierName(CarrierName.FedEx);

        if (shipments.getCurrentStatus() == null)
            shipments.setCurrentStatus(CurrentStatus.Pending);

        shipments.setExpectedDelivery(shipments.getExpectedDelivery());
        return shipmentRepository.save(shipments);
    }

    public List<ShipmentsDetailsDTO> getAllShipmentDetails() {
        List<Shipments> shipments = shipmentRepository.findAll();
        return shipments.stream()
                .flatMap(shipment -> shipment.getOrder().getItems().stream()
                        .map(item -> new ShipmentsDetailsDTO(shipment.getShipmentId(), shipment.getCurrentStatus(),
                                shipment.getCarrierName(), shipment.getOrder().getOrderId(),
                                shipment.getOrder().getUsers().getUsername(), shipment.getOrder().getUsers().getEmail(),
                                item.getProduct().getName(), item.getQuantity(), shipment.getOrder().getCreatedAt(),
                                shipment.getOrder().getOrderType(), shipment.getOrder().getOrderStatus())))
                .collect(Collectors.toList());

                // some code commit to dev before main
    }
}
