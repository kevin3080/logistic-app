package com.logisticapp.backend_logistic_app.domain.port;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.logisticapp.backend_logistic_app.domain.model.LandShipment;

public interface GetLandShipmentUseCase {
    Optional<LandShipment> getShipmentById(UUID id);
    List<LandShipment> getAllShipments();
}