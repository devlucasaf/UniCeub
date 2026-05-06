/*
Paradigmas de Linguagens de Programação
Livro Base - Capítulo 5: Programação Concorrente em Java
Página 81: Sincronização de Threads
*/

package sincronizacao;

public class SharedCell {

    public static void main(String[] args) {
        HoldIntegerUnsynchronized   h = new HoldIntegerUnsynchronized();
        ProduceInteger              p = new ProduceInteger(h);
        ConsumeInteger              c = new ConsumeInteger(h);

        p.start();
        c.start();
    }
}