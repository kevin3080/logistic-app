package com.logisticapp.backend_logistic_app.infrastructure.adepter.in.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logisticapp.backend_logistic_app.domain.model.Client;
import com.logisticapp.backend_logistic_app.domain.model.LandShipment;
import com.logisticapp.backend_logistic_app.domain.model.Product;
import com.logisticapp.backend_logistic_app.domain.model.Warehouse;
import com.logisticapp.backend_logistic_app.domain.port.CreateLandShipmentUseCase;
import com.logisticapp.backend_logistic_app.domain.port.DeleteLandShipmentUseCase;
import com.logisticapp.backend_logistic_app.domain.port.GetLandShipmentUseCase;
import com.logisticapp.backend_logistic_app.domain.port.UpdateLandShipmentUseCase;
import com.logisticapp.backend_logistic_app.infrastructure.adepter.in.dto.LandShipmentRequest;
import com.logisticapp.backend_logistic_app.infrastructure.adepter.out.persistence.adapter.ClientRepositoryImpl;
import com.logisticapp.backend_logistic_app.infrastructure.adepter.out.persistence.adapter.ProductRepositoryImpl;
import com.logisticapp.backend_logistic_app.infrastructure.adepter.out.persistence.adapter.WarehouseRepositoryImpl;
import com.logisticapp.backend_logistic_app.infrastructure.exception.ResourceNotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/land-shipments")
@RequiredArgsConstructor
public class LandShipmentController {

    private final CreateLandShipmentUseCase createLandShipmentUseCase;
    private final GetLandShipmentUseCase getLandShipmentUseCase;
    private final UpdateLandShipmentUseCase updateLandShipmentUseCase;
    private final DeleteLandShipmentUseCase deleteLandShipmentUseCase;
    private final ClientRepositoryImpl clientRepository;
    private final ProductRepositoryImpl productRepository;
    private final WarehouseRepositoryImpl warehouseRepository;

    @Operation(summary = "Create a new land shipment")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Shipment created",
            content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = LandShipment.class)) }),
        @ApiResponse(responseCode = "400", description = "Invalid input data",
            content = @Content),
        @ApiResponse(responseCode = "422", description = "Unprocessable Entity (e.g., duplicate guide number)",
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Related resource not found (Client, Product, Warehouse)",
            content = @Content)
    })
    @PostMapping
    public ResponseEntity<LandShipment> createLandShipment(@Valid @RequestBody LandShipmentRequest request) {
        Client client = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with ID: " + request.getClientId()));
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + request.getProductId()));
        Warehouse warehouse = warehouseRepository.findById(request.getWarehouseId())
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found with ID: " + request.getWarehouseId()));

        LandShipment newShipment = new LandShipment(
                null, client, product, request.getQuantity(),
                request.getRegistrationDate(), request.getDeliveryDate(),
                warehouse, request.getShippingCost(), 0.0,
                request.getVehiclePlate(), request.getGuideNumber()
        );
        LandShipment createdShipment = createLandShipmentUseCase.createShipment(newShipment);
        return new ResponseEntity<>(createdShipment, HttpStatus.CREATED);
    }

    @Operation(summary = "Get a land shipment by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the shipment",
            content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = LandShipment.class)) }),
        @ApiResponse(responseCode = "404", description = "Shipment not found",
            content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<LandShipment> getLandShipmentById(@PathVariable UUID id) {
        return getLandShipmentUseCase.getShipmentById(id)
                .map(shipment -> new ResponseEntity<>(shipment, HttpStatus.OK))
                .orElseThrow(() -> new ResourceNotFoundException("Land Shipment not found with id: " + id));
    }

    @Operation(summary = "Get all land shipments")
    @ApiResponse(responseCode = "200", description = "List of all shipments",
        content = { @Content(mediaType = "application/json",
        schema = @Schema(implementation = LandShipment.class)) })
    @GetMapping
    public ResponseEntity<List<LandShipment>> getAllLandShipments() {
        List<LandShipment> shipments = getLandShipmentUseCase.getAllShipments();
        return new ResponseEntity<>(shipments, HttpStatus.OK);
    }

    @Operation(summary = "Update an existing land shipment")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Shipment updated successfully",
            content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = LandShipment.class)) }),
        @ApiResponse(responseCode = "400", description = "Invalid input data",
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Shipment or related resource not found",
            content = @Content),
        @ApiResponse(responseCode = "422", description = "Unprocessable Entity (e.g., duplicate guide number)",
            content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<LandShipment> updateLandShipment(@PathVariable UUID id, @Valid @RequestBody LandShipmentRequest request) {
        Client client = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with ID: " + request.getClientId()));
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + request.getProductId()));
        Warehouse warehouse = warehouseRepository.findById(request.getWarehouseId())
                .orElseThrow(() -> new ResourceNotFoundException("Warehouse not found with ID: " + request.getWarehouseId()));

        LandShipment updatedShipment = new LandShipment(
                id, client, product, request.getQuantity(),
                request.getRegistrationDate(), request.getDeliveryDate(),
                warehouse, request.getShippingCost(), 0.0, // Discount will be applied by service
                request.getVehiclePlate(), request.getGuideNumber()
        );
        LandShipment result = updateLandShipmentUseCase.updateShipment(id, updatedShipment);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Operation(summary = "Delete a land shipment by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Shipment deleted successfully",
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Shipment not found",
            content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLandShipment(@PathVariable UUID id) {
        deleteLandShipmentUseCase.deleteShipment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
