package com.logisticapp.backend_logistic_app.infrastructure.adepter.out.persistence.adapter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.logisticapp.backend_logistic_app.domain.model.MaritimeShipment;
import com.logisticapp.backend_logistic_app.domain.port.out.SaveMaritimeShipmentPort;
import com.logisticapp.backend_logistic_app.infrastructure.adepter.out.persistence.repository.SpringDataMaritimeShipmentRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MaritimeShipmentRepositoryImpl implements SaveMaritimeShipmentPort {

    private final SpringDataMaritimeShipmentRepository maritimeShipmentRepository;

    @Override
    public MaritimeShipment save(MaritimeShipment shipment) {
        return maritimeShipmentRepository.save(shipment);
    }

    @Override
    public Optional<MaritimeShipment> findById(UUID id) {
        return maritimeShipmentRepository.findById(id);
    }

    @Override
    public List<MaritimeShipment> findAll() {
        return maritimeShipmentRepository.findAll();
    }

    @Override
    public void deleteById(UUID id) {
        maritimeShipmentRepository.deleteById(id);
    }

    @Override
    public boolean existsByGuideNumber(String guideNumber) {
        return maritimeShipmentRepository.existsByGuideNumber(guideNumber);
    }
}
