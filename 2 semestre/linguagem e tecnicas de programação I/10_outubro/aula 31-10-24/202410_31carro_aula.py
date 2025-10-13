"""            Comentários de várias linhas.
ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Uma classe abstrata deve herdar de ABC (Abstract Base Classes)
- Analise o problema de locação de carros e o valor da diária.

- O valor do preço básico de locação é o mesmo (R$ 100.00) para todos os
tipos de carro, como podemos modelar as classes. E o preço da diária depende
da classificação do carro (econômico, intermediário e automático).

O gerente do sistema precisa implantar a RN (Regra de Negócio) que toda subclasse
incluida no sistema precisa ter a funcionalidade de cálculo do preço da diária.

Obs.: RN (Regra de Negócio) para o carro econômico:
      O preço da diária corresponde ao preço base de locação acrescido de 5%
Obs.: RN (Regra de Negócio) para o carro intermediário:
      O preço da diária corresponde ao preço base de locação acrescido de 10%
Obs.: RN (Regra de Negócio) para carro automático:
      O preço da diária corresponde ao preço base de locação acrescido de 25%

- Implemente:

1- Crie a superclasse abstrata Carro
2- Crie o construtor com o atributo de instância modelo
3- Crie os métodos de instância get_modelo e set_modelo
4- Crie o método abstrato preço da diária
5- Crie um objeto da superclasse abstrata Carro, teste
6- Como implementar o preço base de locação com valor fixo de R$ 100,00?
   Crie o atributo de classe preço base de locação igual a 100 reais.
7a- Crie os métodos de classe get_preco_base e set_preco_base
7b- Altere o preço base de locação do carro
8- Crie a subclasse Economico com o atributo modelo.
9- No construtor da subclasse, chame o construtor da superclasse e passe o modelo
10- Crie um objeto da subclasse Economico, teste. Por quê deu erro?
11- Sobrescreva o método abstrato da superclasse
    Obs.: RN (Regra de Negócio) para o carro econômico:
    O preço da diária corresponde ao preço base de locação acrescido de 5%
12- Crie um objeto da subclasse Economico, teste. Por quê deu certo?
13- Crie a subclasse Intermediário com o atributo modelo.
14- No construtor da subclasse, chame o construtor da superclasse e passe o modelo
15- Crie um objeto da subclasse Intermediário, teste. Por quê deu erro?
16- Sobrescreva o método abstrato da superclasse
    Obs.: RN (Regra de Negócio) para o carro intermediário:
    O preço da diária corresponde ao preço base de locação acrescido de 10%
17- Crie um objeto da subclasse Intermediário, teste. Por quê deu certo?

"""


from abc import ABC, abstractmethod  # Módulo abc
class Carro(ABC):                 # Superclasse abstrata Carro, herda de ABC
    preco_base = 100                 # Atributo de classe
    @classmethod                     # Decorator
    def get_preco_base(cls):         # Método de classe
        return cls.preco_base
    @classmethod
    def set_preco_base(cls, novo_valor):  # Método de classe
        cls.preco_base = novo_valor
    def __init__(self, modelo):
        self.modelo = modelo         # Atributo de instância
    def get_modelo(self):            # Método concreto
        return self.modelo
    def set_modelo(self, modelo):    # Método concreto
        self.modelo = modelo
    # Método abstrato, obrigatório pelo menos um na superclasse abstrata
    @abstractmethod                  # Decorator
    def preco_diaria(self):          # Método abstrato
        pass                         # ...


from carro import Carro
""" Obs.: RN (Regra de Negócio) para o carro econômico:
    O preço da diária corresponde ao preço base de locação acrescido de 5% """
class Economico(Carro):  # Subclasse Economico que herda da superclasse Carro
    def __init__(self, modelo):
        super().__init__(modelo)      # Chama o construtor da superclasse Carro

    # Método obrigatório, sobrescreve o método abstrato da superclasse
    def preco_diaria(self):               # Método obrigatório
        val_diaria = Carro.get_preco_base() * 1.05  # NomeClasse.nome_metodo_classe()
        return val_diaria


from carro import Carro
""" Obs.: RN (Regra de Negócio) para o carro intermediário:
    O preço da diária corresponde ao preço base de locação acrescido de 10%
"""
class Intermediario(Carro):
    def __init__(self, modelo):
        super().__init__(modelo)                          # Modo 1

    # Método obrigatório, sobrescreve o método abstrato da superclasse
    def preco_diaria(self):
        return Carro.get_preco_base() * 1.1
        # return self.get_preco_base() * 1.1
