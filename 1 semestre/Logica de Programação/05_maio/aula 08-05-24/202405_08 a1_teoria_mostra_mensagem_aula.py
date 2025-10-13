"""               Comentários de várias linhas.

ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

A convenção ( __ ) é utilizada em Python e chamamos de "dunder".
Vem da expressão em inglês "double-underscore" - sublinhado duplo.

- Implemente uma função pra mostrar a mensagem fixa "Bem-vindo ao def
do Python". Ela não recebe nada e não retorna nada.
Crie o programa principal (função main) para chamar a funçao.

"""

# O def indica o início de uma função (uma rotina)
def mostra_mensagem():                  # Assinatura da função
    print("Bem-vindo ao uso do def do Python.")

# Chama a função (def) dentro do main
# O if abaixo indica o início da execução do programa principal (main)
if __name__ == '__main__':          # Atalho: mai + tecla<tab>
    mostra_mensagem()               # Chama a função sem passar nada
''' 
----- ALTERAÇÕES:
a. Na função principal, mostre uma mensagem antes de chamar a função 
b. Na função principal, mostre uma mensagem depois de chamar a função
c. Crie a função mostra_mensagem_2, ela recebe uma frase e mostra a
    frase recebida. Na função principal, chame a função passando uma 
    mensagem
d. No main, chame a função mostra_mensagem2 mais uma vez, passe a 
    mensagem
e. No main, use o input para ler uma mensagem e chame a função 
    mostra_mensagem2 mais uma vez passando a mensagem lida.
    ----- DICAS:
if __name__ == '__main__':                   # Atalho: mai + tecla<tab>
    print("Mensagem antes de chamar a função")  # No início do main # a.
    mostra_mensagem()                  # Chama a função sem passar nada
    print("Mensagem depois de chamar a função")                     # b.

'''
