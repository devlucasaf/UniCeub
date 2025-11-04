/**
Paradigmas de Linguagens de Programação
Data: 16-10-25
*/

// ===============================
// Classe Midia
// ===============================
public class Midia {
    private String tipoMidia; // físico, digital, CD, etc.
    private String descricao;

    public Midia(String tipoMidia, String descricao) {
        this.tipoMidia = tipoMidia;
        this.descricao = descricao;
    }

    public void definirMidia(String tipoMidia, String descricao) {
        this.tipoMidia = tipoMidia;
        this.descricao = descricao;
    }

    public void consultarMidia() {
        System.out.println("Mídia: " + tipoMidia + " | Descrição: " + descricao);
    }
}
