package com.sistema.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Entity
public class Funcionario extends Pessoa{

    @NotBlank(message = "Senha obrigatória.")
    @Length(min = 8, message = "Senha deve ter no mínimo 8 caracteres.")
    private String senha;

    @JsonIgnore
    @OneToMany(mappedBy = "funcionario")
    private List<Matricula> matriculas = new ArrayList<>();

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void cadastrarAluno(){}
    public void cadastrarDisciplina(){}
    public void cadastrarMatricula(){}
}
