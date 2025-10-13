"""            Comentários de várias linhas.

ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Com base nos conceitos de Programação Orientada a Objetos (POO),
implemente a entidade pessoa com estas características (atributos):
nome,
peso,
altura e
ano de nascimento.

-Implemente os itens:

1- Crie o arquivo .py e a classe Pessoa
2- Crie o método construtor: ele recebe quatro parâmetros que serão armazenados
   nos atributos. No construtor, crie os quatro atributos da classe (nome, peso,
   altura e ano de nascimento)
3- No método main, crie (instancie) dois objetos da classe Pessoa e passe os
   argumentos.
4- Mostre os objetos criados, teste (rode) a classe
5- Crie os métodos get (consultar) e set (alterar) para os atributos nome e
   ano_nascimento.
   def get_nome_atributo1(self):              # Modelo do método get (consulta)
       return self.nome_atributo1
   def set_nome_atributo1(self, novo_valor):  # Modelo do método set (altera)
       self.nome_atributo1 = novo_valor
6- No main, teste os métodos criados dos atributos da classe Pessoa
   Mostre o atributo nome e mostre o atributo ano de nascimento
7- Altere o valor do atributo ano de nascimento da pessoa 1 para 2005. Teste
8- Faça uma crítica no método set ano de nascimento para evitar dados
   inconsistentes. Teste
9- Crie o método calcula_idade, ele não recebe nada e retorna à idade da pessoa.
    Teste
10- Crie o método IMC (Índice de Massa Corporal), ele não recebe nada, calcula
    e retorna o valor do IMC. O IMC é calculado dividindo o peso (em kg) pela
    altura ao quadrado (em metros).
11- Sobrescreva o método especial __str__ . Ele não recebe nada e retorna os
    dados de uma pessoa (nome, peso e ano de nascimento) concatenados. Teste.
12- Altere o construtor para ele aceitar objetos sem a ano nascimento,
    ou seja, ele recebe somente o nome, o peso e a altura. Para isso,
    coloque o valor default 2000 para o atributo ano de nascimento.
13- No programa (método main), crie (instancie) mais um objeto passando
    apenas estes três argumentos: nome, peso e altura.
14- Teste o item anterior, ou seja, mostre o valor dos atributos do objeto criado

"""


import datetime
# O nome de classe começa com letra maiúscula e as outras letras minúsculas.
# Nome de classe: primeira letra de cada palavra com letra maiúscula
# class Pessoa:
# class Pessoa():       # Três formas equivalentes de criar a classe.
class Pessoa(object):
    def __init__(self, nome, peso, altura, ano_nascimento):  #Método construtor
        self.nome = nome                                     # Atributos
        self.peso = peso
        self.altura = altura
        self.ano_nascimento = ano_nascimento
    def get_nome(self):                     # Método consulta
        return self.nome
    def set_nome(self, novo_nome):          # Método altera
        self.nome = novo_nome
    def get_peso(self):
        return self.peso
    def set_peso(self, novo_peso):
        self.peso = novo_peso
    def get_ano_nascimento(self):           # Consulta
        return self.ano_nascimento
    # def set_ano_nascimento(self, novo_ano):  # Altera sem crítica
    #     self.ano_nascimento = novo_ano
    # def set_ano_nascimento(self, novo_ano):  # Altera com crítica
    #     if novo_ano > 1900:
    #         self.ano_nascimento = novo_ano
    #     else:
    #         print("Erro: ano inválido.")
    def set_ano_nascimento(self, novo_ano):  # Altera com crítica
        # if isinstance(novo_ano, int):
        if type(novo_ano) == int:
            self.ano_nascimento = novo_ano
        else:
            print("Erro: tipo inválido.")
    def calcula_idade(self):                # Método funcional
        hoje = datetime.date.today()        # Data  de hoje (ano, mes, dia)
        idade = hoje.year - self.ano_nascimento  # year pega o ano de uma data
        return idade                        # Solução incompleta
    def imc(self):                          # Método funcional
        # vl_imc = self.peso / self.altura ** 2        # solução 1
        # vl_imc = self.peso / pow(self.altura, 2)     # solução 2
        vl_imc = self.peso/(self.altura * self.altura) #3(Parênteses obrigatórios)
        return vl_imc
        # return self.peso/(self.altura * self.altura) #4(Parênteses obrigatórios)
    def __str__(self):                 # Sobrescreve o método especial __str__
        s = f"Nome: {self.nome}, Peso: {self.peso}, Altura: {self.altura}" \
            f"e Idade: {self.ano_nascimento}"  # f-string
        return s
        # s = self.get_nome()+' ' + str(self.peso) + ', ' +
        # str(self.get_ano_nascimento())
        # s = "{} {}, {}" .format(self.nome, self.peso, self.ano_nascimento)
        # s = f"{self.get_nome()}, {self.peso} {self.get_ano_nascimento()}"
        # return f"Nome: {self.nome}, Peso: {self.peso},
        # Nascimento: {self.ano_nascimento}"
