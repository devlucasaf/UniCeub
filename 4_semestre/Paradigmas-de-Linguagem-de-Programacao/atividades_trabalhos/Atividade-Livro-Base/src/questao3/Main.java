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

public class Main {
    public static void main(String[] args) {
        Printer sharedPrinter = new Printer();

        Thread threadA = new Thread(new LetterPrinter(sharedPrinter, 'A'), "Thread-A");
        Thread threadB = new Thread(new LetterPrinter(sharedPrinter, 'B'), "Thread-B");
        Thread threadC = new Thread(new LetterPrinter(sharedPrinter, 'C'), "Thread-C");

        System.out.println("Iniciando a sequência controlada...");
        System.out.println("-----------------------------------");

        threadC.start(); 
        threadA.start(); 
        threadB.start(); 

        try {
            threadA.join();
            threadB.join();
            threadC.join();
        } catch (InterruptedException e) {
            System.err.println("O programa foi interrompido.");
        }

        System.out.println("-----------------------------------");
        System.out.println("Sequência ABC finalizada após " + sharedPrinter.getIterations() + " repetições.");
    }
}
