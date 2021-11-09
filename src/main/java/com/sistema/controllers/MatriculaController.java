package com.sistema.controllers;

import com.sistema.entities.Matricula;
import com.sistema.service.MatriculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/apirest/matriculas" )
public class MatriculaController {

    @Autowired
    private MatriculaService MatriculaService;

//    @GetMapping
//    public ResponseEntity getAll(){
//        return ResponseEntity.status(HttpStatus.OK).body(MatriculaService.findAll());
//    }

    @GetMapping
    public ResponseEntity getAll(@RequestParam(name = "page",defaultValue = "0",required = false) int page,
                                      @RequestParam(name = "size",defaultValue = "10",required = false) int size){
        return ResponseEntity.status(HttpStatus.OK).body(MatriculaService.findAll(page,size));
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity getOne(@PathVariable("id") Long id){
        return ResponseEntity.ok( MatriculaService.findById(id));
    }

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Matricula matricula){
        matricula.setId(null);
        MatriculaService.save(matricula);
        return ResponseEntity.status(HttpStatus.CREATED).body(matricula);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity update(@Valid @RequestBody Matricula Matricula,@PathVariable("id") Long id){
        Matricula.setId(id);
        MatriculaService.update(Matricula);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        MatriculaService.delete(id);
        return ResponseEntity.ok().build();
    }
}
