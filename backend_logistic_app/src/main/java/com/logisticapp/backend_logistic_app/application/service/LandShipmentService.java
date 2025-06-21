package com.logisticapp.backend_logistic_app.application.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.logisticapp.backend_logistic_app.domain.model.LandShipment;
import com.logisticapp.backend_logistic_app.domain.port.in.CreateLandShipmentUseCase;
import com.logisticapp.backend_logistic_app.domain.port.in.DeleteLandShipmentUseCase;
import com.logisticapp.backend_logistic_app.domain.port.in.GetLandShipmentUseCase;
import com.logisticapp.backend_logistic_app.domain.port.out.SaveLandShipmentPort;
import com.logisticapp.backend_logistic_app.domain.port.in.UpdateLandShipmentUseCase;
import com.logisticapp.backend_logistic_app.infrastructure.exception.DuplicateGuideNumberException;
import com.logisticapp.backend_logistic_app.infrastructure.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LandShipmentService implements CreateLandShipmentUseCase, GetLandShipmentUseCase, UpdateLandShipmentUseCase, DeleteLandShipmentUseCase {

    private final SaveLandShipmentPort saveLandShipmentPort;

    @Override
    public LandShipment createShipment(LandShipment shipment) {
        if (saveLandShipmentPort.existsByGuideNumber(shipment.getGuideNumber())) {
            throw new DuplicateGuideNumberException("Land shipment with guide number " + shipment.getGuideNumber() + " already exists.");
        }
        shipment.applyDiscount();
        return saveLandShipmentPort.save(shipment);
    }

    @Override
    public Optional<LandShipment> getShipmentById(UUID id) {
        return saveLandShipmentPort.findById(id);
    }

    @Override
    public List<LandShipment> getAllShipments() {
        return saveLandShipmentPort.findAll();
    }

    @Override
    public LandShipment updateShipment(UUID id, LandShipment updatedShipment) {
        return saveLandShipmentPort.findById(id).map(existingShipment -> {
            if (!existingShipment.getGuideNumber().equals(updatedShipment.getGuideNumber()) &&
                saveLandShipmentPort.existsByGuideNumber(updatedShipment.getGuideNumber())) {
                throw new DuplicateGuideNumberException("Land shipment with guide number " + updatedShipment.getGuideNumber() + " already exists.");
            }

            existingShipment.setClient(updatedShipment.getClient());
            existingShipment.setProduct(updatedShipment.getProduct());
            existingShipment.setQuantity(updatedShipment.getQuantity());
            existingShipment.setRegistrationDate(updatedShipment.getRegistrationDate());
            existingShipment.setDeliveryDate(updatedShipment.getDeliveryDate());
            existingShipment.setWarehouse(updatedShipment.getWarehouse());
            existingShipment.setShippingCost(updatedShipment.getShippingCost());
            existingShipment.setVehiclePlate(updatedShipment.getVehiclePlate());
            existingShipment.setGuideNumber(updatedShipment.getGuideNumber());

            existingShipment.applyDiscount();
            return saveLandShipmentPort.save(existingShipment);
        }).orElseThrow(() -> new ResourceNotFoundException("Land Shipment not found with id: " + id));
    }

    @Override
    public void deleteShipment(UUID id) {
        if (saveLandShipmentPort.findById(id).isEmpty()) {
            throw new ResourceNotFoundException("Land Shipment not found with id: " + id);
        }
        saveLandShipmentPort.deleteById(id);
    }
}
