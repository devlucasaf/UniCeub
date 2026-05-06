# Sintaxe:
# from nome_arquivo_sem_extensao import nomeClasse
from ponto_aula import Point
if __name__ == '__main__':       # Atalho: mai <tab>
    p1 = Point()                 # Instantiate an object of type Point
    print("Objeto p1 da classe Point", p1)  # Duas linhas equivalentes
    print("Objeto p1 da classe Point", p1.__str__())
    # Objeto p1 da classe Point <ponto.Point object at 0x000001CAD8916F70>
    print("Atributo x do ponto p1=", p1.get_x())
    print("Atributo y do ponto p1=", p1.get_y())
    p1.set_x(2)                         # Teste, argumento correto
    print("Atributo x do ponto p1=", p1.get_x())
    p1.set_x('Mensagem')                # Teste, argumento errado
    print("Atributo x do ponto p1=", p1.get_x())
    p2 = Point(2, 3)                    # Chama o método construtor __init__
    print("Dados do objeto p2 da classe Point", p2)  # 2 linhas equivalentes
    print("Dados do objeto p2 da classe Point", p2.__str__())
    print("Atributo x do ponto p2=", p2.get_x())
    print("Atributo y do ponto p2=", p2.get_y())
    p3 = Point(3)                       # Passa só o valor do x
    print("Dados do objeto p3:", p3)    # print("__str__ ", p3.__str__())
    print("Atributo x do ponto p1=", p3.get_x())
    print("Atributo y do ponto p1=", p3.get_y())
    # p4 = Point(0, 5)                  # Solução 1, não é uma boa solução
    p4 = Point(y=5)                     # Solução 2, a solução correta
    print("Dados do objeto p4:", p4)    # print("__str__ ", p4.__str__())
    print("Atributo x do ponto p1=", p4.get_x())
    print("Atributo y do ponto p1=", p4.get_y())
