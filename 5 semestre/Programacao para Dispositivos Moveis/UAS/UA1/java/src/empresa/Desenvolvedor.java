package empresa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Desenvolvedor extends Funcionario {
    private NivelExperiencia nivel;
    private List<String> linguagens;

    public Desenvolvedor(String nome, String cpf, double salarioBase, Departamento departamento,
                        LocalDate dataAdmissao, NivelExperiencia nivel, List<String> linguagens) {
        super(nome, cpf, salarioBase, departamento, dataAdmissao);
        this.nivel = nivel;
        this.linguagens = new ArrayList<>(linguagens);
    }

    public NivelExperiencia getNivel() {
        return nivel;
    }

    public List<String> getLinguagens() {
        return linguagens;
    }

    public void adicionarLinguagem(String linguagem) {
        if (!linguagens.contains(linguagem)) {
            linguagens.add(linguagem);
        }
    }

    @Override
    public double calcularBonus() {
        switch (nivel) {
            case ESTAGIARIO: 
                return salarioBase * 0.05;
            case JUNIOR: 
                return salarioBase * 0.08;
            case PLENO: 
                return salarioBase * 0.12;
            case SENIOR: 
                return salarioBase * 0.18;
            case ESPECIALISTA: 
                return salarioBase * 0.25;
            default: 
                return 0;
        }
    }

    @Override
    public double calcularSalarioTotal() {
        return salarioBase + calcularBonus();
    }

    @Override
    public String toString() {
        return "[DEV] " + super.toString() + " | Nível: " + nivel + " | Linguagens: " + String.join(", ", linguagens);
    }
}