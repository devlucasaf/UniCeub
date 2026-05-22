from produto import Produto, linha

produto1 = Produto("Camisa", 59.99, 100, "Vestuário")
produto2 = Produto("Batedeira", 55.00, 50, "Eletrodoméstico")
produto3 = Produto("Calculadora", 25.00, 200, "Eletrônicos")

# MOSTRANDO DADOS DIRETAMENTE DOS ATRIBUTOS
print("\nDados diretamente dos atributos:\n")

linha()
produto1.mostra_dados_atributos()

linha()
produto2.mostra_dados_atributos()

linha()
produto3.mostra_dados_atributos()

linha()

# MOSTRANDO DADOS UTILIZANDO MÉTODOS GET
print("\nDados utilizando os métodos get:\n")

linha()
produto1.mostra_dados_metodos()

linha()
produto2.mostra_dados_metodos()

linha()
produto3.mostra_dados_metodos()

linha()

# AUMENTANDO QUANTIDADE
print("\nAumentando a quantidade do produto 1 em 20 unidades:\n")

produto1.aumentar_quantidade(20)

linha()
produto1.mostra_dados_atributos()

linha()

# TESTE DE PREÇO NEGATIVO
print("\nTentando definir um preço negativo:")
produto1.set_preco(-100)

# RETORNANDO DADOS EM DICIONÁRIO
print("\nDados retornados do produto 1:")

dados_produto1 = produto1.retorna_dados()

print(dados_produto1)