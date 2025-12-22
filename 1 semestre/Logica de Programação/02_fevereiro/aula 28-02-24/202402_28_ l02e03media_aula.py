"""            Comentários de várias linhas.

ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

Crie um novo programa dentro do mesmo projeto:
- Coluna da esquerda - mouse direito no nome do projeto - New - Python File
- Digite o nome do programa Python sem espaço.

Operadores aritméticos utilizados em Python:
    +   →   soma
    –   →   subtração
    *   →   multiplicação
    /   →   divisão
    //  →   divisão de inteiros (quociente da divisão)
    %   →   módulo (resto da divisão)
    **  →   potenciação

- Implemente o programa que calcule a média aritmética de duas notas
bimestrais fornecidas pelo usuário.

  Onde:     média = nota1 + nota2
                          2

Teste 1: Entrada: nota1: 4.5 e nota2: 6.1             Saída: 5.3
Teste 2: Entrada: nota1: 7.5 e nota2: 5.1             Saída: 6.3

- Passos para a elaboração do programa:
Entrada de dados (leia)
Processamento (cálculo)
Saída de dados (escreva)
"""                     # fim do Comentário de várias linhas.
nota1 = float(input("Primeira nota: "))  # Recebe os valores para float
nota2 = float(input("Segunda nota: "))
# Atribui o cálculo à variável media
media = (nota1 + nota2) / 2             # Parênteses obrigatórios.
print(f"Média: {media}")                  # Mostra o resultado

''' ----- ALTERAÇÕES:
a. Mostre a média com duas casas decimais

print(f'Média: {media:.2f}')  # Mostra com 2 casas decimais.    # a.

'''
