package br.com.wsoubar.sample.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.wsoubar.sample.model.Pessoa;
import br.com.wsoubar.sample.service.PessoaService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
public class PessoaController {
    
    private PessoaService pessoaService;

    PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    public void setPessoaService(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @GetMapping("/pessoa/listAll")
    public ResponseEntity<?> findAll() {
        List<Pessoa> pessoas = pessoaService.findAll();
        return ResponseEntity.ok(pessoas);
    }

    @PostMapping(value="/pessoa/add")
    public ResponseEntity<?> insert(@RequestBody Pessoa newPessoa) {
        try {
            System.out.println(newPessoa.toString());
            Pessoa pessoa = pessoaService.insert(newPessoa);
            if (pessoa != null) {
                return ResponseEntity.ok(pessoa);
            } else {
                return null;
            }
        } catch (Exception e) {
            System.out.println("Entrou no exception");
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}