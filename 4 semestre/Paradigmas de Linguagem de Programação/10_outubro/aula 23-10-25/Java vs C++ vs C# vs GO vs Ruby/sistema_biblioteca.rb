=begin
  Paradigmas de Linguagem de Programação
  Aula: 23-10-25
  Slide 9: Diferença entre Java vs C++ vs C# vs GO vs Ruby
  Exemplo em Ruby - Sistema de Biblioteca
=end


class Livro
  attr_reader :titulo, :autor
  attr_accessor :emprestado

  def initialize(titulo, autor)
    @titulo = titulo
    @autor = autor
    @emprestado = false
  end

  def emprestar
    @emprestado = true
  end

  def devolver
    @emprestado = false
  end

  def to_s
    status = @emprestado ? "Emprestado" : "Disponível"
    "#{@titulo} - #{@autor} (#{status})"
  end
end

class Biblioteca
  def initialize
    @livros = []
  end

  def adicionar_livro(titulo, autor)
    @livros << Livro.new(titulo, autor)
  end

  def listar_livros
    if @livros.empty?
      puts "Nenhum livro cadastrado."
    else
      @livros.each_with_index do |livro, i|
        puts "#{i + 1}. #{livro}"
      end
    end
  end

  def buscar_por_titulo(titulo)
    livro = @livros.find { |l| l.titulo.downcase == titulo.downcase }
    if livro
      puts "Encontrado: #{livro}"
    else
      puts "Livro não encontrado."
    end
  end

  def emprestar_livro(indice)
    if indice >= 0 && indice < @livros.size
      livro = @livros[indice]
      if !livro.emprestado
        livro.emprestar
        puts "Livro emprestado com sucesso."
      else
        puts "Livro já está emprestado."
      end
    else
      puts "Índice inválido."
    end
  end

  def devolver_livro(indice)
    if indice >= 0 && indice < @livros.size
      livro = @livros[indice]
      if livro.emprestado
        livro.devolver
        puts "Livro devolvido com sucesso."
      else
        puts "Livro não está emprestado."
      end
    else
      puts "Índice inválido."
    end
  end
end

biblioteca = Biblioteca.new
opcao = nil

loop do
  puts "\n--- Menu ---"
  puts "1. Adicionar livro"
  puts "2. Listar livros"
  puts "3. Buscar por título"
  puts "4. Emprestar livro"
  puts "5. Devolver livro"
  puts "0. Sair"
  print "Escolha uma opção: "
  opcao = gets.to_i

  case opcao
  when 1
    print "Título: "
    titulo = gets.chomp
    print "Autor: "
    autor = gets.chomp
    biblioteca.adicionar_livro(titulo, autor)
  when 2
    biblioteca.listar_livros
  when 3
    print "Digite o título: "
    busca = gets.chomp
    biblioteca.buscar_por_titulo(busca)
  when 4
    biblioteca.listar_livros
    print "Digite o número do livro para emprestar: "
    indice = gets.to_i - 1
    biblioteca.emprestar_livro(indice)
  when 5
    biblioteca.listar_livros
    print "Digite o número do livro para devolver: "
    indice = gets.to_i - 1
    biblioteca.devolver_livro(indice)
  when 0
    puts "Encerrando..."
    break
  else
    puts "Opção inválida."
  end
end
