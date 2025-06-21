package com.logisticapp.backend_logistic_app.domain.port.in;

import java.util.UUID;

public interface DeleteLandShipmentUseCase {
    void deleteShipment(UUID id);
}
