package empresa;

import java.time.LocalDate;

public class Estagiario extends Funcionario {
    private int cargaHoraria;
    private String supervisor;

    public Estagiario(String nome, String cpf, double salarioBase, Departamento departamento,
                        LocalDate dataAdmissao, int cargaHoraria, String supervisor) {
        super(nome, cpf, salarioBase, departamento, dataAdmissao);
        this.cargaHoraria = cargaHoraria;
        this.supervisor = supervisor;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public String getSupervisor() {
        return supervisor;
    }

    @Override
    public double calcularBonus() {
        return salarioBase * 0.02;
    }

    @Override
    public double calcularSalarioTotal() {
        return salarioBase + calcularBonus();
    }

    @Override
    public String toString() {
        return "[EST] " + super.toString() + " | Carga: " + cargaHoraria + "h | Supervisor: " + supervisor;
    }
}