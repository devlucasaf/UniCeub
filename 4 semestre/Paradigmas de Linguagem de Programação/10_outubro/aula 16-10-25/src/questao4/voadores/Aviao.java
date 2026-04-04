package questao4.voadores;

import questao4.base.Voador;

public class Aviao extends Voador {
    @Override
    public void voar() {
        System.out.println("O avião voa usando motores a jato.");
    }
}