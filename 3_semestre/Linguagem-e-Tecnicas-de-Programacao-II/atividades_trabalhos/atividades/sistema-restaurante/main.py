from restaurante import Restaurante, linha

# Criação dos restaurantes
restaurante1 = Restaurante("Restaurante Quarteto Massas", "Rua Principal, 123", 250, 59.90)
restaurante2 = Restaurante("Restaurante Academia do Pastel", "Avenida Secundária, 914", 54, 15)
restaurante3 = Restaurante()

# Dados diretamente dos atributos
print(f"\n{'>>Dados diretamente dos atributos<<':^80}\n")
linha()
restaurante1.mostra_dados()
restaurante1.aumentar_capacidade(10)
linha()

# Dados utilizando getters
print(f"\n{'>>Dados utilizando os métodos get<<':^80}\n")
linha()
restaurante1.mostra_dados_com_get()
linha()

# Desconto no restaurante 2
restaurante2.desconto(15)
restaurante2.mostra_dados()
linha()

# Restaurante 3 com valores padrão
restaurante3.mostra_dados()
linha()

restaurante3.set_nome("Restaurante Master Carnes")
restaurante3.set_preco(62.99)
restaurante3.set_capacidade(70)
restaurante3.set_endereco("Rua das Argilas, 834")

restaurante3.mostra_dados()
linha()

# Aumentando capacidade
restaurante1.aumentar_capacidade(3)
restaurante1.mostra_dados()
linha()

# Retornando dados em dicionário
dados_restaurante2 = restaurante2.retornar_dados()
print(f"\n{dados_restaurante2}\n")
linha()
