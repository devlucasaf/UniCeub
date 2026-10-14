package paradigmapookotlin

enum class TipoUsuario {
    ESTUDANTE, 
    PROFESSOR, 
    FUNCIONARIO, 
    ADMIN;
    
    fun getLimiteEmprestimos(): Int {
        return when (this) {
            ESTUDANTE -> 3
            PROFESSOR -> 10
            FUNCIONARIO -> 5
            ADMIN -> Int.MAX_VALUE
        }
    }
}