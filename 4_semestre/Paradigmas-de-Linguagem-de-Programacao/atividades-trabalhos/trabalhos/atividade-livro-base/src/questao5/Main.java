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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

public class Main {

    private static final String[] CORES_LEBRES = {
            "#FF5733", 
            "#33FF57", 
            "#3357FF", 
            "#FF33F6", 
            "#33FFF9"  
    };

    private static final int NUM_LEBRES = 5;

    public static void main(String[] args) {
        List<Lebre>  lebres = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();

        System.out.println("=== CORRIDA DAS LEBRES (Distância: 20m) ===");

        for (int i = 0; i < NUM_LEBRES; i++) {
            String nome = "Lebre-" + (i + 1);
            String corHex = CORES_LEBRES[i];

            Lebre lebre = new Lebre(nome, corHex);
            lebres.add(lebre);

            Thread thread = new Thread(lebre);
            threads.add(thread);
            thread.start();
        }

        try {
            for (Thread t : threads) {
                t.join(); 
            }
        } catch (InterruptedException e) {
            System.err.println("A corrida foi interrompida!");
            Thread.currentThread().interrupt(); 
        }

        System.out.println("\n=== RESULTADO FINAL DA CORRIDA ===");

        List<Lebre> resultadosFinais = lebres.stream()
                .filter(l -> l.getDistanciaPercorrida() >= Lebre.distanciaTotal)
                .sorted(Comparator.comparingLong(Lebre::getTempoChegada))
                .toList();

        if (resultadosFinais.isEmpty()) {
            System.out.println("Nenhuma lebre conseguiu terminar a corrida.");
            return; 
        }

        int colocacao = 1; 

        System.out.println("\n## 🏆 VENCEDORA:");
        String corVencedora = resultadosFinais.get(0).getCorANSI();
        System.out.printf("   - %s%s%s (Tempo de Chegada: %d ms)\n",
                corVencedora, resultadosFinais.get(0).getNome(), ANSIConverter.RESET, resultadosFinais.get(0).getTempoChegada());

        System.out.println("\n## 🏅 COLOCAÇÃO E ESTATÍSTICAS:");
        System.out.println("-------------------------------------------------------");
        System.out.printf("| %-10s | %-12s | %-10s |\n", "Colocação", "Lebre", "Total Pulos");
        System.out.println("-------------------------------------------------------");

        for (Lebre l : resultadosFinais) {
            System.out.printf("| %-10d | %s%-12s%s | %-10d |\n",
                    colocacao++, l.getCorANSI(), l.getNome(), ANSIConverter.RESET, l.getTotalPulos());
        }
        
        System.out.println("-------------------------------------------------------");
    }
}

