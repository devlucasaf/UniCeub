/** 
Aula: Java HashMap
Estrutura de Dados
Professor: Francisco Carlos Molina Duarte Junior
Aluno: Lucas Freitas
Data: 04-09-2024
*/

import java.util.HashMap;       // importa a classe HashMap
import java.util.Map;

public class AulaJavaHashMap {
    
    // Método principal que demonstra operações básicas com HashMap
    public static void Principal() {
        // Cria um HashMap com chave String e valor String
        HashMap<String, String> capitais = new HashMap<String, String>();
        
        // Adiciona pares chave-valor (País, Capital)
        capitais.put("Inglaterra", "Londres");
        capitais.put("Alemanha", "Berlim");
        capitais.put("Noruega", "Oslo");
        capitais.put("Camboja", "Phnom Penh");
        
        // Imprime todo o HashMap
        System.out.println(capitais);

        // Operações comentadas que podem ser utilizadas:
        // capitais.get("Inglaterra");             // Acessa o valor pela chave
        // capitais.put("Inglaterra", "xxx");      // Altera o valor de uma chave
        // capitais.remove("Inglaterra");          // Remove um item pela chave
        // capitais.clear();                       // Remove todos os itens
        // capitais.size();                        // Mostra o tamanho do HashMap
    }

    // Método que demonstra como percorrer apenas as chaves de um HashMap
    public static void LoopThroughHashMapPrintKeys() {
        HashMap<String, String> capitais = new HashMap<String, String>();
        
        // Adiciona pares chave-valor (País, Capital)
        capitais.put("Inglaterra", "Londres");
        capitais.put("Alemanha", "Berlim");
        capitais.put("Noruega", "Oslo");
        capitais.put("Camboja", "Phnom Penh");
        
        // Percorre e imprime apenas as chaves do HashMap
        for (String i : capitais.keySet()) {
            System.out.println(i);
        }
    }

    // Método que demonstra como percorrer apenas os valores de um HashMap
    public static void LoopThroughHashMapPrintValues() {
        HashMap<String, String> capitais = new HashMap<String, String>();
        
        // Adiciona pares chave-valor (País, Capital)
        capitais.put("Inglaterra", "Londres");
        capitais.put("Alemanha", "Berlim");
        capitais.put("Noruega", "Oslo");
        capitais.put("Camboja", "Phnom Penh");
        
        // Percorre e imprime apenas os valores do HashMap
        for (String i : capitais.values()) {
            System.out.println(i);
        }
    }

    // Método que demonstra como percorrer chaves e valores de um HashMap
    public static void LoopThroughHashMapPrintKeysAndValues() {
        HashMap<String, String> capitais = new HashMap<String, String>();
        
        // Adiciona pares chave-valor (País, Capital)
        capitais.put("Inglaterra", "Londres");
        capitais.put("Alemanha", "Berlim");
        capitais.put("Noruega", "Oslo");
        capitais.put("Camboja", "Phnom Penh");
        
        // Percorre as chaves e acessa os valores correspondentes
        for (String i : capitais.keySet()) {
            System.out.println("Chave: " + i + " valor: " + capitais.get(i));
        }
    }

    // Método que demonstra o uso de HashMap com tipos diferentes (String, Integer)
    public static void OtherTypes() {
        // Cria um HashMap com chave String e valor Integer
        HashMap<String, Integer> people = new HashMap<String, Integer>();
        
        // Adiciona pares chave-valor (Nome, Idade)
        people.put("John Arias", 27);
        people.put("Bernal", 21);
        people.put("Canobbio", 26);
        
        // Percorre e imprime chaves e valores
        for (String i : people.keySet()) {
            System.out.println("Chave: " + i + " valor: " + people.get(i));
        }
    }

    // Método que implementa exercícios práticos com HashMap
    public static void Exercicio() {
        // 1. Crie um HashMap vazio chamado "myMap"
        HashMap<String, String> myMap = new HashMap<>();

        // 2. Adicione uma chave "nome" e um valor "João" ao HashMap "myMap"
        myMap.put("nome", "João");
        System.out.println("2. Adicionado: nome = João");

        // 3. Verifique se o HashMap "myMap" contém a chave "nome"
        boolean contemNome = myMap.containsKey("nome");
        System.out.println("3. Contém chave 'nome'? " + contemNome);

        // 4. Adicione mais três pares chave-valor ao HashMap "myMap"
        myMap.put("idade", "25");
        myMap.put("cidade", "São Paulo");
        myMap.put("profissao", "Desenvolvedor");
        System.out.println("4. Adicionados mais três pares chave-valor");

        // 5. Obtenha o valor associado à chave "idade" do HashMap "myMap"
        String idade = myMap.get("idade");
        System.out.println("5. Valor da chave 'idade': " + idade);

        // 6. Remova a chave "nome" do HashMap "myMap"
        String valorRemovido = myMap.remove("nome");
        System.out.println("6. Chave 'nome' removida. Valor removido: " + valorRemovido);

        // 7. Verifique se o HashMap "myMap" está vazio
        boolean vazio = myMap.isEmpty();
        System.out.println("7. HashMap está vazio? " + vazio);

        // 8. Obtenha o tamanho atual do HashMap "myMap"
        int tamanho = myMap.size();
        System.out.println("8. Tamanho atual do HashMap: " + tamanho);

        // 9. Percorra todas as chaves do HashMap "myMap" e imprima cada uma delas
        System.out.println("9. Chaves do HashMap:");
        for (String chave : myMap.keySet()) {
            System.out.println("   - " + chave);
        }

        // 10. Percorra todos os valores do HashMap "myMap" e imprima cada um deles
        System.out.println("10. Valores do HashMap:");
        for (String valor : myMap.values()) {
            System.out.println("    - " + valor);
        }

        // Bônus: Mostrar todos os pares chave-valor usando EntrySet
        System.out.println("\nBônus - Todos os pares chave-valor:");
        for (Map.Entry<String, String> entry : myMap.entrySet()) {
            System.out.println("    " + entry.getKey() + " = " + entry.getValue());
        }
    }

    // Método main - ponto de entrada do programa
    public static void main(String[] args) {
        // Executa todos os métodos de demonstração
        Principal();
        LoopThroughHashMapPrintKeys();
        LoopThroughHashMapPrintValues();
        LoopThroughHashMapPrintKeysAndValues();
        OtherTypes();
        Exercicio(); // Executa os exercícios práticos
    }
}
