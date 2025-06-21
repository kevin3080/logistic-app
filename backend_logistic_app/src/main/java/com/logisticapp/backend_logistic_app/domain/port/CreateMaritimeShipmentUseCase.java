package com.logisticapp.backend_logistic_app.domain.port;

import com.logisticapp.backend_logistic_app.domain.model.MaritimeShipment;

public interface CreateMaritimeShipmentUseCase {
    MaritimeShipment createShipment(MaritimeShipment shipment);
}