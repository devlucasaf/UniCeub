numero = 0
ct_impares = 0
ct_pares = 0
ct = 0
soma_par = 0
somar_impar = 0

while True:
    num = float(input("Digite um número: "))

    if num == 0:
        break

    resto = num % 2

    if resto != 0:
        ct_impares += 1
        soma_impar += num
    
    else:
        ct_pares += 1
        soma_par += num
    
    numero += 1

media_par = soma_par / ct_pares
media_impar = soma_impar / ct_impares

print(f"A quantidade de números pares é: {ct_pares}")
print(f"A quantidade de números impares é: {ct_impares}")
print(f"A média dos números pares é: {media_par}")
print(f"A média dos números impares é: {media_impar}")
print(f"A quantidade de números é: {numero}")