package paradigmapoo

class RevistaDigital(
    override val id:            String,
    override val titulo:        String,
    override val autor:         String,
    override val anoPublicacao: Int,
    val edicao:                 Int,
    val mesPublicacao:          String,
    var periodoEmprestimoDias:  Int
) : ItemBiblioteca(id, titulo, autor, anoPublicacao) {
    
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
        return diasAtraso * 1.00
    }
    
    override fun getTipo(): String = "Revista Digital"
    
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