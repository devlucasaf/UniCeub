import javax.swing.JOptionPane;
import java.util.Arrays;

public class Principal_NanoS {

    public static void main(String[] args) {
        mostraOpcoes();
    }

    public static int[] lista = {
        432, 809, 213, 725, 37, 960, 578, 63, 921, 145, 689, 281, 506, 955, 194, 374, 820, 62,
        890, 485, 786, 911, 394, 178, 627, 902, 420, 579, 733, 96, 311, 654, 250, 771, 9, 624,
        712, 135, 505, 884, 445, 688, 77, 912, 721, 390, 538, 893, 470, 679, 1, 869, 302, 946,
        561, 144, 790, 422, 769, 57, 899, 308, 687, 469, 237, 630, 961, 36, 578, 799, 180, 628,
        886, 298, 835, 62, 974, 214, 518, 746, 132, 899, 243, 511, 798, 235, 681, 61, 918, 375,
        692, 993, 183, 553, 846, 24, 954, 286, 647, 129
    };


    ////////////////////////////////////////////////////////////////////////////
    // FORMULA SELECTION SORT
    
    public static int[] selectionSort(int[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < n; j++) {
                if (array[j] < array[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = array[minIndex];
            array[minIndex] = array[i];
            array[i] = temp;
        }
        return array;
    }

    ////////////////////////////////////////////////////////////////////////////
    // FORMULA MEDIR O TEMPO EM NANO SEGUNDOS
    
    public static long medirTempo(Runnable metodo, int repeticoes) {
        long soma = 0;
        for (int i = 0; i < repeticoes; i++) {
            long inicio = System.nanoTime();
            metodo.run();
            long fim = System.nanoTime();
            soma += (fim - inicio);
        }
        return soma / repeticoes;
    }


    ////////////////////////////////////////////////////////////////////////////
    // PIOR CASO

    public static void mostrarPiorCaso() {
        int[] listaCopia = lista.clone();
        Arrays.sort(listaCopia);
        int[] listaDcrsc = new int[listaCopia.length];

        for (int i = 0; i < listaCopia.length; i++) {
            listaDcrsc[i] = listaCopia[listaCopia.length - 1 - i];
        }

        long tempoMedio = medirTempo(() -> selectionSort(listaDcrsc.clone()), 10);

        JOptionPane.showMessageDialog(null, "Lista decrescente (pior caso):\n" + formatarLista(listaDcrsc) +
                "\n\nLista ordenada (com selection sort):\n" + formatarLista(selectionSort(listaDcrsc.clone())) +
                "\n\nTempo médio de execução: " + tempoMedio + " ns");
    }


    ////////////////////////////////////////////////////////////////////////////
    // MEDIO CASO

    public static void mostrarMedioCaso() {
        long tempoMedio = medirTempo(() -> selectionSort(lista.clone()), 10);

        JOptionPane.showMessageDialog(null, "Lista original (médio caso):\n" + formatarLista(lista) +
                "\n\nLista ordenada (com selection sort):\n" + formatarLista(selectionSort(lista.clone())) +
                "\n\nTempo médio de execução: " + tempoMedio + " ns");
    }


    ////////////////////////////////////////////////////////////////////////////
    // MELHOR CASO

    public static void mostrarMelhorCaso() {
        int[] listaCopia = lista.clone();
        Arrays.sort(listaCopia);

        long tempoMedio = medirTempo(() -> selectionSort(listaCopia.clone()), 10);

        JOptionPane.showMessageDialog(null, "Lista crescente (melhor caso):\n" + formatarLista(listaCopia) +
                "\n\nLista ordenada (com selection sort):\n" + formatarLista(selectionSort(listaCopia.clone())) +
                "\n\nTempo médio de execução: " + tempoMedio + " ns");
    }


    ////////////////////////////////////////////////////////////////////////////
    // FORMATA LISTA PARA EXIBIÇÃO
    public static String formatarLista(int[] array) {
        StringBuilder sb = new StringBuilder();
        int contador = 0;

        for (int i = 0; i < array.length; i++) {
            sb.append(array[i]);
            contador++;

            if (contador == 20) { // Quebra de linha a cada 20 elementos
                sb.append("\n");
                contador = 0;
            } else if (i != array.length - 1) {
                sb.append(", ");
            }
        }

        return sb.toString();
    }


    ////////////////////////////////////////////////////////////////////////////
    // MENU
    public static void mostraOpcoes() {
        String opcao = JOptionPane.showInputDialog("Digite um número para ordenar a lista: \n" +
                "1 - Pior caso\n" +
                "2 - Médio caso \n" +
                "3 - Melhor caso \n" +
                "4 - Sair");

        if (opcao.equals("1")) {
            mostrarPiorCaso();
            mostraOpcoes();

        } else if (opcao.equals("2")) {
            mostrarMedioCaso();
            mostraOpcoes();

        } else if (opcao.equals("3")) {
            mostrarMelhorCaso();
            mostraOpcoes();

        } else if (opcao.equals("4")) {
            JOptionPane.showMessageDialog(null, "Saindo...");
        } else {
            JOptionPane.showMessageDialog(null, "Opção inválida!");
            mostraOpcoes();
        }
    }

}