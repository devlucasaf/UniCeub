"""   
Teclas de atalho: ctlr <d>, duplica linha. ctrl <y>, apaga linha. ctrl </>, comenta linha.

- Elabore o programa que gere a sequência dos números naturais até 10 na vertical.

Teste 1 - Saída:
Números naturais na vertical:
0
1
2
3
...
10
Encererou o programa.                       """
print('- Números naturais na vertical:')                        # print('Cabeçalho')
for i in range(0, 11):        # for i in range (0, 10+1, 1):    # for i in range(11):
    print(i)                  # Mostra i na tela. O print() quebra a linha por padrão
print('Encerrou o programa.')
''' ----- ALTERAÇÕES:
a. Faça com for, substitua o range(0, 11) por uma lista equivalente.
b. No final, mostre a quantidade de números da sequência. Não use contador. 
    Saída:  Quantidade = 11
c. Resolva usando list comprehensions (compreensão de listas).
    ----- DICAS:    
lista1 = [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10]     (solução 1, lista feita na mão)     # a.
for i in lista1:
    print(i)                                                         
lista2 = range (0, 10+1, 1)                     (solução 2, lista feita com range)
for i in lista2:
    print(i)                                                      
qtd = len(lista1)                                                                   # b.
print ('Quantidade:', qtd)                      # Depois do for       
[print(i) for i in range(0, 10+1, 1)]           (solução 1)                         # c.
lista3 = [i for i in range(0, 10+1, 1)]         (solução 2)  
print(lista3)
lista4 = [i for i in range(0, 20+1, 2)]         (solução 1)                         # d.
print(lista4)
lista5 = [2*i for i in range(0, 10+1, 1)]       (solução 2)                         # e.
print(lista5)


- Teoria:
List Comprehensions (Compreensão de Listas)
List Comprehension foi concebida na PEP 202 e é uma forma concisa de criar e
manipular listas.

Sua sintaxe básica é:
[expr for item in lista]
Em outras palavras: aplique a expressão expr em cada item da lista.

Exemplo: dado o seguinte código:
for item in range(10):
    lista.append(item**2)
Podemos reescrevê-lo, utilizando list comprehensions, da seguinte forma:
lista = [item**2 for item in range(10)]

https://pythonacademy.com.br/blog/list-comprehensions-no-python

'''
