package com.logisticapp.backend_logistic_app.infrastructure.adepter.out.persistence.adapter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.logisticapp.backend_logistic_app.domain.model.Client;
import com.logisticapp.backend_logistic_app.domain.port.out.SaveClientPort;
import com.logisticapp.backend_logistic_app.infrastructure.adepter.out.persistence.repository.SpringDataClientRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ClientRepositoryImpl implements SaveClientPort { // Not implementing a domain port directly for Client/Product/etc. as per original UML, but can be done if needed
    private final SpringDataClientRepository clientRepository;

    public Client save(Client client) {
        return clientRepository.save(client);
    }

    public Optional<Client> findById(UUID id) {
        return clientRepository.findById(id);
    }

    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    public void deleteById(UUID id) {
        clientRepository.deleteById(id);
    }
}
