package aulas.aula6;

import javax.swing.JOptionPane;

public class Main {
    public static void main(String[] args) {
        String numero1 = JOptionPane.showInputDialog("Informe primeiro número");
        String numero2 = JOptionPane.showInputDialog("Informe segundo número");
        int resultado = (Integer.valueOf(numero1) + Integer.valueOf(numero2));
        JOptionPane.showMessageDialog(null, resultado);
    }
}

