package com.sistema.service;

import com.sistema.entities.Funcionario;
import com.sistema.exception.NotFoundException;
import com.sistema.repositories.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.validation.ConstraintViolationException;
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
            throw new NotFoundException("Funcionario nao encontrado");
        }
        return result.get();
    }

    public Funcionario save(Funcionario funcionario){
        try {
            return repository.save(funcionario);
        }catch (Exception e){
            throw new RuntimeException("Falha ao salvar Funcionario");
        }
    }

    public Funcionario update(Funcionario funcionario,String senhaAtual,String novaSenha, String confirmarNovaSenha){
            //verufuca se fyncionario ja existe
            Funcionario obj = findById(funcionario.getId());
            //verifica alteracao da senha
            alterarSenha(obj,senhaAtual,novaSenha,confirmarNovaSenha);

            try {
                funcionario.setCpf(obj.getCpf());
                funcionario.setSenha(obj.getSenha());
                return repository.save(funcionario);
            }catch (Exception e){
                Throwable t = e;
                while (t.getCause() != null) {
                    t = t.getCause();
                    if (t instanceof ConstraintViolationException) {
                        throw ((ConstraintViolationException) t);
                    }
                }
                throw new RuntimeException("Falha ao atualizar o Funcionario.");
            }
    }

    private void alterarSenha(Funcionario obj,String senhaAtual,String novaSenha, String confirmarNovaSenha){
        if(!senhaAtual.isBlank() && novaSenha.isBlank() && confirmarNovaSenha.isBlank()){
            if(!senhaAtual.equals(obj.getSenha())){
                throw new RuntimeException("Senha atual est?? incorreta");
            }
            if(!novaSenha.equals(confirmarNovaSenha)){
                throw new RuntimeException(("Nova senha e confirmar nova senha n??o conferem"));
            }
            obj.setSenha(novaSenha);
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
