"""            Comentários de várias linhas.
ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Programa anterior:
- Construa o programa com as três funções: soma, subtrai e main
(programa principal).
- A função soma recebe dois valores inteiros, realiza a soma e retorna
o resultado do cálculo.
- A função subtrai recebe dois valores inteiros, realiza a subtração
deles e retorna o resultado do cálculo.
- O programa (função main) lê dois valores inteiros digitados pelos
usuários, chama a função soma passando os dois valores lidos e
depois chama a função subtrai passando os dois valores lidos.
A função main recebe o valor retornado pelas funções soma e subtrai
e gera a tela de saída com essas informações.

- Programa atual:
- Altere o programa anterior, agora o usuário vai escolher apenas uma
    operação. A opção desejada será lida no programa prircipal (main) que
    chama uma das funções (soma ou subtrai) passando os dois valores lidos
    como argumentos.
Teste 1: valores v1 = 13, v2 = 14 e opcao = 1. Resultado: Soma: 27
Teste 2: valores v1 = 13, v2 = 14 e opcao = 2. Resultado: Subtração: -1
"""
def soma(x, y):     # Função soma, recebe 2 valores e retorna a soma
    return x + y
def subtrai(x, y):  # Recebe 2 valores, retorna a subtração
    calculo = x - y
    return calculo
# O if abaixo indica o início da execução do programa principal (main)
if __name__ == '__main__':               # Atalho: main + <tab>
    v1 = int(input("Primeiro valor: "))  # Início da função main.
    v2 = int(input("Segundo valor: "))
    opcao = int(input("[1] Somar\n[2] Subtrair\nOpção: "))
    if opcao == 1:              # Se digitou 1, chama a função soma,
        print(f'Soma: {soma(v1, v2)}')
    else:                       # senão, chama a função subtrai
        print(f'Subtração: {subtrai(v1, v2)}')

''' ----- ALTERAÇÕES:
a. Mostre também uma mensagem de opção inválida.
Teste 3: valores v1 = 13, v2 = 14 e opcao = 3. Resultado: Opção inválida
b. Refaça usando o símbolo + e - no lugar de 1 e 2 na mensgagem de leitura:
    opcao = int(input("[+] Somar\n[-] Subtrair\nOpção: "))
c. Use a função 'soma' para somar 4 valores e mostre o resultado. 
    Não altere (mexa) a função soma.                          

    ---- DICAS ABAIXO:    
    . . .                                                           # a.
    if opcao == 1:                              
        print(f"Soma: {soma(v1, v2)}")
    elif opcao == 2:                                              
        print(f"Subtração: {subtrai(v1, v2)}")
    else:
        print("Opção inválida")                                

'''
