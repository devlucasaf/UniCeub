"""             Comentários de várias linhas.
  CEUB  -  FATECS  -  BCC  -  ADS  -  Programação  -  Prof. Barbosa
ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Desenvolva a função converte_em_minuto. Ela recebe o horário no
formato (hora e minuto) e retorna o valor correspondente em minutos.

A função main lê dois horários (hora e minuto) e chama a função
converte_em_minuto duas vezes. Com os valores retornado pela função
converte_em_minuto, a função main mostra a diferença entre os dois
horários em minutos.

Teste 1: Entrada 1: horas: 2, minutos: 10
         Entrada 2: horas: 1, minutos: 30  Saída: Diferença: 40 minutos
"""


def converte_em_minuto(horas, minutos):  # Assinatura da função
    """ Converte hh:mm em minutos. """   # docstring
    vl_minutos = horas * 60 + minutos
    return vl_minutos                    # Retorna o valor calculado

# Início do programa principal
if __name__ == '__main__':               # Atalho: mai <tab>
    h = int(input("Horas: "))            # Lê os dados do horário 1
    m = int(input("Minutos: "))
    horario1 = converte_em_minuto(h, m)  # Chama a função
    h = int(input("Horas: "))            # Lê os dados do horário 2
    m = int(input("Minutos: "))
    horario2 = converte_em_minuto(h, m)  # Chama a função
    diferenca = horario2 - horario1
    print("\nDiferença em minutos dos dois horários: ", diferenca,
          "minutos.")

''' --- ALTERAÇÕES:
a. No final do main, mostre uma destas mensagens: "O primeiro horário 
   é maior", "O primeiro horário é menor" ou "Os horários são iguais".

    --- Dicas:
   ...                                                          # a.
    if diferenca > 0:                                  
        print("O primeiro horário é maior")
    elif diferenca < 0:
        print("O primeiro horário é maior")
    else:
        print("Os horários são iguais")

'''
