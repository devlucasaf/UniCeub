package poojava;

import java.util.Objects;
import java.util.Locale;

public class Professor extends Pessoa implements Pagavel {
    private final String    idProfessor;
    private double          valorHora;
    private int             horasNoMes;

    public Professor(String nome, String email, String idProfessor, double valorHora) {
        super(nome, email);
        
        setValorHora(valorHora);
        
        this.idProfessor = Objects.requireNonNull(idProfessor);
        this.horasNoMes = 40;
    }

    public String getIdProfessor() {
        return idProfessor;
    }

    public double getValorHora() {
        return valorHora;
    }

    public void setValorHora(double valorHora) {
        if (valorHora <= 0) {
            throw new IllegalArgumentException("Valor hora inválido");
        }
        
        this.valorHora = valorHora;
    }

    public int getHorasNoMes() {
        return horasNoMes;
    }

    public void setHorasNoMes(int horasNoMes) {
        if (horasNoMes < 0) {
            throw new IllegalArgumentException("Horas inválidas");
        }
        
        this.horasNoMes = horasNoMes;
    }

    public void lancarNota(Matricula matricula, String nomeAvaliacao, double nota) {
        if (matricula == null) {
            throw new IllegalArgumentException("Matrícula nula");
        }
        
        if (matricula.getCurso().getProfessor() != this) {
            throw new IllegalStateException("Professor não associado ao curso");
        }
        
        matricula.setNota(nomeAvaliacao, nota);
    }

    @Override
    public double valorPagamento() {
        return valorHora * horasNoMes;
    }

    @Override
    public String resumoPagamento() {
        return "Professor " + getNome() + " (" + idProfessor + ") recebe R$ " + String.format(Locale.US, "%.2f", valorPagamento());
    }

    @Override
    public String resumo() {
        return super.resumo() + " | idProfessor=" + idProfessor + " | valorHora=" + valorHora;
    }
}