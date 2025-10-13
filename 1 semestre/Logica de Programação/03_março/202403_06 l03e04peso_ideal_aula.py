"""               Comentários de várias linhas.

ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Construa o programa que calcule o peso ideal de uma pessoa.
  Utilize as seguintes fórmulas:
- Se homem, o peso ideal é calculado assim: (72,7 . altura) - 58;
- Se mulher, o peso ideal é calculado assim: (62,1 . altura) - 44,7.
Analise o problema e verifique quais são os dados que o usuário precisa
fornecer (digitar).

Teste 1: Entrada: altura = 1.70 e genero = 1  Saída: peso ideal = 65.59 Kg
Teste 2: Entrada: altura = 1.70 e genero = 2  Saída: peso ideal = 60.8699999 Kg

- Passos para a implementaçao do programa:
Entrada de dados (leia)
Testes (se ...) - Processamento (cálculo)
Saída de dados (escreva)                            """

altura = float(input("Altura: "))        # Lê e atribui à variável
genero = int(input("[1]- para Masculino\n[2]- para Feminino\nOpção: "))
if genero == 1:
    peso_ideal = 72.7 * altura - 58    # Imprime, se gênero for igual a 1
    print("Peso ideal =", peso_ideal, "Kg")
else:
    peso_ideal = 62.1 * altura - 44.7  # Imprime, se gênero for diferente de 1
    print("Peso ideal =", peso_ideal, "Kg")
''' --- ALTERAÇÕES:
a. Mostre o peso com duas casas decimais.
b. Troque as duas linhas dentro do else por uma única linha de comando.
c. Troque a entrada genero 1 ou 2 para 'm' ou 'f'.
   Teste3: altura = 1.8     genero = 'm'          Saída: peso ideal = 72.86 Kg 
d. Mostre uma mensagem de erro se ele digitar valor de gênero diferente de m e f.
    ----- DICAS ABAIXO:
if genero == 1:                                                             # a.
    peso_ideal = 72.7 * altura - 58    # Imprime, se gênero for igual a 1
    print(f"Peso ideal = {peso_ideal:.2f} Kg")
else:
    peso_ideal = 62.1 * altura - 44.7  # Imprime, se gênero for diferente de 1
    print(f"Peso ideal = {peso_ideal:.2f} Kg")
...                                                                         # b.
else:                     
    print(f"Peso ideal = {62.1 * altura - 44.7:.2f} Kg")

'''

