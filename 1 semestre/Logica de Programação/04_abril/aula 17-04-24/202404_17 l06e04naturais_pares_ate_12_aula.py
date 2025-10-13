"""            Comentários de várias linhas.

ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Elabore o programa que gere a sequência dos números naturais
pares até 12.

- Teste:

Saída:

- Números naturais pares:
0
2
4
6
...
12

"""



print('- Números naturais pares:')      # Cabeçalho.
for par in range(0, 13, 2):             # for par in range(0, 12+1, 2):
    print(par)                          # Na vertical.
print('Encerrou o programa.')
''' --- ALTERAÇÕES: 
a. Mostre a sequência dos números na horizontal.
print(par, end=" ")                                             # a.
b. Coloque uma vírgula entre os números da sequência.    
print(par, end=", ")                                            # b.
c. Substiua a vírgula do último número por um ponto (.). 
    Dica: if ... else


- Obs.:
    # o end=" " evita a quebra de linha, o padrão é end="\n"
    print(par, end=", ")                # Na horizontal.

'''
