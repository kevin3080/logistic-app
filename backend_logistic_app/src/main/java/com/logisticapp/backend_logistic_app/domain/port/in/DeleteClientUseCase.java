package com.logisticapp.backend_logistic_app.domain.port.in;

import java.util.UUID;

public interface DeleteClientUseCase {
    void deleteClient(UUID id);
}
