package paradigmapoo

interface Digitalizavel {
    val formato:    String
    val tamanhoMB:  Double
    
    fun podeSerBaixado(): Boolean
    fun getLinkDownload(): String
}