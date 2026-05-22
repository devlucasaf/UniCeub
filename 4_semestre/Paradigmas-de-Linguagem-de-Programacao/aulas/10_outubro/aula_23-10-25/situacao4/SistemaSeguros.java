/**
Paradigmas de Linguagem de Programação
Aula: 23-10-25
II Atividade 2 (POO e Diagrama de Classes)
Situação 4 - Sistema de Seguros de Carros
*/

package situacao4;

import java.util.Date;

public class SistemaSeguros {
    public static void main(String[] args) {
        Cliente cliente = new Cliente("João Silva", "123.456.789-00");

        Modelo modelo = new Modelo("Civic");
        Carro carro = new Carro(2022, "Honda", modelo, "Preto", "ABC-1234");

        Seguro seguro = new Seguro(carro, cliente, 3500.00, new Date());

        seguro.emitirApolice();
    }
}