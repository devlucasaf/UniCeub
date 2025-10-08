from abc import ABC, abstractmethod  # Importa o módulo abc (abstract base classes)
class FormaGeometrica(ABC):  # Classe abstrata, herda da classe ABC
    def __init__(self):
        ...  # pass
    # Método abstrato, obrigatório pelo menos um na superclasse abstrata
    @abstractmethod                  # Decorator
    def area(self):                  # Método abstrato
        ...                          # Equivalente: pass
    @abstractmethod                  # Decorator
    def perimetro(self):             # Método abstrato
        pass                         # Equivalente: ...
    def mensagem(self):              # Método concreto
        print('Método concreto da superclasse abstrata FormaGeometrica que herda de ABC')
    def mensagem2(self):             # Método concreto
        print('Método concreto da superclasse abstrata FormaGeometrica que herda de ABC')
        print('Dados do obejto:', self)
    def mensagem3(self):             # Método concreto
        print('Método concreto da superclasse abstrata FormaGeometrica que herda de ABC')
        print('Nome da classe:', self.__class__.__name__)  # nome_objeto.__class__.__name__


# A subclasse concreta Quadrado herda da superclasse abstrata FormaGeometrica
class Quadrado(FormaGeometrica):  # class NomeSubclasse(NomeSuperclasse):  (sintaxe)
    def __init__(self, lado):
        super().__init__()        # Opcional
        self.lado = lado
    def get_lado(self):
        return self.lado
    def set_lado(self, valor):
        self.lado = valor
    # Método obrigatório, sobrescreve o método abstrato da superclasse abstrata
    def area(self):
        vl_area = self.lado ** 2        # vl_area = self.lado * self.lado
        return vl_area
    # Método obrigatório, sobrescreve o método abstrato da superclasse abstrata
    def perimetro(self):
        vl_perimetro = 4 * self.lado
        return vl_perimetro


import math                     # Como usar:    math.pi
from math import pi             # Como usar:    pi
# A subclasse concreta Circulo herda da superclasse abstrata FormaGeometrica
class Circulo(FormaGeometrica):  # class NomeSubclasse(NomeSuperclasse):  (sintaxe)
    def __init__(self, raio=1):             # Construtor
        super().__init__()                  # Opcional
        self.raio = raio                    # Atributo
    def get_raio(self):
        return self.raio
    def set_raio(self, valor):
        self.raio = valor
    # Método obrigatório, sobrescreve o método abstrato da superclasse abstrata
    def area(self):                         # Obrigatório
        vl_area = 3.14 * pow(self.raio, 2)  # vl_area = 3.14 * self.raio ** 2
        return vl_area
    # Método obrigatório, sobrescreve o método abstrato da superclasse abstrata
    def perimetro(self):                    # Obrigatório
        vl_perimetro = 2 * 3.14 * self.raio
        return vl_perimetro

# from nome_arquivo_sem_extensao import NomeClasse1, NomeClasse2, NomeClasse3
from forma_geometrica_aula import FormaGeometrica, Quadrado, Circulo
if __name__ == '__main__':
    # obj_forma = FormaGeometrica()  # TypeError:
    # print(obj_forma)
    # Can't instantiate abstract class FormaGeometrica with abstract methods
    # print("Quantidade:", FormaGeometrica.get_qtd_figura())
    obj_quad = Quadrado(3)          # Objeto da subclasse concreta Quadrado
    # print("Quantidade:", FormaGeometrica.get_qtd_figura())
    print('- Quadrado:\nLado:', obj_quad.get_lado())
    print('Área:', obj_quad.area())
    print('Perímetro:', obj_quad.perimetro())
    obj_quad.set_lado(4)            # Altera o valor do lado
    print('Lado:', obj_quad.get_lado())
    print('Área:', obj_quad.area())
    print('Perímetro:', obj_quad.perimetro())
    obj_circ = Circulo(3)           # Objeto da subclasse concreta Circulo
    # print("Quantidade:", FormaGeometrica.get_qtd_figura())
    print('- Círculo:\nRaio:', obj_circ.get_raio())
    print('Área:', obj_circ.area())
    print('Perímetro:', obj_circ.perimetro())
    obj_circ.set_raio(2)
    print('Raio:', obj_circ.get_raio())
    print(f"Área: {obj_circ.area():.3f}")   # Com três casas decimais
    print('Perímetro:', obj_circ.perimetro())
    print("- Testando os métodos mensagem")
    obj_quad.mensagem()                     # Use um objeto da subclasse
    obj_circ.mensagem()
    FormaGeometrica.mensagem(obj_quad)
    FormaGeometrica.mensagem(obj_circ)
    obj_quad.mensagem2()    # <quadrado.Quadrado object at 0x000001D990E8DFD0>
    obj_circ.mensagem2()
    obj_quad.mensagem3()    # Nome da classe: Quadrado
    obj_circ.mensagem3()

