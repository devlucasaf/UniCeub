package paradigmapookotlin

abstract class ItemBiblioteca(
    open val id:            String,
    open val titulo:        String,
    open val autor:         String,
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