"""               Comentários de várias linhas.

  CEUB  -  FATECS  -  BCC  -  ADS  -  Programação  -  Prof. Barbosa
ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Elabore o programa que leia um número qualquer e verifique se ele é
positivo, nulo ou negativo.

Teste 1: numero = 4             Saída: Número Positivo
Teste 2: numero = 0             Saída: Número Nulo
Teste 3: numero = -4            Saída: Número Negativo

- Sintaxe do if ... elif ... else:
if condicao1:            # A indentação é obrigatória.
    print(“Mensagem 1”)  # Executa, se a condição1 for verdadeira
elif condicao2:
    print(“Mensagem 2”)  # Executa, se a condição2 for verdadeira
else:
    print("Mensagem 3")  # Executa, se todos os testes anteriores forem falsos.

- Passos para a implementaçao do programa:
Entrada de dados (leia)
Testes (se ...)
Saída de dados (escreva)
"""

numero = float(input("Digite um número: "))  # Lê, converte e atribui à variável
if numero > 0:
    print("Número positivo.")               # Imprime, se numero for maior que 0
elif numero < 0:
    print("Número negativo.")               # Imprime, se numero for menor que 0
else:
    print("Número nulo.")                   # Imprime, se numero for igual a 0
''' --- ALTERAÇÕES:
a. Além da mensagem, mostre também o número digitado pelo usuário.
b. Se o número for positivo, mostre a mensagem, o valor da variável numero e 
   o dobro;
   Se negativo, mostre a mensagem, o valor da variável numero e o triplo;
   Se nulo, mostre a mensagem, o valor da variável numero.
   --- DICAS ABAIXO:
numero = float(input('Insira um número: '))                                 # a.
if numero > 0:
    print(f'o valor {numero} é um número positivo.')
elif numero == 0:
    print(f'o valor {numero} é um número nulo.')
else:
    print(f'o valor {numero} é um número negativo.')

'''
