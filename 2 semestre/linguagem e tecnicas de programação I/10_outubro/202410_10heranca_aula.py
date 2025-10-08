"""             Comentários de várias linhas.

  CEUB  -  FATECS  -  BCC  -  ADS  -  Programação  -  Prof. Barbosa
ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Com base nos conceitos de Programação Orientada a Objetos (POO), vamos
trabalhar com duas classes (entidades).

Implemente a entidade funcionário com estas características (atributos):
nome e salario.

E implemente a entidade gerente com estas características (atributos):
nome, salario e quantidade de funcionários que ele gerencia.

- Implemente estes itens:

1- Crie a classe Funcionario, o construtor, def __init___ (self, ...),
   da classe com os atributos nome e salario e pelo menos um atributo
   com valor default (padrão)
2- Crie uma instância (objeto) da classe com os dados necessários
   (f1 = Funcionario( ... )), teste
3- Crie os métodos get e set. Teste
4- Crie um segundo funcionário passando apenas o nome. Teste
5- Crie a classe Gerente, o construtor com os atributos nome, salario
   e quantidade de funcionários que ele gerencia e pelo menos um atributo
   com valor default (padrão).
6- Crie uma instância (objeto) da classe Gerente com os dados necessários, teste
7- Crie os métodos gets e sets. Teste
8 - Crie o método mostra dados na classe Gerente. Ele não recebe nada e mostra
   todos os dados (atributos) de um gerente.
9- Use o método mostra dados para o gerente instanciado (objeto g1).
10- Use o método mostra dados para o funcionario instanciado (objeto f1).
    Por quê deu erro?
11- Crie outra instância (objeto g2) da classe Gerente passando apenas o nome
   e o salário.
... heranca1_atualizado:
12- Com base nos conceitos de Programação Orientada a Objetos (POO) e
    inheritance (herança), vamos usar duas ou mais classes (entidades).
13- A classe funcionário com os atributos nome e salário é mais geral,
    então ela será a superclasse.
    E a classe gerente é mais específica com os atributos nome, salário e
    quantidade que gerencia, então ela será a subclasse.
    Ou seja, a subclasse Gerente herda da superclasse Funcionario
    - Sintaxe: class NomeSubclasse(NomeSuperclasse):
    - Exemplo: class Gerente(Funcionario):
14- Conceito de herança: eliminar código repetido, herdar os atributos repetidos
    Obs.: altere o construtor da subclasse Gerente.
    def __init__(self, nome, salario, qtd_gerencia):
        # self.nome = nome
        # self.salario = salario
        super().__init__(nome, salario)  # Chama o construtor da superclasse
        self.qtd_gerencia = qtd_gerencia
15- Conceito de herança: eliminar código repetido, herdar os métodos repetidos
    Obs.: retire os métodos gets e sets repetidos da subclasse Gerente
16- Não altere o main e rode com as alterações de herança realizadas.
17- Todos os tipos de funcionário recebem uma bonificação de 10% do valor do
    salário. Então, crie o método bonificacao, ele não recebe nada e retorna
     o valor da bonificação.
18- Mostre a bonificacao das instâncias (objetos) f1 e g1.

"""


# O nome de classe começa com letra maiúscula e as outras letras minúsculas.
# Nome de classe: primeira letra de cada palavra com letra maiúscula
# class Funcionario(object):
# class Funcionario():  # Três formas eauivalentes de criar uma classe
class Funcionario:
    def __init__(self, nome, salario=0.0):  # Construtor com default
        self.nome = nome
        self.salario = salario
    def get_nome(self):                 # Consulta, retorna pro main
        return self.nome
    def set_nome(self, novo_nome):      # Altera o valor na memória
        self.nome = novo_nome
    def get_salario(self):
        return self.salario
    def set_salario(self, novo_salario):
        self.salario = novo_salario


class Gerente(object):
    def __init__(self, nome, salario, qtd_gerencia=1):  # default
        self.nome = nome                # Atributos de instância
        self.salario = salario
        self.qtd_gerencia = qtd_gerencia
    def get_nome(self):                 # Consulta
        return self.nome
    def set_nome(self, novo_nome):      # Altera
        self.nome = novo_nome
    def get_salario(self):
        return self.salario
    def set_salario(self, novo_salario):
        self.salario = novo_salario
    def get_qtd_gerencia(self):
        return self.qtd_gerencia
    def set_qtd_gerencia(self, nova_qtd):
        self.qtd_gerencia = nova_qtd
    def mostra_dados(self):
        print("Nome: ", self.nome)
        print("Salário: ", self.salario)
        print("Qtd. gerencia: ", self.qtd_gerencia)


