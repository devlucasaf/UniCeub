"""            Comentários de várias linhas.
ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Desenvolva a função (def) converte em minutos. Ela recebe dois
valores inteiros que corresponde ao horário no formato (hora e minuto)
e retorna um valor inteiro que corresponde ao horário convertido em
minutos.
O programa principal (main) lê o horário (hora e minuto), chama a
função converte em minutos passando os dois argumentos (hora e minuto)
e com o valor retornado pela função, mostra uma mensagem apropriada,
ou seja, o horário convertido em minutos.

Teste 1: Entrada: horas: 1, minutos: 30     Saída: 90 minutos.
Teste 2: Entrada: horas: 2, minutos: 10     Saída: 130 minutos.  """

def converte_em_minuto(horas, minutos):     # Assinatura da função
    """ Converte hh:mm em minutos. """      # docstring
    vl_minutos = horas * 60 + minutos
    return vl_minutos                       # Retorna o valor calculado

# Início do programa principal
if __name__ == '__main__':                  # Atalho: mai <tab>
    h = int(input("Horas: "))               # Lê os dados de entrada
    m = int(input("Minutos: "))
    retorno = converte_em_minuto(h, m)      # Chama a função
    print(f"Horário em minutos: {retorno}")
''' ----- ALTERAÇÕES:
a. No final do main, mostre também o valor das horas e minutos lidos.
    print(h, "horas e", m, "minutos =", retorno, "minutos.")        # a.
    print("{} horas e {} minutos = {} minutos." .format(h, m, retorno))
    print(f"{h} horas e {m} minutos = {retorno} minutos.")

b. Chame a função converte_em_minuto pelo menos duas vezes.
    retorno = converte_em_minuto(10, 20)  # No final do main.     # b.
    print(f"{h} horas e {m} minutos = {retorno} minutos.")
c. Crie a função converte_em_minuto_2, que recebe dois números inteiros
    (hora e minuto) e mostra o valor da conversão. 
    A função não retorna nada.

'''
