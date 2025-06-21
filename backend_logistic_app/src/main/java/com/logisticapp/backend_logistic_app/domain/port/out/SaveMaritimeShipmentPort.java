package com.logisticapp.backend_logistic_app.domain.port.out;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.logisticapp.backend_logistic_app.domain.model.MaritimeShipment;

public interface SaveMaritimeShipmentPort {
    MaritimeShipment save(MaritimeShipment shipment);
    Optional<MaritimeShipment> findById(UUID id);
    List<MaritimeShipment> findAll();
    void deleteById(UUID id);
    boolean existsByGuideNumber(String guideNumber);
}
