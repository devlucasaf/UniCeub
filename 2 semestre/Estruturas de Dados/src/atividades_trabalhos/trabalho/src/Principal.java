import javax.swing.JOptionPane;
import java.util.Arrays;

public class Principal {

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

//FÓRMULA DO SELECTION SORT

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

//PIOR CASO (Lista Decrescente)

    public static void mostrarPiorCaso() {
        int[] listaCopia = lista.clone();    //copiando a lista original    
        Arrays.sort(listaCopia);               
        int[] listaDcrsc = new int[listaCopia.length];
    
        for (int i = 0; i < listaCopia.length; i++) {                          
            listaDcrsc[i] = listaCopia[listaCopia.length - 1 - i];   //colocando em ordem descrescente
        }
    
        StringBuilder listaDcrsc_2 = new StringBuilder();
        for (int i = 0; i < listaDcrsc.length; i++) {
            listaDcrsc_2.append(listaDcrsc[i]).append(" ");
             
            if ((i + 1) % 20 == 0) {              //Isso aqui é só pra organizar a tabela de números        
                listaDcrsc_2.append("\n");
            }
        }
    
        long tempoI = System.currentTimeMillis();   //Formula pra calcular o tempo de execução (ms)
        int[] listaOrd = selectionSort(listaDcrsc);  //Ordenando a lista decrescente com selection sort  
        long tempoF = System.currentTimeMillis();
    
        StringBuilder listaOrd_2 = new StringBuilder();
        for (int i = 0; i < listaOrd.length; i++) {
            listaOrd_2.append(listaOrd[i]).append(" ");
            
            if ((i + 1) % 20 == 0) {           //organizar a tabela de números       
                listaOrd_2.append("\n");
            }
        }
    
        JOptionPane.showMessageDialog(null, "Lista decrescente (pior caso):\n" + listaDcrsc_2 +  //saída
                "\n\nLista ordenada (com selection sort):\n" + listaOrd_2 +
                "\n\nTempo de execução: " + (tempoF - tempoI) + " ms");
    }
    
////////////////////////////////////////////////////////////////////////////

//MÉDIO CASO (Lista Original)

    public static void mostrarMedioCaso() {
        StringBuilder original = new StringBuilder();
        for (int i = 0; i < lista.length; i++) {
            original.append(lista[i]).append(" ");
            
            if ((i + 1) % 20 == 0) {        //organizar a tabela de números    
                original.append("\n");
            }
        }
    
        long tempoI = System.currentTimeMillis();   //Formula pra calcular o tempo de execução (ms)
        int[] listaOrd = selectionSort(lista.clone()); //ordenando a lista original com selection sort   
        long tempoF = System.currentTimeMillis();
    
        StringBuilder ordenada = new StringBuilder();
        for (int i = 0; i < listaOrd.length; i++) {
            ordenada.append(listaOrd[i]).append(" ");
            
            if ((i + 1) % 20 == 0) {         //organizar a tabela de números    
                ordenada.append("\n");
            }
        }
    
        JOptionPane.showMessageDialog(null, "Lista original (médio caso):\n" + original +  //saída
                "\n\nLista ordenada (com selection sort):\n" + ordenada +
                "\n\nTempo de execução: " + (tempoF - tempoI) + " ms");
    }
    
////////////////////////////////////////////////////////////////////////////

//MELHOR CASO (Lista Crescente)

    public static void mostrarMelhorCaso() { 
        int[] listaCopia = lista.clone();  //copiando a lista original  
        Arrays.sort(listaCopia); 
    
        StringBuilder listaCrsc = new StringBuilder();
        for (int i = 0; i < listaCopia.length; i++) {          //colocando em ordem crescente
            listaCrsc.append(listaCopia[i]).append(" ");
            
            if ((i + 1) % 20 == 0) {           //organizar a tabela de números  
                listaCrsc.append("\n");
            }
        }
    
        long tempoI = System.currentTimeMillis();     //Formula pra calcular o tempo de execução (ms)
        int[] listaOrd = selectionSort(listaCopia);   //Ordenando a lista crescente com selection sort (não vai fazer diferença)
        long tempoF = System.currentTimeMillis();
    
        StringBuilder listaOrd_2 = new StringBuilder();
        for (int i = 0; i < listaOrd.length; i++) {
            listaOrd_2.append(listaOrd[i]).append(" ");
            
            if ((i + 1) % 20 == 0) {                    //organizar a tabela de números  
                listaOrd_2.append("\n");
            }
        }
    
        JOptionPane.showMessageDialog(null, "Lista crescente(melhor caso):\n" + listaCrsc +  //saída
                "\n\nLista ordenada (com selection sort):\n" + listaOrd_2 +
                "\n\nTempo de execução: " + (tempoF - tempoI) + " ms");
    }

////////////////////////////////////////////////////////////////////////////

//if else da escolha do usuário 

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
////////////////////////////////////////////////////////////////////////////