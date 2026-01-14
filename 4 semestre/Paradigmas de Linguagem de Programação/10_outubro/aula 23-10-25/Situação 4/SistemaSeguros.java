/**
Paradigmas de Linguagem de Programação
Aula: 23-10-25
II Atividade 2 (POO e Diagrama de Classes)
Situação 4 - Sistema de Seguros de Carros
*/

import java.util.Date;

class Cliente {
    private String nome;
    private String cpf;

    public Cliente(String nome, String cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() { return nome; }
    public String getCpf() { return cpf; }
}

class Modelo {
    private String nome;

    public Modelo(String nome) {
        this.nome = nome;
    }

    public String getNome() { 
        return nome; 
    }
}

class Carro {
    private int ano;
    private String marca;
    private Modelo modelo;
    private String cor;
    private String placa;

    public Carro(int ano, String marca, Modelo modelo, String cor, String placa) {
        this.ano = ano;
        this.marca = marca;
        this.modelo = modelo;
        this.cor = cor;
        this.placa = placa;
    }

    public String getPlaca() { 
        return placa; 
    }
    public String getDescricao() {
        return ano + " " + marca + " " + modelo.getNome() + " (" + cor + ")";
    }
}

class Seguro {
    private Carro carro;
    private Cliente cliente;
    private double valor;
    private Date vigencia;

    public Seguro(Carro carro, Cliente cliente, double valor, Date vigencia) {
        this.carro = carro;
        this.cliente = cliente;
        this.valor = valor;
        this.vigencia = vigencia;
    }

    public void emitirApolice() {
        System.out.println("Apólice emitida para " + cliente.getNome() +
            " - Carro: " + carro.getDescricao() +
            " - Valor: R$" + valor +
            " - Vigência até: " + vigencia);
    }
}

public class SistemaSeguros {
    public static void main(String[] args) {
        Cliente cliente = new Cliente("João Silva", "123.456.789-00");

        Modelo modelo = new Modelo("Civic");
        Carro carro = new Carro(2022, "Honda", modelo, "Preto", "ABC-1234");

        Seguro seguro = new Seguro(carro, cliente, 3500.00, new Date());

        seguro.emitirApolice();
    }
}
