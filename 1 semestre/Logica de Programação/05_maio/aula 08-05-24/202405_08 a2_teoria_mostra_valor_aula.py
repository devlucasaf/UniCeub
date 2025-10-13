"""               Comentários de várias linhas.

ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Crie uma função (def) que recebe um valor e mostra o valor recebido.
A função main (principal) chama a função três vezes, passa um valor
inteiro, um valor float e depois um valor negativo. """
# O def indica o início de uma função (uma rotina)
def mostra_valor(p_valor):  # Assinatura da função
    print(f"Parâmetro recebido: {p_valor}")
# Chama a função (def) dentro do main.
# O if abaixo indica o início da execução do programa principal (main)
if __name__ == '__main__':  # Função principal, atalho: mai + <tab>
    # Primeira forma de fazer (sem variáveis, passa o valor direto):
    mostra_valor(5)         # Chama a função
    mostra_valor(23.8)      # Chama a função
    mostra_valor(-55)       # Chama a função
    # Segunda forma de fazer (com variáveis, sem input):
    inteiro = 5
    mostra_valor(inteiro)   # Chama a função
    numero = 23.8
    mostra_valor(numero)    # Chama a função
    negativo = -55
    mostra_valor(negativo)  # Chama a função
    # Terceira forma de fazer (com variáveis, com input):
    inteiro = int(input("valor"))
    mostra_valor(inteiro)  # Chama a função
    real = float(input("valor"))
    mostra_valor(real)      # Chama a função
    negativo = int(input("valor"))
    mostra_valor(negativo)  # Chama a função
''' ----- ALTERAÇÕES:
a. Na função principal, mostre uma mensagem antes de chamar a função
b. Na função principal, mostre uma mensagem depois de chamar a função
c. Crie mais uma função para mostrar dois valores, ela recebe dois 
    valores e mostra os valores. No main, chame a função.
d. Chame novamente a função mostra_dois_valores, o usuário digita os 
    dois valores na função principal (main)

    ----- DICAS:
    print ("Mensagem antes da chamada da função")                   # a.
    ...                                                             # b.
    print ("Mensagem depois da chamada da função")                  

'''
