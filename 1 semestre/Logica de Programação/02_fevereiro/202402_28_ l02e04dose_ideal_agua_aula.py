"""            Comentários de várias linhas.

  CEUB  -  FATECS  -  BCC  -  ADS  -  Programação  -  Prof. Barbosa
ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

Crie um novo programa dentro do mesmo projeto:
- Coluna da esquerda - mouse direito no nome do projeto - New - Python File
- Digite o nome do programa Python sem espaço.

- Operadores aritméticos utilizados em Python:
    +   →   soma
    –   →   subtração
    *   →   multiplicação
    /   →   divisão
    //  →   divisão de inteiros (quociente da divisão)
    %   →   módulo (resto da divisão)
    **  →   potenciação

- A água é um nutriente essencial. Sem ela, o corpo não pode funcionar
com perfeição. Cada pessoa precisa de uma quantidade diferente de água
para hidratar o corpo. A dose ideal, ou seja, a necessidade diária em
litros é calculada através da fórmula: massa (em kg) vezes 0,03. 
Elabore o programa que realize esse cálculo.

Teste 1: Entrada: massa = 71               Saida: dose_ideal = 2.13
Teste 2: Entrada: massa = 60               Saida: dose_ideal = 1.7999999

- Passos para a elaboração do programa:
Entrada de dados (leia)
Processamento (cálculo)
Saída de dados (escreva)
"""                     # fim do Comentário de várias linhas.

massa = float(input("Digite a massa: "))  # Entrada de dados
dose_ideal = massa * 0.03                 # Notação americana, ponto decimal.
print('Quantidade ideal:', dose_ideal)    # Dose ideal de água

''' ----- ALTERAÇÕES:
a. Na tela de saída, mostre também o valor da massa.
b. Mostre o resultado com uma casa decimal.

             
    ----- DICAS ABAIXO:
print ('Valor da massa: ', massa)                                     # a.
print("Quantidade ideal: {:.1f}" .format(dose_ideal))  # Solução 1    # b.

'''
