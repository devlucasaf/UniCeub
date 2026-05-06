class Aluno {
    private String nome;
    private double nota1;
    private double nota2;

    public Aluno(String nome, double nota1, double nota2) {
        this.nome = nome;
        this.nota1 = nota1;
        this.nota2 = nota2;
    }

    public Aluno(String nome) {
        this.nome = nome;
        this.nota1 = 7.0;
        this.nota2 = 7.0;
    }

    public String getNome() {
        return nome;
    }

    public double getNota1() {
        return nota1;
    }

    public double getNota2() {
        return nota2;
    }

    public void setNota1(double valor) {
        if (valor >= 0 && valor <= 10) {
            this.nota1 = valor;
        } 
        
        else {
            System.out.println("Nota inválida: " + valor + ". Deve estar entre 0 e 10.");
        }
    }

    public void setNota2(double valor) {
        if (valor >= 0 && valor <= 10) {
            this.nota2 = valor;
        } 
        
        else {
            System.out.println("Nota inválida: " + valor + ". Deve estar entre 0 e 10.");
        }
    }

    public double calcularMedia() {
        return (nota1 + nota2) / 2.0;
    }

    public boolean estaAprovado() {
        return calcularMedia() >= 6.0;
    }

    @Override
    public String toString() {
        return "Aluno: " + nome +
                " | Notas: " + nota1 + ", " + nota2 +
                " | Média: " + String.format("%.1f", calcularMedia()) +
                " | " + (estaAprovado() ? "Aprovado" : "Reprovado");
    }
}