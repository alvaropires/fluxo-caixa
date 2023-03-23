package com.construlider.fluxocaixa.models;

import com.construlider.fluxocaixa.models.enums.TipoEntrada;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Entity
@Data
@NoArgsConstructor
public class Entrada {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String descricao;
    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;
    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;
    @ManyToOne
    @JoinColumn(name = "pessoa_id")
    private Pessoa pessoa;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TipoEntrada tipoEntrada;
    private double valor;
    private boolean estaPago;
    private LocalDate dataEntrada;
    private LocalDate dataPagamento;
    private String observacao;

}
