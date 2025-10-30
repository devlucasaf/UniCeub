from abc import ABC, abstractmethod
from math import pi


def lin ():  #linhas
    print('-'*55)

class FormaGeometrica(ABC):
    
    def __init__(self):
        ...

    @abstractmethod
    def area(self):
        ...
    
    @abstractmethod
    def perimetro(self):
        pass

#--------------------------------------------------------

class Quadrado(FormaGeometrica):
    def __init__(self, lado):
        
        self.lado = lado

    def get_lado(self):
        return self.lado
    def set_lado(self, novo_l):
        self.lado = novo_l

    def area(self):
        a = self.lado * self.lado
        return a
    
    def perimetro(self):
        p = self.lado * 4
        return p
    
#--------------------------------------------------------

class Circulo(FormaGeometrica):
    def __init__(self, raio):
        self.raio = raio

    def get_raio(self):
        return self.raio
    def set_raio(self, novo_r):
        self.raio = novo_r

    def area(self):
        a = (self.raio**2) * 3.14
        return a
    
    def perimetro(self):
        p = 2 * pi * self.raio
        return p

if __name__ == '__main__':

    lin()

    q1 = Quadrado(5)
    print('[Quadrado 1]')
    print(f'lado = {q1.get_lado()}')
    print(f'area = {q1.area()}')
    print(f'perimetro = {q1.perimetro()}')

    lin()

    c1 = Circulo(5)
    print('[Circulo 1]')
    print(f'lado = {c1.get_raio():.2f}')
    print(f'area = {c1.area():.2f}')
    print(f'perimetro = {c1.perimetro():.2f}')

    lin()
    
