package com.construlider.fluxocaixa.dto.response;

import com.construlider.fluxocaixa.models.Category;
import com.construlider.fluxocaixa.models.Person;
import com.construlider.fluxocaixa.models.Product;
import com.construlider.fluxocaixa.models.enums.TypeEntry;
import lombok.Data;

import java.time.LocalDate;

@Data
public class EntryResponse {
    private int id;
    private String description;
    private Product product;
    private Category category;
    private Person person;
    private TypeEntry typeEntry;
    private double amount;
    private boolean isPaid;
    private LocalDate entryDate;
    private LocalDate paymentDate;
    private String observation;

    public String getProduct(){
        return product.getName();
    }
    public String getCategory(){
        return category.getName();
    }
    public String getPerson(){
        return person.getName();
    }
}
