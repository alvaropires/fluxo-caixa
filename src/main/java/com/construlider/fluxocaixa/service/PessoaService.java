package com.construlider.fluxocaixa.service;

import com.construlider.fluxocaixa.models.Pessoa;
import com.construlider.fluxocaixa.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> findAll(){
        return pessoaRepository.findAll();
    }
    public Pessoa create(Pessoa pessoa){
        return pessoaRepository.save(pessoa);
    }
    public Pessoa findById(int id){
        return pessoaRepository.findById(id).orElseThrow();
    }

    public Pessoa update(int id, Pessoa pessoaAtualizada){
        var pessoa = this.findById(id);
        pessoa = pessoaAtualizada;
        return pessoaRepository.save(pessoa);
    }
    public void delete(int id){
        pessoaRepository.deleteById(id);
    }

}
