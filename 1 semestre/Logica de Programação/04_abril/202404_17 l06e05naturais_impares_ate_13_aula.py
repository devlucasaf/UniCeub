"""            Comentários de várias linhas.

  CEUB  -  FATECS  -  BCC  -  ADS  -  Programação  -  Prof. Barbosa
ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Elabore o programa que gere a sequência dos números naturais ímpares até 13.

- Teste:







Saída:
- Números ímpares:
1
3
5
...
13
"""
print('- Números ímpares:')                 # Cabeçalho.
for impar in range(1, 13+1, 2):             # for impar in range(1, 14, 2):
    print(impar)
''' ----- ALTERAÇÕES:
a. Mostre a quantidade de valores gerados, use contador. Saída: Quantidade = 7
b. Mostre a soma dos valores da sequência, use somador.  Saída: Soma = 49
c. Refaça o exercício usando o passo 1.
   ----- DICAS ABAIXO:
ct = 0                                  # Antes do for                  # a.
    ct = ct + 1                         # Dentro do for, indentado
print("Quantidade =", ct)               # Depois do for
soma = 0                                # Antes do for                  # b.
    soma = soma + impar                 # Dentro do for, indentado
print ("Soma = ", soma)                 # Depois do for         
for i in range(0, 13 + 1, 1):                                           # c.
    if i % 2 != 0:          # if i % 2 == 1:     # se i é impar
        print(i)                                         
            
'''
