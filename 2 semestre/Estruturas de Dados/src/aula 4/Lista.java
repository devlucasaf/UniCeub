import javax.swing.JOptionPane;
import java.util.ArrayList;

public class Lista {

    public static void main(String[] args) {
        
    	String nrElementos = JOptionPane.showInputDialog("Quantoss numeros a lista vai ter? ");

        int nElementos = Integer.valueOf(nrElementos);

        ArrayList<Integer> lista = new ArrayList<Integer>();
        
        for (int i = 0; i < nElementos; i++){
            
        	String valor = JOptionPane.showInputDialog("Informe o valor: " + i);
            
            Integer aaa = Integer.valueOf(valor);
            lista.add(aaa);
        }
        
        System.out.println(lista);
    }

}