/**
Paradigmas de Linguagens de Programação
Data: 16-10-25
*/

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Autor autor = new Autor("José da Silva", "Brasileira");
        Editora editora = new Editora("Novatec", "São Paulo", "Brasil");

        List<Autor> autores = new ArrayList<>();
        autores.add(autor);

        Obra obra = new Obra("Programação Java", "Livro", "Português", autores, editora, 2023, 2);
        Exemplar exemplar = new Exemplar(1, obra, "Física");

        Aluno aluno = new Aluno("Lucas Freitas", "Rua A", "9999-9999", "lucas@email.com", "Engenharia", "20231001");

        exemplar.emprestar();
        exemplar.devolver();
    }
}
