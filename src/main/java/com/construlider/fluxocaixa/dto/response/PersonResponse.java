package com.construlider.fluxocaixa.dto.response;

import com.construlider.fluxocaixa.models.Adress;
import com.construlider.fluxocaixa.models.enums.TypePerson;
import lombok.Data;

@Data
public class PersonResponse {
    private int id;
    private String name;
    private String phoneNumber;
    private String email;
    private Adress adress;
    private TypePerson typePerson;
}
