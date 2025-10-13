"""               Comentários de várias linhas.

ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Programa anterior:
- Implemente o programa que leia o ano de nascimento de uma pessoa e calcule
sua idade. Verifique se ela já tem idade para votar (16 anos ou mais).
Mostre a mensagem informando a situação dela.

- Programa atual:
- Implemente o programa que leia o ano de nascimento de uma pessoa e calcule
sua idade. Verifique se ela já tem idade para votar (16 anos ou mais) e para
conseguir a CNH - Carteira Nacional de Habilitação (18 anos ou mais).
Mostre a mensagem informando a situação dela.

Teste 1: Entrada: Ano nascimento = 2010          Saída: Não votar, não CNH.
Teste 2: Entrada: Ano nascimento = 2007          Saída: Pode votar, não CNH.
Teste 3: Entrada: Ano nascimento = 2000          Saída: Pode votar, pode CNH.

- Passos para a implementaçao do programa:
Entrada de dados (leia)
Cálculo
Testes (se ...)
Saída de dados (escreva)
"""
# Solução 1
ano_nascimento = int(input("Ano de nascimento: "))
idade = 2024 - ano_nascimento
if idade >= 18:
    print("Pode tirar CNH e votar.")
elif idade >= 16 and idade < 18:
    print("Pode votar e não tem idade para tirar CNH.")
else:
    print("Não tem idade nem para votar nem para tirar CNH.")
''' --- ALTERAÇÕES:
a. Retire a redundância dos testes na solução 1        
    --- DICAS:
    ...                                                         # a.
    elif idade >= 16:
    ...
'''

# Solução 2
ano_nascimento = int(input("Ano de nascimento: "))
idade = 2022 - ano_nascimento
if idade < 16:                          # Se a idade for menor que 16
    print("Você não pode votar nem tirar a CNH.")
elif 16 <= idade < 18:                  # if idade>=16 and idade<18:
    # Se a idade for maior ou igual a 16 e menor que 18
    print("Você pode votar mas não pode tirar a CNH.")
else:                                   # Se a idade for maior que 18
    print("Você pode votar e tirar a CNH.")
''' --- ALTERAÇÕES:
a. Retire a redundância dos testes na solução 2
b. Mostre também a idade da pessoa.
    --- DICAS:
...                                                         # a.
elif idade < 18:                                         
...
print('Idade:', idade)                                      # b.
print(f'Idade: {idade}')                                    

'''
