# Função auxiliar para imprimir linha decorativa
def linha():
    print("+=+="*69)  # Imprime um padrão de linha com 69 repetições de "+=+="

# Classe base Pessoa
class Pessoa:
    # Método construtor com valores padrão
    def __init__(self, nome, idade, genero="Não informado"):
        self.nome = nome  # Atributo para armazenar o nome
        self.idade = idade  # Atributo para armazenar a idade
        self.genero = genero  # Atributo para armazenar o gênero

    # Métodos getters (para obter valores)
    def get_nome(self):
        return self.nome  # Retorna o nome da pessoa

    def get_idade(self):
        return self.idade  # Retorna a idade da pessoa

    def get_genero(self):
        return self.genero  # Retorna o gênero da pessoa

    # Métodos setters (para alterar valores)
    def set_nome(self, nome):
        self.nome = nome  # Altera o nome da pessoa

    def set_idade(self, idade):
        if idade > 0:  # Validação para idade positiva
            self._idade = idade
        else:
            print("Idade inválida, deve ser um valor positivo.\n")

    def set_genero(self, genero):
        self.genero = genero  # Altera o gênero da pessoa

    # Método para exibir informações da pessoa
    def apresentar(self):
        print(f"\nNome: {self.nome}")
        print(f"Idade: {self.idade}")
        print(f"Gênero: {self.genero}\n")

# Classe Funcionario que herda de Pessoa
class Funcionario(Pessoa):
    # Método construtor com valores adicionais específicos de funcionário
    def __init__(self, nome, idade, genero, cargo, salario, carga_horaria=40):
        super().__init__(nome, idade, genero)  # Chama o construtor da classe pai
        self.cargo = cargo  # Atributo específico: cargo do funcionário
        self.salario = salario  # Atributo específico: salário do funcionário
        self.carga_horaria = carga_horaria  # Atributo específico: carga horária (padrão 40h)

    # Getters específicos para Funcionario
    def get_cargo(self):
        return self.cargo

    def get_salario(self):
        return self.salario

    def get_carga_horaria(self):
        return self.carga_horaria

    # Setters específicos para Funcionario com validações
    def set_cargo(self, cargo):
        self.cargo = cargo

    def set_salario(self, salario):
        if salario > 0:  # Valida salário positivo
            self.salario = salario
        else:
            print("Salário inválido, deve ser um valor positivo.")

    def set_carga_horaria(self, carga_horaria):
        if 0 < carga_horaria <= 40:  # Valida carga horária entre 1 e 40h
            self.carga_horaria = carga_horaria
        else:
            print("Carga horária inválida, deve estar entre 1 e 40 horas.")

    # Método específico para cálculo de salário mensal
    def calcular_salario_mensal(self):
        return f"O salário mensal de {self.nome} é {self.salario}."

# Bloco principal que executa quando o script é rodado diretamente
if __name__ == "__main__":
    # Cria instâncias de Pessoa e Funcionario para demonstração
    pessoa1 = Pessoa("Yuri Alberto", 25)
    linha()
    print(pessoa1.apresentar())  # Exibe informações da pessoa

    # Testa validação de idade
    pessoa1.set_idade(-5)  # Idade inválida
    pessoa1.set_idade(30)  # Idade válida
    linha()
    print(pessoa1.apresentar())

    # Cria dois funcionários com diferentes cargas horárias
    funcionario1 = Funcionario("Maria", 30, "Feminino", "Engenheira", 5000)
    funcionario2 = Funcionario("Carlos", 40, "Masculino", "Analista", 3000, carga_horaria=35)

    # Exibe informações dos funcionários
    linha()
    print(funcionario1.apresentar())
    linha()
    print(funcionario2.apresentar())

    # Exibe salários mensais
    linha()
    print(funcionario1.calcular_salario_mensal())
    linha()
    print(funcionario2.calcular_salario_mensal())

    # Testa validação de salário
    funcionario1.set_salario(-1000)  # Salário inválido
    funcionario1.set_salario(6000)  # Salário válido
    linha()
    print(funcionario1.calcular_salario_mensal())
    linha()

    # Testa validação de carga horária
    funcionario2.set_carga_horaria(45)  # Carga horária inválida
    funcionario2.set_carga_horaria(38)  # Carga horária válida
    print(f"Nova carga horária de {funcionario2.get_nome()}: {funcionario2.get_carga_horaria()} horas semanais.")
