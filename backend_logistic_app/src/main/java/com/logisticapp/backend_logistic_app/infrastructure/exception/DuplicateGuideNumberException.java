package com.logisticapp.backend_logistic_app.infrastructure.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY) // 422
public class DuplicateGuideNumberException extends RuntimeException {
    public DuplicateGuideNumberException(String message) {
        super(message);
    }
}

