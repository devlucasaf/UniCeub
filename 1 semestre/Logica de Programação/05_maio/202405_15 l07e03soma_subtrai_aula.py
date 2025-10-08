"""            Comentários de várias linhas.
  CEUB  -  FATECS  -  BCC  -  ADS  -  Programação  -  Prof. Barbosa
ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

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

Teste 1: Entrada: 3 e 2         Saída: Soma = 5 e Subtração = 1
Teste 2: Entrada: 2 e 6         Saída: Soma = 8 e Subtração = -4 """

def soma(a, b):                         # Recebe dois valoes
    somar = a + b                       # Com variável local
    return somar
def subtrai(a, b):
    return a - b                        # Sem variável local
# O if abaixo indica o início da execução do programa principal (main)
if __name__ == '__main__':              # mai <tab>
    num1 = int(input("Primeiro valor inteiro: "))  # Lê os valores
    num2 = int(input("Segundo valor inteiro: "))
    retorno_soma = soma(num1, num2)     # Chama def com variável
    # A variável retorno_soma recebe o valor retornado pela função
    print("soma é:", retorno_soma)
    print("Subtração é:", subtrai(num1, num2))  # Chama def sem variável
    # O retorno das funções pode substituir os argumentos
''' --- ALTERAÇÕES:
a. Refaça a funçao soma sem usar a variável dentro da funçao 
   (variável somar)
b. Refaça o program principal (main) sem usar a variável retorno_soma.
c. Crie mais uma função (le_inteiro) para realizar o trabalho de
leitura de dados do usuário (que é feito com int(input("..."))). 
Ela não recebe nada e retorna um valor lido. Chame essa função 
duas vezes na função principal para substituir os inputs.
d. Na função main, coloque comentário ( # ) nos dois inputs e chame
a função recebe_inteiro duas vezes. 
e. Crie a função le_inteiro_2, troque a mensagem estática "Digite um
    --- DICAS ABAIXO:
def soma(a, b):                                                     # a.
    return a + b                                   
if __name__ == '__main__':                                          # b.
    ...
    # retorno_soma = soma(num1, num2)             # No main    
    # print("A soma é:", retorno_soma)  
    print("A soma é:", soma(num1, num2))           

Obs.:
O print() é uma função já implementada no Python que apenas utilizamos

'''
