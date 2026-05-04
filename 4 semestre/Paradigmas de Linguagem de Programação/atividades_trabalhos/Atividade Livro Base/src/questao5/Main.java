package questao5;

/**
    Paradigmas de Linguagens de Programação
    Atividades do Livro Base - Capítulo 5 - Programação Concorrente em Java
    Exercício do Capítulo 5

    Questão 5)
        Cinco lebres disputarão uma corrida. Cada lebre pode dar um salto
        que varia de 1 a 3 metros de distância. A distância percorrida é de 20
        metros. Na corrida, cada lebre dará um salto. Informar quantos
        metros ela pulou a cada salto realizado. Em seguida, a lebre pára para
        descansar por 2 segundos (sleep). Escreva um programa, utilizando
        threads (uma para cada lebre), que informe a lebre vencedora e a
        colocação de cada uma delas ao final da corrida. Informar, também,
        quantos pulos cada uma delas deu.

    *Atividade Desenvolvida com o auxílio da IA com autorização do professor*

    prompts:
        - Desenvolva e explique o passo a passo em Java utilizando threads para simular uma corrida entre cinco lebres usando threads. Cada lebre deve ser um thread separada, 
        onde cada uma delas faz saltos aleatórios entre 1 e 3 metros até percorrer 20 metros. Depois de cada salto, cada lebre deverá descansar por 2 segundos. 
        O sistema deverá registrar a quantidade de pulos que cada lebre deu, que lebre chegou primeiro e me apresente a classificação geral do final da corrida. 
        Gere um código completo e comentado linha por linha.
        - Adicione cores hexadecimais para fazer a diferenciação de cada lebre. Eu quero as seguintes cores: vermelho cênico, verde elétrico, azul brilhante, rosa choque e ciano claro
*/

// Importa as classes necessárias para listas, comparação, geração de números aleatórios
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

// Classe principal que gerencia a corrida das lebres
public class Main {

    // Lista de cores hexadecimais para as lebres (array constante)
    private static final String[] CORES_LEBRES = {
            "#FF5733", // Vermelho Cênico (Lebre-1)
            "#33FF57", // Verde Elétrico (Lebre-2)
            "#3357FF", // Azul Brilhante (Lebre-3)
            "#FF33F6", // Rosa Choque (Lebre-4)
            "#33FFF9"  // Ciano Claro (Lebre-5)
    };

    // Número total de lebres na corrida
    private static final int NUM_LEBRES = 5;

    // Método principal - ponto de entrada do programa
    public static void main(String[] args) {
        // Lista para armazenar os objetos Lebre
        List<Lebre> lebres = new ArrayList<>();
        // Lista para armazenar as threads de cada lebre
        List<Thread> threads = new ArrayList<>();

        // Mensagem inicial da corrida
        System.out.println("=== CORRIDA DAS LEBRES (Distância: 20m) ===");

        // 1. Criação e Início das Threads
        // Loop para criar 5 lebres (de i=0 a i=4)
        for (int i = 0; i < NUM_LEBRES; i++) {
            // Cria o nome da lebre (Lebre-1, Lebre-2, etc.)
            String nome = "Lebre-" + (i + 1);
            // Obtém a cor hexadecimal do array CORES_LEBRES
            String corHex = CORES_LEBRES[i];

            // Cria um novo objeto Lebre com nome e cor
            Lebre lebre = new Lebre(nome, corHex);
            // Adiciona a lebre à lista de lebres
            lebres.add(lebre);

            // Cria uma nova thread associada à lebre
            Thread thread = new Thread(lebre);
            // Adiciona a thread à lista de threads
            threads.add(thread);
            // Inicia a execução da thread (chama o método run())
            thread.start();
        }

        // 2. Aguarda o término de todas as threads
        try {
            // Percorre todas as threads na lista
            for (Thread t : threads) {
                t.join(); // Aguarda cada thread terminar sua execução
            }
        } catch (InterruptedException e) {
            // Trata interrupção durante a espera
            System.err.println("A corrida foi interrompida!");
            Thread.currentThread().interrupt(); // Restaura o status de interrupção
        }

        // 3. Processamento e Classificação dos Resultados
        System.out.println("\n=== RESULTADO FINAL DA CORRIDA ===");

        // Filtra e classifica as lebres pelo menor tempo de chegada
        List<Lebre> resultadosFinais = lebres.stream()
                // Filtra apenas as lebres que completaram a corrida
                .filter(l -> l.getDistanciaPercorrida() >= Lebre.distanciaTotal)
                // Ordena as lebres pelo tempo de chegada (menor tempo primeiro)
                .sorted(Comparator.comparingLong(Lebre::getTempoChegada))
                // Converte o stream para uma lista
                .toList();

        // Verifica se alguma lebre terminou a corrida
        if (resultadosFinais.isEmpty()) {
            System.out.println("Nenhuma lebre conseguiu terminar a corrida.");
            return; // Encerra o programa se nenhuma lebre terminou
        }

        // 4. Impressão da Classificação com cores
        int colocacao = 1; // Contador para a colocação

        // Imprime o vencedor com destaque
        System.out.println("\n## 🏆 VENCEDORA:");
        // Obtém a cor ANSI da lebre vencedora (primeira da lista ordenada)
        String corVencedora = resultadosFinais.get(0).getCorANSI();
        // Imprime informações da lebre vencedora com sua cor
        System.out.printf("   - %s%s%s (Tempo de Chegada: %d ms)\n",
                corVencedora, resultadosFinais.get(0).getNome(), ANSIConverter.RESET, resultadosFinais.get(0).getTempoChegada());

        // Imprime cabeçalho da tabela de resultados
        System.out.println("\n## 🏅 COLOCAÇÃO E ESTATÍSTICAS:");
        System.out.println("-------------------------------------------------------");
        // Cabeçalho da tabela com formatação
        System.out.printf("| %-10s | %-12s | %-10s |\n", "Colocação", "Lebre", "Total Pulos");
        System.out.println("-------------------------------------------------------");

        // Loop para imprimir cada lebre na tabela de resultados
        for (Lebre l : resultadosFinais) {
            // Usa a cor ANSI de cada lebre na tabela e formata os dados
            System.out.printf("| %-10d | %s%-12s%s | %-10d |\n",
                    colocacao++, l.getCorANSI(), l.getNome(), ANSIConverter.RESET, l.getTotalPulos());
        }
        // Rodapé da tabela
        System.out.println("-------------------------------------------------------");
    }
}

