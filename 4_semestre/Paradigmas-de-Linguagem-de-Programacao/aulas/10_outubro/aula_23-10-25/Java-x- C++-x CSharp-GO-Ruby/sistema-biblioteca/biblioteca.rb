require_relative "livro"

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
