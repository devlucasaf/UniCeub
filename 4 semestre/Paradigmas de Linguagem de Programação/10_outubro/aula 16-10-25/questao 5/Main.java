/**
Paradigmas de Linguagens de Programação
Data: 16-10-25
*/

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Professor prof = new Professor("Maria Souza", "P001");

        Disciplina poo = new Disciplina("Programação Orientada a Objetos", "D001");
        Disciplina bd = new Disciplina("Banco de Dados", "D002");
        poo.atribuirProfessor(prof);
        bd.atribuirProfessor(prof);

        Curso engSoft = new Curso("Engenharia de Software", "C001");
        engSoft.adicionarDisciplina(poo);
        engSoft.adicionarDisciplina(bd);

        Sala salaA = new Sala("203", "Bloco B", 40);

        Turma turma1 = new Turma("2025.1", engSoft, salaA);

        Aluno lucas = new Aluno("Lucas Freitas", "A001");
        Aluno joao = new Aluno("João Souza", "A002");

        engSoft.matricularAluno(lucas);
        engSoft.matricularAluno(joao);

        turma1.adicionarAluno(lucas);
        turma1.adicionarAluno(joao);

        engSoft.listarDisciplinas();
        prof.listarDisciplinas();
        turma1.listarAlunos();
        salaA.exibirInformacoes();
    }
}
