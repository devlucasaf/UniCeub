package poojava;

import java.time.LocalDate;
import java.util.*;

public class Fatura {
    private final UUID      id;
    private final Aluno     aluno;
    private final int       quantidadeParcelas;
    private final double    valorTotal;
    private double          valorPago;
    private final LocalDate dataCriacao;

    public Fatura(Aluno aluno, int quantidadeParcelas, double valorTotal) {
        this.id = UUID.randomUUID();
        this.aluno = Objects.requireNonNull(aluno);
        
        if (quantidadeParcelas <= 0) {
            throw new IllegalArgumentException("Parcelas inválidas");
        }
        
        if (valorTotal <= 0) {
            throw new IllegalArgumentException("Valor inválido");
        }
        
        this.quantidadeParcelas = quantidadeParcelas;
        this.valorTotal = valorTotal;
        this.valorPago = 0.0;
        this.dataCriacao = LocalDate.now();
    }

    public UUID getId() {
        return id;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public double getValorPago() {
        return valorPago;
    }

    public LocalDate getDataCriacao() {
        return dataCriacao;
    }

    public double restante() {
        return Math.max(0.0, valorTotal - valorPago);
    }

    public boolean isPaga() {
        return restante() <= 0.000001;
    }

    public void pagar(double valor) {
        if (valor <= 0) {
            throw new IllegalArgumentException("Pagamento inválido");
        }
        
        if (isPaga()) {
            throw new IllegalStateException("Fatura já quitada");
        }
        
        valorPago += valor;
        
        if (valorPago > valorTotal) {
            valorPago = valorTotal;
        }
    }

    public double valorParcela() {
        return valorTotal / quantidadeParcelas;
    }

    @Override
    public String toString() {
        return "Fatura{id=" + id +
                ", aluno=" + aluno.getNome() +
                ", total=" + String.format(Locale.US, "%.2f", valorTotal) +
                ", pago=" + String.format(Locale.US, "%.2f", valorPago) +
                ", restante=" + String.format(Locale.US, "%.2f", restante()) +
                ", parcelas=" + quantidadeParcelas +
                ", valorParcela=" + String.format(Locale.US, "%.2f", valorParcela()) +
                ", dataCriacao=" + dataCriacao +
                ", status=" + (isPaga() ? "PAGA" : "ABERTA") +
                "}";
    }
}