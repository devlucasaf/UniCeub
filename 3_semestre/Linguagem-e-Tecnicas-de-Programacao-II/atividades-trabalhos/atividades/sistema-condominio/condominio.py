def linha():
    print("+=+=" * 69)

class Condominio(object):

    def __init__(self, nome, aluguel, idade, apartamento, andar):
        self.nome = nome
        self.aluguel = aluguel
        self.idade = idade
        self.apartamento = apartamento
        self.andar = andar

    def get_nome(self):
        return self.nome

    def set_nome(self, novonome):
        self.nome = novonome

    def get_aluguel(self):
        return self.aluguel

    def set_aluguel(self, novo_valor):
        self.aluguel = novo_valor

    def get_idade(self):
        return self.idade

    def set_idade(self, nova_idade):
        self.idade = nova_idade

    def get_apartamento(self):
        return self.apartamento

    def set_apartamento(self, novo_apartamento):
        self.apartamento = novo_apartamento

    def get_andar(self):
        return self.andar

    def set_andar(self, novo_andar):
        self.andar = novo_andar

    def mostra_dados(self):
        linha()
        print(f"[MORADOR 1] \nNome do morador: {self.nome}")
        print(f"Valor do aluguel: R$ {self.aluguel},00")
        print(f"Idade: {self.idade} anos")
        print(f"Número do apartamento: {self.apartamento}")
        print(f"Número do andar: {self.andar}\n")

    def mostrar_dados2(self):
        linha()
        print(f"[MORADOR 2] \nNome do morador: {self.nome}")
        print(f"Valor do aluguel: R$ {self.aluguel},00")
        print(f"Idade: {self.idade} anos")
        print(f"Número do apartamento: {self.apartamento}")
        print(f"Número do andar: {self.andar}\n")

    def retornar_dados(self):
        linha()
        return vars(self)