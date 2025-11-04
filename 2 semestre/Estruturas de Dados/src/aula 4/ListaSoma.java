import javax.swing.JOptionPane;
import java.util.ArrayList;

public class ListaSoma {

    public static void main(String[] args) {
        
    int soma = 0;
		int soma_par = 0;
		int soma_endereco_i = 0;
    int total = 0;

    String nrElementos = JOptionPane.showInputDialog("Quantoss numeros a lista vai ter? ");

        int nElementos = Integer.valueOf(nrElementos);

        ArrayList<Integer> lista = new ArrayList<Integer>();
        
        for (int i = 0; i < nElementos; i++){
            
        String valor = JOptionPane.showInputDialog("Informe o valor: " + i);
            
            Integer valor_int = Integer.valueOf(valor);
            lista.add(valor_int);

        }

        for (int i = 0; i < lista.size(); i++) {
            soma = soma + lista.get(i);

            if (lista.get(i) % 2 == 0) {
                soma_par = soma_par + lista.get(i);
            }

            if(i % 2 != 0) {
                soma_endereco_i = soma_endereco_i + lista.get(i);

            }

        }
        ArrayList<Integer> media = new ArrayList<Integer>();
        
        for (Integer i = 0; i < media.size(); i++) {
          total+= media.get(i);
        }
        Integer mediala = total/media.size();
        System.out.println(mediala);

        System.out.println(lista);
        System.out.println(soma);
        System.out.println(soma_par);
        System.out.println(soma_endereco_i);

    }

}





/*  ARRAYLIST: 

A classe ArrayList classe é um array redimensionável que pode ser encontrado no java.utilpacote.
A diferença entre um array embutido e um ArrayList em Java, é que o tamanho de um array não pode ser modificado (se você quiser adicionar ou remover elementos de/para um array, você deve criar um novo). Enquanto os elementos podem ser adicionados e removidos de um ArrayList sempre que você quiser. A sintaxe também é um pouco diferente:

import java.util.ArrayList; // import the ArrayList class
ArrayList<String> cars = new ArrayList<String>(); // Create an ArrayList 
 

ADD ITEMS:

ArrayList<String> cars = new ArrayList<String>();
cars.add("Mazda");
System.out.println(cars);


ACESSAR UM ITEM:
cars.get(0);

MUDAR UM ITEM:
cars.set(0, "Opel");

REMOVER UM ITEM:
cars.remove(0);

REMOVER TUDO;
cars.clear();

TAMANHO DO ARRAYLIST
cars.size();

nome.reverse();
---------------------------------------------------------
ORGANIZAR O ARRAYLIST:
Collections.sort(cars);  // Sort cars
    for (String i : cars) {
      System.out.println(i);
import java.util.ArrayList;
import java.util.Collections;  // Import the Collections class

public class Main {
  public static void main(String[] args) {
    ArrayList<Integer> myNumbers = new ArrayList<Integer>();
    myNumbers.add(33);
    myNumbers.add(15);
    myNumbers.add(20);
    myNumbers.add(34);
    myNumbers.add(8);
    myNumbers.add(12);

    Collections.sort(myNumbers);  // Sort myNumbers

    for (int i : myNumbers) {
      System.out.println(i);      

---------------------------------------------------------
LOOP:
public class Main {
  public static void main(String[] args) {
    ArrayList<String> cars = new ArrayList<String>();
    cars.add("Volvo");
    cars.add("BMW");
    cars.add("Ford");
    cars.add("Mazda");
    for (int i = 0; i < cars.size(); i++) {
      System.out.println(cars.get(i));
    }
  }
}
---------------------------------------------------------
LOOP FOR EACH
public class Main {
public static void main(String[] args) {
    ArrayList<String> cars = new ArrayList<String>();
    cars.add("Volvo");
    cars.add("BMW");
    cars.add("Ford");
    cars.add("Mazda");
    for (String i : cars) {
    System.out.println(i);
    }
    }
}




 */