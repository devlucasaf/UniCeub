from pessoa import Pessoa, linha


# Classe Funcionario que herda de Pessoa
class Funcionario(Pessoa):

    # Método construtor
    def __init__(self, nome, idade, genero, cargo, salario, carga_horaria=40):
        super().__init__(nome, idade, genero)

        self.cargo = cargo
        self.salario = salario
        self.carga_horaria = carga_horaria

    # Getters
    def get_cargo(self):
        return self.cargo

    def get_salario(self):
        return self.salario

    def get_carga_horaria(self):
        return self.carga_horaria

    # Setters
    def set_cargo(self, cargo):
        self.cargo = cargo

    def set_salario(self, salario):
        if salario > 0:
            self.salario = salario
        else:
            print("Salário inválido, deve ser um valor positivo.")

    def set_carga_horaria(self, carga_horaria):
        if 0 < carga_horaria <= 40:
            self.carga_horaria = carga_horaria
        else:
            print("Carga horária inválida, deve estar entre 1 e 40 horas.")

    # Método específico
    def calcular_salario_mensal(self):
        return f"O salário mensal de {self.nome} é R$ {self.salario}."


if __name__ == "__main__":
    pessoa1 = Pessoa("Yuri Alberto", 25)

    linha()
    pessoa1.apresentar()

    # Teste idade
    pessoa1.set_idade(-5)
    pessoa1.set_idade(30)

    linha()
    pessoa1.apresentar()

    # Funcionários
    funcionario1 = Funcionario("Maria", 30, "Feminino", "Engenheira", 5000)

    funcionario2 = Funcionario("Carlos", 40, "Masculino", "Analista", 3000, carga_horaria=35)

    # Exibição
    linha()
    funcionario1.apresentar()

    linha()
    funcionario2.apresentar()

    # Salários
    linha()
    print(funcionario1.calcular_salario_mensal())

    linha()
    print(funcionario2.calcular_salario_mensal())

    funcionario1.set_salario(-1000)
    funcionario1.set_salario(6000)

    linha()
    print(funcionario1.calcular_salario_mensal())

    linha()
    funcionario2.set_carga_horaria(45)
    funcionario2.set_carga_horaria(38)

    print(f"Nova carga horária de "
        f"{funcionario2.get_nome()}: "
        f"{funcionario2.get_carga_horaria()} horas semanais."
    )