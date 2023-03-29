package com.construlider.fluxocaixa.models;

import com.construlider.fluxocaixa.models.enums.TypePerson;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String phoneNumber;
    private String email;
    @Embedded
    private Adress adress;
    private TypePerson typePerson;
}
