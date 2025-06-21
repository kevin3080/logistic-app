package com.logisticapp.backend_logistic_app.domain.model;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "maritime_shipments")
public class MaritimeShipment {
    @Id
    @UuidGenerator
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private int quantity;
    private LocalDate registrationDate;
    private LocalDate deliveryDate;

    @ManyToOne
    @JoinColumn(name = "port_id")
    private Port port;
    
    private double shippingCost;
    private double discountedCost;
    private String fleetNumber;
    private String guideNumber;

    public void applyDiscount() {
        this.discountedCost = quantity > 10 ? shippingCost * 0.97 : shippingCost;
    }
}