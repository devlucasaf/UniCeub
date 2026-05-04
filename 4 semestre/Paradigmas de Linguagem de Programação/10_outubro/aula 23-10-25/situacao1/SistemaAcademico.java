/**
Paradigmas de Linguagem de Programação
Aula: 23-10-25
II Atividade 2 (POO e Diagrama de Classes)
Situação 1 - Sistema de Gestão de Matrículas Acadêmica
*/

package situacao1;

public class SistemaAcademico {
    public static void main(String[] args) {
        Aluno aluno = new Aluno("Lucas", 123, "Computação");
        Disciplina disciplina = new Disciplina("POO101", "Programação Orientada a Objetos", 60);
        Professor professor = new Professor("Maria", "111.222.333-44", "Engenharia de Software");

        aluno.realizarMatricula(disciplina, "2023.2");
        disciplina.exibirPlanoAula();
        professor.atribuirNota(aluno, disciplina, 9.5);
    }
}
