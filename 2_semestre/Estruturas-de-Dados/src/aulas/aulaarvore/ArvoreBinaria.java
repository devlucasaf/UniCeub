package aulas.aulaarvore;

import java.util.LinkedList;
import java.util.Queue;

public class ArvoreBinaria {
    No raiz;

    public ArvoreBinaria() {
        this.raiz = null;
    }

    public void inserir(int valor) {
        raiz = inserirRecursivo(raiz, valor);
    }

    private No inserirRecursivo(No atual, int valor) {
        if (atual == null) {
            return new No(valor);
        }

        if (valor < atual.valor) {
            atual.esquerda = inserirRecursivo(atual.esquerda, valor);
        } else if (valor > atual.valor) {
            atual.direita = inserirRecursivo(atual.direita, valor);
        }

        return atual;
    }

    public boolean buscar(int valor) {
        return buscarRecursivo(raiz, valor);
    }

    private boolean buscarRecursivo(No atual, int valor) {
        if (atual == null) {
            return false;
        }

        if (atual.valor == valor) {
            return true;
        }

        if (valor < atual.valor) {
            return buscarRecursivo(atual.esquerda, valor);
        } else {
            return buscarRecursivo(atual.direita, valor);
        }
    }

    public void preOrdem() {
        System.out.print("Pré-ordem: ");
        preOrdemRec(raiz);
        System.out.println();
    }

    private void preOrdemRec(No atual) {
        if (atual != null) {
            System.out.print(atual.valor + " ");
            preOrdemRec(atual.esquerda);
            preOrdemRec(atual.direita);
        }
    }

    public void emOrdem() {
        System.out.print("Em ordem: ");
        emOrdemRec(raiz);
        System.out.println();
    }

    private void emOrdemRec(No atual) {
        if (atual != null) {
            emOrdemRec(atual.esquerda);
            System.out.print(atual.valor + " ");
            emOrdemRec(atual.direita);
        }
    }

    public void posOrdem() {
        System.out.print("Pós-ordem: ");
        posOrdemRec(raiz);
        System.out.println();
    }

    private void posOrdemRec(No atual) {
        if (atual != null) {
            posOrdemRec(atual.esquerda);
            posOrdemRec(atual.direita);
            System.out.print(atual.valor + " ");
        }
    }

    public void porNivel() {
        System.out.print("Por nível: ");

        if (raiz == null) {
            System.out.println("árvore vazia");
            return;
        }

        Queue<No> fila = new LinkedList<>();
        fila.add(raiz);

        while (!fila.isEmpty()) {
            No atual = fila.poll();
            System.out.print(atual.valor + " ");

            if (atual.esquerda != null) {
                fila.add(atual.esquerda);
            }
            
            if (atual.direita != null) {
                fila.add(atual.direita);
            }
        }

        System.out.println();
    }

    public int altura() {
        return alturaRec(raiz);
    }

    private int alturaRec(No atual) {
        if (atual == null) {
            return -1;
        }

        int esq = alturaRec(atual.esquerda);
        int dir = alturaRec(atual.direita);

        return Math.max(esq, dir) + 1;
    }

    public int contarNos() {
        return contarNosRec(raiz);
    }

    private int contarNosRec(No atual) {
        if (atual == null) {
            return 0;
        }

        return 1 + contarNosRec(atual.esquerda) + contarNosRec(atual.direita);
    }

    public int contarFolhas() {
        return contarFolhasRec(raiz);
    }

    private int contarFolhasRec(No atual) {
        if (atual == null) {
            return 0;
        }

        if (atual.esquerda == null && atual.direita == null) {
            return 1;
        }

        return contarFolhasRec(atual.esquerda) + contarFolhasRec(atual.direita);
    }

    public void exibirInformacoes() {
        System.out.println("===== INFORMAÇÕES DA ÁRVORE =====");

        if (raiz == null) {
            System.out.println("A árvore está vazia.");
            return;
        }

        System.out.println("Raiz da árvore: " + raiz.valor);
        System.out.println("Quantidade de nós: " + contarNos());
        System.out.println("Quantidade de folhas: " + contarFolhas());
        System.out.println("Altura da árvore: " + altura());
        System.out.println();
    }

    public void mostrarGrauDoNo(int valor) {
        No no = localizarNo(raiz, valor);

        if (no == null) {
            System.out.println("Nó " + valor + " não encontrado.");
            return;
        }

        int grau = 0;
        if (no.esquerda != null) {
            grau++;
        }

        if (no.direita != null) {
            grau++;
        }

        System.out.println("O grau do nó " + valor + " é: " + grau);
    }

    private No localizarNo(No atual, int valor) {
        if (atual == null) {
            return null;
        }

        if (atual.valor == valor) {
            return atual;
        }

        if (valor < atual.valor) {
            return localizarNo(atual.esquerda, valor);
        } else {
            return localizarNo(atual.direita, valor);
        }
    }
}