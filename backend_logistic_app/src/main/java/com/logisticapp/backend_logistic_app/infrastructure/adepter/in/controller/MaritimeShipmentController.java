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
import com.logisticapp.backend_logistic_app.domain.model.MaritimeShipment;
import com.logisticapp.backend_logistic_app.domain.model.Port;
import com.logisticapp.backend_logistic_app.domain.model.Product;
import com.logisticapp.backend_logistic_app.domain.port.in.CreateMaritimeShipmentUseCase;
import com.logisticapp.backend_logistic_app.domain.port.in.DeleteMaritimeShipmentUseCase;
import com.logisticapp.backend_logistic_app.domain.port.in.GetMaritimeShipmentUseCase;
import com.logisticapp.backend_logistic_app.domain.port.in.UpdateMaritimeShipmentUseCase;
import com.logisticapp.backend_logistic_app.infrastructure.adepter.in.dto.MaritimeShipmentRequest;
import com.logisticapp.backend_logistic_app.infrastructure.adepter.out.persistence.adapter.ClientRepositoryImpl;
import com.logisticapp.backend_logistic_app.infrastructure.adepter.out.persistence.adapter.PortRepositoryImpl;
import com.logisticapp.backend_logistic_app.infrastructure.adepter.out.persistence.adapter.ProductRepositoryImpl;
import com.logisticapp.backend_logistic_app.infrastructure.exception.ResourceNotFoundException;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/maritime-shipments")
@RequiredArgsConstructor
public class MaritimeShipmentController {

    private final CreateMaritimeShipmentUseCase createMaritimeShipmentUseCase;
    private final GetMaritimeShipmentUseCase getMaritimeShipmentUseCase;
    private final UpdateMaritimeShipmentUseCase updateMaritimeShipmentUseCase;
    private final DeleteMaritimeShipmentUseCase deleteMaritimeShipmentUseCase;
    private final ClientRepositoryImpl clientRepository;
    private final ProductRepositoryImpl productRepository;
    private final PortRepositoryImpl portRepository;

    @Operation(summary = "Create a new maritime shipment")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "201", description = "Shipment created",
            content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = MaritimeShipment.class)) }),
        @ApiResponse(responseCode = "400", description = "Invalid input data",
            content = @Content),
        @ApiResponse(responseCode = "422", description = "Unprocessable Entity (e.g., duplicate guide number)",
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Related resource not found (Client, Product, Port)",
            content = @Content)
    })
    @PostMapping
    public ResponseEntity<MaritimeShipment> createMaritimeShipment(@Valid @RequestBody MaritimeShipmentRequest request) {
        Client client = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with ID: " + request.getClientId()));
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + request.getProductId()));
        Port port = portRepository.findById(request.getPortId())
                .orElseThrow(() -> new ResourceNotFoundException("Port not found with ID: " + request.getPortId()));

        MaritimeShipment newShipment = new MaritimeShipment(
                null, client, product, request.getQuantity(),
                request.getRegistrationDate(), request.getDeliveryDate(),
                port, request.getShippingCost(), 0.0,
                request.getFleetNumber(), request.getGuideNumber()
        );
        MaritimeShipment createdShipment = createMaritimeShipmentUseCase.createShipment(newShipment);
        return new ResponseEntity<>(createdShipment, HttpStatus.CREATED);
    }

    @Operation(summary = "Get a maritime shipment by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Found the shipment",
            content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = MaritimeShipment.class)) }),
        @ApiResponse(responseCode = "404", description = "Shipment not found",
            content = @Content)
    })
    @GetMapping("/{id}")
    public ResponseEntity<MaritimeShipment> getMaritimeShipmentById(@PathVariable UUID id) {
        return getMaritimeShipmentUseCase.getShipmentById(id)
                .map(shipment -> new ResponseEntity<>(shipment, HttpStatus.OK))
                .orElseThrow(() -> new ResourceNotFoundException("Maritime Shipment not found with id: " + id));
    }

    @Operation(summary = "Get all maritime shipments")
    @ApiResponse(responseCode = "200", description = "List of all shipments",
        content = { @Content(mediaType = "application/json",
        schema = @Schema(implementation = MaritimeShipment.class)) })
    @GetMapping
    public ResponseEntity<List<MaritimeShipment>> getAllMaritimeShipments() {
        List<MaritimeShipment> shipments = getMaritimeShipmentUseCase.getAllShipments();
        return new ResponseEntity<>(shipments, HttpStatus.OK);
    }

    @Operation(summary = "Update an existing maritime shipment")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Shipment updated successfully",
            content = { @Content(mediaType = "application/json",
            schema = @Schema(implementation = MaritimeShipment.class)) }),
        @ApiResponse(responseCode = "400", description = "Invalid input data",
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Shipment or related resource not found",
            content = @Content),
        @ApiResponse(responseCode = "422", description = "Unprocessable Entity (e.g., duplicate guide number)",
            content = @Content)
    })
    @PutMapping("/{id}")
    public ResponseEntity<MaritimeShipment> updateMaritimeShipment(@PathVariable UUID id, @Valid @RequestBody MaritimeShipmentRequest request) {
        Client client = clientRepository.findById(request.getClientId())
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with ID: " + request.getClientId()));
        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new ResourceNotFoundException("Product not found with ID: " + request.getProductId()));
        Port port = portRepository.findById(request.getPortId())
                .orElseThrow(() -> new ResourceNotFoundException("Port not found with ID: " + request.getPortId()));

        MaritimeShipment updatedShipment = new MaritimeShipment(
                id, client, product, request.getQuantity(),
                request.getRegistrationDate(), request.getDeliveryDate(),
                port, request.getShippingCost(), 0.0, // Discount will be applied by service
                request.getFleetNumber(), request.getGuideNumber()
        );
        MaritimeShipment result = updateMaritimeShipmentUseCase.updateShipment(id, updatedShipment);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Operation(summary = "Delete a maritime shipment by ID")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Shipment deleted successfully",
            content = @Content),
        @ApiResponse(responseCode = "404", description = "Shipment not found",
            content = @Content)
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaritimeShipment(@PathVariable UUID id) {
        deleteMaritimeShipmentUseCase.deleteShipment(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
