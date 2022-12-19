package br.com.wsoubar.sample.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.wsoubar.sample.model.Pessoa;
import br.com.wsoubar.sample.service.PessoaService;

@RestController
public class PessoaController {
    
    @Autowired
    private PessoaService pessoaService;

    private static final Logger LOGGER = LoggerFactory.getLogger(PessoaController.class);


    PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    public void setPessoaService(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping("/pessoas")
    public ResponseEntity<?> findAll() {
        List<Pessoa> pessoas = pessoaService.findAll();
        return ResponseEntity.ok(pessoas);
    }

    @PostMapping(value="/pessoas")
    public ResponseEntity<?> insert(@RequestBody Pessoa newPessoa) {
        try {
            LOGGER.info(newPessoa.toString());
            Pessoa pessoa = pessoaService.insert(newPessoa);
            if (pessoa != null) {
                return ResponseEntity.ok(pessoa);
            } else {
                return null;
            }
        } catch (Exception e) {
            LOGGER.info("Entrou no exception");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(value="/pessoas/{id}")
    public ResponseEntity<?> getById(@PathVariable Integer id) {
        LOGGER.debug("id> "+ id);
        Pessoa pessoa = pessoaService.getById(id);
        if (pessoa != null) {
            return ResponseEntity.ok(pessoa);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }
    
    @PutMapping(value="/pessoas/{id}")
    public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Pessoa pessoa) {
        Pessoa p = pessoaService.update(id, pessoa);
        if (p != null) {
            return ResponseEntity.ok(p);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping(value="/pessoas/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {
        ResponseEntity<?> retorno = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        
        try {
            pessoaService.delete(id);
            retorno = new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            retorno = new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return retorno;
    }
    
}
