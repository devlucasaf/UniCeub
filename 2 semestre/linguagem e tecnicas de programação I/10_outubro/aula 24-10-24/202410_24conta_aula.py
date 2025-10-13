"""                 Comentários de várias linhas

ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Com base nos conceitos de Programação Orientada a Objetos (POO) e
inheritance (herança), implemente a entidade conta corrente com as
especificidades de conta corrente de uma pessoa física e de conta
corrente de uma pessoa jurídica.

- Algumas características (atributos) de uma conta corrente:
nome, saldo, gênero e modalidade de PJ

- Levante as características comuns e as características específicas de
conta corrente de pessoa física e de conta corrente de pessoa jurídica.
Características comuns: nome e saldo

- Implemente estes itens:

1- A superclasse Conta
2- O método construtor com os atributos nome e saldo. Use valor padrão (default), teste
3- Os métodos gets e sets da superclasse, teste
4- Sobrescreva o método __str__ para ele retornar os atributos, teste
5- A subclasse conta pessoa física, use o conceito de herança
6- O método construtor com os atributos nome, saldo e genero, use o super()
7- Uma instância (objeto) de conta pessoa física, teste
8- Os métodos get e set, teste
9- Use os métodos da subclasse conta pessoa física
10- Use o método do Python vars.              Sintaxe: print(vars(objeto))
11- Use o método do Python __dict__                    print(objeto.__dict__)
12- A subclasse conta pessoa jurídica, use o conceito de herança
13- O método construtor com os atributos nome, saldo e modalidade, use o super()
14- Uma instância (objeto) de conta pessoa jurídica
15- Os métodos get e set, teste
16- O método deposito, ele recebe o valor do depósito e atualiza o saldo, teste
17- O método retirada, ele recebe o valor da retirada e atualiza o saldo, teste
18- Refaça o método retirada usando RNs (Regras de Negócio) bancárias. Teste
19- A política do sistema bancário mudou, o depósito continua sem cobrar tarifa.
    No saque, a pessoa física tem tarifa de 2 reais e pessoa jurídica de 5 reais.
    Então, substitua o método retirada da superclasse por dois métodos retirada
    nas duas subclasses.
20- Teste a alteração anterior.

"""


# O nome de classe começa com letra maiúscula e as outras letras minúsculas.
# class Conta:       # Três formas equivalentes de criar classe
# class Conta():
class Conta(object):                    # Superclasse
    def __init__(self, nome, saldo=0.0):  # Construtor com valor default
        self.nome = nome                # Atributos de instância (objeto)
        self.saldo = saldo
    def get_nome(self):                 # Consulta
        return self.nome
    def set_nome(self, novo_nome):      # Altera
        self.nome = novo_nome
    def get_saldo(self):
        return self.saldo
    # Não implemente o método set_saldo, RN (Regra de Negócio) dos bancos.
    def __str__(self):                  # Sobrescreve o método __str__ do Python
        # s = 'Nome: ' + self.nome + ', R$ ' + str(self.saldo)  # Concatenação
        s = f'Nome: {self.nome}, R$ {self.saldo}'               # f-string
        return s
    def deposito(self, valor):          # Métodos funcionais
        self.saldo += valor             # self.saldo = self.saldo + valor
    def retirada(self, valor):          # Sem RN (Regra de Negócio)
        self.saldo -= valor             # self.saldo = self.saldo - valor
    def retirada_rn(self, valor):       # RN obrigatória (natural dos bancos)
        if valor > self.saldo:
            print('Saldo insuficiente.')
        else:
            self.saldo -= valor


# O nome de classe começa com letra maiúscula e as outras letras minúsculas.
# class NomeSubclasse(NomeSuperclasse): # Sintaxe
class PessoaFisica(Conta):      # Subclasse PessoaFisica herda da superclasse Conta
    def __init__(self, nome, saldo=0.0, genero='m'):  # Valores default
        super().__init__(nome, saldo)   # Chama o construtor da superclasse
        self.genero = genero
    def get_genero(self):               # Consulta
        return self.genero
    def set_genero(self, novo_genero):  # Altera
        self.genero = novo_genero
    def retirada_rn(self, valor):       # RN obrigatória (natural dos bancos)
        total = valor + 2
        if total > self.saldo :
            print('Saldo insuficiente.')
        else:
            self.saldo -= total


""" - As principais modalidades de PJ:
1 - MEI (Microempreendedor Individual)
2 - ME – Microempresa
3 - EPP (Empresa de Pequeno Porte)
4 - EI (Empresário Individual) 
5 - EIRELI (Empresa Individual de Responsabilidade Limitada)
6 - Sociedade Limitada – LTDA
7 - Sociedade Anônima (SA)                                  """
# O nome de classe começa com letra maiúscula e as outras letras minúsculas.
# class NomeSubclasse(NomeSuperclasse):   # Sintaxe
class PessoaJuridica(Conta):  # Subclasse PessoaJuridica herda da superclasse Conta
    def __init__(self, nome, saldo=0.0, modalidade='MEI'):  # Construtor com default
        super().__init__(nome, saldo)       # Chama o construtor da superclasse
        self.modalidade = modalidade
        # super(PessoaJuridica, self).__init__(nome, saldo)
        # Conta.__init__(self, nome, saldo)
    def get_modalidade(self):
        return self.modalidade
    def set_modalidade(self, nova_modalidade):
        self.modalidade = nova_modalidade
