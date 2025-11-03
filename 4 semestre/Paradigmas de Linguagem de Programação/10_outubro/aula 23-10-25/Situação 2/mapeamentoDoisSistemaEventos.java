/**
Paradigmas de Linguagem de Programação
Aula: 23-10-25
II Atividade 2 (POO e Diagrama de Classes)
*/

class Evento {
    private String nome, local;
    private Date data;
    public void abrirPortoes() { /* ... */ }
    public void encerrarShow() { /* ... */ }
}

class Artista {
    private String nome, genero;
    public void apresentar() { /* ... */ }
}

class Cliente {
    private String nome, cpf, email;
    public void comprarIngresso(Evento e) { /* ... */ }
}

class Ingresso {
    private int codigo;
    private double valor;
    private String assento;
    private Evento evento;
    private Cliente cliente;
    public boolean validar() { /* ... */ return true; }
}
