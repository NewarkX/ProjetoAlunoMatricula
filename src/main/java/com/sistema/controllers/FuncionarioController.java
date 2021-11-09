package com.sistema.controllers;

import com.sistema.entities.Funcionario;
import com.sistema.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/apirest/funcionarios" )
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

//    @GetMapping
//    public ResponseEntity getAll(){
//        return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.findAll());
//    }

    @GetMapping
    public ResponseEntity getAll(@RequestParam(name = "page",defaultValue = "0",required = false) int page,
                                      @RequestParam(name = "size",defaultValue = "10",required = false) int size){
        return ResponseEntity.status(HttpStatus.OK).body(funcionarioService.findAll(page,size));
   }

    @GetMapping(path = "/{id}")
    public ResponseEntity getOne(@PathVariable("id") Long id){
        return ResponseEntity.ok( funcionarioService.findById(id));
    }

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Funcionario funcionario){
        funcionario.setId(null);
        funcionarioService.save(funcionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionario);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity update(@PathVariable("id") Long id,@RequestBody Funcionario funcionario){
        funcionario.setId(id);
        funcionarioService.update(funcionario,"","","");
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        funcionarioService.delete(id);
        return ResponseEntity.ok().build();
    }
}
