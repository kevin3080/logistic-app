package com.logisticapp.backend_logistic_app.domain.port;

import com.logisticapp.backend_logistic_app.domain.model.MaritimeShipment;

import java.util.UUID;

public interface UpdateMaritimeShipmentUseCase {
    MaritimeShipment updateShipment(UUID id, MaritimeShipment shipment);
}

