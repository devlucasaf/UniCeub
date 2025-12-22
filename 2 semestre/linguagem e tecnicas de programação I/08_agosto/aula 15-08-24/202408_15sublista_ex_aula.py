"""                 Comentários de várias linhas.

ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

Implemente:
1- uma lista com valores naturais até 20, use list(range(...))
2- uma lista com valores naturais até 20, use list(range(...)) com
   valores default
3- a cópia da lista, use slicing
4- uma lista na ordem inversa, use slicing
5- uma sublista com números pares, use slicing
6- uma sublista com números pares decrescente, use slicing
7- uma sublista com números ímpares, use slicing
8- uma sublista com números ímpares decrescente, use slicing
9- uma sublista com múltiplo de três, use slicing
10- uma sublista com múltiplo de cinco, use slicing
11- uma sublista com múltiplo de três e ímpar ao mesmo tempo, use slicing
12- uma lista com os meses do ano.
13- uma sublista com os meses do primeiro semestre
14- Uma sublista com os meses do segundo trimestre
15- Uma sublista com os meses do quarto trimestre com índices positivos
16- Uma sublista com os meses do quarto trimestre com índices negativos """
numeros = list(range(0, 21, 1))         # 1
print(numeros)
numeros = list(range(21))               # 2
print(numeros)
copia = numeros[:]                      # 3
print(copia)
inverso = numeros[::-1]                 # 4
print(inverso)
pares = numeros[::2]                    # 5
print(pares)
pares_dec = numeros[::-2]               # 6
print(pares_dec)
impares = numeros[1::2]                 # 7
print(impares)
impares_dec = numeros[-2::-2]           # 8
print(impares_dec)
multiplo_3 = numeros[0::3]
print(multiplo_3)
multiplo_5 = numeros[0::5]
print(multiplo_5)
multiplo_3_5 = numeros[0::6]            # 11
print(multiplo_3_5)
meses = ['janeiro', 'fevereiro', 'março', 'abril', 'maio', 'junho', 'julho',
         'agosto', 'setembro', 'outubro', 'novembro', 'dezembro' ]
tri4 = meses [:-4:-1]                   # 16
print(tri4)
"""
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20]
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20]
[0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20]
[20, 19, 18, 17, 16, 15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0]
[0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20]
[20, 18, 16, 14, 12, 10, 8, 6, 4, 2, 0]
[1, 3, 5, 7, 9, 11, 13, 15, 17, 19]
[19, 17, 15, 13, 11, 9, 7, 5, 3, 1]
[0, 3, 6, 9, 12, 15, 18]
[0, 5, 10, 15, 20]
[0, 6, 12, 18]
"""