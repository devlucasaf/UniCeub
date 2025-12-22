/**
Paradigmas de Linguagens de Programação
Data: 16-10-25
*/

public class Exemplar {
    private int codigo;
    private Obra obra;
    private String midia; // física ou digital
    private boolean disponivel;

    public Exemplar(int codigo, Obra obra, String midia) {
        this.codigo = codigo;
        this.obra = obra;
        this.midia = midia;
        this.disponivel = true;
    }

    public void emprestar() {
        if (disponivel) {
            disponivel = false;
            System.out.println("Exemplar emprestado com sucesso!");
        } else {
            System.out.println("Exemplar indisponível!");
        }
    }

    public void devolver() {
        disponivel = true;
        System.out.println("Exemplar devolvido!");
    }
}
