"""            Comentários de várias linhas.

  CEUB  -  FATECS  -  BCC  -  ADS  -  Programação  -  Prof. Barbosa
ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Solução com lista:

- Construa o programa que encontre o menor valor e o maior valor de
um conjunto de valores inteiros digitados pelo usuário. A condição
de saída será o valor -1 que não será considerado na pesquisa.

Teste 1:    entrada: 2, 4, 3, 1, -1     Saída: Menor: 1      Maior: 4
Teste 2:    entrada: 1, 2, 3, -1        Saída: Menor: 1      Maior: 3
Teste 3:    entrada: 3, 2, 1, -1        Saída: Menor: 1      Maior: 3

"""
lista = list()             # Duas formas de criar uma lista vazia
# lista = []
print('Digite [-1] para sair')
while True:
    valor = int(input("Valor inteiro: "))
    if valor == -1:
        break
    lista.append(valor)         # Armazena o valor no final da lista
    # Fim da estrutura de repetição while
menor = min(lista)              # Menor
maior = max(lista)              # Maior
print("Menor valor:", menor)
print("Maior valor:", maior)
''' --- ALTERAÇÕES:
a. Mostre (acrescente) todos os valores armazenados na lista 
b. Mostre (acrescente) todos os valores armazenados na lista 
   na vertical e suas respectivas posições (índices) 
c. Mostre (acrescente) a quantidade de valores armazenados na lista
d. Mostre (acrescente) a soma de valores armazenados na lista 
e. Refaça o programa sem usar as funções max e min.
f. Digite o valor -1 de primeira e mostre somente esta mensagem:
   "Não foi digitado nenhum valor."          
g. Calcule e mostre a média dos valores armazenados na lista 
    ---  DICAS ABAIXO:
print("Valores na lista\n", lista)                          # a.
ct = 0                                                      # b.
for elemento in lista:
    print(f'{ct} - {elemento}')
    ct += 1
print("Quantidade:", len(lista)                             # c.
print("Soma:", sum(lista)                                   # d.

- Teoria list:
l = []
l.append(2)
len(l)
sum(l)
print(l)
min(l)
max(l)
sort(l)

Obs.: se tentar usar o max ou min com lista vazia dá erro.

'''
