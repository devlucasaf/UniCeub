"""             Comentários de várias linhas.

  CEUB  -  FATECS  -  BCC  -  ADS  -  Programação  -  Prof. Barbosa
ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Elabore o programa que imprima a tabuada de multiplicação do número
5 (cinco) de 1 (um) até 10 (dez). Gere o seguinte layout:

- Saída:
Tabuada de multiplicação:
 1 x 5 =  5
 2 x 5 = 10
 3 x 5 = 15
. . .
10 x 5 = 50                         """



print('Tabuada de multiplicação de 5:')
for i in range(1, 10+1):                    # for i in range(1, 11, 1):
    calculo = i * 5
    print(i, " x 5 = ", calculo)

''' ----- ALTERAÇÕES:
a. Refaça sem usar a variável calculo dentro do for.
b. Use o f" (f-string) para configurar o layout, alinhamento à direita
c. Altere para a tabuada de soma.
d. Use o .format para configurar o leiaute        

    ----- DICAS ABAIXO:
    # calculo = i * 5                                           # a.
    print(i, "x 5 =", i * 5)                                        
    print(f"{i:2} x 5 = {calculo:2}")                           # b.
    print(i , "+ 5 =", i + 5)                                   # c. 
    print("{:2} + 5 = {:2}" .format(i, (i+5)))                  # d.

'''
