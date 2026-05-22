package paradigmapookotlin

// Função de extensão
fun ItemBiblioteca.getDescricaoResumida(): String {
    return "$titulo ($anoPublicacao) - $autor"
}

fun main() {
    println("=== SISTEMA DE BIBLIOTECA DIGITAL ===\n")
    
    val livro1 = LivroDigital(
        "LIV-001",
        "Domain-Driven Design",
        "Eric Evans",
        2003,
        "PDF",
        5.2,
        528,
        "978-8550800653",
        10
    )
    
    val livro2 = LivroDigital(
        "LIV-002",
        "Clean Code",
        "Robert C. Martin",
        2008,
        "EPUB",
        3.8,
        464,
        "978-0132350884",
        5
    )
    
    val revista1 = RevistaDigital(
        "REV-001",
        "Scientific American",
        "Vários Autores",
        2023,
        150,
        "Outubro",
        7
    )
    
    val usuario1 = Usuario("USR-001", "João Silva", "joao@email.com", TipoUsuario.ESTUDANTE)
    val usuario2 = Usuario("USR-002", "Maria Santos", "maria@email.com", TipoUsuario.PROFESSOR)
    val admin = Usuario.criarUsuarioAdmin()
    
    val biblioteca = Biblioteca()
    
    biblioteca.adicionarItem(livro1)
    biblioteca.adicionarItem(livro2)
    biblioteca.adicionarItem(revista1)
    
    biblioteca.cadastrarUsuario(usuario1)
    biblioteca.cadastrarUsuario(usuario2)
    biblioteca.cadastrarUsuario(admin)
    
    println("\n=== TESTANDO DOWNLOAD ===")
    livro1.realizarDownload()
    livro1.realizarDownload()
    
    println("\n=== TESTANDO RESERVA ===")
    revista1.reservar("Maria Santos")
    revista1.exibirDetalhes()
    
    println("\n=== TESTANDO EMPRÉSTIMOS ===")
    biblioteca.realizarEmprestimo("LIV-001", "USR-001", "2024-01-15")
    biblioteca.realizarEmprestimo("LIV-002", "USR-002", "2024-01-15")
    
    println("\n=== USANDO FUNÇÃO DE EXTENSÃO ===")
    println("Descrição resumida: ${livro1.getDescricaoResumida()}")
    
    println("\n=== TESTANDO MULTA ===")
    println("Multa por 5 dias de atraso no livro: R$ ${livro1.calcularMulta(5)}")
    println("Multa por 5 dias de atraso na revista: R$ ${revista1.calcularMulta(5)}")
    
    println("\n=== LISTANDO ITENS ===")
    biblioteca.listarItensPorTipo("Livro Digital")
    
    println("\n=== PROCESSANDO ITENS COM HIGHER-ORDER FUNCTION ===")
    biblioteca.processarItens { item ->
        println("- ${item.getDescricaoResumida()}")
    }
    
    println("\n=== TESTANDO ENUM ===")
    println("Limite de empréstimos para estudante: ${TipoUsuario.ESTUDANTE.getLimiteEmprestimos()}")
    println("Limite de empréstimos para professor: ${TipoUsuario.PROFESSOR.getLimiteEmprestimos()}")
    
    println("\n=== TESTANDO INTERFACE ===")
    if (livro1.podeSerBaixado()) {
        println("Livro disponível para download: ${livro1.getLinkDownload()}")
    }
    
    println("\n=== TESTANDO SEALED CLASS ===")
    val resultado: ResultadoBusca = ResultadoBusca.Sucesso(listOf(livro1, livro2))
    
    when (resultado) {
        is ResultadoBusca.Sucesso -> println("Busca bem sucedida: ${resultado.itens.size} itens encontrados")
        is ResultadoBusca.Erro -> println("Erro na busca: ${resultado.mensagem}")
        ResultadoBusca.Vazio -> println("Nenhum item encontrado")
    }
    
    println("\n=== SISTEMA FINALIZADO ===")
}