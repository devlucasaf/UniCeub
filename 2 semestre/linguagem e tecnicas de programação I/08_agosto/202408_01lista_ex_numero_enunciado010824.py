"""                 Comentários de várias linhas.
  CEUB  -  FATECS  -  BCC  -  ADS  -  Programação  -  Prof. Barbosa
ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Desenvolva um programa que crie uma lista vazia, leia vários números
e os armazene na lista, em seguida, exiba estas informações:

a- Mostre a lista;
b- Mostre a quantidade de elementos armazenados na lista;
c- Mostre a soma dos valores da lista;
d- Mostre o maior valor da lista;
e- Mostre o menor valor da lista;
f- Faça uma pesquisa. Leia um valor e verificar se ele está na lista;
g- No item anterior, mostre também a posição (índice) do valor encontrado;
h- Mostre a lista na ordem crescente;
i- Insira (acrescente) o valor 33 na posição (índice) 1 da lista;
j- Mostre a lista na ordem decrescente;
k- Calcule e mostre a média dos valores da lista;
l- Mostre a média com três casas decimais;
m- Porcentagem dos números maiores que dez da lista.                """

lista = []                  # Cria uma lista vazia
# lista = list()            # Cria uma lista vazia, usando a classe list
print('Digite [0] pra sair')
while True:
    valor = int(input("Valor: "))
    if valor == 0:          # Se condição de saída
        break               # Sai de uma estrutura de repetição
    lista.append(valor)     # Acrescenta o valor no final da lista
    # Fim do while
print("Valores da lista:\n", lista)                                 # a.
print('Quantidade de elementos da lista:', len(lista))              # b.
print('Soma dos elementos da lista:', sum(lista))                   # c.
print('Maior valor:', max(lista))                                   # d.
print('Menor valor:', min(lista))                                   # e.
pesquisa = int(input("Valor da pesquisa: "))                        # f.
if pesquisa in lista:  # if lista.__contains__(pesquisa): # Método dunder
    print("Valor encontrado.")
    posicao = lista.index(pesquisa)          # Solução 1            # g.
    print('Posição da pesquisa:', posicao)
    # print('Posição da pesquisa:', lista.index(pesquisa))  # Solução 2
else:
    print("Valor não encontrado.")
print('Antes do sort():\n', lista)                                  # h.
lista.sort()  # lista.sort(reverse=False)   # Solução 1
print('Depois do sort():\n', lista)
lista_o = sorted(lista)                     # Solução 2
print('Ordem crescente:\n', lista_o)
print('Ordem crescente:\n', sorted(lista))  # Solução 3
lista.insert(1, 33)   # lista.insert(indice, item)                  # i.
print(lista)
# lista.reverse()   # Errado, não coloca na ordem decrescente       # j.
# print(lista)
lista.sort()                                # Solução 1
lista.reverse()
print('Ordem decrescente:\n', lista)
lista.sort()                                # Solução 2
lista_2 = list(reversed(lista))
print('Ordem decrescente:\n', lista_2)
lista.sort(reverse=True)                    # Solução 3
print('Ordem decrescente:\n', lista)
print('Média:', sum(lista)/len(lista))      # Solução 1             # k.
import statistics                           # Solução 2
media = statistics.mean(lista)
print('Média:', media)
from statistics import mean                 # Solução 3
media = mean(lista)
print('Média:', media)
print(f'Média: {media:.3f}')    # Solução 1, 3 casas decimais       # l.
print('Média: {:.3f}' .format(media))  # Solução 2, 3 casas decimais
''' - Dicas:                                                        # m.
Porcentagem dos números maiores que dez na lista.
Teste 1:  lista = [1, 33]             Saida: Porcentagem 50
Teste 2:  lista = [1, 2, 33]          Saida: Porcentagem 33.3
Regra de três:      100%   -   x%     
                    O todo - uma parte      
Isolando o x = uma parte * 100 / todo   # x = uma parte / todo * 100
porcentagem = uma parte dividido pelo todo vezes 100            '''
ct = 0                                # Solução 1, com contador
for v in lista:
    if v > 10:
        ct += 1                 # ct = ct + 1
porc = ct / len(lista) * 100    # porc = ct * 100 / len(lista)
print("Porcentagem de números maiores que 10: ", porc)
# Refaça o item anterior sem usar o contador (ct)
l_maior_10 = []                       # Solução 2, sem contador
for v in lista:
    if v > 10:
        l_maior_10.append(v)
porc = len(l_maior_10) / len(lista) * 100
# porc = len(l_maior_10) * 100 / len(lista)
print("Porcentagem de números maiores que 10: ", porc)
