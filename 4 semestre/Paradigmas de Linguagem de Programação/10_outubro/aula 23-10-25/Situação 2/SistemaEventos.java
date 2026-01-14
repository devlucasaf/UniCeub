/**
Paradigmas de Linguagem de Programação
Aula: 23-10-25
II Atividade 2 (POO e Diagrama de Classes)
Situação 2 - Sistema de Gestão de Eventos
*/

import java.util.Date;

class Evento {
    private String nome;
    private String local;
    private Date data;

    public Evento(String nome, String local, Date data) {
        this.nome = nome;
        this.local = local;
        this.data = data;
    }

    public void abrirPortoes() {
        System.out.println("Portões abertos para o evento: " + nome);
    }

    public void encerrarShow() {
        System.out.println("Show encerrado: " + nome);
    }

    public String getNome() { return nome; }
    public String getLocal() { return local; }
    public Date getData() { return data; }
}

class Artista {
    private String nome;
    private String genero;

    public Artista(String nome, String genero) {
        this.nome = nome;
        this.genero = genero;
    }

    public void apresentar() {
        System.out.println("Apresentação do artista " + nome + " - Gênero: " + genero);
    }

    public String getNome() { return nome; }
}

class Cliente {
    private String nome;
    private String cpf;
    private String email;

    public Cliente(String nome, String cpf, String email) {
        this.nome = nome;
        this.cpf = cpf;
        this.email = email;
    }

    public void comprarIngresso(Evento e, int codigo, double valor, String assento) {
        Ingresso ingresso = new Ingresso(codigo, valor, assento, e, this);
        System.out.println("Ingresso comprado para o evento " + e.getNome() + " por " + nome);
    }

    public String getNome() { return nome; }
}

class Ingresso {
    private int codigo;
    private double valor;
    private String assento;
    private Evento evento;
    private Cliente cliente;

    public Ingresso(int codigo, double valor, String assento, Evento evento, Cliente cliente) {
        this.codigo = codigo;
        this.valor = valor;
        this.assento = assento;
        this.evento = evento;
        this.cliente = cliente;
    }

    public boolean validar() {
        System.out.println("Ingresso válido para o evento " + evento.getNome() + ", assento " + assento);
        return true;
    }

    public int getCodigo() { return codigo; }
}

public class SistemaEventos {
    public static void main(String[] args) {
        Evento evento = new Evento("Festival de Música", "Estádio Nacional", new Date());

        Artista artista1 = new Artista("Lucas Rock", "Rock");
        Artista artista2 = new Artista("Maria Pop", "Pop");

        Cliente cliente = new Cliente("João Silva", "123.456.789-00", "joao@email.com");

        cliente.comprarIngresso(evento, 101, 150.00, "A12");

        evento.abrirPortoes();
        artista1.apresentar();
        artista2.apresentar();
        evento.encerrarShow();

        Ingresso ingresso = new Ingresso(101, 150.00, "A12", evento, cliente);
        ingresso.validar();
    }
}
