/**
    Paradigmas de Linguagens de Programação
    Atividades do Livro Base - Capítulo 5 - Programação Concorrente em Java
    Exercício do Capítulo 5

    Questão 2)
    Crie duas threads em Java, uma que fica enviando notícias a cada 5
    segundos (textos quaisquer, no total de 10), enquanto a outra thread
    fica enviando a hora, a cada 10 segundos. A hora deverá ser informada
    nomínimo 5 vezes para que o programa se encerre;

    *Atividade Desenvolvida com o auxílio da IA com autorização do professor*
*/

// Importa a classe LocalTime para trabalhar com horários
import java.time.LocalTime;
// Importa a classe DateTimeFormatter para formatar a exibição de horários
import java.time.format.DateTimeFormatter;

// Thread 1: Envia notícias a cada 5 segundos (Total: 10 notícias)
class ThreadNoticias implements Runnable {
    // Implementa o método run() da interface Runnable
    @Override
    public void run() {
        // Array contendo 10 notícias pré-definidas
        String[] noticias = {
                "Caesb tem a melhor água do país!",
                "Motoboy buzina para encontrar dono do pedido",
                "Van♡Maid anuncia o fim das atividades",
                "Flamengo é o time mais odiado do Brasil",
                "Pedro comeu o frango",
                "Casal VV: Vinicius JR e Virginia. O novo Pelé e Xuxa?",
                "Maria Flor anda de cavalo",
                "Centauro finaliza uma entrega!",
                "Encerrado a Segunda Guerra Mundial!",
                "Titanic afunda nas coordenadas de 41°43'57\"N, 49°56'49\"W. Teremos sobreviventes?"
        };

        // Bloco try-catch para tratar possíveis interrupções da thread
        try {
            // Loop para enviar as 10 notícias
            for (int i = 0; i < 10; i++) {
                // Imprime a notícia atual com seu número de sequência
                System.out.println("[NOTÍCIA " + (i + 1) + "/10]: " + noticias[i]);

                // Pausa a execução da thread por 5 segundos (5000 milissegundos)
                Thread.sleep(5000);
            }
        } catch (InterruptedException e) {
            // Captura e trata a exceção caso a thread seja interrompida durante o sleep
            System.out.println("Thread de notícias interrompida.");
        }
        // Mensagem indicando o fim do envio de notícias
        System.out.println(">> Fim das Notícias.");
    }
}

// Thread 2: Envia a hora a cada 10 segundos (Total: 5 vezes)
class ThreadHora implements Runnable {
    // Implementa o método run() da interface Runnable
    @Override
    public void run() {
        // Cria um formatador para exibir a hora no formato HH:mm:ss (horas:minutos:segundos)
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("HH:mm:ss");

        // Bloco try-catch para tratar possíveis interrupções da thread
        try {
            // Loop para enviar a hora 5 vezes
            for (int i = 0; i < 5; i++) {
                // Obtém a hora atual do sistema e formata conforme o padrão definido
                String horaAtual = LocalTime.now().format(formatador);
                // Imprime a hora atual formatada com destaque visual
                System.out.println("\n********** [HORA " + (i + 1) + "/5]: " + horaAtual + " **********\n");

                // Pausa a execução da thread por 10 segundos (10000 milissegundos)
                Thread.sleep(10000);
            }
        } catch (InterruptedException e) {
            // Captura e trata a exceção caso a thread seja interrompida durante o sleep
            System.out.println("Thread de hora interrompida.");
        }
        // Mensagem indicando o fim do relógio
        System.out.println(">> Fim do Relógio.");
    }
}

// Classe principal que contém o método main
public class Questao2 {
    // Método principal - ponto de entrada do programa
    public static void main(String[] args) {
        // Cria uma thread para notícias, passando uma instância de ThreadNoticias
        Thread tNoticias = new Thread(new ThreadNoticias());
        // Cria uma thread para hora, passando uma instância de ThreadHora
        Thread tHora = new Thread(new ThreadHora());

        // Imprime mensagem de início do sistema
        System.out.println("=== INICIANDO SISTEMA INFORMATIVO ===");

        // Inicia a execução da thread de notícias
        tNoticias.start();
        // Inicia a execução da thread de hora
        tHora.start();

        // O main aguarda as threads terminarem para encerrar o programa
        try {
            // Aguarda a thread de notícias terminar sua execução
            tNoticias.join();
            // Aguarda a thread de hora terminar sua execução
            tHora.join();
        } catch (InterruptedException e) {
            // Captura e imprime a stack trace caso ocorra interrupção durante o join
            e.printStackTrace();
        }

        // Mensagem final indicando o encerramento do programa
        System.out.println("=== PROGRAMA ENCERRADO ===");
    }
}

