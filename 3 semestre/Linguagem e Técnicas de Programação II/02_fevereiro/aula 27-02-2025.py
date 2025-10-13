class Titular(object):            # class Titular:
    def __init__(self, cpf, nome, sobrenome=""):  # Construtor com valor default
        self.cpf = cpf
        self.nome = nome          # Atributos de instância
        self.sobrenome = sobrenome
    def get_cpf(self):            # Métodos gets e sets
        return self.cpf
    def get_nome(self):
        return self.nome
    def set_nome(self, novo_nome):
        self.nome = novo_nome
    def get_sobrenome(self):
        return self.sobrenome
    def nome_completo(self):                        # Método funcional
        nome_c = self.nome + " " + self.sobrenome   # Usa concatenação de strings
        # nome_c = f'{self.nome} {self.sobrenome}'  # Usa o f-string
        # As duas linhas acima são equivalentes
        return nome_c


class Conta(object):                    # class Conta:
    def __init__(self, numero, o_titular, saldo, limite=1000.0):
        self.numero = numero
        self.titular = o_titular
        # O atributo self.titular recebe o endereço do objeto da classe Titular
        self.saldo = saldo
        self.limite = limite
    def get_saldo(self):
        return self.saldo
    def get_titular(self):                  # Retorna o endereço do objeto titular
        return self.titular
    # Chama os métodos da classe Titular dentro destes métodos da classe Conta
    def get_titular_nome(self):             # Retorna nome do titular
        return self.titular.get_nome()
    def set_titular_nome(self, novo_nome):  # Altera nome do titular
        self.titular.set_nome(novo_nome)    # self.titular.nome = novo_nome
    def get_titular_sobrenome(self):        # Retorna sobrenome do titular
        return self.titular.get_sobrenome()
    def get_titular_cpf(self):              # Retorna cpf do titular
        return self.titular.get_cpf()
    def extrato_reduzido(self):
        print(f"Extrato 1:\nNúmero: {self.numero}, Saldo: {self.saldo}")
    def extrato_normal(self):                # Usando métodps da classe Titular
        print(f'Extrato 2:\nNome: {self.titular.get_nome()} '
        f'{self.titular.get_sobrenome()} CPF: {self.titular.get_cpf()}')
        print(f"\nNúmero: {self.numero}, Saldo: {self.saldo}")
    def dados_titular(self):
        # return self.titular.__dict__
        return vars(self.titular)
    def deposito(self, valor):
        self.saldo += valor
    # def saque(self, valor):             # Sem crítica
    #     self.saldo -= valor
    def saque(self, valor):             # Com crítica (RN - Regra de Negócio)
        if self.saldo + self.limite < valor:
            print('Saldo insuficiente.')
            return False
        else:
            self.saldo -= valor
            print('Saque realizado.')
            return True

# from conta_agregacao import *
# Sintaxe: from nome_arquivo_sem_extensao import NomeClasse1, NomeClasse2
from conta2_agregacao_aula import Conta, Titular
from conta2_agregacao_titular import Titular
if __name__ == '__main__':          # Atalho: mai <tab>
    titular1 = Titular('371-1', 'Lia', 'Oliveira')  # Objeto da classe Titular
    print('Nome:', titular1.get_nome())
    print('Nome completo:', titular1.nome_completo())
    # Cria objeto da classe Conta usando o objeto da classe Titular
    conta1 = Conta('123-4', titular1, 1200.00, 2000.00)
    print(titular1)  # <conta_agregacao.Titular object at 0x000002B9DBA4AFD0>
    print(conta1)    # <conta_agregacao.Conta object at 0x000002B9DBA4AF70>
    print(conta1.get_titular())
    # <conta_agregacao.Titular object at 0x000002B9DBA4AFD0>, endereço do atributo
    print('Nome:', titular1.get_nome())  # Consulta usando a classe Titular
    print('Sobrenome:', titular1.get_sobrenome())
    print('CPF:', titular1.get_cpf())
    titular1.set_nome('Ana')            # Altera o nome usando a classe Titular
    print('Nome:', titular1.get_nome())
    print('Nome completo:', titular1.nome_completo())
    print('Nome:', conta1.get_titular().get_nome())  # Consulta usando a classe Conta
    print('Nome:', conta1.get_titular_nome())  # Consulta usando a classe Conta
    print('Sobrenome:', conta1.get_titular_sobrenome())
    print('CPF:', conta1.get_titular_cpf())
    conta1.set_titular_nome('Alice')        # Altera o nome usando a classe Conta
    print('Nome:', conta1.get_titular_nome())
    conta1.extrato_reduzido()
    conta1.extrato_normal()
    print('conta1.dados_titular():', conta1.dados_titular())
    print(conta1.get_titular())         # Retorna o endereço
    conta1_titular = conta1.get_titular()
    print(conta1_titular.__dict__)
    print(vars(conta1_titular))
    print("----------------")
    print(titular1.__dict__)            # Métodos semelhantes
    print(vars(titular1))
    print("----------------")
    conta1.deposito(200)
    conta1.saque(100)
    titular2 = Titular('388-1', 'Paulo', 'Pereira')  # Objeto da classe Titular
    print('Nome:', titular2.get_nome())
    print('Nome completo:', titular2.nome_completo())
    conta2 = Conta('143-6', titular2, 900.00)

    print('- special method or dunder:')
    print(titular1.__class__)           # <class 'conta_agregacao.Titular'>
    print(titular1.__class__.__name__)  # Titular
