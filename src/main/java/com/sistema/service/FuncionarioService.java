package com.sistema.service;

import com.sistema.entities.Funcionario;
import com.sistema.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository repository;

    public List<Funcionario> findAll(int page, int size){
        Pageable p = PageRequest.of(page,size);
        return repository.findAll(p).toList();
    }

    public List<Funcionario> findAll(){
        return repository.findAll();
    }

    public Funcionario findById(Long id){
        Optional<Funcionario> result = repository.findById(id);
        if(result.isEmpty()){
            throw new RuntimeException("Funcionario não encontradA");
        }
        return result.get();
    }

    public Funcionario save(Funcionario Funcionario){
        try {
            return repository.save(Funcionario);
        }catch (Exception e){
            throw new RuntimeException("Falha ao salvar Funcionario");
        }
    }

    public Funcionario update(Funcionario Funcionario){

            try {
                return repository.save(Funcionario);
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
