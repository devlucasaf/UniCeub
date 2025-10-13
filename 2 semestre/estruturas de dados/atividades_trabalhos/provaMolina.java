import java.util.HashMap;
import java.util.ArrayList;

public class provaMolina {  
    public static void main(String[] args) {
    int a =2;
    int b =3;
    int resultado = somar(a,b);

    System.out.println("O resultado é:");
    System.out.print(resultado);

    int number = 5;
    int fatorial = 1;

    for(int i = 1;i <= number; i++){
        fatorial = fatorial * i;
        System.out.println(i);
    }

    int result = calculo(4);
    System.out.println(result);

    }
    //RECURSIVIDADE
    public static int somar(int a, int b){
        int resultado = a + b;
        return resultado;
    }
    public static int calculo(int n){
        if (n == 0 || n == 1){
            return 1;
        }
        else{
            return n * calculo(n - 1);
        }
    }
}

// ARRAYLIST
public class ARRAY {
	public static void main(String[] args) {
        ArrayList<String> cars = new ArrayList<String>();
    cars.add("Volvo");
    cars.add("BMW");
    cars.add("Ford");
    cars.add("Mazda");
    System.out.println(cars);
        //cars.get(0); (Access item)
        //cars.set(0, "Opel"); (change item)
        //cars.remove(0); (remove an item)
        //cars.clear(); (remove all)
        //cars.size(); (array size)
    }
}

// HASHMAP
public class Mymap {
    public static void main(String[] args) {
		int i = 0;
		HashMap<String, String> Mymap = new HashMap<>();
		Mymap.put("Nome", "João");
		Mymap.put("Idade", "24");
		Mymap.put("Altura", "0,80");
		Mymap.put("Endereço", "Brasilia");
		
		System.out.println(Mymap);
		
		if (Mymap.size() != i) {
			System.out.println("Deu bom oh");
		}
		if (Mymap.size() == i) {
			System.out.println("Tem nada aq nn doidão");
			}
		for (String p: Mymap.values()) {
			System.out.println(p);
		}	
	}	
}
