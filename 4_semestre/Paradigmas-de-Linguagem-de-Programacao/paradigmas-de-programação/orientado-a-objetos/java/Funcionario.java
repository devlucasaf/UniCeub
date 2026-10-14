package poojava;

import java.util.Locale;
import java.util.Objects;

public class Funcionario extends Pessoa implements Pagavel {
    private final String    idFuncionario;
    private String          cargo;
    private double          salarioMensal;

    public Funcionario(String nome, String email, String idFuncionario, String cargo) {
        super(nome, email);
        
        setCargo(cargo);
        
        this.idFuncionario = Objects.requireNonNull(idFuncionario);
        this.salarioMensal = 3200.0;
    }

    public String getIdFuncionario() {
        return idFuncionario;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        if (cargo == null || cargo.isBlank()) {
            throw new IllegalArgumentException("Cargo inválido");
        }
        
        this.cargo = cargo.trim();
    }

    public double getSalarioMensal() {
        return salarioMensal;
    }

    public void setSalarioMensal(double salarioMensal) {
        if (salarioMensal <= 0) {
            throw new IllegalArgumentException("Salário inválido");
        }
        
        this.salarioMensal = salarioMensal;
    }

    @Override
    public double valorPagamento() {
        return salarioMensal;
    }

    @Override
    public String resumoPagamento() {
        return "Funcionario " + getNome() + " (" + idFuncionario + ") recebe R$ " + String.format(Locale.US, "%.2f", valorPagamento());
    }

    @Override
    public String resumo() {
        return super.resumo() + " | idFuncionario=" + idFuncionario + " | cargo=" + cargo;
    }
}