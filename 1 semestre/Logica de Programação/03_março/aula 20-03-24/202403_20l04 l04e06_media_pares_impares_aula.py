"""               Comentários de várias linhas.

ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Programa anterior:
- Construa o programa que calcule a média aritmética dos números pares.
O usuário fornecerá os valores de entrada que pode ser um número qualquer
par ou ímpar. A condição de saída será o número 0 (zero).
- Dicas: 1. O operador modulo (%) pega o resto da divisão de dois números.
        2. media = soma / ct

- Programa atual:
- Construa o programa que calcule a média aritmética dos números pares
e a média aritmética dos números ímpares. O usuário fornecerá os valores
de entrada que pode ser um número qualquer par ou ímpar.
A condição de saída será o número 0.

- Dicas (algoritmo):
variáveis ct                 (inicia as variáveis)
variáveis soma
estrutura de repetição
    lê número
    testa condição saída
    número é par?
        contador par
        somador par
    senão, número é ímpar?
        contador impar
        somador impar
calcula médias
tela de saída               (fim do algoritmo)

Teste 1:   Entrada: 1, 2 e -1.          Saída: Média pares: 2
                                                Média ímpares: 1
Teste 2:   Entrada: 1, 2, 3, 4 e -1.    Saída: Média pares: 3
                                                Média ímpares: 2
"""
ct_par = 0                              # Inicializa as variáveis
ct_impar = 0
soma_par = 0
soma_impar = 0
print('Digite [0] para sair')
while True:                 # Laço (loop) de repetição while com o uso do break
    numero = int(input("Digite um número: "))
    if numero == 0:                     # Condição de saída
        break                           # Interrompe a repetição while
    if numero % 2 == 0:                 # Se resto da divisão de 2 for 0, é par
        soma_par = soma_par + numero    # soma_par += numero    (somador)
        ct_par = ct_par + 1             # ct_par += 1           (contador)
    else:                               # Senão, ele é ímpar
        soma_impar = soma_impar + numero
        ct_impar = ct_impar + 1
    # Fim da estrutura de repetição (while)
media_par = soma_par/ct_par
print(f"Média dos pares: {media_par:.2f}")
media_impar = soma_impar/ct_impar
print(f"Média dos ímpares: {media_impar:.2f}")
''' ----- ALTERAÇÕES:
a. Mostre também a quantidade de números pares.
b. Mostre também a quantidade de números ímpares.
c. Mostre também a quantidade de números digitados.
d. Calcule e mostre a porcentagem dos números pares.

    ----- DICAS:
print("Quantidade de pares:", ct_par)                               # a.                                      
print("Quantidade de pares: ", ct_par)                              # b.                                      
print("Quantidade de números digitados: ", ct_par + ct_impar)       # c.

'''
