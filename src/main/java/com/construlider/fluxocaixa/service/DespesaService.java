package com.construlider.fluxocaixa.service;

import com.construlider.fluxocaixa.models.Despesa;
import com.construlider.fluxocaixa.repository.DespesaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DespesaService {
    @Autowired
    private DespesaRepository despesaRepository;
    public Despesa create(Despesa despesa){
        return despesaRepository.save(despesa);
    }

    public List<Despesa> findAll(){
        return despesaRepository.findAll();
    }

    public Despesa findById(int id){
        return despesaRepository.findById(id).orElseThrow();
    }
    public void deleteById(int id){
        despesaRepository.deleteById(id);
    }
}
