"""             Comentários de várias linhas.

  CEUB  -  FATECS  -  BCC  -  ADS  -  Programação  -  Prof. Barbosa
ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Programa anterior:
- Elabore o programa que imprima a tabuada de multiplicação do número
5 (cinco) de 1 (um) até 10 (dez). Gere o seguinte layout:

- Saída:
Tabuada de multiplicação:
 1 x 5 =  5
 2 x 5 = 10
 3 x 5 = 15
. . .
10 x 5 = 50

- Programa atual:
- Torne o programa anterior mais interessante. Agora, o programa deve
gerar a tabuada de multiplicação de 1 (um) até 10 (dez) de um número
inteiro qualquer fornecido pelo usuário.
"""

numero = int(input("Número para obter a tabuada: "))  # Recebe o valor
# range(1, 10) não inclui o valor 10, por isso 11 foi utilizado
for i in range(1, 11, 1):  # for i in range(1, 10+1):
    print(i, " x ", numero, " = ", i * numero)       # Mostra a tabela

''' ----- ALTERAÇÕES:
a. Coloque um cabeçalho (título) antes de gerar a tabuada.
b. Refaça o print com o f-strint, alinhamento à direita do layout de saída.
c. Permita que o usuário escolha a tabuada de multiplicação ou soma.
d. Mostre uma mensagem de erro se o usuário digitar operação diferente
   de 'x' e '+'

    ----- DICAS ABAIXO:
print('Tabuada de multiplicação de ', numero)  # Antes do for       # a.
print(f"{i:2} x {numero} = {i * numero:2}")                         # b.
operador = input('Tabuada de multiplicação [x] ou de soma [+]')     # c.
if operador == '+':
    for i in range(1, 11):      # for i in range(1, 10+1):
        print(i, " x ", numero, " = ", i * numero)              
elif operador == '*':
    for i in range(1, 11):      # for i in range(1, 10+1):
        print(i, " x ", numero, " = ", i * numero)   
else:                                                               # d.
    print('Opção inválida')           
 
'''
