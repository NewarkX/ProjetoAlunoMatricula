package com.sistema.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
public class Funcionario extends Pessoa{

    @JsonIgnore
    @OneToMany(mappedBy = "funcionario")
    private List<Matricula> matriculas = new ArrayList<>();

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    public void cadastrarAluno(){}
    public void cadastrarDisciplina(){}
    public void cadastrarMatricula(){}
}
