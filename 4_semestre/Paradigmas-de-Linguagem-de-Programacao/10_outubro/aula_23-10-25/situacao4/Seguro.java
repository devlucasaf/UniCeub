package situacao4;

import java.util.Date;

public class Seguro {
    private Carro   carro;
    private Cliente cliente;
    private double  valor;
    private Date    vigencia;

    public Seguro(Carro carro, Cliente cliente, double valor, Date vigencia) {
        this.carro = carro;
        this.cliente = cliente;
        this.valor = valor;
        this.vigencia = vigencia;
    }

    public void emitirApolice() {
        System.out.println("Apólice emitida para " + cliente.getNome() +
            " - Carro: " + carro.getDescricao() +
            " - Valor: R$" + valor +
            " - Vigência até: " + vigencia);
    }
}