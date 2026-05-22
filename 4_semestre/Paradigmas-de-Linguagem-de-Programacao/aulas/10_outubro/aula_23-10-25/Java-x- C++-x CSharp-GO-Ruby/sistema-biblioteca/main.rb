require_relative "biblioteca"

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
