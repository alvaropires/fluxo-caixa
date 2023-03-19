package com.construlider.fluxocaixa.repository;

import com.construlider.fluxocaixa.models.Categoria;
import com.construlider.fluxocaixa.models.Despesa;
import com.construlider.fluxocaixa.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.JpaQueryCreator;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> {
    @Query("SELECT d FROM Despesa d WHERE d.categoria = :categoria")
    List<Despesa> despesasDaCategoria(@Param("categoria")Categoria categoria);


}
