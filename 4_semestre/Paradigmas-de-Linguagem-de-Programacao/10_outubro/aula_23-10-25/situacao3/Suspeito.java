package situacao3;

public class Suspeito {
    private String nome;
    private String cpf;
    private String situacao;

    private List<Ocorrencia> ocorrencias; 

    public Suspeito(String nome, String cpf, String situacao) {
        this.nome = nome;
        this.cpf = cpf;
        this.situacao = situacao;
        this.ocorrencias = new ArrayList<>();
    }

    public void prestarDepoimento() {
        System.out.println("Suspeito " + nome + " prestou depoimento.");
    }

    public String getNome() { return nome; }
}