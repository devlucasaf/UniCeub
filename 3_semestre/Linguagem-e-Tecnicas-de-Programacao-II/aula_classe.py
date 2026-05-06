# Códigos de cores ANSI para formatação de texto no terminal
red = "\33[1;49;31m"  # Texto vermelho
green = "\33[1;49;32m"  # Texto verde
yellow = "\33[1;49;33m"  # Texto amarelo
blue = "\33[1;49;34m"  # Texto azul
pink = "\33[1;49;35m"  # Texto rosa
cyan = "\33[1;49;36m"  # Texto ciano
white = "\33[1;49;37"  # Texto branco (faltando 'm' no final)
nc = "\33[m"  # Resetar formatação de cor


# Função para imprimir uma linha decorativa
def linha():
    print("+=+=" * 69)


# Classe que representa um Produto
class Produto(object):
    # Construtor com valores padrão
    def __init__(self, nome="Nome Padrão", preco=32.54, vendas=98, qntd_estoque=100, qntd_minima=10):
        self.nome = nome  # Nome do produto
        self.preco = preco  # Preço do produto
        self.vendas = vendas  # Quantidade de vendas
        self.qntd_estoque = qntd_estoque  # Quantidade em estoque
        self.qntd_minima = qntd_minima  # Quantidade mínima em estoque

    # --- MÉTODOS GETTER ---
    def get_nome(self):
        return self.nome

    def get_preco(self):
        return self.preco

    def get_vendas(self):
        return self.vendas

    def get_qntd_estoque(self):
        return self.qntd_estoque

    def get_qntd_minima(self):
        return self.qntd_minima

    # --- MÉTODOS SETTER ---
    def set_nome(self, nome):
        if isinstance(nome, str):
            self.nome = nome
        else:
            print("Erro! O nome deve ser uma string!")

    def set_preco(self, preco):
        if isinstance(preco, (int, float)) and preco >= 0:
            self.preco = preco
        else:
            print("Erro! Valor não pode ser menor do que 0!")

    def set_vendas(self, vendas):
        if isinstance(vendas, (int, float)) and vendas >= 0:
            self.vendas = vendas
        else:
            print("Erro! Valor não pode ser menor do que 0!")

    # Observação: Existem dois métodos set_qntd_estoque (o segundo sobrescreve o primeiro)
    def set_qntd_estoque(self, nova_qntd):
        self.qntd_estoque = nova_qntd

    def set_qntd_estoque(self, qntd_estoque):
        if isinstance(qntd_estoque, int) and qntd_estoque >= 0:
            self.qntd_estoque = qntd_estoque

    # Observação: Existem dois métodos set_qntd_minima (o segundo sobrescreve o primeiro)
    def set_qntd_minima(self, nova_qntdMin):
        self.qntd_minima = nova_qntdMin

    def set_qntd_minima(self, qntd_minima):
        if isinstance(qntd_minima, int) and qntd_minima >= 0:
            self.qntd_minima = qntd_minima

    # Aplica desconto ao preço do produto
    def desconto(self, percentual):
        if 0 < percentual < 100:
            self.preco -= self.preco * (percentual / 100)

    # Calcula o lucro (contém erro - deveria ser self.vendas ao invés de self.venda)
    def lucro(self):
        return self.venda - self.preco  # Isso causará um erro

    # Mostra dados do produto acessando atributos diretamente
    def mostrar_dados(self):
        print(f"\n{yellow}Nome: {self.nome}{nc}")
        print(f"{yellow}Preço: R${self.preco:.2f}{nc}")
        print(f"{yellow}Vendas: {self.vendas}{nc}")
        print(f"{yellow}Quantidade no estoque: {self.qntd_estoque}{nc}")
        print(f"{yellow}Quantidade mínima: {self.qntd_minima}{nc}")
        print(f"{yellow}Lucro: {lucro()}{nc}\n")  # Isso causará um erro

    # Mostra dados do produto usando métodos getter
    def mostrar_dadosGET(self):
        print(f"\n{red}Nome: {self.get_nome()}{nc}")
        print(f"{red}Preço: R${self.get_preco():.2f}{nc}")
        print(f"{red}Vendas: {self.get_vendas()}{nc}")
        print(f"{red}Quantidade no estoque: {self.get_qntd_estoque()}{nc}")
        print(f"{red}Quantidade mínima: {self.get_qntd_minima()}{nc}")
        print(f"{yellow}Lucro: {lucro()}{nc}\n")  # Isso causará um erro

    # Retorna os dados do produto como um dicionário
    def retornar_dados(self):
        return {
            "nome": self.nome,
            "preco": self.preco,
            "vendas": self.vendas,
            "qntd_estoque": self.qntd_estoque,
            "qntd_minima": self.qntd_minima
        }

    # Aumenta a quantidade em estoque (contém erro lógico - dobra o estoque ao invés de adicionar)
    def aumentar_estoque(self, qntd_estoque):
        if qntd_estoque > 0:
            self.qntd_estoque += self.qntd_estoque  # Deveria ser += qntd_estoque
        else:
            print("Erro: A quantidade a ser aumentada deve ser maior que zero.")


# Bloco principal de execução
if __name__ == "__main__":
    # Cria instâncias de produtos
    produto1 = Produto("Carregador", 26.98, 46, 80, 20)
    produto2 = Produto("Fone de ouvido", 120.45, 60, 50, 10)
    produto3 = Produto()  # Usa valores padrão

    # Mostra dados dos produtos
    print(f"\n{blue}{">>Mostrar dados com atributos<<":^224}{nc}\n")
    linha()
    produto1.mostrar_dados()
    produto1.aumentar_estoque(60)  # Isso dobrará o estoque (bug)
    linha()

    print(f"\n{pink}{">>Dados utilizando os métodos get<<":^224}{nc}\n")
    linha()
    produto1.mostrar_dadosGET()
    linha()

    # Aplica desconto e mostra dados do produto2
    produto2.desconto(15)
    produto2.mostrar_dados()
    linha()

    # Configura e mostra dados do produto3
    produto3.set_nome("Capinha")
    produto3.set_preco(40.59)
    produto3.set_vendas(80)
    produto3.set_qntd_estoque(170)
    produto3.set_qntd_minima(30)
    produto3.mostrar_dados()
    linha()
