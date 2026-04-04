package questao4.main;

import questao4.base.*;
import questao4.voadores.*;
import questao4.seres.*;
import questao4.armazenamento.*;
import questao4.tratamento.*;

public class PolimorfismoExercicio {
    public static void main(String[] args) {

        Voador[] voadores = { new Borboleta(), new Passaro(), new Aviao() };
        for (Voador v : voadores) {
            v.voar();
        }

        System.out.println("\n+=+=+=+=+=+=+=+=+=+=+=");

        Ser[] seres = { new Pessoa(), new Carro(), new Cavalo() };
        for (Ser s : seres) {
            s.mover();
        }

        System.out.println("\n+=+=+=+=+=+=+=+=+=+=+=");

        Armazenamento[] dispositivos = { new Disquete(), new HD() };
        for (Armazenamento a : dispositivos) {
            a.armazenarDados();
        }

        System.out.println("\n+=+=+=+=+=+=+=+=+=+=+=");

        Tratamento[] tratamentos = { new Seringa(), new Remedio() };
        for (Tratamento t : tratamentos) {
            t.tratar();
        }
    }
}