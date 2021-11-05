package com.sistema.controllers;

import com.sistema.entities.Funcionario;
import com.sistema.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/apirest/funcionario" )
public class FuncionarioController {

    @Autowired
    private FuncionarioService FuncionarioService;

    @GetMapping
    public ResponseEntity getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(FuncionarioService.findAll());
    }

//    @GetMapping
//    public ResponseEntity getAll(@RequestParam(name = "page",defaultValue = "0",required = false) int page,
//                                      @RequestParam(name = "size",defaultValue = "10",required = false) int size){
//        return ResponseEntity.status(HttpStatus.OK).body(FuncionarioService.findAll(page,size));
//    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getOne(@PathVariable("id") Long id){
        return ResponseEntity.ok( FuncionarioService.findById(id));
    }

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Funcionario Funcionario){
        Funcionario.setId(null);
        FuncionarioService.save(Funcionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(Funcionario);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity update(@Valid @RequestBody Funcionario Funcionario,@PathVariable("id") Long id){
        Funcionario.setId(id);
        FuncionarioService.update(Funcionario);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        FuncionarioService.delete(id);
        return ResponseEntity.ok().build();
    }
}
