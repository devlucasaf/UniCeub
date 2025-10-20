/**
Paradigmas de Linguagens de Programação
Data: 16-10-25
*/

// ===============================
// Classe Emprestimo
// ===============================
import java.time.LocalDate;

public class Emprestimo {
    private int idEmprestimo;
    private Usuario usuario;
    private Exemplar exemplar;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
    private String status; // ativo, finalizado

    public Emprestimo(int idEmprestimo, Usuario usuario, Exemplar exemplar) {
        this.idEmprestimo = idEmprestimo;
        this.usuario = usuario;
        this.exemplar = exemplar;
        this.dataEmprestimo = LocalDate.now();
        this.status = "ativo";
    }

    public void registrarEmprestimo() {
        exemplar.emprestar();
        System.out.println("Empréstimo registrado em " + dataEmprestimo);
    }

    public void finalizarEmprestimo() {
        exemplar.devolver();
        dataDevolucao = LocalDate.now();
        status = "finalizado";
        System.out.println("Empréstimo finalizado em " + dataDevolucao);
    }

    public void calcularMulta() {
        System.out.println("Cálculo de multa (exemplo)...");
    }
}
