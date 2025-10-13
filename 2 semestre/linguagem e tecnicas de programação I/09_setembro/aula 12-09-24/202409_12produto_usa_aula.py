# Sintaxe:
# from nome_arquivo_sem_extensao import NomeClasse
from produto_aula import Produto
if __name__ == '__main__':                  # Atalho: mai <tab>
    prod_1 = Produto("Arroz", 19.00, 27.50, 67, 20)  # Chama o __init__
    print(prod_1)                           # Chama o método __str__
    # print(prod_1.__str__())               # Duas linhas equivalentes.
    # <__main__.Produto object at 0x000001A1E15B1B80>
    print('Produto 1:\nNome:', prod_1.get_nome())  # nome_objeto.nome_metodo()
    print('Qtd. estoque:', prod_1.get_qtd_estoque())
    print('Valor de compra:', prod_1.get_vlr_compra())
    qtd = int(input("Quantidade em estoque: "))
    prod_1.set_qtd_estoque(qtd)              # Argumento correto, int
    print('Qtd. estoque alterado:', prod_1.get_qtd_estoque())  # Confirma
    prod_1.set_qtd_estoque(-10)              # Argumento inconsistente
    print('Qtd. estoque alterado:', prod_1.get_qtd_estoque())  # Confirma
    print('Qtd. mínima:', prod_1.get_qtd_minima())
    qtd = int(input("Quantidade minima: "))
    prod_1.set_qtd_minima(qtd)          # Ex.: nome_objeto.nome_metodo(valor)
    print('Qtd. minima alterada:', prod_1.get_qtd_minima())  # Confirma
    prod_1.set_qtd_minima(23)           # Ex.: nome_objeto.nome_metodo(valor)
    print('Qtd. minima alterada:', prod_1.get_qtd_minima())  # Confirma
    prod_1.set_nome('Novo arroz')           # Argumento correto
    print('Nome alterado:', prod_1.get_nome())       # Teste
    prod_1.set_nome(15)                     # Argumento errado, int
    print('Nome, não alterou:', prod_1.get_nome())       # Teste
    prod_1.set_qtd_estoque(30)              # Argumento correto, int
    print('Qtd. estoque alterado:', prod_1.get_qtd_estoque())  # Confirma
    prod_1.set_qtd_estoque(-22)          # Argumento errado
    print('Qtd. estoque, não alterou:', prod_1.get_qtd_estoque())  # Confirma
    prod_1.set_vlr_compra(22.00)            # Argumento corrreto
    print(f'Valor de compra: {prod_1.get_vlr_compra()}')  # Teste
    print('Mostra dados:', prod_1)          # Duas linhas equivalentes
    print('Mostra dados:', prod_1.__str__())
    print("Lucro:", prod_1.lucro())
    prod_1.atualiza_estoque(3)      # O argumento é a quantidade vendida.
    print('Qtd. estoque:', prod_1.get_qtd_estoque())
    prod_1.repor_estoque(11)      # O argumento é a quantidade comprada.
    print('Qtd. estoque:', prod_1.get_qtd_estoque())
    print('Mostra dados:', prod_1)          # Teste
    # situacao = prod_1.alerta_estoque()    # Solução 1, com variável
    # if situacao == True:
    if prod_1.alerta_estoque():             # Solução 2, sem variável
        print("Precisa comprar.")
    else:
        print("Não precisa comprar.")
    # O operador ternário equivalente ao if ... else ... anterior.  - Solução 3
    # Sintaxe: (se condicão verdadeira) if condição else (se condição falsa)
    print("Precisa comprar.") if prod_1.alerta_estoque() else print("Não precisa comprar.")
    prod_1.repor_estoque(11)
    print('Qtd. estoque:', prod_1.get_qtd_estoque())  # Teste
