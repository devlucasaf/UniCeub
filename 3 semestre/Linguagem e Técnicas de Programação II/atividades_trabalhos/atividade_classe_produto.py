# Função auxiliar para imprimir uma linha decorativa
def linha():
    print("+=+="*69)  # Imprime o padrão "+=+=" repetido 69 vezes

# Classe Produto para representar itens de um estoque/comércio
class Produto():
    # Método construtor com type hints (dicas de tipo)
    def __init__(self, nome: str, preco: float, quantidade: int, categoria: str):
        self.nome = nome        # Nome do produto (string)
        self.preco = preco      # Preço unitário (float)
        self.quantidade = quantidade  # Quantidade em estoque (int)
        self.categoria = categoria    # Categoria do produto (string)

    # Métodos getters (para obter valores)
    def get_nome(self) -> str:
        return self.nome  # Retorna o nome do produto

    def set_nome(self, nome: str) -> None:
        self.nome = nome  # Define um novo nome para o produto

    def get_preco(self) -> float:
        return self.preco  # Retorna o preço do produto

    def set_preco(self, preco: float) -> None:
        if preco >= 0:  # Valida se preço não é negativo
            self.preco = preco
        else:
            print("Erro: O preço não pode ser negativo!.")

    def get_quantidade(self) -> int:
        return self.quantidade  # Retorna a quantidade em estoque

    def set_quantidade(self, quantidade: int) -> None:
        self.quantidade = quantidade  # Define nova quantidade

    def get_categoria(self) -> str:
        return self.categoria  # Retorna a categoria do produto

    def set_categoria(self, categoria: str) -> None:
        self.categoria = categoria  # Define nova categoria

    # Método para mostrar dados acessando atributos diretamente
    def mostra_dados_atributos(self) -> None:
        print(f"\nNome: {self.nome}")
        print(f"Preço: R$ {self.preco:.2f}")  # Formata com 2 casas decimais
        print(f"Quantidade: {self.quantidade}")
        print(f"Categoria: {self.categoria}\n")

    # Método para mostrar dados usando os métodos get
    def mostra_dados_metodos(self) -> None:
        print(f"\nNome: {self.get_nome()}")
        print(f"Preço: R$ {self.get_preco():.2f}")
        print(f"Quantidade: {self.get_quantidade()}")
        print(f"Categoria: {self.get_categoria()}\n")

    # Retorna os dados do produto como dicionário
    def retorna_dados(self) -> dict:
        return {
            "nome": self.nome,
            "preco": self.preco,
            "quantidade": self.quantidade,
            "categoria": self.categoria
        }

    # Método para aumentar a quantidade em estoque
    def aumentar_quantidade(self, valor: int) -> None:
        if valor > 0:  # Valida se o aumento é positivo
            self.quantidade += valor
        else:
            print("Erro: O valor de aumento deve ser positivo.")

# Bloco principal que executa quando o script é rodado diretamente
if __name__ == "__main__":
    # Cria 3 produtos diferentes para demonstração
    produto1 = Produto("Camisa", 59.99, 100, "Vestuário")
    produto2 = Produto("Batedeira", 55.00, 50, "Eletrodoméstico")
    produto3 = Produto("Calculadora", 25.00, 200, "Eletrônicos")

    # Mostra dados acessando atributos diretamente
    print("\nDados diretamente dos atributos:\n")
    linha()
    produto1.mostra_dados_atributos()
    linha()
    produto2.mostra_dados_atributos()
    linha()
    produto3.mostra_dados_atributos()
    linha()

    # Mostra dados usando os métodos get (forma recomendada)
    print("\nDados utilizando os métodos get:\n")
    linha()
    produto1.mostra_dados_metodos()
    linha()
    produto2.mostra_dados_metodos()
    linha()
    produto3.mostra_dados_metodos()
    linha()

    # Demonstra o método de aumentar quantidade
    print("\nAumentando a quantidade do produto 1 em 20 unidades:\n")
    produto1.aumentar_quantidade(20)
    linha()
    produto1.mostra_dados_atributos()
    linha()

    # Testa a validação de preço negativo
    print("\nTentando definir um preço negativo:")
    produto1.set_preco(-100)

    # Mostra dados retornados como dicionário
    print("\nDados retornados do produto 1:")
    dados_produto1 = produto1.retorna_dados()
    print(dados_produto1)
