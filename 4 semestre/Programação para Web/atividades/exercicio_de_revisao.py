#   Exercício de Revisão - Programação para Web

# 1) Conversão de dados

print("\n>>>>>>>>>>>Questão 1<<<<<<<<<<\n") 

num1 = float(input("Número 1: "))
num2 = float(input("Número 2: "))

print(f"Soma: {num1 + num2:.0f}")
print(f"Subtração: {num1 - num2:.0f}")
print(f"Multiplicação: {num1 * num2:.0f}")
print(f"Divisão: {num1 / num2:.0f}")

# 2) Formatação de strings

print("\n>>>>>>>>>>>Questão 2<<<<<<<<<<\n")

nome = input("Nome: ")
idade = int(input("Idade: "))
print(f"Olá, {nome}! Você tem {idade} anos.")

# 3) Aprovação de aluno

print("\n>>>>>>>>>>>Questão 3<<<<<<<<<<\n")

notas_aluno = []

for i in range(3):
    notas = int(input("Digite a nota do aluno: "))
    notas_aluno.append(notas)

soma = sum(notas_aluno) / 3

if soma >= 7:
    print(f"Aluno Aprovado! A nota do aluno foi: {soma:.2f}")

elif soma >= 5:
    print(f"Aluno em Recuperação! A nota do aluno foi: {soma:.2f}")

else:
    print(f"Aluno Reprovado! A nota do aluno foi: {soma:.2f}")

# 4) Calculadora simples

print("\n>>>>>>>>>>>Questão 4<<<<<<<<<<\n")

x = float(input("x: "))
op = input("Operação (+ - * /): ").strip()
y = float(input("y: "))
res = {
    "+": x + y,
    "-": x - y,
    "*": x * y,
    "/": (x / y if y != 0 else "Divisão por zero")
}.get(op, "Operação inválida")
print(res)

# 5) Classificação etária

print("\n>>>>>>>>>>>Questão 5<<<<<<<<<<\n")

idade = int(input("Idade: "))
if idade >= 18:
    print("Adulto")
elif idade >= 14:
    print("Juvenil")
elif idade >= 9:
    print("Infantil")
else:
    print("Mirim")

# 6) Tabuada

print("\n>>>>>>>>>>>Questão 6<<<<<<<<<<\n")

n = int(input("Número: "))
for i in range(1, 11):
    print(f"{n} x {i} = {n*i}")

# 7) Contador com for

print("\n>>>>>>>>>>>Questão 7<<<<<<<<<<\n")

for i in range(1, 101, 2):
    print(i)

# 8) Soma de números pares

print("\n>>>>>>>>>>>Questão 8<<<<<<<<<<\n")

soma = 0

while True:
    num = int(input("Número (0 para sair): "))
    if num == 0:
        break
    if num % 2 == 0:
        soma += num
print(f"Soma dos números pares: {soma}")

# 9) Lista de compras

print("\n>>>>>>>>>>>Questão 9<<<<<<<<<<\n")

lista_de_compras = []

for i in range(5):
    item = input("Item para adicionar à lista de compras: ")
    lista_de_compras.append(item)
print("Lista de compras:")
for item in lista_de_compras:
    print(f"- {item}")

# 10) Maior e menornúmero

print("\n>>>>>>>>>>>Questão 10<<<<<<<<<<\n")

numeros = []

for i in range(10):
    num = int(input("Digite um número: "))
    numeros.append(num)

print(f"Maior número: {max(numeros)}")
print(f"Menor número: {min(numeros)}")

# 11) Contar ocorrências

print("\n>>>>>>>>>>>Questão 11<<<<<<<<<<\n")

palavras = input("Lista de palavras (separadas por espaço): ").split()
alvo = input("Palavra a buscar: ")
print("Ocorrências:", sum(1 for p in palavras if p == alvo))

# 12) Cadastro de usuários

print("\n>>>>>>>>>>>Questão 12<<<<<<<<<<\n")

usuarios = []

for i in range(3):
    nome = input("Nome: ")
    idade = int(input("Idade: "))
    email = input("Email: ")
    usuarios.append({"nome": nome, "email": email, "idade": idade})

