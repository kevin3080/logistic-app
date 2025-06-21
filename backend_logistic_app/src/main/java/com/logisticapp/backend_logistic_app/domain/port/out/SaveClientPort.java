package com.logisticapp.backend_logistic_app.domain.port.out;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.logisticapp.backend_logistic_app.domain.model.Client;

public interface SaveClientPort {
    Client save(Client client);
    Optional<Client> findById(UUID id);
    List<Client> findAll();
    void deleteById(UUID id);
}
