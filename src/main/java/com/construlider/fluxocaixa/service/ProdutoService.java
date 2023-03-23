package com.construlider.fluxocaixa.service;

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
    public Produto update(int id, Produto novoProduto){
        Produto produto = this.findById(id);
        produto.setNome(novoProduto.getNome());
        produtoRepository.save(produto);
        return produto;
    }
    public void deleteById(int id){
        produtoRepository.deleteById(id);
    }

}
