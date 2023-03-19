package com.construlider.fluxocaixa.repository;

import com.construlider.fluxocaixa.models.Despesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Integer> {
}
