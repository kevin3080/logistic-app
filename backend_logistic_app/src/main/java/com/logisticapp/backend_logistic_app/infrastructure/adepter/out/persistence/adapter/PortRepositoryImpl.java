package com.logisticapp.backend_logistic_app.infrastructure.adepter.out.persistence.adapter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.logisticapp.backend_logistic_app.domain.model.Port;
import com.logisticapp.backend_logistic_app.infrastructure.adepter.out.persistence.repository.SpringDataPortRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PortRepositoryImpl {
    private final SpringDataPortRepository portRepository;

    public Port save(Port port) {
        return portRepository.save(port);
    }

    public Optional<Port> findById(UUID id) {
        return portRepository.findById(id);
    }

    public List<Port> findAll() {
        return portRepository.findAll();
    }

    public void deleteById(UUID id) {
        portRepository.deleteById(id);
    }
}
