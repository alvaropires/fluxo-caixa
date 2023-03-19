package com.construlider.fluxocaixa.service;

import com.construlider.fluxocaixa.models.Categoria;
import com.construlider.fluxocaixa.models.Despesa;
import com.construlider.fluxocaixa.models.Produto;
import com.construlider.fluxocaixa.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria create(Categoria categoria){
        return categoriaRepository.save(categoria);
    }

    public List<Categoria> findAll(){
        return categoriaRepository.findAll();
    }

    public Categoria findById(int id){
        return categoriaRepository.findById(id).orElseThrow();
    }

    public void deleteById(int id){
        categoriaRepository.deleteById(id);
    }
    public List<Despesa> getDespesasDaCategoria(Categoria categoria){
        return categoriaRepository.despesasDaCategoria(categoria);
    }


}
