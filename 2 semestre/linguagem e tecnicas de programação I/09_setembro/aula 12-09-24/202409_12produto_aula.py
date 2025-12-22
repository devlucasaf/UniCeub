"""             Comentários de várias linhas.

ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Com base nos conceitos de POO (Programação Orientada a Objetos),
implemente a entidade produto com estas características (atributos):

nome do produto,
valor de compra,
valor de venda,
quantidade em estoque e
quantidade mínima (qdo. a empresa precisa realizar uma nova compra do produto)

- Implemente os itens:

1- Crie dois arquivos .py: O arquivo produto_classe e arquivo produto_usa.
2- Crie a classe Produto e o construtor da classe com os atributos:
   nome, vlr_compra, vlr_venda, qtd_estoque, qtd_minima
3- No main, crie (instancie) o primeiro objeto da classe e passe os dados. Teste
4- Crie os métodos gets e sets somente dos atributos:
   nome do produto e quantidade em estoque. Teste.
5- Altere o valor da quantidade em estoque cadastrado para um valor digitado.
   Teste.
6- Faça pelo menos uma crítica no método set_qtd_estoque.
7- Teste o método set_qtd_estoque com valor correto e com valor inconsistente
8- Sobrescreva o método __str__ . Ele não recebe nada e retorna todos os
   atributos concatenados (juntos). Teste.
9- Crie o método funcional lucro. Não recebe nada e retorna o valor do lucro
   do produto. Teste
10- Crie o método funcional atualiza_estoque. Ele recebe a quantidade vendida
    do produto e atualiza o estoque. Ele não retorna nada, teste
11- Método funcional repor_produto. Ele recebe a quantidade adquirida de produtos
    pela empresa e atualiza o estoque, teste
12- Crie o método alerta_estoque. Não recebe nada e retorna um valor booleano.
    Retorna True, se a quantidade em estoque for menor que a quantidade mínima.
    Senão, retorna False. Teste
13- Crie o segundo objeto da classe passando apenas os argumentos nome do produto,
    valor de compra e valor de venda, teste
14- No main, crie mais um objeto da classe passando apenas os argumentos nome
    e valor de compra. Teste
15- No main, crie mais um objeto da classe passando apenas o argumento nome e
    a quantidade em estoque. Teste

"""


# O nome de classe começa com letra maiúscula e as outras letras minúsculas.
# Nome de classe: primeira letra de cada palavra com letra maiúscula
# class Produto:
# class Produto():
class Produto(object):  # Três formas equivalentes de criar a classe.
    def __init__(self, nome, vlr_compra, vlr_venda, qtd_estoque, qtd_minima):
        self.nome = nome                      # Atributos de instância
        self.vlr_compra = vlr_compra
        self.vlr_venda = vlr_venda
        self.qtd_estoque = qtd_estoque
        self.qtd_minima = qtd_minima
    def get_nome(self):             # Modelo do método get (retorna o valor)
        return self.nome
    def set_nome(self, novo_nome):  # Modelo do método set (altera o valor)
        self.nome = novo_nome
    def get_vlr_compra(self):
        return self.vlr_compra
    def set_vlr_compra(self, novo_valor):     # Sem crítica
        self.vlr_compra = novo_valor
    def get_qtd_estoque(self):
        return self.qtd_estoque
    # def set_qtd_estoque(self, novo_vlr):  # Sem crícia
    #     self.qtd_estoque = novo_vlr
    def set_qtd_estoque(self, novo_vlr):  # Com crítica
        if novo_vlr >= 0:
            self.qtd_estoque = novo_vlr
        else:
            print('Erro: qtd. estoque negativo.')
    def get_qtd_minima(self):            # get_nome_atributo(self):
        return self.qtd_minima
    def set_qtd_minima(self, novo_vlr):  # set_nome_atributo(self, valor):
        self.qtd_minima = novo_vlr
    def __str__(self):  # Sobrescreve o método especial __str__ do Python
        s = f"Nome: {self.nome}, {self.vlr_compra}, {self.vlr_venda}, " \
            f"{self.qtd_estoque}, {self.qtd_minima}"
        return s
    def lucro(self):
        vlr_lucro = self.vlr_venda - self.vlr_compra
        return vlr_lucro
    def atualiza_estoque(self, qtd_vendida):
        self.qtd_estoque -= qtd_vendida
        # self.qtd_estoque = self.qtd_estoque - qtd_vendida
    def repor_estoque(self, qtd_comprada):
        self.qtd_estoque += qtd_comprada
        #  self.qtd_estoque = self.qtd_estoque + qtd_comprada
    def alerta_estoque(self):
        if self.qtd_estoque < self.qtd_minima:
            var = True
        else:
            var = False
        return var
