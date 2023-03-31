package com.construlider.fluxocaixa.dto.request;

import com.construlider.fluxocaixa.models.enums.TypeEntry;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EntryRequest {
    private String description;
    private int product;
    private int category;
    private int person;
    private TypeEntry typeEntry;
    private double amount;
    private boolean isPaid;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate entryDate;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate paymentDate;
    private String observation;
}
