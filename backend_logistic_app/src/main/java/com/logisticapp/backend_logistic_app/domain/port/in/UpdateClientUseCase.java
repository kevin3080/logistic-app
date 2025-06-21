package com.logisticapp.backend_logistic_app.domain.port.in;

import java.util.UUID;

import com.logisticapp.backend_logistic_app.domain.model.Client;

public interface UpdateClientUseCase {
    Client updateClient(UUID id, Client client);
}

