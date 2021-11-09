package com.sistema.controllers;

import com.sistema.entities.Disciplina;
import com.sistema.service.DisciplinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/apirest/disciplinas" )
public class DisciplinaController {

    @Autowired
    private DisciplinaService DisciplinaService;

//    @GetMapping
//    public ResponseEntity getAll(){
//        return ResponseEntity.status(HttpStatus.OK).body(DisciplinaService.findAll());
//    }

    @GetMapping
    public ResponseEntity getAll(@RequestParam(name = "page",defaultValue = "0",required = false) int page,
                                      @RequestParam(name = "size",defaultValue = "10",required = false) int size){
        return ResponseEntity.status(HttpStatus.OK).body(DisciplinaService.findAll(page,size));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getOne(@PathVariable("id") Long id){
        return ResponseEntity.ok( DisciplinaService.findById(id));
    }

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Disciplina disciplina){
        disciplina.setId(null);
        DisciplinaService.save(disciplina);
        return ResponseEntity.status(HttpStatus.CREATED).body(disciplina);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity update(@Valid @RequestBody Disciplina disciplina,@PathVariable("id") Long id){
        disciplina.setId(id);
        DisciplinaService.update(disciplina);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        DisciplinaService.delete(id);
        return ResponseEntity.ok().build();
    }
}
