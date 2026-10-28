/*
Paradigmas de Linguagens de Programação
Livro Base - Capítulo 5: Programação Concorrente em Java
Página 81: Sincronização de Threads
*/

// Threads com sincronização

package sincronizacaothread;

public class ThreadSincronizada {
    public static void main(String[] args) {
        NumeroSincronizado  h = new NumeroSincronizado();
        ProduzirInteiro     p = new ProduzirInteiro(h);
        ConsumirInteiro     c = new ConsumirInteiro(h);

        p.start();
        c.start();
    }
}