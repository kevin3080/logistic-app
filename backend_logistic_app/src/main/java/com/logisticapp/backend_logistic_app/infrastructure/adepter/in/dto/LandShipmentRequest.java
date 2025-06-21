package com.logisticapp.backend_logistic_app.infrastructure.adepter.in.dto;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LandShipmentRequest {
    @NotNull(message = "Client ID is required")
    private UUID clientId;

    @NotNull(message = "Product ID is required")
    private UUID productId;

    @Min(value = 1, message = "Quantity must be at least 1")
    private int quantity;

    @NotNull(message = "Registration date is required")
    private LocalDate registrationDate;

    @NotNull(message = "Delivery date is required")
    private LocalDate deliveryDate;

    @NotNull(message = "Warehouse ID is required")
    private UUID warehouseId;

    @Min(value = 0, message = "Shipping cost cannot be negative")
    private double shippingCost;

    @NotBlank(message = "Vehicle plate is required")
    @Pattern(regexp = "[A-Z]{3}\\d{3}", message = "Vehicle plate must be 3 initial letters and 3 numbers (e.g., ABC123)")
    private String vehiclePlate;

    @NotBlank(message = "Guide number is required")
    @Size(min = 10, max = 10, message = "Guide number must be 10 alphanumeric characters")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Guide number must be alphanumeric")
    private String guideNumber;
}
