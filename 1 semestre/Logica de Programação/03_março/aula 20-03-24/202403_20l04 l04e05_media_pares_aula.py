"""            Comentários de várias linhas.

ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Construa o programa que calcule a média aritmética dos números pares.
O usuário fornecerá os valores de entrada que pode ser um número qualquer
par ou ímpar. A condição de saída será o número 0 (zero).

- Dicas: 1. O operador modulo (%) pega o resto da divisão de dois números.
        2. media = soma / ct

- Dicas (algoritmo):
variável ct                 (inicia as variáveis)
variável soma
estrutura de repetição
    lê número
    testa condição saída
    número é par?
        contador
        somador
calcula média
tela de saída               (fim do algoritmo)

Teste 1: valor: 1, 2 e 0        Saída: Média 2
Teste 2: valor: 1, 2, 3, 4 e 0  Saída: Média 3

"""

ct = 0                  # Contador, conta os números pares
soma = 0                # Somador, soma os números pares
# ct = soma = 0         # Inicializa todas as variáveis numa linha
print('Digite zero [0] para sair')
while True:             # while valor != 0:
    valor = int(input("Digite um número: "))  # Recebe um número inteiro
    if valor == 0:      # valor igual (==) a 0 é a condição de saída
        break           # O break força a saída da estrutura de repetição
    resto = valor % 2   # O operador % pega o resto da divisão
    if resto == 0:      # Se o resto for zero o valor é par
        soma = soma + valor     # soma += valor
        ct = ct + 1             # ct += 1  # incrementa a contagem
    # Fim da estrutura de repetição "while"
media = soma / ct               # Calcula a média
print("A média de todos os pares é:", media)  # Mostra o resultado

''' --- ALTERAÇÕES:
a. Mostre a média com quatro casas decimais.
b. Mostre a quantidade de números pares.
c. Mostre a quantidade de números digitados.

    --- Dicas:
print(f"Média dos pares: {media:.4f}"                               # a.
print(f'Quantidade de números pares: {ct}')                         # b.

ct_geral = 0                                # Antes do while        # c.
    ct_geral = ct_geral + 1                 # Dentro do while
print(f'Quantidade de números: {ct_geral}') # Depois do while



d. Como resolver o teste 3, corrija esta mensagem de erro do Python.
Teste 3: valor: 1, 3 e 0        Saída: Não foi digitado número par. 

    --- DICAS:
print(f"Média dos pares: {media:.4f}"                               # a.
print("Média dos pares: {:.4f}" .format(media) ) 
print("Média dos pares: %.4f" %(media) )             
print(f'Quantidade de números pares: {ct}')                         # b.
ct_geral = 0                                # Antes do while        # c.
    ct_geral = ct_geral + 1                 # Dentro do while
print(f'Quantidade de números: {ct_geral}') # Depois do while

    # Fim da estrutura de repetição "while"                         # d.
if ct > 0:                      # Verifica o teste 3:                              
    media = soma / ct           # Calcula a média
    print("A média de todos os pares é:", media) 
else:
    print('Não foi digitado número par.')

'''
