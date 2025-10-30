class Estudante {
    String nome;
    String curso;
    String matricula;
    double nota;

    Estudante(String nome, String curso, String matricula, double nota) {
        this.nome = nome;
        this.curso = curso;
        this.matricula = matricula;
        this.nota = nota;
    }
}

public class Main {
    public static void main(String[] args) {
        Estudante estudante1 = new Estudante("Lucas", "Computação", "202501", 9.5);
        Estudante estudante2 = new Estudante("Ana", "Engenharia", "202502", 8.7);
        Estudante estudante3 = new Estudante("Rafael", "Direito", "202503", 7.9);

        // Parte 2.2 abaixo
        System.out.println(estudante1.nome + " está no curso de " + estudante1.curso + " e tem nota " + estudante1.nota);
        System.out.println(estudante2.nome + " está no curso de " + estudante2.curso + " e tem nota " + estudante2.nota);
        System.out.println(estudante3.nome + " está no curso de " + estudante3.curso + " e tem nota " + estudante3.nota);
    }
}

