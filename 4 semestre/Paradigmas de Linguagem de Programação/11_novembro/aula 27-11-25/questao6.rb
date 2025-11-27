=begin
    Paradigmas de Linguagens de Programação
    II Atividade 5 (Linguagens Multiparadigmas)
    Aula: 27-11-25
    Aluno: Lucas André Ferreira de Freitas
=end

# Paradigma Imperativo

def fatorial_imperativo(n)
    result = 1
    for i in 1..n
        result *= 1
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
        returno 1 if @n == 0

        result = 1
        @n.times do |i|
            result *= (i + 1)
        end
        result
    end
end

puts "Paradigma Imperativo: fatorial de 5 = #{fatorial_imperativo(5)}"
puts "Paradigma Funcional: fatorial de 5 = #{fatorial_funcional}"

fat = Fatorial.new(5)
puts "Paradigma Orientado a Objetos: fatorial de 5 = #{fat.calcula}"
