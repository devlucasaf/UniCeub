package empresa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Gerente extends Funcionario {
    private List<Funcionario> equipe;
    private double bonusGestao;

    public Gerente(String nome, String cpf, double salarioBase, Departamento departamento,
                    LocalDate dataAdmissao, double bonusGestao) {
        super(nome, cpf, salarioBase, departamento, dataAdmissao);
        this.equipe = new ArrayList<>();
        this.bonusGestao = bonusGestao;
    }

    public void adicionarSubordinado(Funcionario f) {
        if (f != null && !equipe.contains(f)) {
            equipe.add(f);
        }
    }

    public void removerSubordinado(Funcionario f) {
        equipe.remove(f);
    }

    public List<Funcionario> getEquipe() {
        return new ArrayList<>(equipe);
    }

    public double getBonusGestao() {
        return bonusGestao;
    }

    public void setBonusGestao(double bonusGestao) {
        this.bonusGestao = bonusGestao;
    }

    @Override
    public double calcularBonus() {
        return salarioBase * (bonusGestao / 100) + (equipe.size() * 100);
    }

    @Override
    public double calcularSalarioTotal() {
        return salarioBase + calcularBonus();
    }

    @Override
    public String toString() {
        return "[GER] " + super.toString() + " | Bonus Gestão: " + bonusGestao + "% | Equipe: " + equipe.size();
    }
}