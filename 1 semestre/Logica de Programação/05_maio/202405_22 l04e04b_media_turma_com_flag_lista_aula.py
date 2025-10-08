"""            Comentários de várias linhas.
  CEUB  -  FATECS  -  BCC  -  ADS  -  Programação  -  Prof. Barbosa
ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Resolva com lista

- Desenvolva o programa que calcule a média aritmética de uma turma,
onde cada aluno realizou uma avaliação. Não sabemos a quantidade de
alunos, por isso usaremos o valor “-1” como condição (flag) de saída.
Na tela de saída, mostre a média aritmética da turma e a quantidade
de alunos da turma.

- Dica: media = soma / ct

         - Entrada:     - Saída:
Teste 1: 5, 6 e -1      Média 5.5               Quantidade: 2
Teste 2: 5, 6, 7 e -1   Média 6                 Quantidade: 3
Teste 3: 5, 6, 6 e -1   Média 5.666666666666667 Quantidade: 3

- Dicas (algoritmo):
cria lista vazia
estrutura de repetição
    lê número
    testa condição saída
    armazena valor no final da lista
usa função quantidade de itens na lista
usa função soma itens na lista
calcula média
tela de saída               (fim do algoritmo)

"""
l_notas = []                # Cria uma lista vazia
print('Digite [-1] para sair')
while True:  # Laço de repetição, repete enquanto verdade
    nota = float(input("Nota do aluno: "))  # Indentação obritória
    if nota == -1:
        break               # Sai de uma estrutura de repetição
    l_notas.append(nota)    # Armazena o valor digitado no final lista
    # Fim da estrutura de repetição "while"
qtd_alunos = len(l_notas)   # Quantidade de elementos na lista
soma_notas = sum(l_notas)   # Soma dos elementos na lista
media = soma_notas/qtd_alunos
print("Média da turma:", media)
print('Quantidade de alunos:', qtd_alunos)
# - Teoria de listas:
# 5.1  6.3  6.7  7.5    <- Valores armazenados na lista (digitados)
#  0    1    2    3     <- posições (índices) positivas da lista
''' --- ALTERAÇÕES:
a. Refaça sem usar as variáveis depois do while
b. Mostre também a soma das notas dos alunos da turma. 
c. Mostre a média da turma com duas casas decimais.
d. Mostre todas as notas dos alunos.     
e. Mostre todas as notas dos alunos na vertical.  
f. Se digite -1 na primeira leitura ocorre o erro: 
   "ZeroDivisionError: division by zero". Resolva esse problema. 
Teste 4: notas: -1   Saída: Lista vazia, não existe divisão por zero
    --- Dicas:
...         # Depois do while                                   # a.
print("Média da turma:", sum(l_notas)/len(l_notas))
print('Quantidade de alunos:', len(l_notas))
print("Soma dos valores:", sum(l_notas))                        # b.
print(f"Média da turma: {media:.2f}")            # f-string     # c.
print(f"Média da turma: {sum(l_notas)/len(l_notas):.2f}")    
print("Média da turma: {:.2f}" .format(media))
print("Média da turma: %.2f" %(media))              
print('Relação das notas:')                                     # d.
print(l_notas)      # Solução 1, mostra os valores na horizontal
print('Relação das notas:')                                     # e.
for nota in l_notas:  # Solução 2, for nome_variavel in nome_lista:
    print(nota)
---

- Teoria list:
l = []
l.append(2)
len(l)
sum(l)
print(l)

'''
