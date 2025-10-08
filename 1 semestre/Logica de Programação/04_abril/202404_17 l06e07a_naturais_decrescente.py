"""            Comentários de várias linhas.

  CEUB  -  FATECS  -  BCC  -  ADS  -  Programação  -  Prof. Barbosa
ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Elabore o programa que gere a sequência dos números naturais
na ordem decrescente de 7 até 0.

Saída:

- Ordem decrescente:
7
6
5
4
3
2
1
0
"""


print('- Ordem decrescente:')         # Cabeçalho
for decrescente in range(7, -1, -1):  # for decrescente in range(7, 0-1, -1):
    print(decrescente)          # para cada item de 7 e -1, em saltos de -1
''' ----- ALTERAÇÕES:
a. Mostre também a soma da sequência gerada. Use somador.  
    Teste 2: Saída: Soma = 28
    ----- Dicas:
soma = 0                          # Antes do for                    # a.
     soma = soma + decrescente    # Dentro do for, indentado  
print("Soma =", soma)             # Depois do for

---

- Outra solução:
for decrescente in reversed(range(0, 7 + 1, 1)):
    print (decrescente)              

'''
