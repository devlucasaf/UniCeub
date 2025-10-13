import java.util.ArrayList;
import java.util.Collections;
    
    
public class projeto1 {
    public static void main(String[] args) {
         ArrayList<String> cars = new ArrayList<String> ();
            //ArrayList<Integer> numeros = new ArrayList<Integer> ();		
        ArrayList<Integer> num = new ArrayList<Integer> ();
        num.add(1);
        num.add(2);
        num.add(8);
        num.add(2);
        num.add(5);
        num.add(6);    
        num.add(4);
        cars.add("Chevrolet");
        cars.add("Creta");
        cars.add("Hilux");
        Collections.sort(num);
        String element = cars.get(1);
        System.out.println(element);
        System.out.println(num);		
        Integer numelem = num.get(0); 
        System.out.print(numelem);
        }
}
