package paradigmapoo

class LivroDigital(
    override val id:            String,
    override val titulo:        String,
    override val autor:         String,
    override val anoPublicacao: Int,
    override val formato:       String,
    override val tamanhoMB:     Double,
    val numero Paginas:         Int,
    val ISBN:                   String,
    var copiasDisponiveis:      Int
) : ItemBiblioteca(id, titulo, autor, anoPublicacao), Digitalizavel {
    
    private var numeroDownloads: Int = 0
    
    val popularidade: String
        get() = when {
            numeroDownloads > 1000 -> "Muito Popular"
            numeroDownloads > 500 -> "Popular"
            numeroDownloads > 100 -> "Moderado"
            else -> "Pouco Conhecido"
        }
    
    override fun calcularMulta(diasAtraso: Int): Double {
        return diasAtraso * 0.50
    }
    
    override fun getTipo(): String = "Livro Digital"
    
    override fun podeSerBaixado(): Boolean = copiasDisponiveis > 0
    
    override fun getLinkDownload(): String {
        return "https://biblioteca.digital/download/$id.$formato"
    }
    
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