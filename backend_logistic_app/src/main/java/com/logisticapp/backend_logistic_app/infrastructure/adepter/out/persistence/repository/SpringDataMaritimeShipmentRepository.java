package com.logisticapp.backend_logistic_app.infrastructure.adepter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.logisticapp.backend_logistic_app.domain.model.MaritimeShipment;

import java.util.UUID;

@Repository
public interface SpringDataMaritimeShipmentRepository extends JpaRepository<MaritimeShipment, UUID> {
    boolean existsByGuideNumber(String guideNumber);
}