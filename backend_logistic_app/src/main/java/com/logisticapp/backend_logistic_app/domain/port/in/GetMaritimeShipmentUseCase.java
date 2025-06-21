package com.logisticapp.backend_logistic_app.domain.port.in;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.logisticapp.backend_logistic_app.domain.model.MaritimeShipment;

public interface GetMaritimeShipmentUseCase {
    Optional<MaritimeShipment> getShipmentById(UUID id);
    List<MaritimeShipment> getAllShipments();
}