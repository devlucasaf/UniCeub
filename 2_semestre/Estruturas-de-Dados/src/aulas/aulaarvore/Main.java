package aulas.aulaarvore;

public class Main {
    public static void main(String[] args) {
        ArvoreBinaria arvore = new ArvoreBinaria();

        arvore.inserir(50);
        arvore.inserir(30);
        arvore.inserir(70);
        arvore.inserir(20);
        arvore.inserir(40);
        arvore.inserir(60);
        arvore.inserir(80);
        arvore.inserir(10);
        arvore.inserir(25);
        arvore.inserir(90);

        arvore.exibirInformacoes();

        System.out.println("===== BUSCAS =====");
        System.out.println("Existe o valor 40? " + arvore.buscar(40));
        System.out.println("Existe o valor 99? " + arvore.buscar(99));
        System.out.println();

        System.out.println("===== PERCURSOS =====");
        arvore.preOrdem();
        arvore.emOrdem();
        arvore.posOrdem();
        arvore.porNivel();
        System.out.println();

        System.out.println("===== GRAU DE ALGUNS NÓS =====");
        arvore.mostrarGrauDoNo(50);
        arvore.mostrarGrauDoNo(30);
        arvore.mostrarGrauDoNo(90);
        System.out.println();

        System.out.println("===== EXPLICAÇÃO FINAL =====");
        System.out.println("Uma árvore é uma estrutura de dados não linear.");
        System.out.println("Ela é usada para representar relações hierárquicas.");
        System.out.println("A raiz é o primeiro nó da estrutura.");
        System.out.println("Nós sem filhos são chamados de folhas.");
        System.out.println("O grau de um nó é a quantidade de filhos que ele possui.");
        System.out.println("A altura da árvore indica o maior nível alcançado.");
        System.out.println("Os percursos mostram maneiras diferentes de visitar os nós.");
    }
}