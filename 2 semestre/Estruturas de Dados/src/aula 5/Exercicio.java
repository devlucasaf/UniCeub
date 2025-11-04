import java.util.HashMap;

public class Exercicio {
    public static void main(String[] args) {
        
        HashMap<String, String>myMap = new HashMap<>();
            
            myMap.put("Nome: ","João");


            if (myMap.containsValue("João")) {
                System.out.println("A lista contémm o nome João.");
            
            } 
            else {
                System.out.println("A lista NÃO contém o nome João.");
            }

            System.out.println(); //pular linha

            myMap.put("RA","22404560");
            myMap.put("Número","61987659080");
            myMap.put("Idade","18");
            
            System.out.println("Lista completa: " + myMap);
            System.out.println(); //pular linha


            myMap.remove("Nome ");
            System.out.println("Verificando se o HashMap está vazio: ");
            
            if (myMap.size() == 0){
                System.out.println("Está vazia.");

            } else {
                System.out.println("Não está vazia.");
                
            }

            System.out.println();
            System.out.println("Tamanho atual do HashMap: ");
            System.out.println(myMap.size());


            System.out.println(); //pular linha

            System.out.println("Lista das chaves: ");
            for(String i : myMap.keySet()) {
                System.out.println(i);
            }
            
            System.out.println(); //pular linha
    
            System.out.println("Lista dos valores: ");
            for(String i : myMap.values()) {
                System.out.println(i);
            }
    
            System.out.println();   //pular linha

    }
}