"""               Comentários de várias linhas.

ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Projete o programa que leia um valor numérico e verifique se ele é maior
ou igual a cem. Mostre uma das mensagens: “Valor maior ou igual a cem.” ou
“Valor menor que cem.”

Teste 1: valor = 200                    Saída: Valor maior ou igual a cem.
Teste 2: valor = 20                     Saída: Valor menor que cem.
Teste 3: valor = 100                    Saída: Valor maior ou igual a cem.

- Passos para a elaboração do programa:
Entrada de dados (leia)
[Processamento (cálculo)]             # Opcional
Testes (se ...)
Saída de dados (escreva)
"""
# Recebe um número digitado
valor = int(input("Digite um número: "))
# Verifica se valor >= 100 para mostrar a mensagem
if valor >= 100:                          # Obrigatório usar os dois pontos [:]
    print("Valor maior ou igual a cem.")  # Indentação (to indent) obrigatória.
else:                                     # Caso não seja verdade
    print("Valor menor que cem.")

''' ----- ALTERAÇÕES:
a. Mostrar também o valor numérico lido, na tela de saída.


    ----- DICAS ABAIXO:
print("Valor lido: ", valor)                  # Solução 1               # a.

'''
