package com.logisticapp.backend_logistic_app.domain.port;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.logisticapp.backend_logistic_app.domain.model.LandShipment;

public interface SaveLandShipmentPort {
    LandShipment save(LandShipment shipment);
    Optional<LandShipment> findById(UUID id);
    List<LandShipment> findAll();
    void deleteById(UUID id);
    boolean existsByGuideNumber(String guideNumber);
}

