package com.construlider.fluxocaixa.models;

import com.construlider.fluxocaixa.models.enums.TypeEntry;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Data
@NoArgsConstructor
public class Entry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String description;
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;
    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
    @ManyToOne
    @JoinColumn(name = "person_id")
    private Person person;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TypeEntry typeEntry;
    private double amount;
    private boolean isPaid;
    private LocalDate entryDate;
    private LocalDate paymentDate;
    private String observation;
}
