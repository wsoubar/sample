package br.com.wsoubar.sample.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

    public Pessoa update (Integer id, Pessoa updPessoa) {
        Pessoa pessoa = getById(id);
        Pessoa pessoaRet = null;
        if (pessoa!=null) {
            pessoa.setNome(updPessoa.getNome());
            pessoa.setIdade(updPessoa.getIdade());
            pessoaRet = pessoaRepository.save(pessoa);
        }
        return pessoaRet;
    }

    public void setPessoaRepository(PessoaRepository pessoaRepository) {
        this.pessoaRepository = pessoaRepository;
    }
}
