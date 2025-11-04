import java.util.Arrays;
import javax.swing.JOptionPane;
//CÓDIGO DO MOLINA

public class Molina {
	
	public static int lista[] = {0,0,0,0,0};
	public static int listaOrdenada[] = {0,0,0,0,0};
	
	public static void main(String[] args) {
		apresentaOpcoes();
	}
	
	public static void apresentarListaOriginal() {
		String original = "";
		for (int i = 0; i < lista.length; i++) {
			original += lista[i] + " ";
		}
		JOptionPane.showMessageDialog(null, original);
		apresentaOpcoes();
	}
	
	public static void apresentarListaOrdenada() {
		String original = "";
		for (int i = 0; i < listaOrdenada.length; i++) {
			original += listaOrdenada[i] + " ";
		}
		JOptionPane.showMessageDialog(null, original);
		apresentaOpcoes();
	}
	
	public static void preencherLista() {
		
		lista[0] = 1;
		lista[1] = 5;
		lista[2] = 2;
		lista[3] = 3;
		lista[4] = 6;
		
		apresentaOpcoes();
	}
	
	public static void ordenarLista() {
		listaOrdenada = lista;
		Arrays.sort(listaOrdenada);
		apresentaOpcoes();
	}
	
	public static void apresentaOpcoes() {
		String opcao = JOptionPane.showInputDialog(
			" 1 - Preencher lista \n" +
			" 2 - Ordenar lista \n" +
			" 3 - Apresentar lista ordenda \n" +
			" 4 - Apresentar lista original \n" +
			" 5 - Sair \n" +
			" 6 - Tempo de execução do melhor caso \n"
		);
		
		if(opcao.equals("1")) {
			preencherLista();
		} else if (opcao.equals("2")) {
			ordenarLista();
		} else if (opcao.equals("3")) {
			apresentarListaOrdenada();
		} else if (opcao.equals("4")) {
			apresentarListaOriginal();
		} else if (opcao.equals("6")) {
			System.out.println("opção 6");
		} else {
			JOptionPane.showMessageDialog(null, "Opção inválida!");
		}
	}
}
