package br.com.wsoubar.sample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.wsoubar.sample.model.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository <Pessoa, Integer> {
    
}
