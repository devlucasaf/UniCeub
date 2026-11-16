package atividadestrabalhos.trabalho;

import java.util.ArrayList;
import java.util.Collections;

public class FirstJavaProject {
    public static void main(String[] args) {
        ArrayList<String> carro = new ArrayList<String> ();

        carro.add("Chevrolet");
        carro.add("Creta");
        carro.add("Hilux");

        ArrayList<Integer> numero = new ArrayList<Integer> ();

        numero.add(1);
        numero.add(2);
        numero.add(8);
        numero.add(2);
        numero.add(5);
        numero.add(6);
        numero.add(4);

        String element = carro.get(1);
        Integer numelem = numero.get(0);

        Collections.sort(numero);
        
        System.out.println(element);
        System.out.println(numero);
        System.out.print(numelem);
    }
}

