"""            Comentários de várias linhas.

  CEUB  -  FATECS  -  BCC  -  ADS  -  Programação  -  Prof. Barbosa
ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Construa o programa que encontre o menor valor e o maior valor de
um conjunto de valores inteiros digitados pelo usuário. A condição
de saída será o valor 0 (zero) que não será considerado na pesquisa.

- Ideia (lógica) pra resolver o problema: preciso de quantas variáveis?
(Variáveis)    Tempo1          Tempo2  Tempo3  Tempo4  Tempo5  Tempo6
valor (input)                   2       4       3       1       0
menor          99999999         2       -       -       1
maior          -99999999        2       4       -       -

- Dicas (algoritmo):
variável menor                  (inicia as variáveis)
variável maior
estrutura de repetição
    lê valor
    testa condição saída
    compara o número lido (valor) com o menor valor
        atualiza ou não o menor
    compara o número lido (valor) com o maior valor
        atualiza ou não o maior
tela de saída                   (fim do algoritmo)

Teste 1:    entrada: 2, 4, 3, 1, 0     Saída: Menor: 1      Maior: 4
Teste 2:    entrada: 1, 2, 3, 0        Saída: Menor: 1      Maior: 3
Teste 3:    entrada: 3, 2, 1, 0        Saída: Menor: 1      Maior: 3   """
menor = 999999                      # menor = float('inf')
maior = -999999                     # maior = float('-inf')
print('Digite [0] para sair')
while True:                         # while valor != -1:
    valor = int(input("Digite um valor: "))
    if valor == 0:
        break
    if valor < menor:
        menor = valor   # Se for menor, atualize o valor da variável menor.
    if valor > maior:   # Aqui, não pode usar else
        maior = valor   # Se for maior, atualize o valor da variável maior.
    # Fim da repetição (while)
print("Menor valor:", menor)
print("Maior valor:", maior)
''' ----- ALTERAÇÕES:
a. Mostre também a quantidade de valores digitados, na tela de saída.
b. Mostre também a soma dos valoes digitados, na tela de saída.         
c. Calcule e mostre a média aritmética dos valores digitados.
d. Digite o valor 0 de primeira e mostre a mensagem: 
   "Não foi digitado nenhum valor."
    ----- DICAS ABAIXO:
ct = 0                              # Antes do while        # a.
    ct = ct + 1                     # Dentro do while
print ('Quantidade:', ct)           # Depois do while           
soma = 0                            # Antes do while        # b.
    soma = soma + valor             # Dentro do while
print ('Soma:', soma)               # Depois do while           
media = soma / ct                   # Depois do while       # c.
print("Média:", media)     
if ct > 0:                          # Depois do while       # d.
    print("Menor:", menor)
    print("Maior:" , maior)
else:
    print ("Não foi digitado nenhum valor.")

'''
