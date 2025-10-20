/**
Paradigmas de Linguagens de Programação
Data: 16-10-25
*/

import java.util.ArrayList;
import java.util.List;

public class Professor {
    private String nome;
    private String matricula;
    private List<Disciplina> disciplinas;

    public Professor(String nome, String matricula) {
        this.nome = nome;
        this.matricula = matricula;
        this.disciplinas = new ArrayList<>();
    }

    public void adicionarDisciplina(Disciplina disciplina) {
        disciplinas.add(disciplina);
    }

    public void listarDisciplinas() {
        System.out.println("Disciplinas ministradas por " + nome + ":");
        for (Disciplina d : disciplinas) {
            System.out.println("- " + d.getNome());
        }
    }
}
