# Paradigma Multiparadigma em Ruby
# Este exemplo mostra:
# - Programação orientada a objetos (classes, herança, polimorfismo)
# - Programação funcional (map, select, reduce, lambdas)
# - Programação imperativa (loops, condicionais)
# - Metaprogramação (definição dinâmica de métodos)

# -------------------------------
# Paradigma Orientado a Objetos
# -------------------------------

class Animal
  attr_accessor :nome, :idade

  def initialize(nome, idade)
    @nome = nome
    @idade = idade
  end

  def falar
    "O animal faz um som."
  end
end

class Cachorro < Animal
  def falar
    "Au au!"
  end
end

class Gato < Animal
  def falar
    "Miau!"
  end
end

# -------------------------------
# Paradigma Funcional
# -------------------------------

# Lista de números para manipulação funcional
numeros = (1..10).to_a

# Usando map para transformar
quadrados = numeros.map { |n| n ** 2 }

# Usando select para filtrar
pares = numeros.select { |n| n.even? }

# Usando reduce para acumular
soma = numeros.reduce(0) { |acc, n| acc + n }

# Lambda para multiplicar
multiplicar = ->(a, b) { a * b }
produto = multiplicar.call(5, 6)

# -------------------------------
# Paradigma Imperativo
# -------------------------------

puts "Exemplo imperativo:"
for n in numeros
  if n % 2 == 0
    puts "#{n} é par"
  else
    puts "#{n} é ímpar"
  end
end

# -------------------------------
# Metaprogramação
# -------------------------------

class Pessoa
  attr_accessor :nome

  def initialize(nome)
    @nome = nome
  end
end

# Criando métodos dinamicamente
[:andar, :correr, :pular].each do |acao|
  Pessoa.define_method(acao) do
    "#{@nome} está #{acao}!"
  end
end

joao = Pessoa.new("João")
puts joao.andar
puts joao.correr
puts joao.pular

# -------------------------------
# Integração dos paradigmas
# -------------------------------

animais = [
  Cachorro.new("Rex", 5),
  Gato.new("Mimi", 3),
  Cachorro.new("Bob", 2)
]

# Usando programação funcional para mapear falas
falas = animais.map(&:falar)
puts "Falas dos animais: #{falas.join(', ')}"

# Usando imperativo para imprimir detalhes
for animal in animais
  puts "#{animal.nome} tem #{animal.idade} anos e fala: #{animal.falar}"
end

# -------------------------------
# Demonstração de polimorfismo
# -------------------------------

def apresentar(animal)
  puts "Este é #{animal.nome}, ele diz: #{animal.falar}"
end

animais.each { |a| apresentar(a) }

# -------------------------------
# Misturando estilos
# -------------------------------

# Imperativo + funcional
idades = animais.map(&:idade)
media_idade = idades.reduce(:+) / idades.size.to_f
puts "A média de idade dos animais é #{media_idade.round(2)} anos."

# -------------------------------
# Conclusão
# -------------------------------
puts "Este código demonstra Ruby como linguagem multiparadigma!"
