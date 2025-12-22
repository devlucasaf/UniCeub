"""
Listas e retomada de conhecimentos sobre Python
Programação para Web
Aula: 28-07-25
"""

lista = []

o = int(input("Digite um número: "))
a = int(input("Digite outro número: "))

lista.insert(o)
lista.insert(a)

print(lista)

print(f"O maior número é: {max(lista)}")
print(f"A média é: {(sum(lista)) / len(lista):.0f}")
