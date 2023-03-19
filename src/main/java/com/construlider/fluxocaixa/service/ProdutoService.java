package com.construlider.fluxocaixa.service;

import com.construlider.fluxocaixa.models.Categoria;
import com.construlider.fluxocaixa.models.Despesa;
import com.construlider.fluxocaixa.models.Produto;
import com.construlider.fluxocaixa.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {
    @Autowired
    private ProdutoRepository produtoRepository;

    public Produto create(Produto produto){
        return produtoRepository.save(produto);
    }

    public List<Produto> findAll(){
        return produtoRepository.findAll();
    }

    public Produto findById(int id){
        return produtoRepository.findById(id).orElseThrow();
    }
    public void deleteById(int id){
        produtoRepository.deleteById(id);
    }
    public List<Despesa> getDespesasDoProduto(Produto produto){
        return produtoRepository.despesasDoProduto(produto);
    }
}
