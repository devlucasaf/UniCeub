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
