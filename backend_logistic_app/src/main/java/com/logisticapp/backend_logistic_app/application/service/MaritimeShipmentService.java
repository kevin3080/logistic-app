package com.logisticapp.backend_logistic_app.application.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.logisticapp.backend_logistic_app.domain.model.MaritimeShipment;
import com.logisticapp.backend_logistic_app.domain.port.in.CreateMaritimeShipmentUseCase;
import com.logisticapp.backend_logistic_app.domain.port.in.DeleteMaritimeShipmentUseCase;
import com.logisticapp.backend_logistic_app.domain.port.in.GetMaritimeShipmentUseCase;
import com.logisticapp.backend_logistic_app.domain.port.out.SaveMaritimeShipmentPort;
import com.logisticapp.backend_logistic_app.domain.port.in.UpdateMaritimeShipmentUseCase;
import com.logisticapp.backend_logistic_app.infrastructure.exception.DuplicateGuideNumberException;
import com.logisticapp.backend_logistic_app.infrastructure.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MaritimeShipmentService implements CreateMaritimeShipmentUseCase, GetMaritimeShipmentUseCase, UpdateMaritimeShipmentUseCase, DeleteMaritimeShipmentUseCase {

    private final SaveMaritimeShipmentPort saveMaritimeShipmentPort;

    @Override
    public MaritimeShipment createShipment(MaritimeShipment shipment) {
        if (saveMaritimeShipmentPort.existsByGuideNumber(shipment.getGuideNumber())) {
            throw new DuplicateGuideNumberException("Maritime shipment with guide number " + shipment.getGuideNumber() + " already exists.");
        }
        shipment.applyDiscount();
        return saveMaritimeShipmentPort.save(shipment);
    }

    @Override
    public Optional<MaritimeShipment> getShipmentById(UUID id) {
        return saveMaritimeShipmentPort.findById(id);
    }

    @Override
    public List<MaritimeShipment> getAllShipments() {
        return saveMaritimeShipmentPort.findAll();
    }

    @Override
    public MaritimeShipment updateShipment(UUID id, MaritimeShipment updatedShipment) {
        return saveMaritimeShipmentPort.findById(id).map(existingShipment -> {
            // Check if guide number is being changed to an existing one
            if (!existingShipment.getGuideNumber().equals(updatedShipment.getGuideNumber()) &&
                saveMaritimeShipmentPort.existsByGuideNumber(updatedShipment.getGuideNumber())) {
                throw new DuplicateGuideNumberException("Maritime shipment with guide number " + updatedShipment.getGuideNumber() + " already exists.");
            }

            existingShipment.setClient(updatedShipment.getClient());
            existingShipment.setProduct(updatedShipment.getProduct());
            existingShipment.setQuantity(updatedShipment.getQuantity());
            existingShipment.setRegistrationDate(updatedShipment.getRegistrationDate());
            existingShipment.setDeliveryDate(updatedShipment.getDeliveryDate());
            existingShipment.setPort(updatedShipment.getPort());
            existingShipment.setShippingCost(updatedShipment.getShippingCost());
            existingShipment.setFleetNumber(updatedShipment.getFleetNumber());
            existingShipment.setGuideNumber(updatedShipment.getGuideNumber());

            existingShipment.applyDiscount(); // Re-apply discount in case quantity/price changed
            return saveMaritimeShipmentPort.save(existingShipment);
        }).orElseThrow(() -> new ResourceNotFoundException("Maritime Shipment not found with id: " + id));
    }

    @Override
    public void deleteShipment(UUID id) {
        if (saveMaritimeShipmentPort.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Maritime Shipment not found with id: " + id);
        }
        saveMaritimeShipmentPort.deleteById(id);
    }
}