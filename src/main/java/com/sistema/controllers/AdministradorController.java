package com.sistema.controllers;

import com.sistema.entities.Administrador;
import com.sistema.service.AdministradorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping(path = "/apirest/administradores" )
public class AdministradorController {

    @Autowired
    private AdministradorService AdministradorService;

//    @GetMapping
//    public ResponseEntity get(){
//        return ResponseEntity.status(HttpStatus.OK).body(AdministradorService.findAll());
//    }

    @GetMapping
    public ResponseEntity getAll(@RequestParam(name = "page",defaultValue = "0",required = false) int page,
                                      @RequestParam(name = "size",defaultValue = "10",required = false) int size){
        return ResponseEntity.status(HttpStatus.OK).body(AdministradorService.findAll(page,size));
   }

    @GetMapping(path = "/{id}")
    public ResponseEntity getOne(@PathVariable("id") Long id){
        return ResponseEntity.ok( AdministradorService.findById(id));
    }

    @PostMapping
    public ResponseEntity save(@Valid @RequestBody Administrador administrador){
        administrador.setId(null);
        AdministradorService.save(administrador);
        return ResponseEntity.status(HttpStatus.CREATED).body(administrador);
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity update(@Valid @RequestBody Administrador administrador,@PathVariable("id") Long id){
        administrador.setId(id);
        AdministradorService.update(administrador);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @DeleteMapping(path = "/{id}")
    public ResponseEntity delete(@PathVariable("id") Long id){
        AdministradorService.delete(id);
        return ResponseEntity.ok().build();
    }
}
