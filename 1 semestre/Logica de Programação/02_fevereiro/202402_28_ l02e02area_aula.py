"""            Comentários de várias linhas.

ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Operadores aritméticos:
    +   →   soma
    –   →   subtração
    *   →   multiplicação
    /   →   divisão
    //  →   divisão de inteiros (quociente da divisão)
    %   →   módulo (resto da divisão)
    **  →   potenciação

Crie um novo programa dentro do mesmo projeto:
- Coluna da esquerda - mouse direito no nome do projeto - New - Python File
- Digite o nome do programa Python sem espaço.

- Projete o programa para calcular a área de um triângulo. O usuário
informará os dados necessários para o cálculo, ou seja, a base e a
altura do triângulo.
  Onde:     área = base . altura
                       2

Teste 1: Entrada: base: 1.5 e altura: 2.6         Saída: 1.9500000000000002

- Passos para a elaboração do programa:
Entrada de dados (leia)
Processamento (cálculo)
Saída de dados (escreva)                
"""                     # fim do Comentário de várias linhas.
# Calcular a área de um triângulo.
base = float(input("Digite a base: "))  # Recebe um valor real do usuário.
altura = float(input("Digite a altura: "))
area = base * altura / 2                # Processamento
print("Área:", area)                    # Saída
''' ----- ALTERAÇÕES:
a. Na tela de saída de dados, mostre também o valor da base e da altura.
b. Mostre o valor da área com três casas decimais.
----- DICAS ABAIXO:
print("Base:", base)                                            # a.
print("Altura:", altura)
print(f"Área: {area:.3f}")                                      # b.
Obs.: o número 3 significa a quantidade de casas decimais.

'''

