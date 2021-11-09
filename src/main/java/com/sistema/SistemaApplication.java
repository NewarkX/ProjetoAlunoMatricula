package com.sistema;

import com.sistema.entities.*;
import com.sistema.entities.enums.Semestre;
import com.sistema.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.GregorianCalendar;

@SpringBootApplication
public class SistemaApplication implements CommandLineRunner {

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private AdministradorRepository administradorRepository;

	@Autowired
	private MatriculaRepository matriculaRepository;

	@Autowired
	private DisciplinaRepository disciplinaRepository;

	public static void main(String[] args) {
		SpringApplication.run(SistemaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Aluno a1 = new Aluno();
		a1.setNome("bruno");
		Calendar nascimentoAluno = Calendar.getInstance();
		nascimentoAluno.set(1992, 11, 10);
		a1.setNascimento(nascimentoAluno);
		a1.setCpf("899.394.340-06");
		a1.setEmail("bruno@gmail.com");
		a1.setTelefone("(22)998965949");
		alunoRepository.save(a1);

		Funcionario f1 = new Funcionario();
		f1.setNome("isabela");
		Calendar nascimentoFuncionario = Calendar.getInstance();
		nascimentoFuncionario.set(1992, 9, 10);
		f1.setNascimento(nascimentoFuncionario);
		f1.setCpf("770.698.910-08");
		f1.setSenha("12345678");
		funcionarioRepository.save(f1);

		Administrador ad1 = new Administrador();
		ad1.setNome("bruno");
		Calendar nascimentoAdministrador = Calendar.getInstance();
		nascimentoAdministrador.set(1992, 9, 10);
		ad1.setNascimento(nascimentoAdministrador);
		ad1.setCpf("899.394.340-06");
		administradorRepository.save(ad1);

		Disciplina d1 = new Disciplina();
		d1.setCódigo(1);
		d1.setNome("Programacao");
		disciplinaRepository.save(d1);

		Matricula m = new Matricula();
		m.setNumero(123456789);
		m.setAno(1992);
		m.setSemestre(Semestre.PRIMEIRO);
		m.setAluno(a1);
		m.setFuncionario(f1);
		m.setDisciplina(d1);
		matriculaRepository.save(m);
	}
}
