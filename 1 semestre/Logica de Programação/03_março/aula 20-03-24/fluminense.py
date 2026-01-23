numero = 0
ct_impares = 0
ct_pares = 0
ct = 0
soma_par = 0
somar_impar = 0  

while True:
    num = float(input("Digite um número: "))

    if num == 0:  # quando o usuário digita 0, o programa para
        break

    resto = num % 2  # calcula o resto da divisão por 2 para saber se é par ou ímpar

    if resto != 0:  # se o resto não for zero, é ímpar
        ct_impares += 1
        soma_impar += num  # soma os ímpares para depois calcular a média
    
    else:  # se o resto for zero, é par
        ct_pares += 1
        soma_par += num  # soma os pares para depois calcular a média
    
    numero += 1  # conta o total de números digitados

# aqui são calculadas as médias de pares e ímpares
media_par = soma_par / ct_pares
media_impar = soma_impar / ct_impares

# por fim, o programa mostra um resumo com as quantidades e médias
print(f"A quantidade de números pares é: {ct_pares}")
print(f"A quantidade de números impares é: {ct_impares}")
print(f"A média dos números pares é: {media_par}")
print(f"A média dos números impares é: {media_impar}")
print(f"A quantidade de números é: {numero}")
