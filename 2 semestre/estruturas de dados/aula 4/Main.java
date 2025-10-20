import javax.swing.JOptionPane;

public class Main {
	
	public static void main(String[] args) {

		String nomei = JOptionPane.showInputDialog("Digite seu nomee: ");
		System.out.println(nomei);
		
		String n1 = JOptionPane.showInputDialog("Digite o N1: ");
		String n2 = JOptionPane.showInputDialog("Digite o N2: ");

		int resultado = (Integer.valueOf(n1) + Integer.valueOf(n2));
		JOptionPane.showMessageDialog(null, resultado);
		
	}
}