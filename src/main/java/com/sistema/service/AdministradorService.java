package com.sistema.service;

import com.sistema.entities.Administrador;
import com.sistema.repositories.AdministradorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministradorService {

    @Autowired
    private AdministradorRepository repository;

    public List<Administrador> findAll(int page, int size){
        Pageable p = PageRequest.of(page,size);
        return repository.findAll(p).toList();
    }

    public List<Administrador> findAll(){
        return repository.findAll();
    }

    public Administrador findById(Long id){
        Optional<Administrador> result = repository.findById(id);
        if(result.isEmpty()){
            throw new RuntimeException("Administrador não encontradA");
        }
        return result.get();
    }

    public Administrador save(Administrador Administrador){
        try {
            return repository.save(Administrador);
        }catch (Exception e){
            throw new RuntimeException("Falha ao salvar Administrador");
        }
    }

    public Administrador update(Administrador Administrador){

            try {
                return repository.save(Administrador);
            }catch (Exception e){
                throw new RuntimeException("Falha ao atualizar");
            }
    }

    public void delete(Long id){

        try {
            repository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException("Falha ao atualizar");
        }
    }

}