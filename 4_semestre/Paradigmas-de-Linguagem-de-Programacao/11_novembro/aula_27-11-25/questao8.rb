=begin
    Paradigmas de Linguagens de Programação
    II Atividade 5 (Linguagens Multiparadigmas)
    Aula: 27-11-25
    8. Escreva um programa em Python ou alguma linguagem multiparadigma que calcule o 
    fatorial de um número natural usando uma abordagem multiparadigmática.
=end
						
class Fatorial						
  def self.imperativo(n)
    resultado = 1           
    for i in 1..n           
      resultado *= i        
    end
    resultado               
  end
						
  def self.funcional(n)
    return 1 if n <= 1      
    n * funcional(n - 1)    
  end

  def self.misto(n)
    valores = (1..n).to_a     

    valores.reduce(1) { |acc, x| acc * x }
  end
end

print "Digite um número: "    
numero = gets.to_i            

puts "Fatorial do Paradigma Imperativo: #{Fatorial.imperativo(numero)}"
puts "Fatorial (Funcional): #{Fatorial.funcional(numero)}"
puts "Fatorial (Multiparadigmático): #{Fatorial.misto(numero)}"
