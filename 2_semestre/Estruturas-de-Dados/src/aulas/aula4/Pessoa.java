package aulas.aula4;

public class Pessoa {
    public String   nome;
    public int      idade;

    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    @Override
    public String toString() {
        return nome + "(" + idade + ")"; // Formata a saída para exibição
    }
}
