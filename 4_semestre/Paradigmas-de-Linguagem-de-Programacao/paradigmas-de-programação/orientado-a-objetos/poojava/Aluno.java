package poojava;

import java.util.*;

public class Aluno extends Pessoa {
    private final String            idAluno;
    private final List<Matricula>   matriculas;

    public Aluno(String nome, String email, String idAluno) {
        super(nome, email);
        this.idAluno = Objects.requireNonNull(idAluno);
        this.matriculas = new ArrayList<>();
    }

    public String getIdAluno() {
        return idAluno;
    }

    public List<Matricula> getMatriculas() {
        return Collections.unmodifiableList(matriculas);
    }

    void adicionarMatricula(Matricula m) {
        matriculas.add(Objects.requireNonNull(m));
    }

    public double mediaGeral() {
        if (matriculas.isEmpty()) {
            return 0.0;
        }
        
        double soma = 0.0;
        
        for (Matricula m : matriculas) {
            soma += m.notaFinal();
        }
        
        return soma / matriculas.size();
    }

    public void pagarFatura(UUID idFatura, double valor) {
        if (idFatura == null) {
            throw new IllegalArgumentException("Fatura inválida");
        }
        
        if (valor <= 0) {
            throw new IllegalArgumentException("Pagamento inválido");
        }
    }

    @Override
    public String resumo() {
        return super.resumo() + " | idAluno=" + idAluno + " | cursos=" + matriculas.size() + " | media=" + String.format(Locale.US, "%.2f", mediaGeral());
    }
}