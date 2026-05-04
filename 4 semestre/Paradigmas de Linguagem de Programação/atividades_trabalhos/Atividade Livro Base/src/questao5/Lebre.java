package questao5;

// Classe que representa cada lebre e sua lógica de corrida (Thread/Runnable)
public class Lebre implements Runnable {
    // Nome da lebre (ex: "Lebre-1")
    private final String nome;
    // Cor hexadecimal original (ex: "#FF5733")
    private final String corHexadecimal;
    // Cor convertida para código ANSI
    private final String corANSI;
    // Distância percorrida pela lebre em metros
    private int distanciaPercorrida = 0;
    // Contador de total de pulos dados pela lebre
    private int totalPulos = 0;
    // Timestamp de quando a lebre cruzou a linha de chegada
    private long tempoChegada = Long.MAX_VALUE;
    // Constante que define a distância total da corrida (20 metros)
    static final int distanciaTotal = 20;

    // Construtor da classe Lebre
    public Lebre(String nome, String corHexadecimal) {
        this.nome = nome; // Atribui o nome da lebre
        this.corHexadecimal = corHexadecimal; // Atribui a cor hexadecimal
        this.corANSI = ANSIConverter.getANSIColor(corHexadecimal); // Converte a cor hexadecimal para ANSI
    }

    // Método run() - será executado quando a thread for iniciada
    @Override
    public void run() {
        // Cria um gerador de números aleatórios para simular os pulos
        Random rand = new Random();

        // Loop principal: continua enquanto a lebre não atingiu a distância total
        while (distanciaPercorrida < distanciaTotal) {
            // Gera um pulo aleatório entre 1 e 3 metros
            int salto = rand.nextInt(3) + 1;

            // Adiciona o pulo à distância total percorrida
            distanciaPercorrida += salto;
            // Incrementa o contador de pulos
            totalPulos++;

            // Garante que a distância atual não exceda a distância total
            int distanciaAtual = Math.min(distanciaPercorrida, distanciaTotal);

            // Usa a cor ANSI na impressão do progresso
            System.out.printf("%s[%s]%s Pulou %d metros. Total percorrido: %d/%d metros.\n",
                    corANSI, nome, ANSIConverter.RESET, salto, distanciaAtual, distanciaTotal);

            // Verifica se a lebre cruzou a linha de chegada
            if (distanciaPercorrida >= distanciaTotal) {
                // Registra o timestamp atual como tempo de chegada
                tempoChegada = System.currentTimeMillis();
                // Usa a cor ANSI na impressão da chegada
                System.out.printf("%s>>> %s CRUZOU A LINHA DE CHEGADA!%s\n", corANSI, nome, ANSIConverter.RESET);
                break; // Sai do loop while
            }

            // Simula o descanso da lebre entre os pulos
            try {
                Thread.sleep(2000); // Descansa 2 segundos (2000 milissegundos)
            } catch (InterruptedException e) {
                // Trata a interrupção da thread
                System.out.println(nome + " foi interrompida.");
                Thread.currentThread().interrupt(); // Restaura o status de interrupção
                break; // Sai do loop while
            }
        }
    }

    // ========== MÉTODOS GETTERS ==========

    // Retorna o nome da lebre
    public String getNome() {
        return nome;
    }

    // Retorna o total de pulos dados pela lebre
    public int getTotalPulos() {
        return totalPulos;
    }

    // Retorna o tempo de chegada da lebre
    public long getTempoChegada() {
        return tempoChegada;
    }

    // Retorna a distância percorrida pela lebre
    public int getDistanciaPercorrida() {
        return distanciaPercorrida;
    }

    // Retorna o código ANSI da cor da lebre
    public String getCorANSI() {
        return corANSI;
    }
}