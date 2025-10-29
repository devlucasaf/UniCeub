white = "\33[1;49;37m" #branco
yellow = "\33[1;49;31m"  #amarelo
purple = "\33[1;49;32m" #roxo
cyan = "\33[1;49;36m" #ciano
nc = "\33[m" #sem cor

c5 = "\33[1;49;33m"
c6 = "\33[1;49;34m"
c7 = "\33[1;49;35m"


def linha(): #mostra linhahas
    print(f"{white}>{"-"*60}<{nc}")

class Funcionario():    #SUPERCLASSE
    def __init__(self, nome, salario=0.0):
        self.nome = nome
        self.salario = salario        

    #+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

    def get_nome (self):
        return self.nome
    
    def get_salario (self):
        return self.salario

    #+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

    def set_nome (self, novo_nome):
        self.nome = novo_nome

    def set_salario (self, novo_salario):
        self.salario = novo_salario

    #+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

    def mostra_dados(self):
        print(f"{purple}Nome: {self.nome} \nSalário: {self.salario}{nc}")
    
    #+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

    def bonificacao(self):
        bon = self.salario * 0.10
        return bon

#--------------------------------------------------------

class Gerente(Funcionario):
    def __init__(self, nome, salario, qtd_gerencia=1):
        super().__init__(nome, salario)  #HERANÇA
        self.qtd_gerencia = qtd_gerencia
    
    #+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+
    
    def get_qtd_gerencia (self):
        return self.qtd_gerencia
    
    #+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

    def set_qtd_gerencia(self, nova_qtd):
        self.qtd_gerencia = nova_qtd

    #+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+

    def mostra_dados(self):
        print(f"Nome: {self.nome}")
        print(f"Salário: {self.salario}")
        print(f"Gerencia: {self.qtd_gerencia}")
if __name__ == "__main__":

    f1 = Funcionario("Michel", 1400.00)
    f2 = Funcionario("Marcos")

    #--------------------------------------------------------

    linha()

    print(f"\n{yellow} -------------- | Funcionários | --------------{nc}\n")


    print(f"{cyan}Funcionário 1{nc}")
    print(f"Nome = {f1.get_nome()}")
    print(f"Salário: {f1.get_salario()}")
    print(f"Gerencia: {f1.get_qtd_gerencia()}")
    f1.set_nome("Vinícius")
    print(f"{cyan}\nAlterando nome do funcionário 1:")
    print(f"{purple}Nome = {f1.get_nome()} / Salário: {f1.get_salario()}{nc}")


    print(f"\n{cyan}Funcionário 2:")
    print(f"Nome = {f1.get_nome()}")
    print(f"Salário: {f1.get_salario()}")
    print(f"Gerencia: {f1.get_qtd_gerencia()}")

    print(f"{cyan}>>MOSTRA DADOS FUNCIONARIO 1<<{nc}")
    f1.mostra_dados()

    print(f"\n{cyan}BONIFICAÇÃO FUNCIONARIO 1\n{purple}10% do Salário: {f1.bonificacao()}{nc}")
    linha()
    
    #--------------------------------------------------------

    print(f"\n{yellow} --------- | Gerente | ---------{yellow}\n")

    g1 = Gerente("Barbosa", 5400.00, 5)
    
    print(f"{cyan}GERENTE:{purple}{nc}")
    print(f"Nome = {g1.get_nome()}")
    print(f"Salário: {g1.get_salario()}")
    print(f"Gerencia: {g1.get_qtd_gerencia()}")

    print(f"{cyan}>>MOSTRA DADOS GERENTE<<{nc}")
    g1.mostra_dados()

    linha()
