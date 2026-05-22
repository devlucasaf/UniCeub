package paradigmapookotlin

data class Usuario(
    val id:     String,
    val nome:   String,
    val email:  String,
    val tipo:   TipoUsuario
) {
    companion object {
        fun criarUsuarioAdmin(): Usuario {
            return Usuario("ADMIN-001", "Administrador", "admin@biblioteca.com", TipoUsuario.ADMIN)
        }
    }
}