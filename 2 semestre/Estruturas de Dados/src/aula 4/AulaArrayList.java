/** Aula: Java ArrayList
Estrutura de Dados
*/

import java.util.*;

public class AulaArrayList {
    
    // Método principal que demonstra operações básicas com ArrayList
    public static void Principal() {
        // Cria um ArrayList de Strings para armazenar carros
        ArrayList<String> cars = new ArrayList<String>();
        // Adiciona elementos ao ArrayList
        cars.add("Volvo");
        cars.add("BMW");
        cars.add("Ford");
        cars.add("Mazda");

        // Imprime todo o ArrayList
        System.out.println(cars);

        // Operações comentadas que podem ser utilizadas:
        // cars.get(0);                // Acessa o item na posição 0
        // cars.set(0, "Opel");        // Altera um item na posição 0
        // cars.remove(0);             // Remove um item na posição 0
        // cars.clear();               // Remove todos os itens
        // cars.size();                // Verifica o tamanho da lista
    }

    // Método que demonstra como percorrer um ArrayList usando loop for tradicional
    public static void LoopThroughArrayList() {
        ArrayList<String> cars = new ArrayList<String>();
        cars.add("Volvo");
        cars.add("BMW");
        cars.add("Ford");
        cars.add("Mazda");

        // Percorre o ArrayList usando índice
        for (int i = 0; i < cars.size(); i++) {
            System.out.println(cars.get(i)); // Acessa cada elemento pelo índice
        }
    }

    // Método que demonstra como percorrer um ArrayList usando for-each
    public static void LoopThroughArrayListForEach() {
        ArrayList<String> cars = new ArrayList<String>();
        cars.add("Volvo");
        cars.add("BMW");
        cars.add("Ford");
        cars.add("Mazda");

        // Percorre o ArrayList usando for-each (mais simplificado)
        for (String i: cars)  {
            System.out.println(i);
        }
    }

    // Método que demonstra o uso de ArrayList com outros tipos (Integer)
    public static void OtherTypes() {
        // Cria um ArrayList de Integers
        ArrayList<Integer> myNumbers = new ArrayList<Integer>();
        myNumbers.add(10);
        myNumbers.add(15);
        myNumbers.add(20);
        myNumbers.add(25);

        // Percorre o ArrayList de números usando for-each
        for (int i: myNumbers)  {
            System.out.println(i);
        }
    }

    // Método que demonstra como ordenar um ArrayList de Strings
    public static void SortArrayListString() {
        ArrayList<String> cars = new ArrayList<String>();
        cars.add("Volvo");
        cars.add("BMW");
        cars.add("Ford");
        cars.add("Mazda");
        
        // Ordena o ArrayList em ordem alfabética
        Collections.sort(cars);

        // Imprime a lista ordenada
        for (String i: cars)  {
            System.out.println(i);
        }
    }

    // Método que demonstra como ordenar um ArrayList de Integers
    public static void SortArrayListInt() {
        ArrayList<Integer> myNumbers = new ArrayList<Integer>();
        myNumbers.add(33);
        myNumbers.add(15);
        myNumbers.add(20);
        myNumbers.add(34);
        myNumbers.add(8);
        myNumbers.add(12);

        // Ordena o ArrayList em ordem crescente
        Collections.sort(myNumbers);

        // Imprime a lista ordenada
        for (int i: myNumbers)  {
            System.out.println(i);
        }
    }

