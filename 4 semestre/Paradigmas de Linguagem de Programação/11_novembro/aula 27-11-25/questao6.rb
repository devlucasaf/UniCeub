=begin
    Paradigmas de Linguagens de Programação
    II Atividade 5 (Linguagens Multiparadigmas)
    Aula: 27-11-25
    6. (Problema) Crie uma função/classe para calcular o fatorial de um número: 
    6.1. Usando o paradigma imperativo 
    6.2. Usando o paradigma funcional 
    6.3. Usando o paradigma orientado a objetos.
=end

# Paradigma Imperativo
def fatorial_imperativo(n)
    result = 1
    for i in 1..n
    result *= i
    end
    result
end

# Paradigma Funcional
def fatorial_funcional(n)
    return 1 if n == 0
    (1..n).reduce(1) { |acc, x| acc * x }
end

# Paradigma Orientado a Objetos
class Fatorial
    def initialize(n)
        @n = n
    end

    def calcula
        return 1 if @n == 0

        result = 1
        @n.times do |i|
        result *= (i + 1)
    end
    result
    end
end

puts "Paradigma Imperativo: fatorial de 5 = #{fatorial_imperativo(5)}"
puts "Paradigma Funcional: fatorial de 5 = #{fatorial_funcional(5)}"

fat = Fatorial.new(5)
puts "Paradigma Orientado a Objetos: fatorial de 5 = #{fat.calcula}"
