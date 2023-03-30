package com.construlider.fluxocaixa.dto.request;

import com.construlider.fluxocaixa.models.Adress;
import com.construlider.fluxocaixa.models.enums.TypePerson;
import lombok.Data;

@Data
public class PersonRequest {
    private String name;
    private String phoneNumber;
    private String email;
    private Adress adress;
    private TypePerson typePerson;
}
