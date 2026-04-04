package questao4.armazenamento;

import questao4.base.Armazenamento;

public class Disquete extends Armazenamento {
    @Override
    public void armazenarDados() {
        System.out.println("O disquete armazena dados magneticamente.");
    }
}
