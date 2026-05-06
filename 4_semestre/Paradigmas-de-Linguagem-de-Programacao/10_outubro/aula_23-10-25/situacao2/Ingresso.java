package situacao2;

public class Ingresso {
    private int     codigo;
    private double  valor;
    private String  assento;
    private Evento  evento;
    private Cliente cliente;

    public Ingresso(int codigo, double valor, String assento, Evento evento, Cliente cliente) {
        this.codigo = codigo;
        this.valor = valor;
        this.assento = assento;
        this.evento = evento;
        this.cliente = cliente;
    }

    public boolean validar() {
        System.out.println("Ingresso válido para o evento " + evento.getNome() + ", assento " + assento);
        return true;
    }

    public int getCodigo() { 
        return codigo; 
    }
}