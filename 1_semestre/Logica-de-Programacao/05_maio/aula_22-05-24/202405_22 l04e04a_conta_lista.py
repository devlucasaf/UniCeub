"""            Comentários de várias linhas.

ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Resolva com lista:

- Desenvolva o programa que leia vários números digitados pelo usuário
e use o valor -1 como condição (flag) de saída da estrutura de repetição
Na tela de saída, mostre a quantidade de números digitados.

Teste 1: Entrada: 5, 6 e -1          Saída: Quantidade de números: 2
Teste 2: Entrada: 5, 6, 7 e -1       Saída: Quantidade de números: 3
Teste 3: Entrada: 5, 6, 6, 7 e -1    Saída: Quantidade de números: 4

- Dicas (algoritmo):
cria lista vazia
estrutura de repetição
    lê número
    testa condição saída
    armazena valor no final da lista
usa função quantidade de item na lista
tela de saída               (fim do algoritmo)
"""

# ct = 0                        # Não use
l_numeros = []                  # Cria uma lista vazia
print('Digite [ -1 ] para sair')
while True:  # Repetição while, repete enquanto condição verdadeira
    numero = int(input("Digite um número: "))  # Indentação é obritória
    if numero == -1:
        break                   # Sai da estrutura de repetição (while)
    # ct = ct + 1               # Não use
    l_numeros.append(numero)    # Armazena valor no final da lista
    # Fim da estrutura de repetição "while"
qtd_numeros = len(l_numeros)    # Quantidade de valores na lista
print(f'Quantidade de números: {qtd_numeros}')
# - Teoria de listas:
# 5   6   6   7         <- Valores armazenados na lista (digitados)
# 0   1   2   3         <- posições (índices) positivas da lista
''' ----- ALTERAÇÕES: 
a. Refaça sem usar a variável qtd_numeros
b. Mostre também todos os valores armazenados na lista (digitados).
c. Mostre (acrescente) a soma dos valores armazenados na lista. 
    ----- DICAS:
...                                                             # a.
print('Quantidade de números:', len(l_numeros)) 
print('Números na lista:\n', l_numeros)                         # b.
print('Soma dos números:', sum(l_numeros))                      # c.

- Teoria list:
l = []
l.append(2)
len(l)
sum(l)
print(l)

'''
