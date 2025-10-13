"""               Comentários de várias linhas.
ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

1- Como criar um projeto no PyCharm:
- File - New Project - Location:
- Escolha o localização (diretório) e o nome do projeto (pythonProject)
2-  Como criar um programa dentro do projeto:
- Coluna da esquerda - mouse direito no nome do projeto - New - Python File
- Digite o nome do programa Python sem espaço.
3- Como roda (executa) o programa:
- Run - run

- Exercício:
- Elabore o programa que calcule a soma de dois valores inteiros que serão
fornecidos pelo usuário.

Teste 1: Entrada: valores 1 e 2.            Saída: 3

- Passos para a elaboração do programa:
Entrada de dados (leia)
Saída de dados (escreva)
"""                             # fim do Comentário de várias linhas.

# Recebe os valores convertidos para inteiro
valor1 = int(input("Digite o primeiro valor: "))        # Entrada de dados
valor2 = int(input("Digite o segundo valor: "))
# Efetua o cálculo atribuindo o resultado à variável soma
soma = valor1 + valor2                                  # Processamento
print(f"A soma é igual a: {soma}")                         # Saída

''' ----- ALTERAÇÕES:
a. No final do programa, acrescente a subtração dos valores lidos e
   mostre o resultado.
b. No final do programa, acrescente a multiplicação dos valores lidos e
   mostre o resultado. 
c. No final do método, leia mais um valor inteiro, ou seja, o terceiro
   valor inteiro.
d. No final do método, acrescente a soma dos três valores lidos e mostre
   o resultado.

'''

