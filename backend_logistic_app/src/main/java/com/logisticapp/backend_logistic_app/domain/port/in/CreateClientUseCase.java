package com.logisticapp.backend_logistic_app.domain.port.in;

import com.logisticapp.backend_logistic_app.domain.model.Client;

public interface CreateClientUseCase {
    Client createClient(Client client);
}
