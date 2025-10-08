"""            Comentários de várias linhas.

  CEUB  -  FATECS  -  BCC  -  ADS  -  Programação  -  Prof. Barbosa
ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Elabore o programa que faça a conversão de graus Fahrenheit (ºF) para
graus Celsius (ºC).
     Onde:     C = 5 . ( F - 32 )
                   9
                   
Teste 1: Entrada: Fahrenheit = 32   Saída: Celsius = 0.0
Teste 2: Entrada: Fahrenheit = 60   Saída: Celsius = 15.555555555555557
Teste 3: Entrada: Fahrenheit = 55   Saída: Celsius = 12.77777777777779

- Passos para a elaboração do programa:
Entrada de dados (leia)
Processamento (cálculo)
Saída de dados (escreva)


"""                             # fim do Comentário de várias linhas.
# Recebe a temperatura em Fahrenheit
fahrenheit = float(input("Digite a temperatura em ºF: "))
# Efetua o cálculo
celsius = 5 / 9 * (fahrenheit - 32)  # Solução 1 (parênteses obrigatórios)
# celsius = 5 * (fahrenheit - 32) / 9  # Solução 2
print("A temperatura em Celsius é", celsius)  # Mostra o resultado

''' -----  ALTERAÇÕES:
a. Mostre o valor celsius com três casas decimais.

print("Graus correspondente em Celsius: {:.3f} °C" .format (celsius))  # a.
print(f"Graus correspondente em Celsius: {celsius:.3f} °C")     
      Obs.: o valor 3 é a quantidade de casas decimais.

'''
