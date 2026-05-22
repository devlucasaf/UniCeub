package poojava;

public class Avaliacao {
    private final String    nome;
    private final double    notaMaxima;
    private Double          nota;

    public Avaliacao(String nome, double notaMaxima) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("Nome da avaliação inválido");
        }
        
        if (notaMaxima <= 0) {
            throw new IllegalArgumentException("Nota máxima inválida");
        }
        
        this.nome = nome.trim();
        this.notaMaxima = notaMaxima;
        this.nota = null;
    }

    public String getNome() {
        return nome;
    }

    public double getNotaMaxima() {
        return notaMaxima;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        if (nota < 0 || nota > notaMaxima) {
            throw new IllegalArgumentException("Nota fora do intervalo");
        }
        
        this.nota = nota;
    }

    public double notaNormalizada() {
        if (nota == null) {
            return 0.0;
        }
        
        return (nota / notaMaxima) * 10.0;
    }

    @Override
    public String toString() {
        return "Avaliacao{" + "nome='" + nome + '\'' + ", notaMaxima=" + notaMaxima + ", nota=" + nota + '}';
    }
}