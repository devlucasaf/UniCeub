"""            Comentários de várias linhas.

  CEUB  -  FATECS  -  BCC  -  ADS  -  Programação  -  Prof. Barbosa
ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Desenvolva o programa que calcule a média aritmética de uma turma,
onde cada aluno realizou uma avaliação. Não sabemos a quantidade de
alunos, por isso usaremos o valor “-1” como condição (flag) de saída.
Na tela de saída, mostre a média aritmética da turma e a quantidade
de alunos da turma.

- Dica: media = soma / ct

- Dicas (algoritmo):
variável ct                 (inicia as variáveis)
variável soma
estrutura de repetição
    lê nota
    testa condição saída
    contador
    somador
calcula média
tela de saída               (fim do algoritmo)

         - Entrada:     - Saída:
Teste 1: 5, 6 e -1      Média 5.5               Quantidade: 2
Teste 2: 5, 6, 7 e -1   Média 6                 Quantidade: 3
Teste 3: 5, 6, 6 e -1   Média 5.666666666666667 Quantidade: 3   """

ct = 0                  # Valor inicial das variáveis
soma = 0
print('Digite [-1] para sair')
while True:  # Laço de repetição while, repete enquanto condição verdadeira
    nota = float(input("Nota do aluno: "))  # Indentação é obritória
    if nota == -1:
        break           # Sai de uma estrutura de repetição (while)
    ct = ct + 1         # ct += 1 (contador), incrementa o ct
    soma = soma + nota  # soma += nota (somador ou acumulador)
    # Fim da estrutura de repetição "while"
media = soma / ct
print("Média da turma:", media)
print('Quantidade de alunos:', ct)

''' --- ALTERAÇÕES:
a. Mostre também a soma das notas dos alunos da turma.
b. Mostre a média da turma com duas casas decimais.
c. Mostre a média e a quantidade de alunos na mesma linha, nesta frase:
   A média da turma de X alunos é X.XX.

    --- Dicas:
print("Soma das notas: ", soma)                                 # a.

print(f"Média da turma: {media:.2f}")           # f-string      # b.

'''
