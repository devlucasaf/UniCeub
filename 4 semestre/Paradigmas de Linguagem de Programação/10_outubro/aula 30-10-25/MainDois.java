// Classe Carro
class Carro {
    void ligar() {
        System.out.println("O carro está ligado.");
    }

    void acelerar() {
        System.out.println("O carro está acelerando.");
    }
}

// Classe Animal
class Animal {
    void comer() {
        System.out.println("O animal está comendo.");
    }

    void dormir() {
        System.out.println("O animal está dormindo.");
    }
}

// Classe Livro
class Livro {
    void abrir() {
        System.out.println("O livro foi aberto.");
    }

    void ler() {
        System.out.println("Você está lendo o livro.");
    }
}

// Programa principal
public class Main2 {
    public static void main(String[] args) {

        // Objetos da classe Carro
        Carro carro1 = new Carro();
        Carro carro2 = new Carro();
        Carro carro3 = new Carro();
        Carro carro4 = new Carro();

        // Objetos da classe Animal
        Animal animal1 = new Animal();
        Animal animal2 = new Animal();
        Animal animal3 = new Animal();
        Animal animal4 = new Animal();

        // Objetos da classe Livro
        Livro livro1 = new Livro();
        Livro livro2 = new Livro();
        Livro livro3 = new Livro();
        Livro livro4 = new Livro();

        // Chamando métodos de um objeto de cada classe
        carro1.ligar();
        animal2.dormir();
        livro3.ler();
    }
}
