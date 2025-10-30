class Pessoa(object):                   # Superclasse (classe pai)
    def __init__(self, nome="", qtd_dependente=0):
        self.nome = nome                # Atributo de instância
        self.qtd_dependente = qtd_dependente
    def get_nome(self):                 # Consulta
        return self.nome
    # def set_nome(self, nv_nome):      # Altera, sem crítica
    #     self.nome = nv_nome
    def set_nome(self, nv_nome):        # Com crítica
        if type(nv_nome) == str:
            self.nome = nv_nome
        else:
            print('Erro (set_nome): tipo tem que ser str')
    def get_qtd_dependente(self):               # Sem crítica
        return self.qtd_dependente
    def set_qtd_dependente(self, qtd_dependente):
        self.qtd_dependente = qtd_dependente
    # def set_qtd_dependente(self, qtd_dependente):  # Com crítica
    #     if type(qtd_dependente) == int:
    #         self.qtd_dependente = qtd_dependente
    #     else:
    #         print("Erro: tipo deve ser inteiro.")
    def valor_extra(self):
        vl_extra = self.qtd_dependente * 100
        return vl_extra


class Professor(Pessoa):    # A subclasse Professor herda da superclasse Pessoa
    def __init__(self, nome="", qtd_dependente=0, qtd_turma=0, valor_turma=0):
        super().__init__(nome, qtd_dependente)  # Chama construtor da superclasse
        self.qtd_turma = qtd_turma
        self.valor_turma = valor_turma
    def get_qtd_turma(self):
        return self.qtd_turma
    # def set_qtd_turma(self, nova_qtd_turma):  # Solução 1, sem crítica
    #     self.qtd_turma = nova_qtd_turma
    def set_qtd_turmas(self, nova_qtd_turma):
        if nova_qtd_turma <= 0:                 # Solução 2
            print("Erro (set_qtd_turma2): quantidade de turmas não pode ser negativo.")
        else:
            self.qtd_turma = nova_qtd_turma
    def set_qtd_turma(self, nova_qtd_turma):    # Solução 3
        # if type(nova_qtd_turma) == int:
        if isinstance(nova_qtd_turma, int):
            self.qtd_turma = nova_qtd_turma
        else:
            print('Erro (set_qtd_turma3): tipo inválido.')
    def rendimentos(self):
        vl_rendimentos = self.qtd_turma * self.valor_turma
        return vl_rendimentos
    def salario_total(self):
        total = self.rendimentos() + self.valor_extra()
        return total
    def mostra_dados(self):
        print(self.get_nome())
        print(self.get_qtd_dependente())
        print(self.get_qtd_turma())
    # def __str__(self):
    #     s = f"Nome: {self.nome}, Qtd. turmas: {self.qtd_turma}"
    #     return s


class Funcionario(Pessoa):
    def __init__(self, nome='', qtd_dependente=0, salario=2000.00):
        super().__init__(nome, qtd_dependente)
        self.salario = salario
    def get_salario(self):
        return self.salario
    # def set_salario(self, n_salario):             # Sem crítica
    #     self.salario = n_salario
    def set_salario(self, n_salario):               # Com crítica
        if type(n_salario) in (float, int):
            if n_salario > 0:
                self.salario = n_salario
            else:
                print('Erro (set_salario): salario tem que ser positivo.')
        else:
            print('Erro (set_salario): tipo deve ser int ou float')
    def salario_total(self):
        total = self.salario + self.valor_extra()
        return total
    def mostra_dados(self):
        print(self.get_nome())
        print(self.get_qtd_dependente())
        print(self.get_salario())

    # def __str__(self):
    #     s = f"Nome: {self.nome}, Salário: {self.salario}"
    #     return s

if __name__ == '__main__':                  # Atalho: main <tab>
    p = Pessoa('Alice', 0)
    print(p)
    p1 = Professor("Silvio Ferreira",1, 5, 1000.00)  # Cria objeto da classe Professor
    print('- Dados do professor ...')
    print(p1)
    print('Nome:', p1.get_nome())
    p1.set_nome('Bruno')
    print(f"Nome: {p1.get_nome()}")
    p1.set_nome(34)                         # Argumento errado
    print(f"Nome: {p1.get_nome()}", )
    p1.set_qtd_turma(6)
    print(f'Qtd turmas: {p1.get_qtd_turma()}')
    p1.set_qtd_turma('nome')                # Argumento errado
    print(f'Qtd turmas: {p1.get_qtd_turma()}')
    p2 = Professor("Alice Dutra", 1)
    print(f"Nome: {p2.get_nome()}")
    p3 = Professor()
    print(f"Nome: {p3.get_nome()} e qtd turmas: {p3.get_qtd_turma()}")
    p4 = Professor("Alice Dutra", qtd_turma=4)
    print(f"Nome: {p4.get_nome()} e qtd turmas: {p4.get_qtd_turma()}")
    print("Rendimentos:", p1.rendimentos())
    print('- Dados do funcionário ...')
    f1 = Funcionario("Roberto Dutra", 2, 2000.00)
    print(f"Nome: {f1.get_nome()}")
    f2 = Funcionario("Vinícius", 2)
    print(f"Nome: {f2.get_nome()}")
    f3 = Funcionario()
    print(f"Nome: {f2.get_nome()}")
    f1.set_salario(3000.0)
    print('Novo salário:', f1.get_salario())
    f1.set_salario('Nome')                  # Argumento errado
    print('Novo salário:', f1.get_salario())
    print(f"Nome do Funcionario: {f1.get_nome()}\nSalário:{f1.get_salario()}")
    f1.set_salario(3000)
    print(f"Sálario após alterar: {f1.get_salario()}")
    p1.rendimentos()
    p1.mostra_dados()
    f1.mostra_dados()
    print("Salário total:", p1.salario_total())
