import java.util.Arrays;
import javax.swing.JOptionPane;

public class Main {

    public static int[] vetor = {432, 809, 213, 725, 37, 960, 578, 63, 921, 145, 689, 281, 506,
        955, 194, 374, 820, 62, 890, 485, 786, 911, 394, 178, 627, 902, 420, 579, 733, 96, 311,
        654, 250, 771, 9, 624, 712, 135, 505, 884, 445, 688, 77, 912, 721, 390, 538, 893, 470,
        679, 1, 869, 302, 946, 561, 144, 790, 422, 769, 57, 899, 308, 687, 469, 237, 630, 961,
        36, 578, 799, 180, 628, 886, 298, 835, 62, 974, 214, 518, 746, 132, 899, 243, 511, 798,
        235, 681, 61, 918, 375, 692, 993, 183, 553, 846, 24, 954, 286, 647, 129
    };

    public static void apresentaOpcoes() {
        while (true) {
            String opcao = JOptionPane.showInputDialog(
                "1 - Apresentar lista original \n" +
                "2 - Apresentar lista ordenada \n" +
                "3 - Sair \n"
            );

            if (opcao.equals("1")) {
                show_original_array();
            } else if (opcao.equals("2")) {
                show_sorted_array();
            } else if (opcao.equals("3")) {
                System.out.println("Sair");
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Opção inválida!");
            }
        }
    }
        
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void main(String[] args) {
        apresentaOpcoes();
    }
    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void show_original_array() {
        StringBuilder arrayOriginal = new StringBuilder("Array Original:\n");
        for (int i = 0; i < vetor.length; i++) {
            arrayOriginal.append(vetor[i]).append(" ");
            if ((i + 1) % 20 == 0) {
                arrayOriginal.append("\n");
            }
        }
        
        JOptionPane.showMessageDialog(null, arrayOriginal);
    }

    /////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    public static void show_sorted_array(){
        //Heap Sort
        long start = System.nanoTime();
        int[] sortedArray = Arrays.copyOf(vetor, vetor.length);
        int n = vetor.length;

        // Construir o heap
        for (int i = n / 2 - 1; i >= 0; i--) {
            aplicarHeap(sortedArray, n, i);
        }

        // Extração dos elementos da heap um por um
        for (int j = n - 1; j > 0; j--) {
            int aux = sortedArray[0];
            sortedArray[0] = sortedArray[j];
            sortedArray[j] = aux;

            aplicarHeap(sortedArray, j, 0);
        }
        
        
        // Exibir o array ordenado
        StringBuilder arrayOrdenado = new StringBuilder("Array Ordenado:\n");
        for (int i = 0; i < sortedArray.length; i++) {
            arrayOrdenado.append(sortedArray[i]).append(" ");
            if ((i + 1) % 20 == 0) {
                arrayOrdenado.append("\n");
            }
        }

        long duration = (System.nanoTime() - start);
        JOptionPane.showMessageDialog(null, arrayOrdenado+ "\n" + "Tempo para a execução: " + duration + " NanoSec");
    }

    //////////////////////////////////////////////////////////////////////////////////////////////////////
    
    private static void aplicarHeap(int[] vetor, int n, int i){
        int raiz = i;
        int esquerda = 2*i + 1;
        int direita = 2*i + 2;
        
        if (esquerda < n && vetor[esquerda] > vetor[raiz]){
            raiz = esquerda;
        }
        if (direita < n && vetor[direita] > vetor[raiz]){
            raiz = direita;
        }
        // troca
        if (raiz != i){
            int aux = vetor[i];
            vetor[i] = vetor[raiz];
            vetor[raiz] = aux;
            
            aplicarHeap(vetor, n, raiz);
        }
    }
    
}
