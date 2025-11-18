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
*/

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

class ANSIConverter {
    // Define o código ANSI para resetar a cor ao padrão do terminal
    public static final String RESET = "\u001B[0m";

    /**
     * Gera o código ANSI de True Color (24-bit) a partir de um valor hexadecimal.
     * @param hexColor A string hexadecimal da cor (ex: "#FF5733" ou "FF5733").
     * @return O código ANSI para definir a cor da fonte.
     */
    public static String getANSIColor(String hexColor) {
        // Remove o '#' se estiver presente
        if (hexColor.startsWith("#")) {
            hexColor = hexColor.substring(1);
        }

        try {
            // Converte o Hex para valores RGB (RRGGBB)
            int r = Integer.parseInt(hexColor.substring(0, 2), 16);
            int g = Integer.parseInt(hexColor.substring(2, 4), 16);
            int b = Integer.parseInt(hexColor.substring(4, 6), 16);

            // Retorna o código ANSI de 24-bit (True Color) para a cor da fonte
            return String.format("\u001B[38;2;%d;%d;%dm", r, g, b);
        } catch (NumberFormatException e) {
            // Retorna o código de reset em caso de formato inválido
            return RESET;
        }
    }
}

// Classe que representa cada lebre e sua lógica de corrida (Thread/Runnable)
class Lebre implements Runnable {
    private final String nome;
    private final String corHexadecimal;       // Cor hexadecimal original
    private final String corANSI;      // Cor convertida para ANSI
    private int distanciaPercorrida = 0;
    private int totalPulos = 0;
    private long tempoChegada = Long.MAX_VALUE;
    static final int distanciaTotal = 20;

    public Lebre(String nome, String corHexadecimal) {
        this.nome = nome;
        this.corHexadecimal = corHexadecimal;
        this.corANSI = ANSIConverter.getANSIColor(corHexadecimal); // Converte a cor aqui
    }

    @Override
    public void run() {
        Random rand = new Random();

        while (distanciaPercorrida < distanciaTotal) {
            int salto = rand.nextInt(3) + 1;

            distanciaPercorrida += salto;
            totalPulos++;

            int distanciaAtual = Math.min(distanciaPercorrida, distanciaTotal);

            // Usa a cor ANSI na impressão
            System.out.printf("%s[%s]%s Pulou %d metros. Total percorrido: %d/%d metros.\n",
                    corANSI, nome, ANSIConverter.RESET, salto, distanciaAtual, distanciaTotal);

            if (distanciaPercorrida >= distanciaTotal) {
                tempoChegada = System.currentTimeMillis();
                // Usa a cor ANSI na impressão da chegada
                System.out.printf("%s>>> %s CRUZOU A LINHA DE CHEGADA!%s\n", corANSI, nome, ANSIConverter.RESET);
                break;
            }

            try {
                Thread.sleep(2000); // Descansa 2 segundos
            } catch (InterruptedException e) {
                System.out.println(nome + " foi interrompida.");
                Thread.currentThread().interrupt();
                break;
            }
        }
    }

    // Getters
    public String getNome() {
        return nome;
    }

    public int getTotalPulos() {
        return totalPulos;
    }

    public long getTempoChegada() {
        return tempoChegada;
    }

    public int getDistanciaPercorrida() {
        return distanciaPercorrida;
    }

    public String getCorANSI() {
        return corANSI;
    }
}

public class Questao5 {

    // Lista de cores hexadecimais para as lebres
    private static final String[] CORES_LEBRES = {
            "#FF5733", // Vermelho Cênico (Lebre-1)
            "#33FF57", // Verde Elétrico (Lebre-2)
            "#3357FF", // Azul Brilhante (Lebre-3)
            "#FF33F6", // Rosa Choque (Lebre-4)
            "#33FFF9"  // Ciano Claro (Lebre-5)
    };

    private static final int NUM_LEBRES = 5;

    public static void main(String[] args) {
        List<Lebre> lebres = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();

        System.out.println("=== CORRIDA DAS LEBRES (Distância: 20m) ===");

        // 1. Criação e Início das Threads
        // Loop de i=0 a i<5, corrigindo o erro de índice.
        for (int i = 0; i < NUM_LEBRES; i++) {

            String nome = "Lebre-" + (i + 1); // Nome vai de 1 a 5
            String corHex = CORES_LEBRES[i];   // Acessa o índice 0 a 4

            Lebre lebre = new Lebre(nome, corHex);
            lebres.add(lebre);

            Thread thread = new Thread(lebre);
            threads.add(thread);
            thread.start();
        }

        // 2. Aguarda o término de todas as threads
        try {
            for (Thread t : threads) {
                t.join();
            }
        } catch (InterruptedException e) {
            System.err.println("A corrida foi interrompida!");
            Thread.currentThread().interrupt();
        }

        // 3. Processamento e Classificação dos Resultados
        System.out.println("\n=== RESULTADO FINAL DA CORRIDA ===");

        // Filtra e classifica as lebres pelo menor tempo de chegada
        List<Lebre> resultadosFinais = lebres.stream()
                .filter(l -> l.getDistanciaPercorrida() >= Lebre.distanciaTotal)
                .sorted(Comparator.comparingLong(Lebre::getTempoChegada))
                .toList();

        if (resultadosFinais.isEmpty()) {
            System.out.println("Nenhuma lebre conseguiu terminar a corrida.");
            return;
        }

        // 4. Impressão da Classificação com cores
        int colocacao = 1;

        System.out.println("\n## 🏆 VENCEDORA:");
        // Usa a cor ANSI da primeira lebre (vencedora)
        String corVencedora = resultadosFinais.get(0).getCorANSI();
        System.out.printf("   - %s%s%s (Tempo de Chegada: %d ms)\n",
                corVencedora, resultadosFinais.get(0).getNome(), ANSIConverter.RESET, resultadosFinais.get(0).getTempoChegada());

        System.out.println("\n## 🏅 COLOCAÇÃO E ESTATÍSTICAS:");
        System.out.println("-------------------------------------------------------");
        System.out.printf("| %-10s | %-12s | %-10s |\n", "Colocação", "Lebre", "Total Pulos");
        System.out.println("-------------------------------------------------------");

        for (Lebre l : resultadosFinais) {
            // Usa a cor ANSI de cada lebre na tabela
            System.out.printf("| %-10d | %s%-12s%s | %-10d |\n",
                    colocacao++, l.getCorANSI(), l.getNome(), ANSIConverter.RESET, l.getTotalPulos());
        }
        System.out.println("-------------------------------------------------------");
    }
}
