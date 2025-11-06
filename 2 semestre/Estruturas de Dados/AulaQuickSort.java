// Aula: Java QuickSort
// Estrutura de Dados

import java.util.Arrays;

public class AulaQuickSort {
    
    // MÉTODO PRINCIPAL DO QUICKSORT
    
    public static void quickSort(int[] array, int inicio, int fim) {
        if (inicio < fim) {

            // Encontra o índice do pivô após particionar
            int indicePivo = particionar(array, inicio, fim);

            // Ordena recursivamente as duas metades
            quickSort(array, inicio, indicePivo - 1);
            quickSort(array, indicePivo + 1, fim);
        }
    }
    
    // MÉTODO DE PARTICIONAMENTO
    
    public static int particionar(int[] array, int inicio, int fim) {

        int pivo = array[fim]; // Usando o último elemento como pivô
        int i = inicio - 1;

        for (int j = inicio; j < fim; j++) {

            // Se o elemento atual for menor que o pivô, troca
            if (array[j] < pivo) {
                i++;

                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        // Coloca o pivô na posição correta
        int temp = array[i + 1];
        array[i + 1] = array[fim];
        array[fim] = temp;

        return i + 1; // Retorna a posição final do pivô
    }

    
    // Demonstração básica do funcionamento
    
    public static void AulaQuickSort() {

        int[] numeros = {9, 4, 7, 3, 10, 2};

        System.out.println("Vetor original: " + Arrays.toString(numeros));

        quickSort(numeros, 0, numeros.length - 1);

        System.out.println("Vetor ordenado com QuickSort: " + Arrays.toString(numeros));
        System.out.println();
    }

    
    // Exemplo com vetor maior
    
    public static void QuickSortGrande() {

        int[] valores = {15, 3, 8, 22, 1, 9, 12, 7, 19, 6};

        System.out.println("Vetor original: " + Arrays.toString(valores));

        quickSort(valores, 0, valores.length - 1);

        System.out.println("Vetor ordenado (grande): " + Arrays.toString(valores));
        System.out.println();
    }

    
    // Exercício
    
    public static void Exercicio() {

        System.out.println("= EXERCÍCIO QUICKSORT =");

        // 1. Crie um vetor com os seguintes números:
        int[] vetor = {30, 12, 5, 7, 40, 25, 1};

        System.out.println("1. Vetor original: " + Arrays.toString(vetor));

        // 2. Ordene usando QuickSort
        quickSort(vetor, 0, vetor.length - 1);

        System.out.println("2. Vetor ordenado: " + Arrays.toString(vetor));

        // 3. Exibir menor e maior valor
        int menor = vetor[0];
        int maior = vetor[vetor.length - 1];

        System.out.println("3. Menor valor: " + menor);
        System.out.println("3. Maior valor: " + maior);

        // 4. Somar todos os valores do vetor
        int soma = 0;
        for (int n : vetor) {
            soma += n;
        }
        System.out.println("4. Soma dos valores: " + soma);

        // 5. Calcular a média
        double media = (double) soma / vetor.length;
        System.out.println("5. Média dos valores: " + media);

        System.out.println("= FIM DO EXERCÍCIO =");
        System.out.println();
    }
    
    public static void main(String[] args) {

        AulaQuickSort();     // Demonstração simples
        QuickSortGrande();   // Exemplo com vetor maior
        Exercicio();         // Exercício final
    }
}
