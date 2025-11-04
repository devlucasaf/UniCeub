public class insertSort {
    /*função que organiza o array usando insertion */
    void organizar(int lista[]) {
        int tamanho = lista.length;
        for (int i = 1; i < tamanho; ++i) {
            int chave = lista[i];
            int j = i - 1;
            /*move os elementos do array lista, que são maiores que o valor chave
         * para uma posição para frente da sua posição atual*/
            while (j >= 0 && lista[j] > chave) {
                lista[j + 1] = lista[j];
                j = j - 1;
            }
            lista[j + 1] = chave;
        }

    }

    /*literalmente e uma função pra dar o print com cada valor em uma linha */
    static void printLista(int lista[]) {
        int tamanho = lista.length;
        for (int i = 0; i < tamanho; ++i) {
            System.out.println(lista[i] + " ");
        }

        System.out.println();
    }

    public static void main(String args[]) {
        /*variavel da lista com os valores que prof pediu*/
        int lista[] = {432, 809, 213, 725, 37, 960, 578, 63, 921, 145,
            689, 281, 506, 955, 194, 374, 820, 62, 890, 485, 786, 911,
            394, 178, 627, 902, 420, 579, 733, 96, 311, 654, 250, 771,
            9, 624, 712, 135, 505, 884, 445, 688, 77, 912, 721, 390,
            538, 893, 470, 679, 1, 869, 302, 946, 561, 144, 790, 422,
            769, 57, 899, 308, 687, 469, 237, 630, 961, 36, 578, 799,
            180, 628, 886, 298, 835, 62, 974, 214, 518, 746, 132, 899,
            243, 511, 798, 235, 681, 61, 918, 375, 692, 993, 183, 553,
            846, 24, 954, 286, 647, 129

        /*lista no melhor caso*/
    /*int lista[] = {1, 9, 24, 36, 37, 57, 61, 62, 62, 63, 77, 96, 129, 132, 135, 144, 145, 178, 
            180, 183, 194, 213, 214, 235, 237, 243, 250, 281, 286, 298, 302, 308, 311, 374, 
            375, 390, 394, 420, 422, 432, 445, 469, 470, 485, 505, 506, 511 , 518, 538, 
            553, 561, 578, 578, 579, 624, 627, 628, 630, 647, 654, 679, 681, 687, 688, 
            689, 692, 712, 721, 725, 733, 746, 769, 771, 786, 790, 798, 799, 809, 820, 
            835, 846, 869, 884, 886, 890, 893, 899, 899, 902, 911, 912, 918, 921, 946, 
            954, 955, 960, 961, 974, 993}
         */

/*lista no pior caso*/
/*int lista[] = {993, 974, 961, 960, 955, 954, 946, 921, 918, 912, 911, 902, 899, 899, 893, 
        890, 886, 884, 869, 846, 835, 820, 809, 799, 798, 790, 786, 771, 769, 746, 
        733, 725, 721, 712, 692, 689, 688, 687, 681, 679, 654, 647, 630, 628, 627, 
        624, 579, 578, 578, 561, 553, 538, 518, 511, 506, 505, 485, 470, 469, 445, 
        432, 422, 420, 394, 390, 375, 374, 311, 308, 302, 298, 286, 281, 250, 243, 
        237, 235, 214, 213, 194, 183, 180, 178, 145, 144, 135, 132, 129, 96, 77, 63, 
        62, 62, 61, 57, 37, 36, 24, 9, 1}
         */
        };

        /*o tempo inicial, a função para organizar a lista, tempo final */
        long tempoInicial = System.nanoTime();
        Insertion_sort ob = new Insertion_sort();
        ob.organizar(lista);
        long tempoFinal = System.nanoTime();

        /*o print de tudo */
        printLista(lista);
        System.out.println("Tempo de execução: " + (tempoFinal - tempoInicial) + " nano segundos");
    }
}
