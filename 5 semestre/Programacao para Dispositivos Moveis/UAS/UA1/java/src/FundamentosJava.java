import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class FundamentosJava {

    static String mensagemGlobal = "Esta é uma variável global.";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("\n=== 1. Variáveis e Tipos ===");

        double n1 = 10.0;
        double n2 = 7.0;

        double media = (n1 + n2) / 2.0;
        System.out.println("Média de " + n1 + " e " + n2 + ": " + media);

        String texto;
        texto = "Java com tipagem forte";
        System.out.println(texto);

        String nomeNullable = null;
        imprimirMensagem(nomeNullable);

        String nomeNaoNullable = "João";
        System.out.println("Nome não nulo: " + nomeNaoNullable);

        System.out.println("\n=== 2. Funções e Cálculos ===");

        int numero = 5;
        System.out.println("Fatorial de " + numero + ": " + fatorial(numero));

        int tamanhoLista = 8;
        List<Double> minhaLista = geradorDeLista(tamanhoLista, 100.0);
        System.out.println("Lista gerada: " + minhaLista);

        double maiorValor = encontrarMaior(minhaLista);
        System.out.println("Maior valor na lista: " + maiorValor);

        System.out.println("\n=== 3. Manipulação de Coleções ===");

        List<Double> maioresQue50 = new ArrayList<>();
        for (double valor : minhaLista) {
            if (valor > 50) {
                maioresQue50.add(valor);
            }
        }
        System.out.println("Valores > 50: " + maioresQue50);

        List<Integer> valoresInteiros = new ArrayList<>();
        for (double valor : minhaLista) {
            valoresInteiros.add((int) Math.round(valor));
        }
        System.out.println("Valores arredondados: " + valoresInteiros);

        System.out.println("\n=== 4. Trabalhando com Classes ===");

        Aluno aluno1 = new Aluno("Ana", 8.5, 7.2);
        Aluno aluno2 = new Aluno("Bruno", 5.0, 6.5);
        Aluno aluno3 = new Aluno("Carla");

        aluno2.setNota2(7.0);

        List<Aluno> turma = new ArrayList<>();
        turma.add(aluno1);
        turma.add(aluno2);
        turma.add(aluno3);

        for (Aluno aluno : turma) {
            System.out.println(aluno);
        }

        System.out.println("\n=== 5. Método auxiliar (equivalente à função aninhada) ===");

        mostrarStatus(aluno1);
        mostrarStatus(aluno2);
        mostrarStatus(aluno3);

        System.out.println("\n=== 6. Entrada/Saída (Console) ===");
        System.out.print("Digite um número inteiro para calcular o fatorial: ");
        String entrada = sc.nextLine();

        if (entrada != null && !entrada.isEmpty()) {
            try {
                int valor = Integer.parseInt(entrada);
                System.out.println("Fatorial de " + valor + ": " + fatorial(valor));
            } 
            
            catch (NumberFormatException e) {
                System.out.println("Erro: você não digitou um número inteiro válido.");
            }
        } 
        
        else {
            System.out.println("Nenhuma entrada fornecida.");
        }

        System.out.println("\n=== 7. Inicialização tardia (equivalente prático) ===");

        System.out.println("O programa continua...");
        double valorCalculado = calcularValorCaro();
        System.out.println("Valor calculado: " + valorCalculado);

        System.out.println("\n=== 8. Tratamento de null ===");

        String textoNullable = null;
        String textoFinal = (textoNullable != null) ? textoNullable : "Valor padrão (coalescência)";
        System.out.println(textoFinal);

        List<Integer> listaNullable = null;

        if (listaNullable != null) {
            listaNullable.add(10);
        } 
        
        else {
            System.out.println("A lista é nula, não é possível adicionar.");
        }

        System.out.println("\n=== 9. Outros Conceitos ===");

        System.out.println("Variável global: " + mensagemGlobal);

        funcaoPrivada();

        Object valorQualquer = 42;
        System.out.println("Objeto como inteiro: " + valorQualquer);
        valorQualquer = "Agora é string";
        System.out.println("Objeto como string: " + valorQualquer);

        System.out.println("\n=== Fim do programa ===");

        sc.close();
    }

    public static int fatorial(int n) {
        if (n <= 0) {
            return 1;
        }
        return n * fatorial(n - 1);
    }

    public static List<Double> geradorDeLista(int quantidade, double maximo) {
        Random rng = new Random();
        List<Double> lista = new ArrayList<>();

        for (int i = 0; i < quantidade; i++) {
            double valor = rng.nextDouble() * maximo;
            lista.add(valor);
        }

        return lista;
    }

    public static double encontrarMaior(List<Double> lista) {
        if (lista == null || lista.isEmpty()) {
            throw new IllegalArgumentException("A lista não pode estar vazia.");
        }

        double maior = lista.get(0);
        int posicao = 1;

        while (posicao < lista.size()) {
            if (lista.get(posicao) > maior) {
                maior = lista.get(posicao);
            }
            posicao++;
        }

        return maior;
    }

    public static void imprimirMensagem(String mensagem) {
        String texto = (mensagem != null) ? mensagem : "Mensagem não fornecida.";
        System.out.println(">>> " + texto);
    }

    public static void mostrarStatus(Aluno a) {
        String status = a.estaAprovado() ? "aprovado" : "reprovado";
        System.out.println(a.getNome() + " está " + status +
                " com média " + String.format("%.1f", a.calcularMedia()));
    }

    private static void funcaoPrivada() {
        System.out.println("Esta função é privada (só pode ser usada dentro desta classe).");
    }

    public static double calcularValorCaro() {
        System.out.println("  > Executando cálculo caro...");
        try {
            Thread.sleep(1000);
        } 
        
        catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return 3.14159;
    }
}