for u in usuarios:
    print(u)

#13) Boletim de alunos

print("\n>>>>>>>>>>>Questão 13<<<<<<<<<<\n")

boletim = {}

for i in range(3):
    nome = input("Nome: ")
    notas = list(map(float, input("Notas (separadas por espaço): ").split()))
    boletim[nome] = sum(notas) / len(notas) if notas else 0

for aluno, media in boletim.items():
    print(f"{aluno}: {media:.2f}")

# 14) Calculador de letras

print("\n>>>>>>>>>>>Questão 14<<<<<<<<<<\n")

from collections import Counter
texto = input("Texto: ").lower()
letras = [c for c in texto if c.isalpha()]
freq = dict(Counter(letras))
print(freq)

# 15) Função de fatorial

print("\n>>>>>>>>>>>Questão 15<<<<<<<<<<\n")

def fatorial(n: int) -> int:
    if n < 0: raise ValueError("n negativo")
    r = 1
    for i in range(2, n+1):
        r *= i
    return r

def fatorial_rec(n: int) -> int:
    if n < 0: raise ValueError("n negativo")
    return 1 if n in (0, 1) else n * fatorial_rec(n-1)

print(fatorial(5), fatorial_rec(5))

# 16) Verificador de palíndromo

print("\n>>>>>>>>>>>Questão 16<<<<<<<<<<\n")

def eh_palindromo(s: str) -> bool:
    s2 = "".join(c.lower() for c in s if c.isalnum())
    return s2 == s2[::-1]

print(eh_palindromo("radar"), eh_palindromo("python"))

# 17) Calculadora com funções

print("\n>>>>>>>>>>>Questão 17<<<<<<<<<<\n")

def somar(a, b):
    return a + b

def subtrair(a, b): 
    return a - b

def multiplicar(a, b): 
    return a * b

def dividir(a, b):
    return a / b if b!=0 else float("inf")

ops = {"+": somar, "-": subtrair, "*": multiplicar, "/": dividir}

a = float(input("a: "))
op = input("op: ")
b = float(input("b: "))

print(ops.get(op, lambda *_: "op inválida")(a,b))

# 18) Classe Pessoa

print("\n>>>>>>>>>>>Questão 18<<<<<<<<<<\n")

class ContaBancaria:
    def __init__(self, saldo=0.0):
        self._saldo = float(saldo)

    def depositar(self, v):
        if v > 0: 
            self._saldo += v

    def sacar(self, v):
        if 0 < v <= self._saldo: 
            self._saldo -= v
        else: 
            print("Saldo insuficiente ou valor inválido.")

    def ver_saldo(self):
        print(f"Saldo: R$ {self._saldo:.2f}")

if __name__ == "__main__": 
    c = ContaBancaria()
    c.depositar(1000)
    c.sacar(250)
    c.ver_saldo()

# 19) Classe ContaBancaria:

print("\n>>>>>>>>>>>Questão 19<<<<<<<<<<\n")

class Produto:
    def __init__(self, nome, preco, desconto=0.0):
        self.nome = nome
        self.preco = float(preco)
        self.desconto = float(desconto)  # entre 0 e 1 (ex.: 0.15 = 15%)

    def preco_final(self):
        return self.preco * (1 - self.desconto)

if __name__ == "__main__":
    p = Produto("Mouse", 100.0, 0.2)
    print(f"Preço final: R$ {p.preco_final():.2f}")

# 20) Classe Produto com desconto:

print("\n>>>>>>>>>>>Questão 20<<<<<<<<<<\n")

class Produto:
    def __init__(self, nome, preco, desconto=0.0):
        self.nome = nome
        self.preco = float(preco)
        self.desconto = float(desconto)  # entre 0 e 1 (ex.: 0.15 = 15%)

    def preco_final(self):
        return self.preco * (1 - self.desconto)

if __name__ == "__main__":
    p = Produto("Mouse", 100.0, 0.2)
    print(f"Preço final: R$ {p.preco_final():.2f}")
