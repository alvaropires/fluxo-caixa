package com.construlider.fluxocaixa.repository;

import com.construlider.fluxocaixa.models.Despesa;
import com.construlider.fluxocaixa.models.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    @Query("SELECT d FROM Despesa d WHERE d.produto = :produto")
    List<Despesa> despesasDoProduto(@Param("produto")Produto produto);
}
