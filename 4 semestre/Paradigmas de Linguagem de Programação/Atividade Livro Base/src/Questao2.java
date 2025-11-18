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

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

// Thread 1: Envia notícias a cada 5 segundos (Total: 10 notícias)
class ThreadNoticias implements Runnable {
    @Override
    public void run() {
        String[] noticias = {
                "Caesb tem a melhor água do país!",
                "Motoboy busina para encontrar dono do pedido",
                "Van♡Maid anuncia o fim das atividades",
                "Flamengo é o time mais odiado do Brasil",
                "Pedro comeu o frango",
                "Casal VV: Vinicius JR e Virginia. O novo Pelé e Xuxa?",
                "Maria Flor anda de cavalo",
                "Centauro finaliza uma entrega!",
                "Encerrado a Segunda Guerra Mundial!",
                "Titanic afunda nas coordenadas de 41°43'57“N, 49°56'49“W. Teremos sobreviventes?"
        };

        try {
            for (int i = 0; i < 10; i++) {
                // Imprime a notícia
                System.out.println("[NOTÍCIA " + (i + 1) + "/10]: " + noticias[i]);

                // Pausa por 5 segundos (5000 milissegundos)
                Thread.sleep(5000);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread de notícias interrompida.");
        }
        System.out.println(">> Fim das Notícias.");
    }
}

// Thread 2: Envia a hora a cada 10 segundos (Total: 5 vezes)
class ThreadHora implements Runnable {
    @Override
    public void run() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("HH:mm:ss");

        try {
            for (int i = 0; i < 5; i++) {
                // Pega a hora atual e formata
                String horaAtual = LocalTime.now().format(formatador);
                System.out.println("\n********** [HORA " + (i + 1) + "/5]: " + horaAtual + " **********\n");

                // Pausa por 10 segundos (10000 milissegundos)
                Thread.sleep(10000);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread de hora interrompida.");
        }
        System.out.println(">> Fim do Relógio.");
    }
}

public class Questao2 {
    public static void main(String[] args) {
        // Instancia as tarefas
        Thread tNoticias = new Thread(new ThreadNoticias());
        Thread tHora = new Thread(new ThreadHora());

        System.out.println("=== INICIANDO SISTEMA INFORMATIVO ===");

        // Inicia as threads
        tNoticias.start();
        tHora.start();

        // O main aguarda as threads terminarem para encerrar o programa
        try {
            tNoticias.join();
            tHora.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("=== PROGRAMA ENCERRADO ===");
    }
}
