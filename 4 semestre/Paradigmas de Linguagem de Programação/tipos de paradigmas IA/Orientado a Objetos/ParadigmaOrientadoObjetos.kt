// ============================================
// EXEMPLO DE PROGRAMAÇÃO ORIENTADA A OBJETOS EM KOTLIN
// Sistema de Gerenciamento de Biblioteca Digital
// ============================================

// 1. CLASSE ABSTRATA (Abstração)
abstract class ItemBiblioteca(
    open val id: String,
    open val titulo: String,
    open val autor: String,
    open val anoPublicacao: Int
) {
    abstract fun calcularMulta(diasAtraso: Int): Double
    abstract fun getTipo(): String
    
    open fun exibirDetalhes() {
        println("ID: $id")
        println("Título: $titulo")
        println("Autor: $autor")
        println("Ano: $anoPublicacao")
    }
}

// 2. INTERFACE
interface Digitalizavel {
    val formato: String
    val tamanhoMB: Double
    
    fun podeSerBaixado(): Boolean
    fun getLinkDownload(): String
}

// 3. HERANÇA e IMPLEMENTAÇÃO DE INTERFACE
class LivroDigital(
    override val id: String,
    override val titulo: String,
    override val autor: String,
    override val anoPublicacao: Int,
    override val formato: String,
    override val tamanhoMB: Double,
    val numeroPaginas: Int,
    val ISBN: String,
    var copiasDisponiveis: Int
) : ItemBiblioteca(id, titulo, autor, anoPublicacao), Digitalizavel {
    
    // 4. ENCAPSULAMENTO com propriedades privadas
    private var numeroDownloads: Int = 0
    
    // 5. GETTER personalizado
    val popularidade: String
        get() = when {
            numeroDownloads > 1000 -> "Muito Popular"
            numeroDownloads > 500 -> "Popular"
            numeroDownloads > 100 -> "Moderado"
            else -> "Pouco Conhecido"
        }
    
    // 6. SOBRESCRITA DE MÉTODO
    override fun calcularMulta(diasAtraso: Int): Double {
        return diasAtraso * 0.50 // R$ 0,50 por dia
    }
    
    override fun getTipo(): String = "Livro Digital"
    
    override fun podeSerBaixado(): Boolean = copiasDisponiveis > 0
    
    override fun getLinkDownload(): String {
        return "https://biblioteca.digital/download/$id.$formato"
    }
    
    // 7. MÉTODO ESPECÍFICO DA CLASSE
    fun realizarDownload(): Boolean {
        if (podeSerBaixado()) {
            copiasDisponiveis--
            numeroDownloads++
            println("Download realizado: $titulo")
            return true
        }
        println("Não há cópias disponíveis para download: $titulo")
        return false
    }
    
    // 8. SOBRESCRITA COM EXTENSÃO DA FUNÇÃO DA CLASSE PAI
    override fun exibirDetalhes() {
        super.exibirDetalhes()
        println("Formato: $formato")
        println("Páginas: $numeroPaginas")
        println("ISBN: $ISBN")
        println("Cópias disponíveis: $copiasDisponiveis")
        println("Downloads: $numeroDownloads")
        println("Popularidade: $popularidade")
        println("Link: ${getLinkDownload()}")
    }
}

// 9. SEGUNDA SUBCLASSE (Herança)
class RevistaDigital(
    override val id: String,
    override val titulo: String,
    override val autor: String,
    override val anoPublicacao: Int,
    val edicao: Int,
    val mesPublicacao: String,
    var periodoEmprestimoDias: Int
) : ItemBiblioteca(id, titulo, autor, anoPublicacao) {
    
    // 10. PROPRIEDADE COM DELEGAÇÃO (Delegated Property)
    private var reservadoPor: String? by ReservaDelegate()
    
    class ReservaDelegate {
        private var valor: String? = null
        
        operator fun getValue(thisRef: RevistaDigital, property: Any): String? {
            println("Acessando reserva para: ${thisRef.titulo}")
            return valor
        }
        
        operator fun setValue(thisRef: RevistaDigital, property: Any, value: String?) {
            println("Modificando reserva para: ${thisRef.titulo}")
            valor = value
        }
    }
    
    override fun calcularMulta(diasAtraso: Int): Double {
        return diasAtraso * 1.00 // R$ 1,00 por dia para revistas
    }
    
    override fun getTipo(): String = "Revista Digital"
    
    // 11. MÉTODOS ESPECÍFICOS
    fun reservar(usuario: String) {
        reservadoPor = usuario
        println("Revista '$titulo' reservada para $usuario")
    }
    
    fun liberarReserva() {
        reservadoPor = null
        println("Reserva da revista '$titulo' liberada")
    }
    
    override fun exibirDetalhes() {
        super.exibirDetalhes()
        println("Edição: $edicao")
        println("Mês: $mesPublicacao")
        println("Período de empréstimo: $periodoEmprestimoDias dias")
        println("Reservado por: ${reservadoPor ?: "Ninguém"}")
    }
}

