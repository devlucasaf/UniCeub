package paradigmapoo

class Biblioteca {
    private val itens = mutableListOf<ItemBiblioteca>()
    private val usuarios = mutableListOf<Usuario>()
    private val emprestimos = mutableListOf<Emprestimo>()
    
    class Emprestimo(
        val itemId:         String,
        val usuarioId:      String,
        val dataEmprestimo: String,
        var dataDevolucao:  String?
    )
    
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
        println("\n=== Itens do tipo: $tipo ===")
        itens.filter { it.getTipo() == tipo }
            .forEach { it.exibirDetalhes() }
    }
    
    fun processarItens(operacao: (ItemBiblioteca) -> Unit) {
        itens.forEach { operacao(it) }
    }
}