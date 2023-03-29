package com.construlider.fluxocaixa.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Embeddable
public class Adress {
    private String street;
    private String number;
    private String complement;
    private String district;
    private String city;
    private String state;

}
