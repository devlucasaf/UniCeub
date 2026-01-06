"""
Lista, For e Condições
Programação para Web
4º semestre - Ciência da Computação
Aula: 11-08-25
"""

lista = []

while True:
    opcao = input("Qual opção?\n 1. Cadastro\n 2. Exibir Funcionários\n 3. Média Salarial\n 4. Salário Maior que R$ 5000\n 5. Sair: ")

    if opcao == "1":
        
        funcionario = {}
        funcionario["nome"] = input("Nome: ")
        funcionario["idade"] = int(input("Idade: "))
        funcionario["cargo"] = input("Cargo: ")
        funcionario["salario"] = float(input("Salário: R$ "))
        
        lista.append(funcionario)

    elif opcao == "2":
        for i in lista:
            print(f"Nome: {i['nome']}")
            print(f"Cargo: {i['cargo']}")
            print("------")  

    elif opcao == "3":
        media = 0  
        for i in lista:
            media += i["salario"]
        print(f"Média Salarial: R$ {media / len(lista):.2f}")

    elif opcao == "4":
        for i in lista:
            if i["salario"] > 5000:
                print(f"Nome: {i['nome']}")
                print(f"Cargo: {i['cargo']}")
                print("------")  

    elif opcao == "5":
        print("Código finalizado!")
        break 

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
