package com.construlider.fluxocaixa.service;

import com.construlider.fluxocaixa.models.Categoria;
import com.construlider.fluxocaixa.models.Entrada;
import com.construlider.fluxocaixa.models.Produto;
import com.construlider.fluxocaixa.repository.EntradaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DespesaService {
    @Autowired
    private EntradaRepository entradaRepository;
    public Entrada create(Entrada entrada){
        return entradaRepository.save(entrada);
    }

    public List<Entrada> findAll(){
        return entradaRepository.findAll();
    }

    public Entrada findById(int id){
        return entradaRepository.findById(id).orElseThrow();
    }
    public void deleteById(int id){
        entradaRepository.deleteById(id);
    }
    public List<Entrada> getDespesasDaCategoria(Categoria categoria){
        return entradaRepository.despesasDaCategoria(categoria);
    }
    public List<Entrada> getDespesasDoProduto(Produto produto){
        return entradaRepository.despesasDoProduto(produto);
    }

}
