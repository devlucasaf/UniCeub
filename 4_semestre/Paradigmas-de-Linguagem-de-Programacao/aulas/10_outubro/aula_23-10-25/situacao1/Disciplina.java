package situacao1;

public class Disciplina {
    private String  codigo;
    private String  nome;
    private int     cargaHoraria;

    public Disciplina(String codigo, String nome, int cargaHoraria) {
        this.codigo = codigo;
        this.nome = nome;
        this.cargaHoraria = cargaHoraria;
    }

    public void exibirPlanoAula() {
        System.out.println("Plano de aula da disciplina " + nome + ": Carga horária de " + cargaHoraria + " horas.");
    }

    public String getNome() { 
        return nome; 
    }
}