package empresa;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Empresa empresa = new Empresa("Tech Solutions Ltda", "12.345.678/0001-99");

        List<String> linguagensAna = new ArrayList<>();
        linguagensAna.add("Java");
        linguagensAna.add("Python");
        Desenvolvedor ana = new Desenvolvedor("Ana Silva", "111.222.333-44", 7500.0,
                Departamento.TECNOLOGIA, LocalDate.of(2022, 3, 10), NivelExperiencia.PLENO, linguagensAna);

        List<String> linguagensCarlos = new ArrayList<>();
        linguagensCarlos.add("JavaScript");
        linguagensCarlos.add("TypeScript");
        linguagensCarlos.add("React");
        Desenvolvedor carlos = new Desenvolvedor("Carlos Souza", "222.333.444-55", 10500.0,
                Departamento.TECNOLOGIA, LocalDate.of(2019, 8, 1), NivelExperiencia.SENIOR, linguagensCarlos);

        List<String> linguagensJulia = new ArrayList<>();
        linguagensJulia.add("C#");
        linguagensJulia.add(".NET");
        Desenvolvedor julia = new Desenvolvedor("Julia Mendes", "333.444.555-66", 5200.0,
                Departamento.TECNOLOGIA, LocalDate.of(2023, 6, 15), NivelExperiencia.JUNIOR, linguagensJulia);

        Gerente roberto = new Gerente("Roberto Alves", "444.555.666-77", 15000.0,
                Departamento.TECNOLOGIA, LocalDate.of(2015, 2, 20), 15.0);

        Gerente fernanda = new Gerente("Fernanda Lima", "555.666.777-88", 13500.0,
                Departamento.VENDAS, LocalDate.of(2018, 11, 5), 12.0);

        Estagiario bruno = new Estagiario("Bruno Rocha", "666.777.888-99", 1800.0,
                Departamento.MARKETING, LocalDate.of(2024, 1, 10), 30, "Fernanda Lima");

        empresa.contratar(ana);
        empresa.contratar(carlos);
        empresa.contratar(julia);
        empresa.contratar(roberto);
        empresa.contratar(fernanda);
        empresa.contratar(bruno);

        roberto.adicionarSubordinado(ana);
        roberto.adicionarSubordinado(carlos);
        roberto.adicionarSubordinado(julia);
        fernanda.adicionarSubordinado(bruno);

        System.out.println("=== LISTA DE FUNCIONÁRIOS ===");
        empresa.getFuncionariosAtivos().forEach(System.out::println);

        System.out.println("\n=== TOTAL DA FOLHA MENSAL ===");
        System.out.printf("R$ %.2f\n", empresa.calcularTotalFolha());

        System.out.println("\n=== DESENVOLVEDORES QUE USAM JAVA ===");
        empresa.getDesenvolvedoresPorLinguagem("Java").forEach(System.out::println);

        System.out.println("\n=== FUNCIONÁRIOS DO DEPARTAMENTO TECNOLOGIA ===");
        empresa.getFuncionariosPorDepartamento(Departamento.TECNOLOGIA).forEach(System.out::println);

        System.out.println("\n=== APLICANDO AUMENTO DE 5% ===");
        empresa.aplicarAumentoGlobal(5.0);
        empresa.getFuncionariosAtivos().forEach(System.out::println);

        System.out.println("\n=== PROMOVENDO JULIA ===");
        empresa.promoverDesenvolvedor(julia.getId(), NivelExperiencia.PLENO);

        System.out.println("\n=== ORDENADOS POR SALÁRIO (DECRESCENTE) ===");
        empresa.listarFuncionariosOrdenadosPorSalario();

        System.out.println("\n=== PROCESSAMENTO DE PAGAMENTO ===");
        empresa.processarPagamentoTodos();

        System.out.println("\n=== BUSCA POR CPF ===");
        empresa.buscarFuncionarioPorCpf("111.222.333-44").ifPresentOrElse(
                f -> System.out.println("Encontrado: " + f),
                () -> System.out.println("Não encontrado")
        );

        System.out.println("\n=== DEMITINDO UM FUNCIONÁRIO ===");
        empresa.demitir(julia.getId());
        System.out.println("Após demissão, ativos:");
        empresa.getFuncionariosAtivos().forEach(System.out::println);

        System.out.println("\n=== NOVA TENTATIVA DE PAGAR DEMITIDO (deve lançar exceção tratada internamente) ===");
        empresa.processarPagamentoTodos();
    }
}