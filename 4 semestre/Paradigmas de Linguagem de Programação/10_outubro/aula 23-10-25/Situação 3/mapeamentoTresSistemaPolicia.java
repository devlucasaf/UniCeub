/**
Paradigmas de Linguagem de Programação
Aula: 23-10-25
II Atividade 2 (POO e Diagrama de Classes)
*/

// Classe Ocorrencia
public class Ocorrencia {
    private int numero;
    private Date data;
    private String local;
    private String descricao;

    // Relacionamentos
    private List<Viatura> viaturas;   
    private List<Suspeito> suspeitos; 
    private List<Policial> policiais; 

    public void registrar() {
        System.out.println("Ocorrência registrada com sucesso!");
    }

    public void encerrar() {
        System.out.println("Ocorrência encerrada.");
    }
}

public class Policial {
    private String nome;
    private int matricula;
    private String cargo;

    private List<Ocorrencia> ocorrencias; 

    public void atenderOcorrencia(Ocorrencia o) {
        System.out.println("Policial " + nome + " atendendo ocorrência nº " + o.getNumero());
    }

}

public class Viatura {
    private String placa;
    private String modelo;
    private String estado;

    private List<Ocorrencia> ocorrencias; 

    public void deslocar(String local) {
        System.out.println("Viatura " + placa + " deslocando-se para " + local);
    }
}

public class Suspeito {
    private String nome;
    private String cpf;
    private String situacao;

    private List<Ocorrencia> ocorrencias; 

    public void prestarDepoimento() {
        System.out.println("Suspeito " + nome + " prestou depoimento.");
    }
}
