package com.sistema.service;

import com.sistema.entities.Disciplina;
import com.sistema.exception.NotFoundException;
import com.sistema.repositories.DisciplinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DisciplinaService {

    @Autowired
    private DisciplinaRepository repository;

    public List<Disciplina> findAll(int page, int size){
        Pageable p = PageRequest.of(page,size);
        return repository.findAll(p).toList();
    }

    public List<Disciplina> findAll(){
        return repository.findAll();
    }

    public Disciplina findById(Long id){
        Optional<Disciplina> result = repository.findById(id);
        if(result.isEmpty()){
            throw new NotFoundException("Disciplina nao encontrada");
        }
        return result.get();
    }

    public Disciplina save(Disciplina disciplina){
        try {
            return repository.save(disciplina);
        }catch (Exception e){
            throw new RuntimeException("Falha ao salvar Disciplina");
        }
    }

    public Disciplina update(Disciplina disciplina){

            try {
                return repository.save(disciplina);
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
