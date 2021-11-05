package com.sistema.repositories;

import com.sistema.entities.Aluno;
import com.sistema.entities.Matricula;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MatriculaRepository  extends JpaRepository<Matricula,Long> {

    @Query("SELECT m.aluno FROM Matricula m WHERE m.numero = :numero")
    public Aluno findAlunoByMatricula(int numero);

}
