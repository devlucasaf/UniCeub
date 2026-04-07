package empresa;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.UUID;

public abstract class Funcionario implements Bonus {
    protected String id;
    protected String nome;
    protected String cpf;
    protected double salarioBase;
    protected Departamento departamento;
    protected LocalDate dataAdmissao;
    protected boolean ativo;

    public Funcionario(String nome, String cpf, double salarioBase, Departamento departamento, LocalDate dataAdmissao) {
        this.id = UUID.randomUUID().toString().substring(0, 8);
        this.nome = nome;
        this.cpf = cpf;
        this.salarioBase = salarioBase;
        this.departamento = departamento;
        this.dataAdmissao = dataAdmissao;
        this.ativo = true;
    }

    public abstract double calcularSalarioTotal();

    public void desativar() {
        this.ativo = false;
    }

    public void ativar() {
        this.ativo = true;
    }

    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public double getSalarioBase() {
        return salarioBase;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public LocalDate getDataAdmissao() {
        return dataAdmissao;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void aumentarSalario(double percentual) {
        if (percentual > 0) {
            this.salarioBase += this.salarioBase * (percentual / 100);
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("%s | %s | %s | R$ %.2f | %s | Adm: %s | Ativo: %s",
                id, nome, cpf, salarioBase, departamento, dataAdmissao.format(fmt), ativo);
    }
}