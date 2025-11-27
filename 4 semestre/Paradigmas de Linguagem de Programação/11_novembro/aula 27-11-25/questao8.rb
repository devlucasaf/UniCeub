# Fatorial com metaprogramação e múltiplos paradigmas

module FatorialMultiparadigma
  # Paradigma de metaprogramação - define métodos dinamicamente
  [:recursivo, :iterativo, :funcional].each do |metodo|
    define_singleton_method("fatorial_#{metodo}") do |n|
      case metodo
      when :recursivo
        n <= 1 ? 1 : n * send("fatorial_#{metodo}", n - 1)
      when :iterativo
        resultado = 1
        (1..n).each { |i| resultado *= i }
        resultado
      when :funcional
        (1..n).reduce(1, :*)
      end
    end
  end
  
  # Paradigma funcional com composição
  def self.fatorial_composicao(n)
    multiplicar = ->(a, b) { a * b }
    (1..n).reduce(1, &multiplicar)
  end
end

# Programa principal
if __FILE__ == $0
  include FatorialMultiparadigma
  
  puts "Fatorial com Metaprogramação"
  puts "=" * 30
  
  [5, 6, 7].each do |n|
    puts "\nFatorial de #{n}:"
    puts "  Recursivo: #{fatorial_recursivo(n)}"
    puts "  Iterativo: #{fatorial_iterativo(n)}"
    puts "  Funcional: #{fatorial_funcional(n)}"
    puts "  Composição: #{fatorial_composicao(n)}"
  end
end