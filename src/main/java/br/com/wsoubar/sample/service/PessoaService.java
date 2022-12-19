package br.com.wsoubar.sample.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.wsoubar.sample.model.Pessoa;
import br.com.wsoubar.sample.repository.PessoaRepository;

@Service
public class PessoaService {
    
    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> findAll() {
        return pessoaRepository.findAll();       
    }

    public Pessoa insert(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Pessoa getById(Integer id) {
        Optional<Pessoa> optPessoa = pessoaRepository.findById(id);
        if (optPessoa.isPresent()) {
            return optPessoa.get();
        } else {
            return null;
        }
    }

    public void setPessoaRepository(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }
}
