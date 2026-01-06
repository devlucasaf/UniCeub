/**
Paradigmas de Linguagem de Programação
Aula: 30-10-25
II Atividade 3 (POO-Encapsulamento e Classes/Obejto)
*/

class Carro {
    void ligar() {
        System.out.println("O carro está ligado.");
    }

    void acelerar() {
        System.out.println("O carro está acelerando.");
    }
}

class Animal {
    void comer() {
        System.out.println("O animal está comendo.");
    }

    void dormir() {
        System.out.println("O animal está dormindo.");
    }
}

class Livro {
    void abrir() {
        System.out.println("O livro foi aberto.");
    }

    void ler() {
        System.out.println("Você está lendo o livro.");
    }
}

public class Main2 {
    public static void main(String[] args) {

        Carro carro1 = new Carro();
        Carro carro2 = new Carro();
        Carro carro3 = new Carro();
        Carro carro4 = new Carro();

        Animal animal1 = new Animal();
        Animal animal2 = new Animal();
        Animal animal3 = new Animal();
        Animal animal4 = new Animal();

        Livro livro1 = new Livro();
        Livro livro2 = new Livro();
        Livro livro3 = new Livro();
        Livro livro4 = new Livro();

        carro1.ligar();
        animal2.dormir();
        livro3.ler();
    }
}
