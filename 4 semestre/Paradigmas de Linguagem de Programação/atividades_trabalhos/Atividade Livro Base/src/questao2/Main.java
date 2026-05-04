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

package questao2;

// Classe principal que contém o método main
public class Main {
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

