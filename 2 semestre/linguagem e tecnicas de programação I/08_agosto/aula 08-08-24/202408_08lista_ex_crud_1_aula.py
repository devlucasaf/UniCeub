"""                 Comentários de várias linhas.

ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- O que significa CRUD?

- CRUD (acrônimo do inglês Create, Read, Update and Delete) são as
  quatro operações básicas (inclui, consulta, atualiza e apaga os dados)
  utilizadas em bases de dados relacionais (RDBMS) fornecidas aos usuários
  do sistema.

- Desenvolva o programa para simular um CRUD usando lista com as funções:
  main com repetição,
  menu,
  create,
  read,
  update e
  delete.
  Obs.: neste exercício, use nomes de pessoas.
1- A função main gerencia o programa usando uma estrutura de repetição,
  ou seja, todas as funções serão chamadas da função main.

2- A função menu não recebe nada, apresenta as quatro operações do CRUD,
   lê a opção do usuário e retorna a opção digitada pra função main:
    [c] - Create (inserir um item)
    [r] - Read (mostrar toda a lista)
    [u] - Update (substituir um item)
    [d] - Delete  (remover um item)
    [e] - Exit (sair)
    Opção:

3- A função create não recebe nada e não retorna nada. Lê um nome e insere na lista
4- A função read não recebe nada e não retorna nada. Mostra todos os itens da lista
5- A função update não recebe nada e não retorna nada. Lê os dados necessários pra
  atualizar (índice e o novo nome) e substituir (alterar) um item da lista.
6- A função delete não recebe nada e não retorna nada. Lê o item (nome) que será
  excluído da lista.

Prof. Barbosa

"""


l_nomes = []                      # Lista vazia
def menu():
    print('[c] - Create')
    print('[r] - Read')
    print('[u] - Update')
    print('[d] - Delete')
    print('[e] - Exit')
    opcao = input('Opção: ')
    return opcao
def create():
    nome = input('Nome: ')
    l_nomes.append(nome)
def read():
    print(l_nomes)                # Na horizontal
def update():
    p = int(input("Qual posição: "))
    novo_nome = input("Novo nome: ")
    # Usando notação de vetor:      Sintaxe: nome_lista[indice] = novo_nome
    l_nomes[p] = novo_nome          # Solução 1, notação vetor.
    # l_nomes.pop(p)                # Solução 2, funções de lista.
    # l_nomes.insert(p, novo_nome)  # Solução 2
def delete():
    nome = input("Qual nome: ")
    l_nomes.remove(nome)