    // Método que testa todos os exercícios implementados
    public static void Exercicio() {
        // Teste dos exercícios
        System.out.println("=== Exercício 1 ===");
        exercicio1();

        System.out.println("\n=== Exercício 2 ===");
        ArrayList<Integer> numeros = new ArrayList<>(Arrays.asList(1, 2, 2, 3, 4, 4, 5));
        System.out.println("Números únicos: " + contarElementosUnicos(numeros));

        System.out.println("\n=== Exercício 3 ===");
        ArrayList<String> lista1 = new ArrayList<>(Arrays.asList("a", "b", "c"));
        ArrayList<String> lista2 = new ArrayList<>(Arrays.asList("b", "c", "d"));
        System.out.println("Elementos únicos: " + elementosUnicos(lista1, lista2));

        System.out.println("\n=== Exercício 4 ===");
        ArrayList<Integer> numeros2 = new ArrayList<>(Arrays.asList(5, 2, 8, 1, 9));
        System.out.println("Índice do menor número: " + indiceMenorNumero(numeros2));

        System.out.println("\n=== Exercício 5 ===");
        ArrayList<String> palavras = new ArrayList<>(Arrays.asList("java", "python", "java", "c++", "java"));
        System.out.println("Ocorrências de 'java': " + contarOcorrencias(palavras, "java"));

        System.out.println("\n=== Exercício 6 ===");
        ArrayList<Integer> numeros3 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8));
        removerNumerosPares(numeros3);
        System.out.println("Lista sem pares: " + numeros3);

        System.out.println("\n=== Exercício 7 ===");
        ArrayList<Pessoa> pessoas = new ArrayList<>();
        pessoas.add(new Pessoa("João", 25));
        pessoas.add(new Pessoa("Maria", 30));
        pessoas.add(new Pessoa("Pedro", 20));
        ordenarPorIdade(pessoas);
        System.out.println("Pessoas ordenadas por idade: " + pessoas);

        System.out.println("\n=== Exercício 8 ===");
        ArrayList<String> nomes = new ArrayList<>(Arrays.asList("Ana", "Bruno", "Carlos", "Alice", "Beatriz"));
        System.out.println("Nomes que começam com 'A': " + filtrarPorLetra(nomes, 'A'));

        System.out.println("\n=== Exercício 9 ===");
        ArrayList<Integer> numeros4 = new ArrayList<>(Arrays.asList(10, 20, 30, 40, 50));
        System.out.println("Média: " + calcularMedia(numeros4));

        System.out.println("\n=== Exercício 10 ===");
        ArrayList<String> frutas = new ArrayList<>(Arrays.asList("maçã", "banana", "laranja", "uva"));
        System.out.println("Lista reversa: " + inverterLista(frutas));
    }

    // Exercício 1: Demonstra operações básicas de ArrayList
    public static void exercicio1() {
        ArrayList<String> lista = new ArrayList<>();
        lista.add("Java");
        lista.add("Python");
        lista.add("C++");
        lista.add("JavaScript");

        // Mostra o tamanho da lista e verifica se contém elementos específicos
        System.out.println("Tamanho da lista: " + lista.size());
        System.out.println("Contém 'Java'? " + lista.contains("Java"));
        System.out.println("Contém 'Ruby'? " + lista.contains("Ruby"));
    }

    // Exercício 2: Conta elementos únicos em uma lista
    public static int contarElementosUnicos(ArrayList<?> lista) {
        // Usa HashSet para remover duplicatas automaticamente
        Set<Object> elementosUnicos = new HashSet<>(lista);
        return elementosUnicos.size(); // Retorna o número de elementos únicos
    }

    // Exercício 3: Retorna elementos únicos de duas listas combinadas
    public static <T> ArrayList<T> elementosUnicos(ArrayList<T> lista1, ArrayList<T> lista2) {
        // Combina as duas listas em um Set para remover duplicatas
        Set<T> conjunto = new HashSet<>(lista1);
        conjunto.addAll(lista2);
        return new ArrayList<>(conjunto); // Converte de volta para ArrayList
    }

    // Exercício 4: Encontra o índice do menor número em uma lista
    public static int indiceMenorNumero(ArrayList<Integer> lista) {
        if (lista.isEmpty()) {
            return -1; // Retorna -1 se a lista estiver vazia
        }

        int menor = lista.get(0); // Assume que o primeiro é o menor
        int indice = 0;

        // Percorre a lista para encontrar o menor número
        for (int i = 1; i < lista.size(); i++) {
            if (lista.get(i) < menor) {
                menor = lista.get(i);
                indice = i; // Atualiza o índice do menor número
            }
        }

        return indice;
    }

    // Exercício 5: Conta ocorrências de uma string específica na lista
    public static int contarOcorrencias(ArrayList<String> lista, String alvo) {
        int contador = 0;
        // Percorre a lista contando as ocorrências do alvo
        for (String elemento : lista) {
            if (elemento.equals(alvo)) {
                contador++;
            }
        }
        return contador;
    }

    // Exercício 6: Remove números pares de uma lista
    public static void removerNumerosPares(ArrayList<Integer> lista) {
        // Usa Iterator para remover elementos com segurança durante a iteração
        Iterator<Integer> iterator = lista.iterator();
        while (iterator.hasNext()) {
            if (iterator.next() % 2 == 0) { // Verifica se é par
                iterator.remove(); // Remove o elemento par
            }
        }
    }

    // Exercício 7: Ordena uma lista de pessoas por idade
    public static void ordenarPorIdade(ArrayList<Pessoa> lista) {
        // Usa Comparator para ordenar por idade
        lista.sort(Comparator.comparingInt(Pessoa::getIdade));
    }

    // Exercício 8: Filtra elementos que começam com uma letra específica
    public static ArrayList<String> filtrarPorLetra(ArrayList<String> lista, char letra) {
        ArrayList<String> resultado = new ArrayList<>();
        // Percorre a lista e adiciona elementos que começam com a letra especificada
        for (String elemento : lista) {
            if (!elemento.isEmpty() && elemento.charAt(0) == letra) {
                resultado.add(elemento);
            }
        }
        return resultado;
    }

    // Exercício 9: Calcula a média dos números em uma lista
    public static double calcularMedia(ArrayList<Integer> lista) {
        if (lista.isEmpty()) {
            return 0.0; // Retorna 0 se a lista estiver vazia
        }

        int soma = 0;
        // Soma todos os elementos da lista
        for (int numero : lista) {
            soma += numero;
        }

        return (double) soma / lista.size(); // Calcula e retorna a média
    }

    // Exercício 10: Inverte a ordem dos elementos em uma lista
    public static <T> ArrayList<T> inverterLista(ArrayList<T> lista) {
        ArrayList<T> resultado = new ArrayList<>();
        // Percorre a lista de trás para frente
        for (int i = lista.size() - 1; i >= 0; i--) {
            resultado.add(lista.get(i)); // Adiciona elementos na ordem inversa
        }
        return resultado;
    }

    // Classe auxiliar para o exercício 7 - representa uma pessoa com nome e idade
    static class Pessoa {
        private String nome;
        private int idade;

        public Pessoa(String nome, int idade) {
            this.nome = nome;
            this.idade = idade;
        }

        public String getNome() {
            return nome;
        }

        public int getIdade() {
            return idade;
        }

        @Override
        public String toString() {
            return nome + "(" + idade + ")"; // Formata a saída para exibição
        }
    }

    // Método main - ponto de entrada do programa
    public static void main(String[] args) {
        // Executa todos os métodos de demonstração
        Principal();
        LoopThroughArrayList();
        LoopThroughArrayListForEach();
        OtherTypes();
        SortArrayListString();
        SortArrayListInt();
        Exercicio(); // Executa os exercícios
    }
}
