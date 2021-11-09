package com.sistema.controllers;

import com.sistema.entities.Aluno;
import com.sistema.service.AlunoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/apirest/alunos" )
public class AlunoController {

    @Autowired
    private AlunoService alunoService;

//    @GetMapping
//    public ResponseEntity get(){
//        return ResponseEntity.status(HttpStatus.OK).body(alunoService.findAll());
//    }

    @GetMapping
    public ResponseEntity getAll(@RequestParam(name = "page",defaultValue = "0",required = false) int page,
                                      @RequestParam(name = "size",defaultValue = "10",required = false) int size){
        return ResponseEntity.status(HttpStatus.OK).body(alunoService.findAll(page,size));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getOne(@PathVariable("id") Long id){
        return ResponseEntity.ok( alunoService.findById(id));
    }

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Aluno aluno){
        aluno.setId(null);
        alunoService.save(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(aluno);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity update(@Valid @RequestBody Aluno aluno,@PathVariable("id") Long id){
        aluno.setId(id);
        alunoService.update(aluno);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        alunoService.delete(id);
        return ResponseEntity.ok().build();
    }
}
