"""            Comentários de várias linhas.

  CEUB  -  FATECS  -  BCC  -  ADS  -  Programação  -  Prof. Barbosa
ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Programa anterior:
- Elabore o programa que gere a sequência dos números inteiros de um
em um, onde o usuário fornecerá os valores inicial e final da sequência.

- Teste:
Entrada: Inicio: x
         Fim: y
Saída:  Sequência de números inteiros:
        x
        ...
        y

- Programa atual:
- Melhore o programa anterior, se o usuário fornecer o valor inicial
menor que o valor final, gere a sequência na ordem crescente. E se o
valor inicial for maior que o valor final, gere a sequência na ordem
decrescente.

Teste 1: Entrada: 1, 5         Saída: 1 2 3 4 5
Teste 2: Entrada: 5, 1         Saída: 5 4 3 2 1
Teste 3: Entrada: 5, 5         Saída: 5                         """

inicio = int(input("Valor inicial: "))  # Recebe os valores
fim = int(input("Valor final: "))
if inicio <= fim:           # inicio < fim (indica ordem crescente)
    for i in range(inicio, fim + 1, 1):
        print(i)
else:                       # inicio > fim (indica ordem decrescente)
    for i in range(inicio, fim - 1, -1):
        print(i)
''' ----- ALTERAÇÕES:
a. Mostre o cabeçalho: "Valores na ordem crescente." ou 
   "Valores na ordem decrescente."
b. Se os valores forem iguais, mostre a  mensagem: "Os valores são iguais."
c. Mostre também a quantidade de números gerados na sequência. Use contador
   Teste: Entrada: Inicial: 1, final: 5              Saída: Quantidade: 5
d. No final, mostre a média dos números gerados na sequência.
   Teste: Entrada: Inicial: 1, final: 5              Saída: Média: 3
e. Deixe o programa mais flexível, permita que o usuário forneça o valor 
   do passo (incremento), ou seja, o intervalor entre os números gerados.  
    ----- DICAS ABAIXO:
if inicio <= fim:                                           # a.
    print("Valores na ordem crescente.")
    for i in range(inicio, fim + 1, 1):
        print(i)                                
else:
    print("Valores na ordem decrescente.")
    for i in range(inicio, fim - 1, -1):
        print(i)
if inicio < fim:                                            # b.
    print("Valores na ordem crescente.")
    for i in range(inicio, fim + 1, 1):
        print(i)
elif inicio > fim:                          
    print("Valores na ordem decrescente.")
    for i in range(inicio, fim - 1, -1):
        print(i)
else:                            # inicio = fim
    print("Os valores são iguais, ", inicio)  
ct = 0                           # Antes do for             # c.
    ct = ct + 1                  # Dentro dos dois for.       
print ("Quantidade: ", ct)       # Depois do for

'''
