=begin
    Paradigmas de Linguagens de Programação
    II Atividade 5 (Linguagens Multiparadigmas)
    Aula: 27-11-25
    8. Escreva um programa em Python ou alguma linguagem multiparadigma que calcule o 
    fatorial de um número natural usando uma abordagem multiparadigmática.
=end

# Fatorial usando múltiplos paradigmas

class FatorialCalculator
  # Paradigma Orientado a Objetos
  def calcular(numero)
    validar_entrada(numero)
    
    return fatorial_funcional(numero)
  end
  
  # Paradigma Funcional 
  def fatorial_funcional(n)
    case n
    when 0, 1 then 1
    else n * fatorial_funcional(n - 1)
    end
  end
  
  # Paradigma Imperativo 
  def fatorial_imperativo(n)
    resultado = 1
    (1..n).each do |i|
      resultado *= i
    end
    resultado
  end
  
  # Paradigma Funcional 
  def fatorial_reduce(n)
    (1..n).reduce(1, :*)
  end
  
  private
  
  # Paradigma de Contrato 
  def validar_entrada(numero)
    unless numero.is_a?(Integer) && numero >= 0
      raise ArgumentError, "Número deve ser natural (inteiro não negativo)"
    end
  end
end

# Paradigma Funcional 
module MathFunctions
  def self.fatorial(n)
    (1..n).inject(1, :*)
  end
end

# Teste do programa
if __FILE__ == $0
  calculator = FatorialCalculator.new
  
  puts "Cálculo de Fatorial - Abordagem Multiparadigma"
  puts "=" * 50
  
  # Teste com diferentes números
  [0, 1, 5, 10].each do |num|
    puts "Fatorial de #{num}:"
    puts "  Funcional (recursão): #{calculator.fatorial_funcional(num)}"
    puts "  Imperativo (loop): #{calculator.fatorial_imperativo(num)}"
    puts "  Funcional (reduce): #{calculator.fatorial_reduce(num)}"
    puts "  Módulo: #{MathFunctions.fatorial(num)}"
    puts "-" * 30
  end
  
  # Interface interativa
  begin
    print "\nDigite um número natural para calcular o fatorial: "
    input = gets.chomp.to_i
    
    if input >= 0
      resultado = calculator.calcular(input)
      puts "Resultado: #{input}! = #{resultado}"
    else
      puts "Erro: Número deve ser não negativo!"
    end
  rescue ArgumentError => e
    puts "Erro: #{e.message}"
  end
end