package com.logisticapp.backend_logistic_app.application.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.logisticapp.backend_logistic_app.domain.model.Client;
import com.logisticapp.backend_logistic_app.domain.port.in.CreateClientUseCase;
import com.logisticapp.backend_logistic_app.domain.port.in.DeleteClientUseCase;
import com.logisticapp.backend_logistic_app.domain.port.in.GetClientUseCase;
import com.logisticapp.backend_logistic_app.domain.port.in.UpdateClientUseCase;
import com.logisticapp.backend_logistic_app.domain.port.out.SaveClientPort;
import com.logisticapp.backend_logistic_app.infrastructure.exception.ResourceNotFoundException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClientService implements CreateClientUseCase, GetClientUseCase, UpdateClientUseCase, DeleteClientUseCase {

    private final SaveClientPort saveClientPort;

    @Override
    public Client createClient(Client client) {
        return saveClientPort.save(client);
    }

    @Override
    public Optional<Client> getClientById(UUID id) {
        return saveClientPort.findById(id);
    }

    @Override
    public List<Client> getAllClients() {
        return saveClientPort.findAll();
    }

    @Override
    public Client updateClient(UUID id, Client updatedClient) {
        return saveClientPort.findById(id).map(existingClient -> {
            existingClient.setName(updatedClient.getName());
            existingClient.setEmail(updatedClient.getEmail());
            existingClient.setPhone(updatedClient.getPhone());
            return saveClientPort.save(existingClient);
        }).orElseThrow(() -> new ResourceNotFoundException("Client not found with id: " + id));
    }

    @Override
    public void deleteClient(UUID id) {
        if (!saveClientPort.findById(id).isPresent()) {
            throw new ResourceNotFoundException("Client not found with id: " + id);
        }
        saveClientPort.deleteById(id);
    }
}