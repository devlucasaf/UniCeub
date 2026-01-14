/*
    Paradigmas de Linguagem de Programação
    Aula: 23-10-25
    Slide 12 - 19: Princípios OO
    Exemplo: Código de Sistema de Veículos
*/

abstract class Pessoa {
    protected String nome;

    public Pessoa(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public abstract String obterRegistroFederal();
}

class PessoaFisica extends Pessoa {
    private String cpf;

    public PessoaFisica(String nome, String cpf) {
        super(nome);
        this.cpf = cpf;
    }

    @Override
    public String obterRegistroFederal() {
        return cpf;
    }
}

class PessoaJuridica extends Pessoa {
    private String cnpj;

    public PessoaJuridica(String nome, String cnpj) {
        super(nome);
        this.cnpj = cnpj;
    }

    @Override
    public String obterRegistroFederal() {
        return cnpj;
    }
}

class Carro {
    private String modelo;
    private Pessoa proprietario;

    public Carro(String modelo) {
        this.modelo = modelo;
    }

    public void setProprietario(Pessoa proprietario) {
        this.proprietario = proprietario;
    }

    public Pessoa getProprietario() {
        return proprietario;
    }

    public String getModelo() {
        return modelo;
    }
}

class Hatch extends Carro {
    public Hatch(String modelo) {
        super(modelo);
    }
}

class Seda extends Carro {
    public Seda(String modelo) {
        super(modelo);
    }
}

class Esportivo extends Carro {
    public Esportivo(String modelo) {
        super(modelo);
    }
}

public class SistemaVeiculos {
    public static void main(String[] args) {
        Pessoa joao = new PessoaFisica("João", "123.456.789-00");
        Carro carroA = new Esportivo("Ferrari F8");

        carroA.setProprietario(joao);

        System.out.println("Proprietário do carro: " + carroA.getProprietario().getNome());
        System.out.println("Modelo do carro: " + carroA.getModelo());
        System.out.println("Registro Federal: " + carroA.getProprietario().obterRegistroFederal());
    }
}
