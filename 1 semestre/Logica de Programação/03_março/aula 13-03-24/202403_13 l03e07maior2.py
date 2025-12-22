"""               Comentários de várias linhas.

ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Construa o programa que leia dois valores quaisquer e mostre o maior
deles ou mostre a mensagem “Os valores são iguais.”

Teste 1: Entrada: 5 e 10                    Saída: O maior valor é 10

Teste 2: Entrada: 10 e 5                    Saída: O maior valor é 10

Teste 3: Entrada: 5 e 5                     Saída: Os valores são iguais.

- Passos para a implementaçao do programa:
Entrada de dados (leia)
Testes (se ...)
Saída de dados (escreva)
"""

# Recebe os dois valores digitados pelo usuário
valor1 = float(input("Primeiro valor: "))
valor2 = float(input("Segundo valor: "))
if valor1 > valor2:
    print(f"O maior valor é: {valor1}")
elif valor2 > valor1:                           # Senão se ...
    print(f"O maior valor é: {valor2}")
else:
    print("Os valores são iguais.")
''' ----- ALTERAÇÕES:
a. Se eles forem diferentes, mostre os valores digitados na ordem decrescente
b. Se eles forem iguais, mostre a mensagem e o valor digitado.

    ----- DICAS ABAIXO:
if valor1 > valor2:                                                 # a
    print("Ordem decrescente:", valor1, valor2)
elif valor2 > valor1:                 # caso contrário
    print("Ordem decrescente:", valor2, valor1)   
else:                                                               # b
    print("Os valores são iguais", valor1)               
                                                                                
'''
