"""               Comentários de várias linhas.

ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Implemente o programa que leia o ano de nascimento de uma pessoa e calcule
sua idade. Verifique se ela já tem idade para votar (16 anos ou mais).
Mostre a mensagem informando a situação dela.

Teste 1: Entrada: Ano nascimento = 2010          Saída: Não pode votar.
Teste 2: Entrada: Ano nascimento = 2000          Saída: Pode votar.

- Passos para a implementaçao do programa:
Entrada de dados (leia)
Testes (se ...)
Saída de dados (escreva)

"""

# Solução 1
ano_nascimento = int(input("Ano de nascimento: "))
idade = 2024 - ano_nascimento
if idade >= 16:
    print("Pode votar.")
else:
    print("Não pode votar.")

''' --- ALTERAÇÕES:
a. Na tela de saída, mostre também a data de nascimento.      
b. Mostre também a idade da pessoa.
    --- DICAS:
print("Ano de nascimento:", ano_nascimento)                     # a.
print('Idade:', idade)                                          # b.

--- ALTERAÇÕES:
c. Pegue o ano atual usando as bibliotecas do Python.

from datetime import date                                       # c.
hoje = date.today()
print ('Hoje:', hoje)
print ('Dia:', hoje.day)               # Mostra somente o dia
print ('Mês:', hoje.month)             # Mostra somente o mês
print ('Ano:', hoje.year)              # Mostra somente o ano

'''
