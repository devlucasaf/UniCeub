package atividadesTrabalhos.trabalho;

import java.util.ArrayList;
import java.util.Collections;

public class FirstJavaProject {
    public static void main(String[] args) {
        ArrayList<String> cars = new ArrayList<String> ();

        cars.add("Chevrolet");
        cars.add("Creta");
        cars.add("Hilux");

        ArrayList<Integer> num = new ArrayList<Integer> ();

        num.add(1);
        num.add(2);
        num.add(8);
        num.add(2);
        num.add(5);
        num.add(6);
        num.add(4);

        String element = cars.get(1);
        Integer numelem = num.get(0);

        Collections.sort(num);
        
        System.out.println(element);
        System.out.println(num);
        System.out.print(numelem);
    }
}

