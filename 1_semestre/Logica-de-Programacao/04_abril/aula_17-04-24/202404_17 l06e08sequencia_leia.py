"""            Comentários de várias linhas.

ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Elabore o programa que gere a sequência dos números inteiros de um
em um, onde o usuário fornecerá os valores inicial e final da sequência.

- Teste:
Entrada: Inicio: x
        Fim: y

Saída:  - Sequência de números inteiros:
        x
        ...
        y

- Algoritmo (lógica de programação):
lê inicio
lê fim
for (...):
    escreva
"""
inicio = int(input("Valor inicial: "))
fim = int(input("Valor final: "))
print("- Sequência de números inteiros:")  # Cabeçalho.
for numero in range(inicio, fim+1):  # for numero in range(inicio, fim+1, 1):
    print(numero)
''' ----- ALTERAÇÕES:
a. No final, mostre a quantidade de números gerados na sequência. Use contador.
    Teste 2:   Entrada:  Inicio: 2, fim: 6   Saída: Quantidade: 5
b. No final, mostre também a soma de números gerados na sequência. Use somador.
    Teste 3:   Entrada:  Inicio: 2, fim: 6            Saída: Soma: 20
c. Mostre os números da sequência na horizontal.        
d. Responda sem rodar o programa. Se digitar 2 valores iguais o que acontece?
    Teste 4: Entrada: Inicial: 5, final : 5         Saída: ???
e. Responda sem rodar o programa. 
    Se digita o primeiro valor maior que o segundo valor o que acontece?
    Teste 4: Entrada: Inicial: 5, final : 2         Saída: ???    
    ----- DICAS:
ct = 0                                      # Antes do for              # a.
    ct = ct + 1           # ct += 1         # Dentro do for, indentado
print ("Quantidade: ", ct)                  # Depois do for
soma = 0                                    # Antes do for              # b.
    soma = soma + numero  # soma += numero  # Dentro do for, indentado
print ('Soma: ', soma)                      # Depois do for
for numero in range(inicio, fim+1):                                     # c.
    print (numero, end=" ")           

'''
