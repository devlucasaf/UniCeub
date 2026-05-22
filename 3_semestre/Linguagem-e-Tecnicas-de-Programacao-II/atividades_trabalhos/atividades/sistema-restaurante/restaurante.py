# Função auxiliar para imprimir uma linha decorativa
def linha():
    print("+=+=" * 69)


class Restaurante:
    # Método construtor
    def __init__(self, nome="Nome Padrão", endereco="Endereço Desconhecido", capacidade=250, preco=39.59):
        self.nome = nome
        self.endereco = endereco
        self.capacidade = capacidade
        self.preco = preco

    # Getters e Setters
    def get_nome(self):
        return self.nome

    def set_nome(self, nome):
        if isinstance(nome, str):
            self.nome = nome
        else:
            print("Erro! O nome deve ser uma string!")

    def get_endereco(self):
        return self.endereco

    def set_endereco(self, endereco):
        if isinstance(endereco, str):
            self.endereco = endereco
        else:
            print("Erro: O endereço deve ser uma string.")

    def get_capacidade(self):
        return self.capacidade

    def set_capacidade(self, capacidade):
        if isinstance(capacidade, int) and capacidade >= 0:
            self.capacidade = capacidade
        else:
            print("Erro: Capacidade deve ser um número inteiro maior ou igual a zero.")

    def get_preco(self):
        return self.preco

    def set_preco(self, preco):
        if isinstance(preco, (int, float)) and preco >= 0:
            self.preco = preco
        else:
            print("Erro! Valor não pode ser menor do que 0!")

    # Métodos de exibição
    def mostra_dados(self):
        print(f"\nNome: {self.nome}")
        print(f"Preço: R$ {self.preco:.2f}")
        print(f"Capacidade: {self.capacidade}")
        print(f"Endereço: {self.endereco}\n")

    def mostra_dados_com_get(self):
        print(f"\nNome: {self.get_nome()}")
        print(f"Preço: R$ {self.get_preco():.2f}")
        print(f"Capacidade: {self.get_capacidade()}")
        print(f"Endereço: {self.get_endereco()}\n")

    def retornar_dados(self):
        return {
            "nome": self.nome,
            "preco": self.preco,
            "capacidade": self.capacidade,
            "endereco": self.endereco
        }

    # Métodos de manipulação
    def aumentar_capacidade(self, capacidade):
        if capacidade > 0:
            self.capacidade += capacidade
        else:
            print("Erro: A quantidade a ser aumentada deve ser maior que zero.")

    def desconto(self, percentual):
        if 0 < percentual < 100:
            self.preco -= self.preco * (percentual / 100)
        else:
            print("Erro: Percentual de desconto inválido.")

    def mudar_endereco(self, novo_endereco):
        if isinstance(novo_endereco, str):
            self.endereco = novo_endereco
        else:
            print("Erro: O novo endereço deve ser uma string.")
