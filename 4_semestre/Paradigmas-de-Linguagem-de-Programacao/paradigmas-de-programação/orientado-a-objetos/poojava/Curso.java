package poojava;

import java.util.Objects;

public class Curso {
    private final String    codigo;
    private String          nome;
    private int             cargaHoraria;
    private Professor       professor;

    public Curso(String codigo, String nome, int cargaHoraria) {
        this.codigo = Objects.requireNonNull(codigo).trim();
        
        setNome(nome);
        setCargaHoraria(cargaHoraria);
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome do curso inválido");
        }
        
        this.nome = nome.trim();
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        if (cargaHoraria <= 0) {
            throw new IllegalArgumentException("Carga horária inválida");
        }
        
        this.cargaHoraria = cargaHoraria;
    }

    public Professor getProfessor() {
        return professor;
    }

    public void atribuirProfessor(Professor professor) {
        this.professor = Objects.requireNonNull(professor);
    }

    public String detalhes() {
        String prof = professor == null ? "Sem professor" : professor.getNome();
        return codigo + " | " + nome + " | CH=" + cargaHoraria + "h | Prof=" + prof;
    }

    @Override
    public String toString() {
        return detalhes();
    }
}