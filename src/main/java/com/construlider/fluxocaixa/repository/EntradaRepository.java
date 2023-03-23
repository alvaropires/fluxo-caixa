package com.construlider.fluxocaixa.repository;

import com.construlider.fluxocaixa.models.Categoria;
import com.construlider.fluxocaixa.models.Entrada;
import com.construlider.fluxocaixa.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DespesaRepository extends JpaRepository<Entrada, Integer> {

    @Query("SELECT d FROM Despesa d WHERE d.categoria = :categoria")
    List<Entrada> despesasDaCategoria(@Param("categoria") Categoria categoria);

    @Query("SELECT d FROM Despesa d WHERE d.produto = :produto")
    List<Entrada> despesasDoProduto(@Param("produto") Produto produto);
}
