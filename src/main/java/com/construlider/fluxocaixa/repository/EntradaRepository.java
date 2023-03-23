package com.construlider.fluxocaixa.repository;

import com.construlider.fluxocaixa.models.Categoria;
import com.construlider.fluxocaixa.models.Entrada;
import com.construlider.fluxocaixa.models.Produto;
import com.construlider.fluxocaixa.models.enums.TipoEntrada;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EntradaRepository extends JpaRepository<Entrada, Integer> {

    @Query("SELECT e FROM Entrada e WHERE e.categoria = :categoria")
    List<Entrada> entradasDaCategoria(@Param("categoria") Categoria categoria);

    @Query("SELECT e FROM Entrada e WHERE e.produto = :produto")
    List<Entrada> entradasDoProduto(@Param("produto") Produto produto);
    @Query("SELECT e FROM Entrada e WHERE e.tipoEntrada = :tipoEntrada")
    List<Entrada> entradasPorTipoDeEntrada(@Param("tipoEntrada") TipoEntrada tipoEntrada);
}
