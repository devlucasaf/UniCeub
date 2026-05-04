package aulas.aula1;

public class Main {
    public static void main(String[] args) {
        Aluno aluno1 = new Aluno();

        aluno1.nome = "Alejandro";
        aluno1.ra = 123456;
        aluno1.email = "rs@gmail.com";

        aluno1.responderChamada();

        Aluno aluno2 = new Aluno();

        aluno2.nome = "Luiz";
        aluno2.ra = 78940;
        aluno2.email = "luiz@gmail.com";
        
        System.out.println(aluno2.nome);

    }

}