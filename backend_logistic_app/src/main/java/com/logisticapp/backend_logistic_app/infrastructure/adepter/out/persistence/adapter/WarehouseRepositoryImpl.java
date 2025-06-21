package com.logisticapp.backend_logistic_app.infrastructure.adepter.out.persistence.adapter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.logisticapp.backend_logistic_app.domain.model.Warehouse;
import com.logisticapp.backend_logistic_app.infrastructure.adepter.out.persistence.repository.SpringDataWarehouseRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class WarehouseRepositoryImpl {
    private final SpringDataWarehouseRepository warehouseRepository;

    public Warehouse save(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    public Optional<Warehouse> findById(UUID id) {
        return warehouseRepository.findById(id);
    }

    public List<Warehouse> findAll() {
        return warehouseRepository.findAll();
    }

    public void deleteById(UUID id) {
        warehouseRepository.deleteById(id);
    }
}
