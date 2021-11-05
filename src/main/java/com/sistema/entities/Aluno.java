package com.sistema.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Entity
public class Aluno extends Pessoa {

    @Column(nullable = false)
    @NotBlank(message = "Email é obrigatório") //focado em string não pode ser uma string vazia
    @Email
    private String email;
    @Column(nullable = false)
    @NotBlank(message = "telefone é obrigatório")
    private String telefone;

    @JsonIgnore
    @OneToMany(mappedBy = "aluno")
    private List<Matricula> matriculas = new ArrayList<>();


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Matricula> getMatriculas() {
        return matriculas;
    }

    public void setMatriculas(List<Matricula> matriculas) {
        this.matriculas = matriculas;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Aluno aluno = (Aluno) o;
        return Objects.equals(email, aluno.email) &&
                Objects.equals(telefone, aluno.telefone) &&
                Objects.equals(matriculas, aluno.matriculas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), email, telefone, matriculas);
    }
}
