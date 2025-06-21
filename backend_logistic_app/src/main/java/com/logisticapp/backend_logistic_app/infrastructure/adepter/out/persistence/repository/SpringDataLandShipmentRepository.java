package com.logisticapp.backend_logistic_app.infrastructure.adepter.out.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.logisticapp.backend_logistic_app.domain.model.LandShipment;

import java.util.UUID;

@Repository
public interface SpringDataLandShipmentRepository extends JpaRepository<LandShipment, UUID> {
    boolean existsByGuideNumber(String guideNumber);
}
