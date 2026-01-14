/**
Paradigmas de Linguagem de Programação
Aula: 23-10-25
II Atividade 2 (POO e Diagrama de Classes)
Situação 3 - Sistema de Ocorrência Policial
*/

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

class Ocorrencia {
    private int numero;
    private Date data;
    private String local;
    private String descricao;

    private List<Viatura> viaturas;   
    private List<Suspeito> suspeitos; 
    private List<Policial> policiais; 

    public Ocorrencia(int numero, Date data, String local, String descricao) {
        this.numero = numero;
        this.data = data;
        this.local = local;
        this.descricao = descricao;
        this.viaturas = new ArrayList<>();
        this.suspeitos = new ArrayList<>();
        this.policiais = new ArrayList<>();
    }

    public void registrar() {
        System.out.println("Ocorrência nº " + numero + " registrada com sucesso!");
    }

    public void encerrar() {
        System.out.println("Ocorrência nº " + numero + " encerrada.");
    }

    public int getNumero() { return numero; }
    public String getLocal() { return local; }
    public String getDescricao() { return descricao; }

    public void adicionarViatura(Viatura v) { viaturas.add(v); }
    public void adicionarSuspeito(Suspeito s) { suspeitos.add(s); }
    public void adicionarPolicial(Policial p) { policiais.add(p); }
}

class Policial {
    private String nome;
    private int matricula;
    private String cargo;

    private List<Ocorrencia> ocorrencias; 

    public Policial(String nome, int matricula, String cargo) {
        this.nome = nome;
        this.matricula = matricula;
        this.cargo = cargo;
        this.ocorrencias = new ArrayList<>();
    }

    public void atenderOcorrencia(Ocorrencia o) {
        ocorrencias.add(o);
        System.out.println("Policial " + nome + " atendendo ocorrência nº " + o.getNumero());
    }

    public String getNome() { return nome; }
}

class Viatura {
    private String placa;
    private String modelo;
    private String estado;

    private List<Ocorrencia> ocorrencias; 

    public Viatura(String placa, String modelo, String estado) {
        this.placa = placa;
        this.modelo = modelo;
        this.estado = estado;
        this.ocorrencias = new ArrayList<>();
    }

    public void deslocar(String local) {
        System.out.println("Viatura " + placa + " deslocando-se para " + local);
    }

    public String getPlaca() { return placa; }
}

class Suspeito {
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

public class SistemaOcorrencias {
    public static void main(String[] args) {
        Ocorrencia ocorrencia = new Ocorrencia(101, new Date(), "Centro da cidade", "Roubo a mão armada");

        Policial policial = new Policial("Carlos Silva", 1234, "Investigador");
        Viatura viatura = new Viatura("ABC-1234", "SUV", "Disponível");
        Suspeito suspeito = new Suspeito("João Souza", "111.222.333-44", "Detido");

        ocorrencia.registrar();
        policial.atenderOcorrencia(ocorrencia);
        viatura.deslocar(ocorrencia.getLocal());
        suspeito.prestarDepoimento();
        ocorrencia.encerrar();
    }
}
