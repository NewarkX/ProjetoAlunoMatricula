package com.sistema.repositories;

import com.sistema.entities.Aluno;
import com.sistema.entities.Funcionario;
import com.sistema.entities.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno,Long> {

    //o proprio spring busca pelo atributo e cria a query
    public Aluno findByNome(String name);

}
