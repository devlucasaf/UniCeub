"""            Comentários de várias linhas.

ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Construa o programa que encontre o menor valor de um conjunto de
valores inteiros digitados pelo usuário. Na condição de saída, use
o valor 0 (zero) que não será considerado na pesquisa.

- Ideia (lógica) pra resolver o problema: preciso de quantas variáveis?
(variáveis)     Tempo1          Tempo2      Tempo3      Tempo4      Tempo5
valor (input)                   2           3           1           0
menor_valor     9999999999      2           -           1

- Dicas (algoritmo):
variável menor_valor        (inicia a variável)
estrutura de repetição
    lê valor
    testa condição saída
    compara o número lido (valor) com o menor valor
    atualiza ou não o menor valor
Tela de saída               (fim do algoritmo)

Teste 1: valor: 2, 1, 3, 0   Saída: Menor = 1
Teste 2: valor: 1, 3, 2, 0   Saída: Menor = 1
Teste 3: valor: 3, 2, 1, 0   Saída: Menor = 1
"""

menor_valor = 99999999          # Valor inicial da variável menor_valor
# menor_valor = float('inf')    # O maior valor possível no Python
print('Digite [0] para sair')   # Executa uma vez
while True:                     # Passa várias vezes
    valor = int(input("Digite um valor: "))  # Recebe um número
    if valor == 0:              # Se o valor for igual a zero
        break                   # Saí do while, encerra o laço.
    if valor < menor_valor:
        menor_valor = valor     # Atualiza a variável menor_valor.
    # Fim da estrutura de repetição (while).
print(f"O menor valor é: {menor_valor}")   # Executa uma vez

''' ----- ALTERAÇÕES:
a. Mostre também a quantidade de valores digitados, na tela de saída.
b. Mostre também a soma dos valoes digitados, na tela de saída.         

    ----- DICAS ABAIXO:
ct = 0                                           # Antes do while   # a.
    ct = ct + 1                                  # Dentro do while
print ('Quantidade de valores digitados: ', ct)  # Depois do while       

soma = 0                                         # Antes do while   # b.
    soma = soma + valor                          # Dentro do while
print ('Soma dos valores digitados: ', soma)     # Depois do while   

'''
