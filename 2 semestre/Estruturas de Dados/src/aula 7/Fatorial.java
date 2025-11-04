public class Fatorial {
    public static void main(String[] args) {

        int numero = 10;
        int fatorial = 1;
    
        System.out.println("");
        System.out.println("==============");
        
        for(int i = 1; i <= numero; i++) {
            fatorial = fatorial * i;
            System.out.println(i + "! = " + fatorial);
        }

        ////////////////////////////////////////////////////
        
        fatorial = 1;
        System.out.println("==============");
        
        for(int i = numero; i >= 1; i--) {
            fatorial = fatorial * i;
        }
        System.out.println(fatorial);
        System.out.println("==============");
    }
}

/*ordenação:
Aprender a utilizar algoritmos de ordenação em Java, dada a sua devida necessidade em ocasiões específicas. Existem diversas aplicabilidades para a ordenação de dados, não só em Java, mas no mundo computacional como um todo.

Características Importantes
melhor desempenho 
melhor legibilidade de código
menor complexidade

Para analisar um algoritmo de ordenação você deve conhecer a sua complexidade no Pior Caso, Caso Médio e o melhor caso. Estas três características presentes em todos os algoritmos dizem respeito a sua velocidade dada uma situação específica. 

Notação “Big O”

Sua teoria baseia-se em ordenar os valores da esquerda para a direita, deixando os elementos lidos (a esquerda) ordenados. Este é geralmente utilizado para ordenar um pequeno número de valores, onde faz-se muito eficiente. A complexidade do código é:
Complexidade Pior Caso: O(n²)
Complexidade Caso Médio: O(n²)
Complexidade Melhor Caso: O(n)

Quando temos um caso onde a complexidade é n² devemos evitar, 
visto que a redução de desempenho deste algoritmo é exponencial. 
Porém, no seu melhor caso temos uma constante n que significa que 
a velocidade não se alterará, de acordo com o valor proporcional 
à quantidade de elementos.

Lembre-se que estamos trabalhando com proporcionalidade, 
então dizer que não varia não significa que um vetor de 10 elementos 
será ordenado na mesma velocidade de um vetor de um milhão de elementos, 
mesmo no melhor caso, porém a proporcionalidade entre a quantidade de elementos 
e sua velocidade continua constante, 
diferente do Pior Caso que aumenta ao quadrado.

O melhor caso ocorre quando todos os elementos já estão ordenados e o pior caso 
é exatamente o contrário, quando todos os elementos estão desordenados. 
Vejamos um exemplo para entender melhor essa teoria na Listagem 1.

O primeiro passo é entender o funcionamento do método insertionSort(). 
Este irá percorrer todo o vetor começando do segundo elemento e atribuindo o mesmo a uma variável chamada key.
O algoritmo começa fazendo uma iteração em todos os elementos do vetor, 
a partir do segundo elemento, por isso j=1 e não j=0.
A variável key armazena inicialmente o primeiro valor lido pelo laço for, 
que no caso será o segundo elemento do vetor. O segundo laço itera sobre os 
valores que estão antes da variável key:

=============================================

Recursividade:

é a capacidade de um programa (função ou procedimento) fazer uma ou mais chamadas a si mesmo.
Na execução de um programa recursivo, uma pilha é responsável pelo armazenamento das variáveis recursivas.
Uma função recursiva tem que seguir duas regras básicas:
--Ter uma condição de parada (para garantir que uma chamada recursiva não criará um loop infinito)
--Tornar o problema mais simples
 */