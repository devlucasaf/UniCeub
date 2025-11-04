import javax.swing.JOptionPane;
import java.util.ArrayList;

public class Lista{
    
    public static void main(String[] args) {
            
        String nrElementos = JOptionPane.showInputDialog("Quantos números a lista vai ter? ");

        int nElementos = Integer.valueOf(nrElementos);

        ArrayList<Integer> lista = new ArrayList<Integer>();
        
        for (int i = 0; i < nElementos; i++){
            
            String valor = JOptionPane.showInputDialog("Informe o valor: " + i);
            
            Integer num = Integer.valueOf(valor);
            lista.add(num);
        }
        
        System.out.println("Números da lista: " + lista); //imprime a lista
        System.out.println("Tamanho da lista: " + lista.size()); //tamanho da lista

        lista.remove(1); //removendo o número 20 pelo index

        System.out.println("Lista Atualizada: " + lista); //lista sem o 20

        if (lista.contains(40)) {

            System.out.println ("O número 40 foi encontrado na lista.");            
        } else {

            System.out.println ("O número 40 não foi encontrado na lista.");
        }

        int x = 4;
        int y = 5;
        int z = x + y;

        System.out.println(z / (y +(z-y)));
    }
}


/*1. Estrutura de Dados
ArrayList: Armazena uma lista ordenada de elementos. Permite duplicatas.
HashMap: Armazena pares de chave-valor. Cada chave é única e associada a um valor.
HashSet: Armazena apenas valores únicos. Não há associação de chave.

2. Implementação
ArrayList: Implementa a interface List.
HashMap: Implementa a interface Map.
HashSet: Implementa a interface Set.

3. Duplicidade
ArrayList: Permite elementos duplicados.
HashMap: Permite chaves duplicadas (sobrescreve o valor) e valores duplicados.
HashSet: Não permite elementos duplicados.

4. Performance
ArrayList: Oferece tempo de acesso O(1) para leitura (índice), mas O(n) para inserções e remoções, especialmente no início ou meio da lista.
HashMap: Oferece tempo de acesso médio O(1) para operações de inserção, remoção e busca.
HashSet: Também oferece tempo de acesso médio O(1) para inserção, remoção e busca.

5. Métodos
ArrayList: Métodos como add(), get(), remove(), contains(), size().
HashMap: Métodos como put(), get(), remove(), containsKey(), containsValue().
HashSet: Métodos como add(), remove(), contains(), size().

6. Iteração
ArrayList: Pode ser iterado sobre seus elementos em ordem de inserção.
HashMap: Pode ser iterado sobre chaves, valores ou pares de chave-valor.
HashSet: É iterado apenas sobre seus valores.

7. Uso
ArrayList: Melhor para armazenar uma lista ordenada de elementos, onde a ordem e duplicatas são permitidas.
HashMap: Ideal para quando você precisa associar valores a chaves.
HashSet: Útil quando você precisa armazenar uma coleção de itens únicos.

8. Ordem dos Elementos
ArrayList: Mantém a ordem de inserção. Os elementos são armazenados na sequência em que foram adicionados.
HashMap: Não garante ordem dos pares chave-valor. A ordem pode mudar ao longo do tempo.
HashSet: Não garante ordem de inserção. A ordem dos elementos pode ser aleatória.

9. Capacidade e Redimensionamento
ArrayList: Tem uma capacidade inicial e é redimensionado automaticamente conforme os elementos são adicionados, o que pode envolver cópias de dados.
HashMap: Também possui uma capacidade inicial e aumenta o tamanho da tabela hash quando a carga atinge um determinado limite, o que pode causar rehashing.
HashSet: Funciona de forma similar ao HashMap, pois é baseado em uma tabela hash e redimensiona conforme necessário.

10. Armazenamento Interno
ArrayList: Internamente usa um array para armazenar elementos, o que permite acesso rápido por índice.
HashMap: Usa uma tabela hash que associa chaves a valores, permitindo busca eficiente baseada na hash das chaves.
HashSet: Também usa uma tabela hash, mas apenas armazena os valores, tratando os elementos como chaves em uma implementação de HashMap.

11. Uso de Memória
ArrayList: Pode ser menos eficiente em termos de memória se o número de elementos frequentemente muda, pois a alocação de arrays pode ser custosa.
HashMap: Pode usar mais memória devido ao armazenamento de pares chave-valor e à estrutura de hash.
HashSet: Semelhante ao HashMap, mas usa menos memória porque armazena apenas os valores.

12. Sincronização
ArrayList: Não é thread-safe. Para acesso concorrente, é necessário sincronizar externamente.
HashMap: Também não é thread-safe. Usar Collections.synchronizedMap() pode ser uma opção.
HashSet: Não é thread-safe. Para acesso concorrente, deve-se utilizar Collections.synchronizedSet().

13. Tipos de Dados
ArrayList: Pode armazenar qualquer tipo de objeto, incluindo tipos primitivos (através de suas classes wrapper).
HashMap: Pode armazenar qualquer tipo de objeto como chave e valor, mas as chaves devem ser de tipos que implementam hashCode() e equals().
HashSet: Pode armazenar qualquer tipo de objeto, mas também deve garantir que os elementos são únicos.*/