# Função auxiliar para imprimir uma linha decorativa
def linha():
    print("+=+="*69)  # Imprime o padrão "+=+=" repetido 69 vezes

# Classe Restaurante para representar estabelecimentos gastronômicos
class Restaurante(object):
    # Método construtor com valores padrão
    def __init__(self, nome="Nome Padrão", endereco="Endereço Desconhecido", capacidade=250, preco=39.59):
        self.nome = nome            # Nome do restaurante
        self.endereco = endereco    # Endereço completo
        self.capacidade = capacidade  # Capacidade de clientes
        self.preco = preco          # Preço médio por pessoa

    # Métodos getters (para obter valores)
    def get_nome(self):
        return self.nome  # Retorna o nome do restaurante

    # Métodos setters (para alterar valores com validações)
    def set_nome(self, nome):
        if isinstance(nome, str):  # Valida se é string
            self.nome = nome
        else:
            print("Erro! O nome deve ser uma string!")

    def get_endereco(self):
        return self.endereco

    def set_endereco(self, endereco):
        if isinstance(endereco, str):  # Valida se é string
            self.endereco = endereco
        else:
            print("Erro: O endereço deve ser uma string.")

    def get_capacidade(self):
        return self.capacidade

    def set_capacidade(self, capacidade):
        if isinstance(capacidade, int) and capacidade >= 0:  # Valida se é inteiro positivo
            self.capacidade = capacidade
        else:
            print("Erro: Capacidade deve ser um número inteiro maior ou igual a zero.")

    def get_preco(self):
        return self.preco

    def set_preco(self, preco):
        if isinstance(preco, (int, float)) and preco >= 0:  # Valida se é número positivo
            self.preco = preco
        else:
            print("Erro! Valor não pode ser menor do que 0!")

    # Métodos para exibir informações
    def mostra_dados(self):
        print(f"\nNome: {self.nome}")
        print(f"Preço: R${self.preco:.2f}")  # Formata com 2 casas decimais
        print(f"Capacidade: {self.capacidade}")
        print(f"Endereço: {self.endereco}\n")

    def mostra_dados_com_get(self):
        print(f"\nNome: {self.get_nome()}")
        print(f"Preço: R${self.get_preco():.2f}")
        print(f"Capacidade: {self.get_capacidade()}")
        print(f"Endereço: {self.get_endereco()}\n")

    # Métodos de operações
    def retornar_dados(self):
        return {  # Retorna dicionário com os dados
            "nome": self.nome,
            "preco": self.preco,
            "capacidade": self.capacidade,
            "endereco": self.endereco
        }

    def aumentar_capacidade(self, capacidade):
        if capacidade > 0:  # Valida aumento positivo
            self.capacidade += capacidade  # Obs: Erro de digitação (deveria ser self.capacidade)
        else:
            print("Erro: A quantidade a ser aumentada deve ser maior que zero.")

    def desconto(self, percentual):
        if 0 < percentual < 100:  # Valida percentual válido
            self.preco -= self.preco * (percentual / 100)
        else:
            print("Erro: Percentual de desconto inválido.")

    def mudar_endereco(self, novo_endereco):
        if isinstance(novo_endereco, str):  # Valida se é string
            self._endereco = novo_endereco  # Obs: Atributo diferente do declarado (inconsistência)
        else:
            print("Erro: O novo endereço deve ser uma string.")

# Bloco principal de execução
if __name__ == "__main__":
    # Cria 3 restaurantes com diferentes configurações
    restaurante1 = Restaurante("Restaurante Quarteto Massas", "Rua Principal, 123", 250, 59.90)
    restaurante2 = Restaurante("Restaurante Academia do Pastel", "Avenida Secundária, 914", 54, 15)
    restaurante3 = Restaurante()  # Usa valores padrão

    # Demonstração dos métodos
    print(f"\n{">>Dados diretamente dos atributos<<":^224}\n")
    linha()
    restaurante1.mostra_dados()
    restaurante1.aumentar_capacidade(10)  # Aumenta capacidade
    linha()
    
    print(f"\n{">>Dados utilizando os métodos get<<":^224}\n")
    linha()
    restaurante1.mostra_dados_com_get()  # Mostra dados via getters
    linha()

    restaurante2.desconto(15)  # Aplica desconto de 15%
    restaurante2.mostra_dados()
    linha()

    # Configura restaurante3 que foi criado com valores padrão
    restaurante3.mostra_dados()  # Mostra valores iniciais
    linha()
    restaurante3.set_nome("Restaurante Master Carnes")
    restaurante3.set_preco(62.99)
    restaurante3.set_capacidade(70)
    restaurante3.set_endereco("Rua das Argilas, 834")
    restaurante3.mostra_dados()  # Mostra valores atualizados
    linha()

    restaurante1.aumentar_capacidade(3)  # Obs: Método com erro de digitação
    restaurante1.mostra_dados()
    linha()

    # Retorna e exibe dados do restaurante2 como dicionário
    dados_produto2 = restaurante2.retornar_dados()
    print(f"\n{dados_produto2}\n")
    linha()
