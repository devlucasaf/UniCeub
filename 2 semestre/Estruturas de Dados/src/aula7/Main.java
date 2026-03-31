package aula7;

import java.util.HashSet;

public class Main {
    public static void main(String[] args) {
        HashSet<String> cars = new HashSet<String>();
        cars.add("Volvo");
        cars.add("BMW");
        cars.add("Ford");
        cars.add("BMW");
        cars.add("Mazda");
        System.out.println(cars);
        //Verificar se existe um item
        cars.contains("Mazda");
        //Remove an Item
        cars.remove("Volvo");
        //Remove all
        cars.clear();
        //ArrayList Size
        cars.size();
    }
}
