package com.logisticapp.backend_logistic_app.domain.port;

import java.util.UUID;

public interface DeleteLandShipmentUseCase {
    void deleteShipment(UUID id);
}
