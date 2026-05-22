package questao4.tratamento;

import questao4.base.Tratamento;

public class Remedio extends Tratamento {
    @Override
    public void tratar() {
        System.out.println("O remédio trata o paciente quando ingerido.");
    }
}
