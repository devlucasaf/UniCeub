"""            Comentários de várias linhas.

  CEUB  -  FATECS  -  BCC  -  ADS  -  Programação  -  Prof. Barbosa
ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Resolver com lista:

- Construa o programa que calcule a média aritmética dos números pares.
O usuário fornecerá os valores de entrada que pode ser um número
qualquer par ou ímpar. A condição de saída será o número 0 (zero).

Teste 1: valor: 1, 2 e 0        Saída: Média 2
Teste 2: valor: 1, 2, 3, 4 e 0  Saída: Média 3
Teste 3: valor: 1, 3 e 0        Saída: Não foi digitado número par.

- Dica: o operador modulo (%) pega o resto da divisão de dois números.
- Dica: media = soma / ct                                    """

numeros_pares = []                      # Cria uma lista vazia
print('Digite zero [0] para sair')
while True:
     valor = int(input("Valor: "))
     if valor == 0:
         break
     if valor % 2 == 0:              # Valor é divisível por 2?
        numeros_pares.append(valor)  # Armazena no final da lista
    # Fim da estrutura de repetição "while"
qtd_par = len(numeros_pares)         # Quantidade
soma_par = sum(numeros_pares)        # Soma
media_par = soma_par/qtd_par
print("Média dos pares:", media_par)
''' --- Alterações:
a. Substitua as quatro últimas linhas por uma única linha de código
b. Mostre também todos os valores pares digitados.
c. Digite somente valores ímpares, corrija este erro.
d. Mostre a média com duas casas decimais.
e. Mostre a quantidade de números digitados.
    --- Dicas:
print("Média dos pares:", sum(numeros_pares)/len(numeros_pares))  # a.
print("Valores pares digitados:\n", numeros_pares)                # b.
print(f"Valores pares digitados:\n{numeros_pares}")              
...                         # Depois do fim do while              # c.
if len(numeros_pares) > 0:  # Verifica se tem algum valor na lista  
    print("Média dos pares:", sum(numeros_pares)/len(numeros_pares))
else:
    print("Não foi digitado número par.")
print("Média dos pares:", media_par)                              # d.
print(f"Média dos pares: {media_par:.2f}")
print("Média dos pares: {:.2f}" .format(media_par))
print("Média dos pares: %.2f" % media_par)
---

- Teoria list:
l = []
l.append(2)
len(l)
sum(l)
print(l)

'''
