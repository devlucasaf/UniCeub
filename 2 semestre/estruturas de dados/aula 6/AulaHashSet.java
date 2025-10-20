// Aula: Java HashSet
// Estrutura de Dados
// Professor: Francisco Carlos Molina Duarte Junior
// Aluno: Lucas Freitas

import java.util.HashSet;      // Importa a classe HashSet
import java.util.Iterator;     // Importa a classe Iterator

public class aulaHashSet {
    
    // Método que demonstra operações básicas com HashSet
    public static void AulaHashSet() {
        // Cria um HashSet de Strings para armazenar carros
        HashSet<String> cars = new HashSet<String>();
        
        // Adiciona elementos ao HashSet (não permite duplicatas)
        cars.add("Volvo");
        cars.add("BMW");
        cars.add("Porsche");
        cars.add("Ford");
        cars.add("Mercedes");
        
        // Imprime o HashSet (ordem não é garantida)
        System.out.println(cars);

        // Operações comentadas que podem ser utilizadas:
        // cars.contains("BMW");     // Verifica se existe um item
        // cars.remove("Volvo");     // Remove um item específico
        // cars.clear();             // Remove todos os itens
        // cars.size();              // Mostra o tamanho do conjunto
    }

    // Método principal que demonstra uso do contains() para verificar elementos
    public static void Principal() {
        // Cria um HashSet de Integers
        HashSet<Integer> numbers = new HashSet<Integer>();

        // Adiciona números ao conjunto
        numbers.add(4);
        numbers.add(7);
        numbers.add(8);

        // Verifica quais números entre 1 e 10 estão no conjunto
        for(int i = 1; i <=10; i++) {
            if (numbers.contains(i)) {
                System.out.println(i + " was found in the set.");
            }
            else {
                System.out.println(i + " was not found in the set.");
            }
        }
    }

    // Método que demonstra o uso de HashSet com outros tipos (Integer)
    public static void OtherTypes() {
        // Cria um HashSet chamado "numbers" para armazenar inteiros
        HashSet<Integer> numbers = new HashSet<Integer>();
        
        // Adiciona valores no set (não aceita duplicatas)
        numbers.add(4);
        numbers.add(7);
        numbers.add(8);
        
        // Mostra quais números entre 1 e 10 estão no conjunto
        for(int i = 1; i <= 10; i++) {
            if(numbers.contains(i)) {
                System.out.println(i + " was found in the set.");
            }
            else {
                System.out.println(i + " was not found in the set.");
            }
        }
    }

    // Método que implementa exercícios práticos com HashSet
    public static void Exercicio() {
        // 1. Crie um HashSet vazio chamado "mySet" para inteiros
        HashSet<Integer> mySet = new HashSet<>();

        // 2. Adicione os números 1, 2, 3, 4 e 5 ao HashSet "mySet"
        mySet.add(1);
        mySet.add(2);
        mySet.add(3);
        mySet.add(4);
        mySet.add(5);
        System.out.println("2. mySet após adicionar elementos: " + mySet);

        // 3. Verifique se o HashSet "mySet" contém o número 3
        boolean contemTres = mySet.contains(3);
        System.out.println("3. mySet contém o número 3? " + contemTres);

        // 4. Remova o número 2 do HashSet "mySet"
        mySet.remove(2);
        System.out.println("4. mySet após remover o número 2: " + mySet);

        // 5. Verifique se o HashSet "mySet" está vazio
        boolean estaVazio = mySet.isEmpty();
        System.out.println("5. mySet está vazio? " + estaVazio);

        // 6. Crie outro HashSet chamado "otherSet" e adicione os números 4, 5, 6 e 7
        HashSet<Integer> otherSet = new HashSet<>();
        otherSet.add(4);
        otherSet.add(5);
        otherSet.add(6);
        otherSet.add(7);
        System.out.println("6. otherSet: " + otherSet);

        // 7. Retenha no HashSet "mySet" apenas os elementos presentes em "otherSet"
        // (Operação de interseção - mantém apenas elementos comuns)
        mySet.retainAll(otherSet);
        System.out.println("7. mySet após reter apenas elementos de otherSet: " + mySet);

        // 8. Adicione todos os elementos de "otherSet" ao HashSet "mySet"
        // (Operação de união - adiciona todos elementos, mantendo apenas únicos)
        mySet.addAll(otherSet);
        System.out.println("8. mySet após adicionar todos elementos de otherSet: " + mySet);

        // 9. Obtenha o tamanho atual do HashSet "mySet"
        int tamanho = mySet.size();
        System.out.println("9. Tamanho atual de mySet: " + tamanho);

        // 10. Percorra todos os elementos do HashSet "mySet" e imprima cada um deles
        System.out.println("10. Elementos de mySet:");

        // Usando Iterator (forma tradicional de percorrer coleções)
        System.out.println("   Usando Iterator:");
        Iterator<Integer> iterator = mySet.iterator();
        while (iterator.hasNext()) {
            System.out.println("   " + iterator.next());
        }

        // Usando enhanced for loop (forma mais moderna e simplificada)
        System.out.println("   Usando enhanced for loop:");
        for (Integer numero : mySet) {
            System.out.println("   " + numero);
        }
    }

    // Método main - ponto de entrada do programa
    public static void main(String[] args) {
        // Executa todos os métodos de demonstração
        AulaHashSet();      // Operações básicas com HashSet
        Principal();        // Verificação de elementos com contains()
        OtherTypes();       // Uso com tipos Integer
        Exercicio();        // Exercícios práticos
    }
}
