package com.logisticapp.backend_logistic_app.domain.port.in;

import com.logisticapp.backend_logistic_app.domain.model.LandShipment;

import java.util.UUID;

public interface UpdateLandShipmentUseCase {
    LandShipment updateShipment(UUID id, LandShipment shipment);
}
