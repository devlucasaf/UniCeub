def lin ():  #linhas
    print('-'*50)


class Titular(object):
    def __init__(self, cpf, nome, sobrenome=""):
        self.cpf = cpf
        self.nome = nome
        self.sobrenome = sobrenome
    #------------------------------

    def get_cpf(self):
        return self.cpf
    def set_cpf(self, n_self):
        self.cpf = n_self

    #------------------------------

    def get_nome(self):
        return self.nome
    def set_nome(self,n_nome):
        self.nome = n_nome

    #------------------------------

    def get_sobrenome(self):
        return self.sobrenome
    def set_sobrenome(self, n_sn):
        self.sobrenome = n_sn

    #------------------------------

    def nome_completo(self):
        nc = f"Nome completo: {self.nome} {self.sobrenome}"
        return nc
        
    #------------------------------


class Conta(object):
    def __init__(self, numero, titular, saldo, limite=1000.0):
        self.numero = numero
        self.titular = titular
        self.saldo = saldo
        self.limite = limite

    def get_numero(self):
        return self.numero
    
    def get_titular(self):
        return self.titular
    
    def get_saldo(self):
        return self.saldo
    
    def get_limite(self):
        return self.limite

    def titular_nome(self):
        return self.titular.get_nome()
    
    def extrato_reduzido(self):
        print(f'Numero:{self.numero} \nSaldo: {self.saldo}')

    def deposito(self,valor):
        self.valor = valor

    def saque(self, valor):
        if self.saldo + self.limite < valor:
            print('Saldo insuficiente')
            return False

        else:
            self.saldo -= valor
            print('Saque Realizado.')
            return True

if __name__ == '__main__':
    
    lin()

    titular1 = Titular ('371-1', 'Guilherme', 'Santiago')
    
    print(f'cpf: {titular1.get_cpf()} \n{titular1.nome_completo()}')

    lin()

    conta1 = Conta('458-1', titular1, 1200.0, 2000.00)
    print(f'numero: {conta1.get_numero()} \ntitular: {conta1.get_titular()} \nsaldo: {conta1.get_saldo()} \nlimite: {conta1.get_limite()}')
    
    print(conta1.get_titular().get_nome(), conta1.get_titular().get_sobrenome())
    
    lin()
    
    print()

