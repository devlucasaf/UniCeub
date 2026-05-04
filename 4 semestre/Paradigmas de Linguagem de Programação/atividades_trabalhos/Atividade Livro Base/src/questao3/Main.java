package questao3;

/**
    Paradigmas de Linguagens de Programação
    Atividades do Livro Base - Capítulo 5 - Programação Concorrente em Java
    Exercício do Capítulo 5

    Questão 3)
    Elabore um programa Java que cria 3 threads. A primeira escreve na
    tela a letra “A”, a segunda escreve a letra “B” e a terceira a letra “C”.
    Faça com que seja escrito na tela sempre “ABC”

    *Atividade Desenvolvida com o auxílio da IA com autorização do professor*
*/

// Classe principal que contém o método main
public class Main {
    // Método principal - ponto de entrada do programa
    public static void main(String[] args) {
        // 1. Cria o objeto monitor que será compartilhado entre todas as threads
        Printer sharedPrinter = new Printer();

        // 2. Cria as três threads, passando o mesmo monitor compartilhado
        // Cada thread recebe uma tarefa LetterPrinter com sua letra correspondente
        Thread tA = new Thread(new LetterPrinter(sharedPrinter, 'A'), "Thread-A");
        Thread tB = new Thread(new LetterPrinter(sharedPrinter, 'B'), "Thread-B");
        Thread tC = new Thread(new LetterPrinter(sharedPrinter, 'C'), "Thread-C");

        // Mensagens iniciais do programa
        System.out.println("Iniciando a sequência controlada...");
        System.out.println("-----------------------------------");

        // 3. Inicia as threads
        // A ordem de start() não importa, pois a sincronização garante a ordem de impressão ABC.
        tC.start(); // Inicia a thread C
        tA.start(); // Inicia a thread A
        tB.start(); // Inicia a thread B

        // Bloco try-catch para aguardar o término de todas as threads
        try {
            // Aguarda a thread A terminar sua execução
            tA.join();
            // Aguarda a thread B terminar sua execução
            tB.join();
            // Aguarda a thread C terminar sua execução
            tC.join();
        } catch (InterruptedException e) {
            // Captura exceção caso o programa principal seja interrompido durante o join
            System.err.println("O programa foi interrompido.");
        }

        // Mensagens finais do programa
        System.out.println("-----------------------------------");
        System.out.println("Sequência ABC finalizada após " + sharedPrinter.getIterations() + " repetições.");
    }
}
