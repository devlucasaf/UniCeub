"""               Comentários de várias linhas.
ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Operadores aritméticos:
    +       →       soma
    –       →       subtração
    *       →       multiplicação
    /       →       divisão
    //      →       divisão de inteiros (quociente da divisão)
    %       →       módulo (resto da divisão)
    **      →       potenciação

- Elabore o programa que verifica se o valor inteiro fornecido pelo
usuário é par ou ímpar.
Analise o problema e verifique quais são os dados que o usuário precisa
fornecer.

- Passos para a implementaçao do programa:
Entrada de dados (leia)
Processamento (cálculo)
Testes (se ...)
Saída de dados (escreva)

Teste 1: valor = 7              Saída: "Número ímpar."
Teste 2: valor = 8              Saída: "Número par."             """

# Solução 1:
valor = int(input("Digite um número: "))  # Recebe um número digitado
resto = valor % 2               # Pega o resto da divisão de dois números.
if resto == 0:  # Verifica se o valor da variável resto = 0, se o número é par
    print("Número par.")
else:                           # Caso contrário
    print("Número ímpar.")

# Solução 2:
valor = int(input("Digita um número: "))
if valor % 2 == 0:
    print(f"O número {valor} é par")
else:
    print(f"O número {valor} é ímpar")
''' --- ALTERAÇÕES:
a. Na tela de saída, acrescente o número digitado e o valor do resto da 
    divisão por 2.
b. Acrescente também se o valor fornecido é divisível por cinco. 

    --- DICAS ABAIXO:
print("Valor digitado: ", valor)                                    # a.
print("Resto da divisão: ", resto)              .
    
'''
