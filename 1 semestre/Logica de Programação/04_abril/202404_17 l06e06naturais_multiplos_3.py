"""            Comentários de várias linhas.

  CEUB  -  FATECS  -  BCC  -  ADS  -  Programação  -  Prof. Barbosa
ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Elabore o programa que gere a sequência dos números naturais
múltiplos de 3 até 21.

- Teste:

Saída:

- Números múltiplo de 3:
0
3
6
9
12
...
21
Encerrou o programa.

"""

print('- Números múltiplo de 3:')    # Cabeçalho.
for multiplo3 in range(0, 21+1, 3):  # for multiplo3 in range(22, 3):
    print(multiplo3)
print('\nEncerrou o programa.')
''' --- ALTERAÇÕES:
a. Mostre também a soma dos valores da sequência gerada. 
   Saída: Soma = 84
b. Mostre também a média dos valores da sequência gerada. 
   Saída: Média = 10.5 
   
c. Obtenha o mesmo resultado usando passo igual a 1   
    --- DICAS ABAIXO:
soma = 0                            # Antes do for          # a.
    soma = soma + multiplo3         # Dentro do for
print ('Soma =', soma)              # Depois do for   
ct = 0                              # Antes do for          # b.
    ct = ct + 1                     # Dentro do for
media = soma / ct                   # Depois do for
print("Média =", media              # Depois do for
for multiplo3 in range(0, 21 + 1, 1):                       # c.
    # Verifica se o resto da divisão por 3 é igual a zero
    if (multiplo3 % 3 == 0):
        print(multiplo3, end=" ")                 

'''
