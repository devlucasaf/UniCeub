package aulas.aula4;

import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {

        String nome = JOptionPane.showInputDialog("Digite seu nome: ");
        System.out.println(nome);

        String numero1 = JOptionPane.showInputDialog("Digite o primeiro número: ");
        String numero2 = JOptionPane.showInputDialog("Digite o segundo número: ");

        int resultado = (Integer.valueOf(numero1) + Integer.valueOf(numero2));
        JOptionPane.showMessageDialog(null, resultado);

    }
}
