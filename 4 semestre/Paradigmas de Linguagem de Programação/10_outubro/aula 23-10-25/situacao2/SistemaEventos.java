/**
Paradigmas de Linguagem de Programação
Aula: 23-10-25
II Atividade 2 (POO e Diagrama de Classes)
Situação 2 - Sistema de Gestão de Eventos
*/

package situacao2;

import java.util.Date;

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
