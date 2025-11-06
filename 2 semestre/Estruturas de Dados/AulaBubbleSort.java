// Aula: Java BubbleSort
// Estrutura de Dados
// Segundo Semestre

import java.util.Arrays;

public class AulaBubbleSort {

    // Método que demonstra o funcionamento básico do BubbleSort
    public static void BubbleSortAula() {

        // Vetor de exemplo
        int[] numeros = {5, 1, 4, 2, 8};

        System.out.println("Vetor original: " + Arrays.toString(numeros));

        // Algoritmo BubbleSort
        for (int i = 0; i < numeros.length - 1; i++) {
            for (int j = 0; j < numeros.length - 1 - i; j++) {
                if (numeros[j] > numeros[j + 1]) {
                    // Troca os elementos
                    int temp = numeros[j];
                    numeros[j] = numeros[j + 1];
                    numeros[j + 1] = temp;
                }
            }
        }

        System.out.println("Vetor ordenado com BubbleSort: " + Arrays.toString(numeros));
        System.out.println();
    }

    // Método que demonstra BubbleSort em ordem decrescente
    public static void BubbleSortDecrescente() {

        int[] valores = {10, 3, 6, 2, 9};

        System.out.println("Vetor original: " + Arrays.toString(valores));

        // BubbleSort decrescente
        for (int i = 0; i < valores.length - 1; i++) {
            for (int j = 0; j < valores.length - 1 - i; j++) {
                if (valores[j] < valores[j + 1]) {
                    int temp = valores[j];
                    valores[j] = valores[j + 1];
                    valores[j + 1] = temp;
                }
            }
        }

        System.out.println("Vetor ordenado (decrescente): " + Arrays.toString(valores));
        System.out.println();
    }

    // Método que verifica quantas trocas foram feitas no BubbleSort
    public static void BubbleSortTrocas() {

        int[] numeros = {9, 1, 5, 3, 7};
        int trocas = 0;

        System.out.println("Vetor original: " + Arrays.toString(numeros));

        for (int i = 0; i < numeros.length - 1; i++) {
            for (int j = 0; j < numeros.length - 1 - i; j++) {

                if (numeros[j] > numeros[j + 1]) {
                    int temp = numeros[j];
                    numeros[j] = numeros[j + 1];
                    numeros[j + 1] = temp;
                    trocas++;
                }
            }
        }

        System.out.println("Vetor ordenado: " + Arrays.toString(numeros));
        System.out.println("Número total de trocas realizadas: " + trocas);
        System.out.println();
    }

    // Exercício
    public static void Exercicio() {

        System.out.println(">>> EXERCÍCIO BUBBLESORT <<<");

        // 1. Crie um vetor de inteiros com os valores: 12, 5, 7, 1, 9, 3
        int[] vetor = {12, 5, 7, 1, 9, 3};
        System.out.println("1. Vetor original: " + Arrays.toString(vetor));

        // 2. Ordene o vetor usando BubbleSort (ordem crescente)
        for (int i = 0; i < vetor.length - 1; i++) {
            for (int j = 0; j < vetor.length - 1 - i; j++) {
                if (vetor[j] > vetor[j + 1]) {
                    int temp = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = temp;
                }
            }
        }
        System.out.println("2. Vetor ordenado (crescente): " + Arrays.toString(vetor));

        // 3. Verifique o menor e maior valor
        int menor = vetor[0];
        int maior = vetor[vetor.length - 1];
        System.out.println("3. Menor número: " + menor);
        System.out.println("3. Maior número: " + maior);

        // 4. Some todos os valores do vetor
        int soma = 0;
        for (int num : vetor) {
            soma += num;
        }
        System.out.println("4. Soma dos valores: " + soma);

        // 5. Calcule a média aritmética dos valores
        double media = (double) soma / vetor.length;
        System.out.println("5. Média dos valores: " + media);

        System.out.println(">>> FIM DO EXERCÍCIO >>>");
    }

    // Método principal que executa todos os outros
    public static void main(String[] args) {

        BubbleSortAula();        // Demonstração do algoritmo básico
        BubbleSortDecrescente(); // BubbleSort em ordem decrescente
        BubbleSortTrocas();      // Contando o número de trocas
        Exercicio();             // Exercício no estilo do professor
    }
}
