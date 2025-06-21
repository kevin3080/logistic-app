package com.logisticapp.backend_logistic_app.infrastructure.adepter.out.persistence.adapter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.logisticapp.backend_logistic_app.domain.model.LandShipment;
import com.logisticapp.backend_logistic_app.domain.port.out.SaveLandShipmentPort;
import com.logisticapp.backend_logistic_app.infrastructure.adepter.out.persistence.repository.SpringDataLandShipmentRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LandShipmentRepositoryImpl implements SaveLandShipmentPort {

    private final SpringDataLandShipmentRepository landShipmentRepository;

    @Override
    public LandShipment save(LandShipment shipment) {
        return landShipmentRepository.save(shipment);
    }

    @Override
    public Optional<LandShipment> findById(UUID id) {
        return landShipmentRepository.findById(id);
    }

    @Override
    public List<LandShipment> findAll() {
        return landShipmentRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        landShipmentRepository.deleteById(id);
    }

    @Override
    public boolean existsByGuideNumber(String guideNumber) {
        return landShipmentRepository.existsByGuideNumber(guideNumber);
    }
}
