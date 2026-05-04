package questao2;

// Thread 1: Envia notícias a cada 5 segundos (Total: 10 notícias)
public class ThreadNoticias implements Runnable {
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