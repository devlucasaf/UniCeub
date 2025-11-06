// Aula: Java MergeSort
// Estrutura de Dados
// Segundo Semestre

import java.util.Arrays;

public class aulaMergeSort {

    
    // Método Principal do MergeSort
    
    public static void mergeSort(int[] array) {

        // Caso base: vetores de tamanho 1 já estão ordenados
        if (array.length <= 1) {
            return;
        }

        // Divide o array em duas metades
        int meio = array.length / 2;
        int[] esquerda = Arrays.copyOfRange(array, 0, meio);
        int[] direita = Arrays.copyOfRange(array, meio, array.length);

        // Chama recursivamente o MergeSort nas metades
        mergeSort(esquerda);
        mergeSort(direita);

        // Junta as duas metades ordenadas
        merge(array, esquerda, direita);
    }

    // Método de intercalacao
    public static void merge(int[] array, int[] esquerda, int[] direita) {

        int i = 0; // índice lado esquerdo
        int j = 0; // índice lado direito
        int k = 0; // índice final

        // Intercalando enquanto há elementos nos dois vetores
        while (i < esquerda.length && j < direita.length) {
            if (esquerda[i] <= direita[j]) {
                array[k] = esquerda[i];
                i++;
            } else {
                array[k] = direita[j];
                j++;
            }
            k++;
        }

        // Copia o restante da parte esquerda, se houver
        while (i < esquerda.length) {
            array[k] = esquerda[i];
            i++;
            k++;
        }

        // Copia o restante da parte direita, se houver
        while (j < direita.length) {
            array[k] = direita[j];
            j++;
            k++;
        }
    }

    
    // Demonstração básica do MergeSort
    
    public static void AulaMergeSort() {

        int[] numeros = {8, 3, 9, 1, 5, 2};

        System.out.println("Vetor original: " + Arrays.toString(numeros));

        mergeSort(numeros);

        System.out.println("Vetor ordenado com MergeSort: " + Arrays.toString(numeros));
        System.out.println();
    }

    
    // Demonstração com vetor maior
    
    public static void MergeSortGrande() {

        int[] valores = {12, 5, 7, 3, 18, 1, 4, 9, 2, 14};

        System.out.println("Vetor original: " + Arrays.toString(valores));

        mergeSort(valores);

        System.out.println("Vetor ordenado (grande): " + Arrays.toString(valores));
        System.out.println();
    }

    
    // Exercício:
    
    public static void Exercicio() {

        System.out.println("===== EXERCÍCIO MERGESORT =====");

        // 1. Crie um vetor com os seguintes números:
        int[] vetor = {27, 10, 15, 3, 8, 2, 19};
        System.out.println("1. Vetor original: " + Arrays.toString(vetor));

        // 2. Ordene usando MergeSort
        mergeSort(vetor);
        System.out.println("2. Vetor ordenado: " + Arrays.toString(vetor));

        // 3. Exiba o menor e o maior valor
        int menor = vetor[0];
        int maior = vetor[vetor.length - 1];

        System.out.println("3. Menor valor: " + menor);
        System.out.println("3. Maior valor: " + maior);

        // 4. Some todos os valores do vetor
        int soma = 0;
        for (int n : vetor) {
            soma += n;
        }
        System.out.println("4. Soma dos valores: " + soma);

        // 5. Calcule a média aritmética
        double media = (double) soma / vetor.length;
        System.out.println("5. Média dos valores: " + media);

        System.out.println("===== FIM DO EXERCÍCIO =====");
        System.out.println();
    }

    public static void main(String[] args) {

        AulaMergeSort();      // Demonstração simples
        MergeSortGrande();    // Exemplo com vetor maior
        Exercicio();          // Exercício final
    }
}
