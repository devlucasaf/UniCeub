"""             Comentários de várias linhas.

  CEUB  -  FATECS  -  BCC  -  ADS  -  Programação  -  Prof. Barbosa
ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Programa anterior
- Elabore o programa que imprima a tabuada de multiplicação do número
5 (cinco) de 1 (um) até 10 (dez). Gere o seguinte layout:
Tabuada de multiplicação:
 1 x 5 =  5
 2 x 5 = 10
 3 x 5 = 15
 . . .
10 x 5 = 50

- Programa anterior
- Torne o programa anterior mais interessante. Agora, o programa deve
gerar a tabuada de multiplicação de 1 (um) até 10 (dez) de um número
inteiro qualquer fornecido pelo usuário.

- Programa atual:
- Modifique o programa anterior para imprimir a tabuada de multiplicação
de 1 (um) até 10 (dez) de quaisquer números, sendo que esses números serão
fornecidos pelo usuário. Para cada número fornecido, o programa gera a
respectiva tabuada.

Dica: crie uma condição de saída.                   """

print("Digite [-1] para sair:")
while True:
    numero = int(input("Número para gerar a tabuada: "))
    if numero == -1:        # se for -1, sai do while e não executa o for
        break
    print('Tabuada de multiplicação de ', numero)
    for i in range(1, 10 + 1):
        print(i, "x", numero, "=", i * numero)
    # fim da estrutura de repetição for
# fim da estrutura de repetição while

''' ----- ALTERAÇÕES:
a. Quantas tabuadas o programa gerou, ou seja, 
    quantos números o usuário digitou?
b. Refaça o programa para obter o mesmo resultado sem usar o if e o break
   dentro do while. 
    ----- DICAS ABAIXO:
ct = 0                  # Antes do while                            # a.
    ct += 1             # dentro do while
print ("Qtd.:", ct)     # No final do programa     
# Solução 1
t = int(input("Número para a tabuada ou [-1] para sair: "))         # b.
while t != -1:
    print('Tabuada de multiplicação')
    for i in range(1, 10 + 1):
        print(i , "x" , t , "=", (i*t))
   # fim da estrutura de repetição for
   t = int(input("Número para a tabuada ou [-1] para sair: "))  
   # Outras leituras
# fim da estrutura de repetição while             

'''
