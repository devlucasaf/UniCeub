"""                 Comentários de várias linhas

ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Com base nos conceitos de Programação Orientada a Objetos (POO)
e inheritance (herança), implemente a entidade veículo com as
especializações de carro e moto.

- Precisamos trabalhar com as características (atributos):
valor, modelo, quilometragem, quantidade de portas e cilindradas.

- Levante as características comuns e as características específicas de
um carro e de uma moto:
valor, modelo, quilometragem
- Implemente estes itens:

1- Crie a superclasse Veiculo e o construtor (__init__) com os atributos comuns:
    valor, modelo (Corolla, Argo etc.) e km que indica a quilometragem.
    Use pelo menos um atributo com valor default (padrão). No main, teste
2- Crie alguns métodos gets e sets da superclasse, teste
3a- Antes do método main, crie a subclasse Carro, que herda da superclasse Veiculo.
    O construtor com os três atributos comuns e o atributo específico qtd_portas
3b- E os métodos get e set, teste
4- Crie três instâncias (objetos) da subclasse Carro com quatro, três e
    dois argumentos. No main, teste
5- Na subclasse Carro, sobrescreva o método __str__ do Python, ele retorna todos os
    dados (atributos). No main, teste
6a- Antes do método main, crie a subclasse Moto, que herda a superclasse Veiculo.
    O construtor com os três atributos comuns e o atributo específico cilindradas.
6b- E os métodos get e set, teste
7- Crie duas instâncias (objetos) da classe Moto com quatro e três argumentos.
    Teste
8- Utilize o método vars() para acessar os atributos de Carro e Moto num dicionário.
    Sintaxe: print(vars(objeto))
9- Use o método __dict__ para acessar os atributos de Carro e Moto num dicionário.
    Sintaxe.: print(objeto.__dict__)
10- Crie o método (def) atualiza valor, ele recebe um valor em reais e não retorna
    nada. O método acrescenta o valor recebido ao valor de qualquer veículo. Teste.
11- Refaça o item anterior com críticas (filtros) dentro da função.
12- Crie o método situacao do veículo para mostrar se é veículo zero, veículo
    seminovo ou veículo usado. O veiculo zero tem km igual a zero, seminovo se
    tiver até 20000 Km e veículo usado se tiver mais que 20000 Km.
    Use o método com os objetos criados.

"""


# Nome de classe começa com letra maiúscula e as outras letras minúsculas.
# class Veiculo:            # Três formas equivalentes de criar classe
# class Veiculo():
class Veiculo(object):  # Superclasse
    def __init__(self, valor, modelo, km=0):  # Construtor com valor default
        self.valor = valor  # Atributos de instância
        self.modelo = modelo
        self.km = km
    def get_valor(self):                      # Consulta
        return self.valor
    def set_valor(self, novo_valor):          # Sem crítica
        self.valor = novo_valor
    # def set_valor(self, valor):             # Com crítica
    #     if valor > 0:
    #         self.valor = valor
    #     else:
    #         print('Valor negativo.')
    def get_modelo(self):
        return self.modelo
    def set_modelo(self, novo_modelo):
        self.modelo = novo_modelo
    def get_km(self):
        return self.km
    def set_km(self, nova_km):
        self.km = nova_km
        # return v
    def __str__(self):
        v = f"Modelo: {self.modelo}, R$ {self.valor}, Km {self.km}"
        return v
    def atualiza_valor(self, vlr_aumento):     # Sem crítica
        self.valor = self.valor + vlr_aumento  # self.valor += vlr_aumento
    # def atualiza_valor(self, vlr_aumento):       # Com uma crítica
    #     if vlr_aumento > 0:
    #         self.valor += vlr_aumento
    #         # self.valor = self.valor + vlr_aumento
    #     else:
    #         print('Erro: valor negativo.')
    def situacao(self):
        if self.km == 0:
            print('Veiculo zero.')
        elif self.km <= 20000:
            print("Veículo seminovo.")
        else:
            print('Veículo usado.')


# O nome de classe começa com letra maiúscula e as outras letras minúsculas.
# class NomeSubclasse(NomeSuperclasse):   # Sintaxe
class Carro(Veiculo):       # A subclasse Carro herda da superclasse Veiculo
    def __init__(self, valor, modelo, km=0, qtd_portas=4):  # Valor default
        super().__init__(valor, modelo, km)  # Chama construtor da superclasse
        self.qtd_portas = qtd_portas
    def get_qtd_portas(self):
        return self.qtd_portas
    def set_qtd_portas(self, nova_qtd):
        self.qtd_portas = nova_qtd


# O nome de classe começa com letra maiúscula e as outras letras minúsculas.
# class NomeSubclasse(NomeSuperclasse):  # Sintaxe
class Moto(Veiculo):          # A subclasse Moto herda da superclasse Veiculo
    def __init__(self, valor, modelo, km=1, cilindradas=0):  # Construtor
        super().__init__(valor, modelo, km)  # Chama construtor da superclasse
        self.cilindradas = cilindradas
    def get_cilindradas(self):
        return self.cilindradas
    def set_cilindradas(self, novo_valor):
        self.cilindradas = novo_valor
