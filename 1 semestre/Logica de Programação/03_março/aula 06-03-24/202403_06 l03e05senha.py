"""               Comentários de várias linhas.

ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Elabore o programa que verifica se o usuário digitou a senha correta.
Mostre a mensagem “Senha incorreta.” ou “Acesso liberado.”.
Supondo que a senha correta seja 123.

Teste 1: senha = 123            Resposta: Acesso liberado.

Teste 2: senha = 111            Resposta: Senha incorreta.

- Passos para a implementaçao do programa:
Entrada de dados (leia)
Testes (se ...)
Saída de dados (escreva)
"""

senha_correta = 123             # Armazena a senha correta na memória
senha_usuario = int(input("Senha: "))
if senha_usuario == senha_correta:  # Se a senha está correta
    print("Acesso liberado.")
else:                           # Caso contrário
    print("Senha incorreta.")

''' ----- ALTERAÇÕES:
a. Considere que a senha correta é o código (string) 1b3.
    ----- DICAS ABAIXO:
senha_correta = '1b3'                      # Armazena a senha correta
senha_usuario=str(input("Digite a senha: ")) # senha_usuario=input("Digite a senha: ")
if senha_usuario == senha_correta:             # Se a senha estiver correta
    print("Acesso liberado.")
else:                                           
    print("Senha incorreta.")

'''
