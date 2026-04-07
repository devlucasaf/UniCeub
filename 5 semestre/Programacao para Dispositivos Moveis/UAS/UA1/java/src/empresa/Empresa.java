package empresa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

class Empresa {
    private String nome;
    private String cnpj;
    private List<Funcionario> funcionarios;
    private FolhaPagamento folha;

    public Empresa(String nome, String cnpj) {
        this.nome = nome;
        this.cnpj = cnpj;
        this.funcionarios = new ArrayList<>();
        this.folha = new FolhaPagamento();
    }

    public void contratar(Funcionario f) {
        if (f != null && !funcionarios.contains(f)) {
            funcionarios.add(f);
        }
    }

    public void demitir(String id) {
        funcionarios.stream().filter(f -> f.getId().equals(id)).findFirst().ifPresent(f -> {
            f.desativar();
            System.out.println("Funcionário " + f.getNome() + " desligado.");
        });
    }

    public List<Funcionario> getFuncionariosAtivos() {
        return funcionarios.stream().filter(Funcionario::isAtivo).collect(Collectors.toList());
    }

    public List<Funcionario> getFuncionariosPorDepartamento(Departamento d) {
        return funcionarios.stream().filter(f -> f.getDepartamento() == d && f.isAtivo()).collect(Collectors.toList());
    }

    public List<Desenvolvedor> getDesenvolvedoresPorLinguagem(String linguagem) {
        return funcionarios.stream()
                .filter(f -> f instanceof Desenvolvedor && f.isAtivo())
                .map(f -> (Desenvolvedor) f)
                .filter(dev -> dev.getLinguagens().contains(linguagem))
                .collect(Collectors.toList());
    }

    public Optional<Funcionario> buscarFuncionarioPorCpf(String cpf) {
        return funcionarios.stream().filter(f -> f.getCpf().equals(cpf)).findFirst();
    }

    public void aplicarAumentoGlobal(double percentual) {
        for (Funcionario f : funcionarios) {
            if (f.isAtivo()) {
                f.aumentarSalario(percentual);
            }
        }
    }

    public double calcularTotalFolha() {
        return funcionarios.stream().filter(Funcionario::isAtivo).mapToDouble(Funcionario::calcularSalarioTotal).sum();
    }

    public void listarFuncionariosOrdenadosPorSalario() {
        funcionarios.stream()
                .filter(Funcionario::isAtivo)
                .sorted(Comparator.comparingDouble(Funcionario::calcularSalarioTotal).reversed())
                .forEach(System.out::println);
    }

    public void processarPagamentoTodos() {
        System.out.println("=== PROCESSANDO FOLHA ===");
        for (Funcionario f : getFuncionariosAtivos()) {
            try {
                folha.processarPagamento(f);
            } 
            
            catch (FuncionarioInativoException e) {
                System.err.println(e.getMessage());
            }
        }
        System.out.println("Total pago: R$ " + String.format("%.2f", folha.totalPago()));
    }

    public void promoverDesenvolvedor(String id, NivelExperiencia novoNivel) {
        funcionarios.stream()
                .filter(f -> f.getId().equals(id) && f instanceof Desenvolvedor && f.isAtivo())
                .map(f -> (Desenvolvedor) f)
                .findFirst()
                .ifPresent(dev -> {
                    dev.aumentarSalario(15);
                    System.out.println("Desenvolvedor " + dev.getNome() + " promovido de " + dev.getNivel() + " para " + novoNivel);
                });
    }
}