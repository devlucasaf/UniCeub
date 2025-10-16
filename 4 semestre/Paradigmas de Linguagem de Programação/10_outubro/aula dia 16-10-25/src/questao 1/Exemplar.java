/**
Paradigmas de Linguagens de Programação
Data: 16-10-25
*/

// ===============================
// Classe Exemplar
// ===============================
public class Exemplar {
    private String codigoExemplar;
    private Obra obra;
    private int anoEdicao;
    private String status; // disponível, emprestado, reservado

    public Exemplar(String codigoExemplar, Obra obra, int anoEdicao, String status) {
        this.codigoExemplar = codigoExemplar;
        this.obra = obra;
        this.anoEdicao = anoEdicao;
        this.status = status;
    }

    public void emprestar() {
        if (status.equalsIgnoreCase("disponível")) {
            status = "emprestado";
            System.out.println("Exemplar emprestado com sucesso!");
        } else {
            System.out.println("Exemplar não está disponível.");
        }
    }

    public void devolver() {
        status = "disponível";
        System.out.println("Exemplar devolvido com sucesso!");
    }

    public void verificarDisponibilidade() {
        System.out.println("Status do exemplar: " + status);
    }
}
