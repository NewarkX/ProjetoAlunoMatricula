package com.sistema.service;

import com.sistema.entities.Aluno;
import com.sistema.entities.Matricula;
import com.sistema.repositories.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {

    @Autowired
    private AlunoRepository repository;

    public List<Aluno> findAll(int page, int size){
        Pageable p = PageRequest.of(page,size);
        return repository.findAll(p).toList();
    }

    public List<Aluno> findAll(){
        return repository.findAll();
    }

    public Aluno findById(Long id){
        Optional<Aluno> result = repository.findById(id);
        if(result.isEmpty()){
            throw new RuntimeException("Aluno não encontrado");
        }
        return result.get();
    }

    public Aluno save(Aluno aluno){
        try {
            return repository.save(aluno);
        }catch (Exception e){
            throw new RuntimeException("Falha ao salvar aluno");
        }
    }

    public Aluno update(Aluno aluno){

       try{
           return repository.save(aluno);
       }catch (Exception e){
            throw new RuntimeException("Falha ao atualizar");
       }
    }

    public void delete(Long id){
        Aluno aluno = this.findById(id);
        verificaExclusaoAlunoComMatricula(aluno);
        try {
            repository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException("Falha ao deletar aluno");
        }
    }

    private void verificaExclusaoAlunoComMatricula(Aluno aluno){
            if(!aluno.getMatriculas().isEmpty()){
                throw new RuntimeException("Não é possivel excluir aluno matriculado");
            }
    }
}
