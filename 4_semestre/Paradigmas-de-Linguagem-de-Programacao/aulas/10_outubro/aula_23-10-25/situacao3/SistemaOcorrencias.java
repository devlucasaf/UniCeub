/**
Paradigmas de Linguagem de Programação
Aula: 23-10-25
II Atividade 2 (POO e Diagrama de Classes)
Situação 3 - Sistema de Ocorrência Policial
*/

package situacao3;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

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
