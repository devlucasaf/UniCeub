import java.util.HashMap;

public class UsandoFor {

    public static void main(String[] args) {
        
        HashMap<String, String> capitais = new HashMap<>();
        capitais.put("Inglaterra ", " Londres");
        capitais.put("Brasil ", " Brasília");
        capitais.put("Argentina ", " Buenos Aires");

        System.out.println();
        System.out.println("Lista completa: " + capitais);
        System.out.println();

    
        System.out.println("Lista dos paísess: ");
        for(String i : capitais.keySet()) {
            System.out.println(i);
        }
        
        System.out.println();

        System.out.println("Lista das capitais: ");
        for(String i : capitais.values()) {
            System.out.println(i);
        }

        System.out.println();

    }
}