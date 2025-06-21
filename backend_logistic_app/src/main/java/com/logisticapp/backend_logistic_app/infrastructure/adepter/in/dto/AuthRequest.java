package com.logisticapp.backend_logistic_app.infrastructure.adepter.in.dto;

import lombok.Data;

@Data
public class AuthRequest {
    private String username;
    private String password;
}
