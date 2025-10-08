"""               Comentários de várias linhas.

  CEUB  -  FATECS  -  BCC  -  ADS  -  Programação  -  Prof. Barbosa
ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Implemente a função calcula_dobro. Ela recebe um valor, calcula o
dobro e retorna o valor calculado. Não use o print dentro desta função.
A função principal (main) lê um valor, chama a função passando o
argumento (o valor lido) e mostra o valor retornado pela função
calcula_dobro.

- Teste: Entrada: 5         Saída: Valor retornado pela função: 10

"""

# O def indica o início de uma função (uma rotina)
def calcula_dobro(p_valor):               # Assinatura da função
    dobro = p_valor * 2
    return dobro

# O if abaixo indica o início da execução do programa principal (main)
if __name__ == '__main__':       # Função principal, atalho: mai + <tab>
    valor = int(input("Valor: "))
    valor_retorno = calcula_dobro(valor)  # Chama a função
    # A variável valor_retorno recebe o valor retornado pela função (def)
    # Valor retornado pela função é armazenado na variável valor_retorno
    print("Valor retornado pela função:", valor_retorno)


''' ----- ALTERAÇÕES:
a. Acrescente a função calcula triplo. Ela recebe um valor, calcula
   o triplo e retorna o valor calculado.
   Função principal mostra o valor retornado pela função calcula triplo
b. Refaça a função main sem usar a variável valor_retorno.

    ----- Dicas:

def calcula_triplo(p_valor):                                        # a.
    triplo = p_valor * 3
    return triplo
    ...

'''
