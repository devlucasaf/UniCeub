"""             Comentários de várias linhas.

ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Com base nos conceitos de POO (Programação Orientada a Objetos),
implemente a entidade produto com estas características (atributos):

- Com base nos conceitos de Programação Orientada a Objetos (POO),
implemente a entidade ponto no plano cartesiano com estas
características (atributos):

O valor da coordenada x no plano cartesiano;
O valor da coordenada y no plano cartesiano.

-Implemente os itens:

1- Crie um novo projeto e a classe Point.
2- Crie o método construtor, ele atribui o valor default (padrão) zero para
   os parâmetros de x e y
3- No construtor, crie os atributos x e y.
4- Na função main, crie o objeto p1 (ponto p1) sem passar argumentos. Teste
5- Crie os métodos gets e sets.
6- Mostre o valor do atributo x e y.
7- Faça pelo menos uma crítica no método set_x (evitar dados inconsistentes),
   teste
8- Na função main, crie o objeto p2 (ponto p2) passando os valores 2 e 3 como
   argumento. Teste
9- Sobrescreva o método __str__. Ele recebe self e retorna todos os atributos
   concatenados. Teste
10- Na função main, crie o objeto p3 (ponto p3) passando somente o argumento x.
    Teste
11- Na função main, crie o objeto p4 (ponto p4) passando somente o argumento y.
    Teste

"""


# O nome de classe começa com letra maiúscula e as outras letras minúsculas.
# Nome de classe: primeira letra de cada palavra com letra maiúscula
# class Point:
# class Point():
class Point(object):  # Três formas eauivalentes de criar uma classe
    def __init__(self, x=0.0, y=0.0):  # def __init__(self, x, y) # Construtor
        self.x = x              # Atributos de objeto (instância)
        self.y = y
    def get_x(self):            # Método get (retorna o valor de um atributo)
        return self.x
    # def set_x(self, novo_x):  # Método set (altera o valor de um atributo)
    #     self.x = novo_x
    def set_x(self, novo_x):    # Método set com crítica
        # if isinstance(novo_x, int) or isinstance(valor, float):  # Equivalentes
        # if isinstance(novo_x, (int, float)):
        if type(novo_x) in (int, float):
            self.x = novo_x
        else:
            print('Erro: tipo tem que ser int ou float.')
    def get_y (self):
        return self.y
    def set_y(self, novo_y):
        self.y = novo_y
    def __str__(self):                          # Método especial (dunder)
        # s = '('+str(self.get_x()) + ', ' + str(self.get_y()) + ')'
        # s = "({}, {})" .format(self.get_x(), self.get_y())  # (x, y) # (2, 3)
        # s = f"({self.get_x()}, {self.get_y()})"   # (x, y)   # (2, 3)
        s = f"({self.x}, {self.y})"             # (x, y)   # (2, 3)
        return s


