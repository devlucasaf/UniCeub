"""            Comentários de várias linhas.
ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Elabore o programa que leia a altura e o gênero (‘m’ para masculino
e ‘f’ para feminino) de um grupo de pessoas. Gere a tela de saída
com as seguintes informações: maior altura do grupo (de todas as
pessoas), menor altura do grupo (de todas as pessoas), quantidade
de homens e quantidade de mulheres.

- Ideia (lógica) pra resolver o problema: preciso de quantas variáveis?

- Dicas (algoritmo):
variável conta masculino    (inicia as variáveis)
variável conta feminino
variável maior
variável menor
estrutura de repetição
    lê altura
    testa condição saída
    lê genero
    testa (trata) altura    (maior e menor)
    testa (trata) genero    (contadores)
tela de saída               (fim do algoritmo)

        - Entrada:                 - Saída:
Teste 1: 1.8, m; 1.6, f; 0          Maior=1.80 Menor=1.60 Masc.=1 Fem.= 1
Teste 2: 1.8, m; 1.9, f; 0          Maior=1.90 Menor=1.80 Masc.=1 Fem.= 1
Teste 3: 1.8, m; 1.6, f; 1.7, m; 0  Maior=1.80 Menor=1.60 Masc.=2 Fem.= 1
Teste 4: 1.8, m; 1.9, f; 1.6, f; 0  Maior=1.90 Menor=1.60 Masc.=1 Fem.= 2   """

ct_masc = 0                    # Inicia as variáveis
ct_fem = 0
maior = 0                      # Começa com o menor valor possível
# maior = float('-inf')        # - infinito, qualquer número é maior que ele
menor = 3                      # Começa com o maior valor possível
# menor = float('inf')         # + infinito, qualquer número é menor que ele
print('Digite [0] para sair')
while True:                    # Início da estrutura de repetição while.
    altura = float(input("Altura: "))  # Lê altura. Indentação obrigatória.
    if altura == 0:            # Testa condição de saída
        break                  # Sai da estrutura de repetição while
    genero = str(input("[m] para Masculino\n[f] para Feminino:\nOpção: "))  # Lê
    if altura > maior:
        maior = altura         # Atualiza a maior altura
    if altura < menor:         # Aqui, não pode usar else
        menor = altura
    if genero == 'm':          # Se gênero é masculino
        ct_masc += 1           # ct_masc = ct_masc + 1
    else:                      # Aqui, deve usar else
        ct_fem += 1            # ct_fem = ct_fem + 1
    # Fim da estrutura de repetição while.
print(f"Maior altura: {maior}")  # print(f'Maior altura: {maior:.2f}')
print(f"Menor altura: {menor}")  # print(f"Menor altura: {menor:.2f}")
print(f"Quantidade de homens: {ct_masc}")
print(f"Quantidade de mulheres: {ct_fem}")

''' ----- ALTERAÇÕES:
a. Calcule e mostre também a média de alturas do grupo.
Teste 4: 1.80, m, 1.60, f, 0  Saída: Maior=1.80 Menor=1.60 Masc.=1 Fem.=1 
        Média = 1.7
b. Calcule e mostre também a porcentagem de homens.
Teste 5: 1.80, m, 1.60, f, 0 Saída: Maior=1.80 Menor=1.60 Masc.=1 Fem.=1 Média=1.7
        Porc = 50
        
    ----- DICAS ABAIXO:
Dicas: precisa somar todas as alturas das pessoas (somador).                # a.
    media = soma / ct                           
soma = 0                                    # No início, antes do while
    soma = soma + altura                   # Dentro do while
media = soma / (ct_mas  +  ct_fem)          # Depois do while     
print ("Média das alturas:", media)
Dica: Fórmula de porcentagem. Uma parte dividida pelo todo vezes 100.       # b.
Uma parte (ct_masc) dividida pelo todo (ct_masc + ct_fem) vezes 100.
pct_masc = ct_masc / (ct_masc + ct_fem) * 100            # Depois do while
print("Porcentagem dos homens:", pct_masc)

'''