// 12. CLASSE DE DADOS (Data Class)
data class Usuario(
    val id: String,
    val nome: String,
    val email: String,
    val tipo: TipoUsuario
) {
    // 13. COMPANION OBJECT (similar a métodos estáticos)
    companion object {
        fun criarUsuarioAdmin(): Usuario {
            return Usuario("ADMIN-001", "Administrador", "admin@biblioteca.com", TipoUsuario.ADMIN)
        }
    }
}

// 14. ENUM CLASS
enum class TipoUsuario {
    ESTUDANTE, PROFESSOR, FUNCIONARIO, ADMIN;
    
    fun getLimiteEmprestimos(): Int {
        return when (this) {
            ESTUDANTE -> 3
            PROFESSOR -> 10
            FUNCIONARIO -> 5
            ADMIN -> Int.MAX_VALUE
        }
    }
}

// 15. CLASSE COM COMPOSIÇÃO
class Biblioteca {
    private val itens = mutableListOf<ItemBiblioteca>()
    private val usuarios = mutableListOf<Usuario>()
    private val emprestimos = mutableListOf<Emprestimo>()
    
    // 16. CLASSE ANINHADA (Nested Class)
    class Emprestimo(
        val itemId: String,
        val usuarioId: String,
        val dataEmprestimo: String,
        var dataDevolucao: String?
    )
    
    // 17. MÉTODOS PÚBLICOS
    fun adicionarItem(item: ItemBiblioteca) {
        itens.add(item)
        println("Item adicionado: ${item.titulo}")
    }
    
    fun cadastrarUsuario(usuario: Usuario) {
        usuarios.add(usuario)
        println("Usuário cadastrado: ${usuario.nome}")
    }
    
    fun realizarEmprestimo(itemId: String, usuarioId: String, data: String): Boolean {
        val usuario = usuarios.find { it.id == usuarioId }
        val item = itens.find { it.id == itemId }
        
        if (usuario == null || item == null) {
            println("Usuário ou item não encontrado")
            return false
        }
        
        // Verifica limite de empréstimos
        val emprestimosAtivos = emprestimos.count { 
            it.usuarioId == usuarioId && it.dataDevolucao == null 
        }
        
        if (emprestimosAtivos >= usuario.tipo.getLimiteEmprestimos()) {
            println("Limite de empréstimos atingido para ${usuario.nome}")
            return false
        }
        
        val novoEmprestimo = Emprestimo(itemId, usuarioId, data, null)
        emprestimos.add(novoEmprestimo)
        println("Empréstimo realizado: ${item.titulo} para ${usuario.nome}")
        return true
    }
    
    fun listarItensPorTipo(tipo: String) {
        println("\n=== Itens do tipo: $type ===")
        itens.filter { it.getTipo() == tipo }
            .forEach { it.exibirDetalhes() }
    }
    
    // 18. HIGHER-ORDER FUNCTION
    fun processarItens(operacao: (ItemBiblioteca) -> Unit) {
        itens.forEach { operacao(it) }
    }
}

// 19. FUNÇÃO DE EXTENSÃO (Extension Function)
fun ItemBiblioteca.getDescricaoResumida(): String {
    return "$titulo ($anoPublicacao) - $autor"
}

// 20. CLASSE SEALED (Sealed Class)
sealed class ResultadoBusca {
    data class Sucesso(val itens: List<ItemBiblioteca>) : ResultadoBusca()
    data class Erro(val mensagem: String) : ResultadoBusca()
    object Vazio : ResultadoBusca()
}

// ============================================
// FUNÇÃO PRINCIPAL PARA TESTAR O SISTEMA
// ============================================

fun main() {
    println("=== SISTEMA DE BIBLIOTECA DIGITAL ===\n")
    
    // Criando instâncias
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
    
    // Criando usuários
    val usuario1 = Usuario("USR-001", "João Silva", "joao@email.com", TipoUsuario.ESTUDANTE)
    val usuario2 = Usuario("USR-002", "Maria Santos", "maria@email.com", TipoUsuario.PROFESSOR)
    val admin = Usuario.criarUsuarioAdmin()
    
    // Criando biblioteca
    val biblioteca = Biblioteca()
    
    // Adicionando itens e usuários
    biblioteca.adicionarItem(livro1)
    biblioteca.adicionarItem(livro2)
    biblioteca.adicionarItem(revista1)
    
    biblioteca.cadastrarUsuario(usuario1)
    biblioteca.cadastrarUsuario(usuario2)
    biblioteca.cadastrarUsuario(admin)
    
    // Testando funcionalidades
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
    
    // Testando Sealed Class
    println("\n=== TESTANDO SEALED CLASS ===")
    val resultado: ResultadoBusca = ResultadoBusca.Sucesso(listOf(livro1, livro2))
    
    when (resultado) {
        is ResultadoBusca.Sucesso -> println("Busca bem sucedida: ${resultado.itens.size} itens encontrados")
        is ResultadoBusca.Erro -> println("Erro na busca: ${resultado.mensagem}")
        ResultadoBusca.Vazio -> println("Nenhum item encontrado")
    }
    
    println("\n=== SISTEMA FINALIZADO ===")
}