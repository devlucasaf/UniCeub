"""             Comentários de várias linhas.

ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Escreva o programa que mostra todos os valores possíveis de um dado.
Lembre-se do objeto “dado” que normalmente é utilizado em jogos de mesa.

Dado:                (Tela de saída)
1
2
3
4
5
6

"""


# for dado in range(1, 6+1):                # Passo 1 é o valor default.
for dado in range(1, 6+1, 1):               # for dado in range(1, 7, 1):
    print(f"Lado: {dado}")

''' ----- ALTERAÇÕES:
a. Coloque um cabeçalho antes de mostrar os valores.
b. Mostre a quantidade de repetições executadas, ou seja,
    a quantidade de números mostradas na tela. Use contador.
c. Mostre os números gerados na horizontal.

    ----- DICAS:
print('Valores de um dado:')                            # a.
ct = 0                              // Antes do for     # b.
    ct = ct + 1  // ct += 1         // Dentro do for
print("Quantidade:", ct)            // Depois do for

    print("Lado:", dado, end=" ")                       # c.

'''
