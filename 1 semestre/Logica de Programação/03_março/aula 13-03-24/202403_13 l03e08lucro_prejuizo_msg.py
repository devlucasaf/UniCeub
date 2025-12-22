"""               Comentários de várias linhas.


- Sintaxe do if ... elif ... else ... :
if condicao1:             # A indentação é obrigatória.
    print (“Mensagem 1”)  # Executa, se a condição1 for verdadeira
elif condicao2:
    print(“Mensagem 2”)   # Executa, se a condição2 for verdadeira
else:
    print("Mensagem 3")   # Executa, se todos os testes anteriores forem falsos

- Analise o resultado de uma transação comercial. Verifique a situação final
do comerciante trabalhando com os valores lidos, ou seja, o preço de compra
e o preço de venda. Gere a tela de saída com uma das seguintes mensagens:
“Teve lucro.”, “Teve prejuízo.” ou “Os valores são iguais.”.

Teste 1: Entrada: compra = 1000, venda = 1200     Saída: Teve lucro.
Teste 2: Entrada: compra = 1200, venda = 1000     Saída: Teve prejuízo.
Teste 3: Entrada: compra = 1000, venda = 1000     Saída: Os valores são iguais.
"""
# Recebe os valores de compra e de venda
# Lê o valor, converte para float e atribui à variável
vl_compra = float(input("Preço de compra: "))
vl_venda = float(input("Preço de venda: "))
if vl_venda > vl_compra:
    print("Teve lucro.")
elif vl_venda < vl_compra:
    print("Teve prejuízo.")
else:
    print("Os valores são iguais.")
''' --- Alterações:
a. Na saída, mostre também o valor do preço de compra e do preço de venda.

    --- Dicas:
print('Preço de compra:', vl_compra)                               # a.
print('Preço de venda:', vl_venda)

'''
