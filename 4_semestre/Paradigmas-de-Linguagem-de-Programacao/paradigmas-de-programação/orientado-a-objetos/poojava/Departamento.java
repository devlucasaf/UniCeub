package poojava;

import java.util.*;

public class Departamento {
    private final String        nome;
    private final List<Curso>   cursos;
    private final List<Pessoa>  membros;

    public Departamento(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do departamento inválido");
        }
        
        this.nome = nome.trim();
        this.cursos = new ArrayList<>();
        this.membros = new ArrayList<>();
    }

    public String getNome() {
        return nome;
    }

    public void adicionarCurso(Curso c) {
        cursos.add(Objects.requireNonNull(c));
    }

    public void adicionarMembro(Pessoa p) {
        membros.add(Objects.requireNonNull(p));
    }

    public List<Curso> getCursos() {
        return Collections.unmodifiableList(cursos);
    }

    public List<Pessoa> getMembros() {
        return Collections.unmodifiableList(membros);
    }
}