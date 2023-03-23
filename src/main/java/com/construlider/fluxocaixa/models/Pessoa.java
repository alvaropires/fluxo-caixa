package com.construlider.fluxocaixa.models;

import com.construlider.fluxocaixa.models.enums.TipoPessoa;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nome;
    private String telefone;
    private String email;
    @Embedded
    private Endereco endereco;
    private TipoPessoa tipoPessoa;
}
