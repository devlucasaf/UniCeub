# Função auxiliar para imprimir linha decorativa
def linha():
    print("+=+=" * 69)

class Pessoa:
    def __init__(self, nome, idade, genero="Não informado"):
        self.nome = nome
        self.idade = idade
        self.genero = genero

    def get_nome(self):
        return self.nome

    def get_idade(self):
        return self.idade

    def get_genero(self):
        return self.genero

    def set_nome(self, nome):
        self.nome = nome

    def set_idade(self, idade):
        if idade > 0:
            self.idade = idade
        else:
            print("Idade inválida, deve ser um valor positivo.\n")

    def set_genero(self, genero):
        self.genero = genero

    def apresentar(self):
        print(f"\nNome: {self.nome}")
        print(f"Idade: {self.idade}")
        print(f"Gênero: {self.genero}\n")