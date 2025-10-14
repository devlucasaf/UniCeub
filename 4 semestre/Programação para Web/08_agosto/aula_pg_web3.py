"""
Lista, For e Condições
Programação para Web
Professor: Felippe Pires Ferreira
Aula: 11-08-25
"""

# Lista vazia para armazenar os funcionários
lista = []

# Loop infinito para o menu interativo
while True:
    # Menu de opções
    opcao = input("Qual opção?\n 1. Cadastro\n 2. Exibir Funcionários\n 3. Média Salarial\n 4. Salário Maior que R$ 5000\n 5. Sair: ")

    # Opção 1 - Cadastrar novo funcionário
    if opcao == "1":
        # Dicionário para armazenar os dados do funcionário
        funcionario = {}
        funcionario["nome"] = input("Nome: ")
        funcionario["idade"] = int(input("Idade: "))
        funcionario["cargo"] = input("Cargo: ")
        funcionario["salario"] = float(input("Salário: R$ "))
        # Adiciona o funcionário à lista
        lista.append(funcionario)

    # Opção 2 - Exibir todos os funcionários cadastrados
    elif opcao == "2":
        for i in lista:
            print(f"Nome: {i['nome']}")
            print(f"Cargo: {i['cargo']}")
            print("------")  # Linha separadora

    # Opção 3 - Calcular e exibir a média salarial
    elif opcao == "3":
        media = 0  # Variável para acumular os salários
        for i in lista:
            media += i["salario"]
        # Calcula e exibe a média (soma dos salários / quantidade de funcionários)
        print(f"Média Salarial: R$ {media / len(lista):.2f}")

    # Opção 4 - Exibir funcionários com salário acima de R$ 5000
    elif opcao == "4":
        for i in lista:
            if i["salario"] > 5000:
                print(f"Nome: {i['nome']}")
                print(f"Cargo: {i['cargo']}")
                print("------")  # Linha separadora

    # Opção 5 - Sair do programa
    elif opcao == "5":
        print("Código finalizado!")
        break  # Encerra o loop while

    # Mensagem para opção inválida
    else:
        print("Não existe esta opção: Tente novamente!")

# -------------------------------------------------------------------------------------------------------------------

#Exercício Classes: Conta Bancaria

class ContaBancaria():
    def __init__(self, numero_conta= str, titular=str, saldo=bool):
        self.numero_conta = numero_conta
        self.titular = titular
        self.saldo = saldo

    def deposito(self):
        valor = float(input("Digite um valor a ser depositado: "))
        print("Valor depositado!")
        return valor

    def sacar(self):
        saque = float(input("ID==Digite um valor a ser sacar: R$ "))
        saque -= self.saldo
        print(f"Você tem agora> R$ {self.saldo}")

    def exibir_saldo(self):
        print(f"Seu saldo atual é de: R$ {self.saldo:.2f}")

if __name__ == "__main__":
    banco = ContaBancaria("1", "Lucas", 0)

    banco.deposito()
    banco.sacar()

# -------------------------------------------------------------------------------------------------------------------

#Exercício Classes: Retângulo

class Retangulo:
    def __init__(self, x1, x2, y1, y2):
        # abs retorna o valor absoluto do número
        self.base = abs(y1 - y2)
        self.altura = abs(x2 - x1)

    def area(self):
        return self.base * self.altura

    def perimetro(self):
        return (self.base + self.altura) * 2

if __name__ == "__main__":
    retangulo = Retangulo(10,5,10,5)

    print(retangulo.area())
    print(retangulo.perimetro())

# -------------------------------------------------------------------------------------------------------------------

class Pessoa:
    def __init__(self, nome):
        self.nome = nome

class Funcionario(Pessoa):
    def __init__(self, nome, cargo):
        super().__init__(nome, cargo)
        self.cargo = cargo

    def apresentar(self):
        print(f"Meu nome é: {self.nome} e o meu cargo é de {self.cargo}")

if __name__ == "__main__":
    funcionario = Funcionario("João Pedro", "Técnico Administrativo")

    funcionario.apresentar()
