"""             Comentários de várias linhas.

  CEUB  -  FATECS  -  BCC  -  ADS  -  Programação  -  Prof. Barbosa
ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Teoria de estrutura de repetição (laço) - while (enquanto):

A estrutura de repetição (laço) while executa o bloco de instrução enquanto
a expressão definida para condição for verdadeira (True).

. . .                       # Comandos antes do while, executados uma vez.
comandos
. . .
while condição:             # O bloco abaixo será executado várias vezes.
    início do bloco         # Indentação (to indent) obrigatória.
    . . .                   # Organizado em colunas
    fim do bloco (repetição while)
. . .                       # Comandos depois do while, executados uma vez.
comandos
. . .

- Desenvolva o programa que leia vários números digitados pelo usuário
e use o valor -1 como condição (flag) de saída da estrutura de repetição.
Na tela de saída, mostre a quantidade de números digitados.

- Dicas (algoritmo):
variável ct                 (inicia a variável)
estrutura de repetição
    lê número
    testa condição saída
    contador
tela de saída               (fim do algoritmo)

Teste 1:    Entrada: 5, 6 e -1          Saída: Quantidade de números: 2
Teste 2:    Entrada: 5, 6, 7 e -1       Saída: Quantidade de números: 3
Teste 3:    Entrada: 5, 6, 6, 7 e -1    Saída: Quantidade de números: 4

"""

ct = 0              # Valor inicial da variável
print('Digite [-1] para sair')
while True:         # Laço de repetição, repete enquanto condição verdadeira
    numero = int(input("Digite um número: "))  # Indentação é obritória
    if numero == -1:
        break       # Sai de uma estrutura de repetição (while)
    ct = ct + 1     # ct += 1 (contador), incrementa o ct
    # Fim da estrutura de repetição "while"
print('Quantidade de números:', ct)

''' ----- ALTERAÇÕES:
a. Na tela de saída, acrescente a soma dos valores digitados.

    ----- DICAS:                                                    '''
ct = 0              # Valor inicial das variáveis                 # a.
soma = 0
print('Digite [-1] para sair')
while True:         # Laço de repetição, repete enquanto condição verdadeira
    numero = int(input("Digite um número: "))  # Indentação é obritória
    if numero == -1:
        break       # Sai de uma estrutura de repetição (while)
    ct = ct + 1     # ct += 1 (contador), incrementa o ct
    soma = soma + numero  # soma += numero (somador ou acumulador)
    # Fim da estrutura de repetição de repetição "while"
print('Quantidade de números:', ct)
print("Soma dos valores digitados:", soma)
