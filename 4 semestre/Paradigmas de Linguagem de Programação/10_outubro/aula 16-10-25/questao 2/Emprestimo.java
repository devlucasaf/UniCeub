/**
Paradigmas de Linguagens de Programação
Data: 16-10-25
*/

import java.util.Date;

public class Emprestimo {
    private Usuario usuario;
    private Exemplar exemplar;
    private Date dataEmprestimo;
    private Date dataDevolucaoPrevista;
    private Date dataDevolucaoReal;

    public Emprestimo(Usuario usuario, Exemplar exemplar, Date dataEmprestimo, Date dataDevolucaoPrevista) {
        this.usuario = usuario;
        this.exemplar = exemplar;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucaoPrevista = dataDevolucaoPrevista;
    }

    public void registrarDevolucao(Date dataDevolucaoReal) {
        this.dataDevolucaoReal = dataDevolucaoReal;
        exemplar.devolver();
    }
}
