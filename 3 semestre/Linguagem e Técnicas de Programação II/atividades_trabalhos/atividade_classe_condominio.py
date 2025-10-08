# Função auxiliar para imprimir uma linha decorativa
def linha():
    print("+=+="*69)  # Imprime o padrão "+=+=" repetido 69 vezes

# Classe Condomínio para representar moradores de um condomínio
class Condominio(object):
    # Método construtor com parâmetros obrigatórios
    def __init__(self, nome, aluguel, idade, apartamento, andar):
        self.nome = nome          # Nome do morador
        self.aluguel = aluguel    # Valor do aluguel
        self.idade = idade        # Idade do morador
        self.apartamento = apartamento  # Número do apartamento
        self.andar = andar        # Número do andar

    # Métodos getters (para obter valores)
    def get_nome(self):
        return self.nome
    
    # Métodos setters (para alterar valores)
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

    # Método para mostrar dados do primeiro morador
    def mostra_dados(self):
        linha()
        print(f"[MORADOR 1] \nNome do morador: {self.nome}")
        print(f"Valor do aluguel: R$ {self.aluguel},00")
        print(f"Idade: {self.idade} anos")
        print(f"Número do apartamento: {self.apartamento}")
        print(f"Número do andar: {self.andar}\n")

    # Método para mostrar dados do segundo morador
    def mostrar_dados2(self):
        linha()
        print(f"[MORADOR 2] \nNome do morador: {self.nome}")
        print(f"Valor do aluguel: R$ {self.aluguel},00")
        print(f"Idade: {self.idade} anos")
        print(f"Número do apartamento: {self.apartamento}")
        print(f"Número do andar: {self.andar}\n")

    # Método para retornar todos os atributos como dicionário
    def retornar_dados(self):
        linha()
        return vars(self)  # Retorna um dicionário com todos os atributos

# Bloco principal de execução
if __name__ == "__main__":
    # Cria 3 instâncias de moradores do condomínio
    morador1 = Condominio("Diego Cavalieri", 1200, 41, 1303, 13)
    morador2 = Condominio("Leanderson", 1200, 26, 101, 1)
    morador3 = Condominio("Everaldo", 1200, 18, 506, 5)

    # Mostra dados dos dois primeiros moradores
    morador1.mostra_dados()
    morador2.mostrar_dados2()

    # Retorna e exibe dados do terceiro morador como dicionário
    print(f"\n>>Retorna atributos<<\n\n[MORADOR 3] \n{morador3.retornar_dados()}\n")
    linha()

    # Demonstra a atualização de dados usando setters
    print("\n>>Teste do método set para atualiza o primeiro gabinete<< \n\n[MORADOR 1 ATUALIZADO]")
    morador1.set_nome("Jhon Arias")
    morador1.set_aluguel("1100")
    morador1.set_idade("26 anos")
    morador1.set_apartamento("1804")
    morador1.set_andar("18")

    # Mostra os dados atualizados do primeiro morador
    print(f"\n[MORADOR NOVO] \nNome do morador: {morador1.nome}")
    print(f"Valor do aluguel: R$ {morador1.aluguel},00")
    print(f"Idade: {morador1.idade}")
    print(f"Número do apartamento: {morador1.apartamento}")
    print(f"Número do andar: {morador1.andar}\n")
    linha()
