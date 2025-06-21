package com.logisticapp.backend_logistic_app.domain.port;

import com.logisticapp.backend_logistic_app.domain.model.LandShipment;

public interface CreateLandShipmentUseCase {
    LandShipment createShipment(LandShipment shipment);
}
