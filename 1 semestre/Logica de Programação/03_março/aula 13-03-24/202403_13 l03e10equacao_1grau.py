"""               Comentários de várias linhas.

ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Sintaxe do if ... else:
if condição:                        # A indentação é obrigatória.
    print (“Mensagem 1”)            # Executado se a condição for verdade
else:
    print(“Mensagem 2”)             # Executado se a condição for falsa

- Construa o programa que calcule a raiz ( x ) de uma equação do
primeiro grau. Expressão de uma equação do 1º grau:  a x + b = 0.

- Passos para isolar o valor de x (raiz):
a x + b = 0
a x = -b
x = - b
    a

Teste 1: Entrada: a = 2 e b = 3.      Resposta: Raiz = -1.5
Teste 2: Entrada: a = 2 e b = -4.     Resposta: Raiz = 2.0
Teste 3: Entrada: a = 0 e b = 1.      Resposta: Não posso dividir por zero.

- Passos para a implementaçao do programa:
Entrada de dados (leia)
Cálculo
Testes (se ...)
Saída de dados (escreva)

"""
# Solução 1:
a = int(input("Valor de a: "))  # Lê, converte para int e atribui à variável
b = int(input("Valor de b: "))
x = - b / a
print(f'Raiz = {x}')             # Imprime a raiz x

# Solução 2:
a = int(input("Valor de a: "))  # Lê, converte para int e atribui à variável
b = int(input("Valor de b: "))
if a == 0:
    print("Não posso dividir por zero.")
else:
    x = - b / a
    print(f'Raiz = {x}')             # Imprime a raiz x

# Solução 3:
a = int(input("Valor de a: "))
if a == 0:                          # a é igual a zero?
    print("Não posso dividir por zero.")
else:
    b = int(input("Valor de b: "))
    x = - b / a
    print(f'Raiz = {x}')

# Solução 4:
a = int(input("Valor de a: "))
if a != 0:                          # a é diferente de zero?
    b = int(input("Valor de b: "))
    x = -b / a
    print(f'Raiz = {x}')
else:
    print("Não posso dividir por zero.")

""" --- Alterações:
a. Se o valor de "a" for diferente de zero, mostre também os valores
lidos "a" e "b".

"""
