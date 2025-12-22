=begin
    Paradigmas de Linguagens de Programação
    II Atividade 5 (Linguagens Multiparadigmas)
    Aula: 27-11-25
    8. Escreva um programa em Python ou alguma linguagem multiparadigma que calcule o 
    fatorial de um número natural usando uma abordagem multiparadigmática.
=end

# Classe que encapsula diferentes formas de calcular o fatorial
class Fatorial

  # =============================
  # MÉTODO IMPERATIVO
  # =============================
  # Usa um laço e uma variável acumuladora (estilo imperativo)
  def self.imperativo(n)
    resultado = 1             # variável acumuladora
    for i in 1..n             # laço que vai de 1 até n
      resultado *= i          # multiplica o acumulador pelo contador
    end
    resultado                 # retorna o resultado final
  end

  # =============================
  # MÉTODO FUNCIONAL
  # =============================
  # Implementado usando recursão, característica do paradigma funcional
  def self.funcional(n)
    return 1 if n <= 1        # caso base da recursão
    n * funcional(n - 1)      # chamada recursiva (função chamando ela mesma)
  end

  # =============================
  # MÉTODO MISTO (MULTIPARADIGMA)
  # =============================
  # Combina orientação a objetos (dentro da classe),
  # estilo imperativo (construção do array)
  # e funcional (uso de reduce)
  def self.misto(n)
    valores = (1..n).to_a     # cria um array com valores de 1 até n (parte imperativa)

    # reduce é uma função de alta ordem (paradigma funcional)
    valores.reduce(1) { |acc, x| acc * x }
  end
end

# =============================
# PROGRAMA PRINCIPAL
# =============================

print "Digite um número: "    # solicita um número ao usuário
numero = gets.to_i            # lê o número digitado e converte para inteiro

# Chama cada versão do cálculo e exibe os resultados
puts "Fatorial do Paradigma Imperativo: #{Fatorial.imperativo(numero)}"
puts "Fatorial (Funcional): #{Fatorial.funcional(numero)}"
puts "Fatorial (Multiparadigmático): #{Fatorial.misto(numero)}"
