# ============================================
# Lista de Exercícios – Estruturas de Dados e Classes
# ============================================

# ============================================
# Parte 1 – Listas, Tuplas e Sets (Básico → Intermediário)
# ============================================

print(f"\n>>>>>>>>>>>Questão 1<<<<<<<<<<\n")
# 1. Lista de frutas
frutas = ["maçã", "banana", "laranja", "uva", "mamão"]
for f in frutas:
    print(f"{f}")

print(f"\n>>>>>>>>>>>Questão 2<<<<<<<<<<\n")
# 2. Soma de lista
numeros = [float(input(f"Número {i+1}: ")) for i in range(5)]
print(f"Soma: {sum(numeros)}")

print(f"\n>>>>>>>>>>>Questão 3<<<<<<<<<<\n")
# 3. Remover duplicatas de uma lista
lista = input("Digite elementos separados por espaço: ").split()
sem_duplicatas = list(set(lista))
print(f"Lista sem duplicatas: {sem_duplicatas}")

print(f"\n>>>>>>>>>>>Questão 4<<<<<<<<<<\n")
# 4. Invertendo uma lista
nums = list(map(int, input("Digite números separados por espaço: ").split()))
print(f"Lista invertida: {nums[::-1]}")

print(f"\n>>>>>>>>>>>Questão 5<<<<<<<<<<\n")
# 5. Máximo e mínimo
valores = list(map(float, input("Digite números separados por espaço: ").split()))
print(f"Maior: {max(valores)}")
print(f"Menor: {min(valores)}")

print(f"\n>>>>>>>>>>>Questão 6<<<<<<<<<<\n")
# 6. Unindo conjuntos
A = {1, 2, 3, 4}
B = {3, 4, 5, 6}
print(f"União: {A | B}")
print(f"Interseção: {A & B}")
print(f"Diferença: {A - B}")

print(f"\n>>>>>>>>>>>Questão 7<<<<<<<<<<\n")
# 7. Elementos únicos de dois sets
print(f"Apenas em A: {A - B}")
print(f"Apenas em B: {B - A}")

print(f"\n>>>>>>>>>>>Questão 8<<<<<<<<<<\n")
# 8. Verificação de elemento em tupla
nomes = ("Lucas", "Maria", "João", "Ana")
nome = input("Digite um nome: ")
print(f"Está na tupla? {nome in nomes}")

print(f"\n>>>>>>>>>>>Questão 9<<<<<<<<<<\n")
# 9. Contagem de elementos
from collections import Counter
lista = input("Digite elementos separados por espaço: ").split()
contagem = dict(Counter(lista))
print(f"Contagem: {contagem}")

print(f"\n>>>>>>>>>>>Questão 10<<<<<<<<<<\n")
# 10. Lista de listas (matriz 3x3)
matriz = [[i + j*3 + 1 for i in range(3)] for j in range(3)]
for linha in matriz:
    print(f"{linha}")

# ============================================
# Parte 2 – Dicionários (Intermediário)
# ============================================

print(f"\n>>>>>>>>>>>Questão 11<<<<<<<<<<\n")
# 11. Cadastro simples
pessoa = {"nome": "Lucas", "idade": 20, "cidade": "Brasília"}
for k, v in pessoa.items():
    print(f"{k.capitalize()}: {v}")

print(f"\n>>>>>>>>>>>Questão 12<<<<<<<<<<\n")
# 12. Agenda de contatos
agenda = {"Lucas": "99999-1111", "Maria": "88888-2222"}
nome = input("Buscar contato: ")
print(f"Telefone: {agenda.get(nome, 'Não encontrado')}")

print(f"\n>>>>>>>>>>>Questão 13<<<<<<<<<<\n")
# 13. Conversão de notas
alunos = {"Lucas": 8, "Maria": 5.5, "João": 7}
aprovados = {a: n for a, n in alunos.items() if n >= 6}
print(f"Aprovados: {aprovados}")

print(f"\n>>>>>>>>>>>Questão 14<<<<<<<<<<\n")
# 14. Mesclando dicionários
chaves = ["nome", "idade", "cidade"]
valores = ["Lucas", 20, "Brasília"]
dicionario = dict(zip(chaves, valores))
print(f"Dicionário: {dicionario}")

print(f"\n>>>>>>>>>>>Questão 15<<<<<<<<<<\n")
# 15. Frequência de palavras
frase = input("Digite uma frase: ").lower().split()
freq = dict(Counter(frase))
print(f"Frequência: {freq}")

# ============================================
# Parte 3 – Classes e Objetos (Avançado)
# ============================================

print(f"\n>>>>>>>>>>>Questão 16<<<<<<<<<<\n")
# 16. Classe Pessoa
class Pessoa:
    def __init__(self, nome, idade):
        self.nome = nome
        self.idade = idade
    def saudacao(self):
        print(f"Olá, meu nome é {self.nome} e tenho {self.idade} anos.")

p1 = Pessoa("Lucas", 20)
p1.saudacao()

print(f"\n>>>>>>>>>>>Questão 17<<<<<<<<<<\n")
# 17. Classe ContaBancaria
class ContaBancaria:
    def __init__(self, saldo=0.0):
        self.saldo = saldo
    def depositar(self, valor):
        self.saldo += valor
    def sacar(self, valor):
        if valor <= self.saldo:
            self.saldo -= valor
        else:
            print(f"Saldo insuficiente!")
    def exibir_saldo(self):
        print(f"Saldo atual: R$ {self.saldo:.2f}")

conta = ContaBancaria()
conta.depositar(1000)
conta.sacar(250)
conta.exibir_saldo()

print(f"\n>>>>>>>>>>>Questão 18<<<<<<<<<<\n")
# 18. Classe Retângulo
class Retangulo:
    def __init__(self, altura, largura):
        self.altura = altura
        self.largura = largura
    def area(self):
        return self.altura * self.largura
    def perimetro(self):
        return 2 * (self.altura + self.largura)

r = Retangulo(5, 3)
print(f"Área: {r.area()}")
print(f"Perímetro: {r.perimetro()}")

print(f"\n>>>>>>>>>>>Questão 19<<<<<<<<<<\n")
# 19. Classe Aluno
class Aluno:
    def __init__(self, nome, notas):
        self.nome = nome
        self.notas = notas
    def media(self):
        return sum(self.notas) / len(self.notas)

a = Aluno("Lucas", [8, 7, 9])
print(f"Média de {a.nome}: {a.media():.2f}")

print(f"\n>>>>>>>>>>>Questão 20<<<<<<<<<<\n")
# 20. Sistema de biblioteca
class Livro:
    def __init__(self, titulo, autor):
        self.titulo = titulo
        self.autor = autor

class Biblioteca:
    def __init__(self):
        self.livros = []
    def adicionar(self, livro):
        self.livros.append(livro)
    def listar(self):
        for l in self.livros:
            print(f"{l.titulo} - {l.autor}")
    def buscar(self, titulo):
        for l in self.livros:
            if l.titulo.lower() == titulo.lower():
                print(f"Encontrado: {l.titulo} - {l.autor}")
                return
        print(f"Livro não encontrado.")

b = Biblioteca()
b.adicionar(Livro("Dom Casmurro", "Machado de Assis"))
b.adicionar(Livro("1984", "George Orwell"))
b.listar()
b.buscar("1984")
