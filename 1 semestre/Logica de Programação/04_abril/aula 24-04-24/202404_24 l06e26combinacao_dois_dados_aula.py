"""             Comentários de várias linhas.

ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Programa anterior:
- Escreva o programa que mostra todos os valores possíveis de um dado.
Lembre-se do objeto “dado” que normalmente é utilizado em jogos de mesa.

- Pragrama atual:
- Escreva o programa que mostre todas as combinações possíveis com os
valores de dois dados (objeto “dado” utilizado em jogos) na vertical.
Ou seja, mostre todas as possibilidades.

Dado 1 - Dado 2:                (Tela de saída)
1 - 1
1 - 2
1 - 3
1 - 4
1 - 5
1 - 6
2 - 1
2 - 2
2 - 3
...
2 - 6
...
6 - 6


- Teoria de fors encadeados ou anninhados:
- Sintaxe de fors encadeados ou aninhados.
for valor1 in range(inicio1 , fim1+1 , passo):     # for externo.
    for valor2 in range(inicio2, fim2+1 , passo):  # for interno, indentado
        . . .
    # Fim do for interno (for valor2 ...)
# Fim do for externo (for valor1 ...)               """

# Resposta do exercício:
for dado1 in range(1, 6+1):      # for externo.
    for dado2 in range(1, 6+1):  # for interno, indentação obrigatória.
        print(f'{dado1} - {dado2}')
    # Fim do for interno, dado2
# Fim do for externo, dado1

''' ----- ALTERAÇÕES:
a. Coloque um cabeçalho (título) antes das combinações. 
    Ex.: "Dado 1 - Dado 2:"
b. Mostre a quantidade de combinações geradas nos fors aninhados. 
    Saída: Qtd=36
c. Mostre todas as combinações na horizontal.
d. Melhore o leiaute, mostre seis combinações por linha.
1 - 1       1 - 2       1 - 3       1 - 4       1 - 5      1 - 6
2 - 1       2 - 2       2 - 3       2 - 4       2 - 5      2 - 6
    .  .  .
6 - 1       6 - 2       6 - 3       6 - 4       6 - 5      6 - 6         

    ----- DICAS ABAIXO:
print('Dado1 - Dado2:')                         # Antes do for      # a.  
ct = 0                                                              # b.
for dado1 in range(1, 6+1):                     # for externo.
    for dado2 in range(1,6+1):                  # for interno.
        print(dado1, ' - ', dado2)
        ct = ct + 1         # ct += 1           # Dentro do for
print('Quantidade = ', ct)          
for dado1 in range(1, 6+1):                                         # c.
    for dado2 in range(1,6+1):                              
        print(dado1, ' - ', dado2, end=" ")      # (solução 1)    
        #print(f"({dado1} - {dado2})", end=" ")  # (solução 2)
    
'''
