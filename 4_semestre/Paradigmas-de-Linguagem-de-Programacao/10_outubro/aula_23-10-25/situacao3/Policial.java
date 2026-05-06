package situacao3;

import java.util.ArrayList;

public class Policial {
    private String nome;
    private String cargo;
    private int matricula;
    

    private List<Ocorrencia> ocorrencias; 

    public Policial(String nome, String cargo, int matricula) {
        this.nome = nome;
        this.cargo = cargo;
        this.matricula = matricula;
        this.ocorrencias = new ArrayList<>();
    }

    public void atenderOcorrencia(Ocorrencia o) {
        ocorrencias.add(o);
        System.out.println("Policial " + nome + " atendendo ocorrência nº " + o.getNumero());
    }

    public String getNome() { 
        return nome; 
    }
}