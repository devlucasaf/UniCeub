package aulas.aula4;

import javax.swing.JOptionPane;
import java.util.ArrayList;

public class Lista {
    public static void main(String[] args) {

        String qntdNumeroElementosLista = JOptionPane.showInputDialog("Quantos numeros a lista vai ter? ");

        int numeroElementos = Integer.valueOf(qntdNumeroElementosLista);

        ArrayList<Integer> lista = new ArrayList<Integer>();

        for (int i = 0; i < numeroElementos; i++) {
            String valor = JOptionPane.showInputDialog("Informe o valor: " + i);

            Integer aaa = Integer.valueOf(valor);
            lista.add(aaa);
        }

        System.out.println(lista);
    }

}