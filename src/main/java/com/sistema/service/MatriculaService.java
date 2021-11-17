package com.sistema.service;

import com.sistema.entities.Matricula;
import com.sistema.entities.Matricula;
import com.sistema.exception.NotFoundException;
import com.sistema.repositories.MatriculaRepository;
import com.sistema.repositories.MatriculaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MatriculaService {

    @Autowired
    private MatriculaRepository repository;

    public List<Matricula> findAll(int page, int size){
        Pageable p = PageRequest.of(page,size);
        return repository.findAll(p).toList();
    }

    public List<Matricula> findAll(){
        return repository.findAll();
    }

    public Matricula findById(Long id){
        Optional<Matricula> result = repository.findById(id);
        if(result.isEmpty()){
            throw new NotFoundException("Matricula nao encontrada");
        }
        return result.get();
    }

    public Matricula save(Matricula matricula){
        try {
            return repository.save(matricula);
        }catch (Exception e){
            throw new RuntimeException("Falha ao salvar Matricula");
        }
    }

    public Matricula update(Matricula matricula){

            try {
                return repository.save(matricula);
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
