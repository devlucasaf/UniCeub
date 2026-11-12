# Função auxiliar para imprimir uma linha decorativa
def linha():
    print("+=+=" * 69)

class Produto:

    def __init__(self, nome: str, preco: float, quantidade: int, categoria: str):
        self.nome = nome
        self.preco = preco
        self.quantidade = quantidade
        self.categoria = categoria


    def get_nome(self) -> str:
        return self.nome

    def set_nome(self, nome: str) -> None:
        self.nome = nome

    def get_preco(self) -> float:
        return self.preco

    def set_preco(self, preco: float) -> None:
        if preco >= 0:
            self.preco = preco
        else:
            print("Erro: O preço não pode ser negativo!")

    def get_quantidade(self) -> int:
        return self.quantidade

    def set_quantidade(self, quantidade: int) -> None:
        self.quantidade = quantidade

    def get_categoria(self) -> str:
        return self.categoria

    def set_categoria(self, categoria: str) -> None:
        self.categoria = categoria

    def mostra_dados_atributos(self) -> None:
        print(f"\nNome: {self.nome}")
        print(f"Preço: R$ {self.preco:.2f}")
        print(f"Quantidade: {self.quantidade}")
        print(f"Categoria: {self.categoria}\n")

    def mostra_dados_metodos(self) -> None:
        print(f"\nNome: {self.get_nome()}")
        print(f"Preço: R$ {self.get_preco():.2f}")
        print(f"Quantidade: {self.get_quantidade()}")
        print(f"Categoria: {self.get_categoria()}\n")

    def retorna_dados(self) -> dict:
        return {
            "nome": self.nome,
            "preco": self.preco,
            "quantidade": self.quantidade,
            "categoria": self.categoria
        }

    def aumentar_quantidade(self, valor: int) -> None:
        if valor > 0:
            self.quantidade += valor
        else:
            print("Erro: O valor de aumento deve ser positivo.")