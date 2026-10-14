package paradigmapookotlin

sealed class ResultadoBusca {
    data class Sucesso(val itens: List<ItemBiblioteca>) : ResultadoBusca()
    data class Erro(val mensagem: String) : ResultadoBusca()
    object Vazio : ResultadoBusca()
}