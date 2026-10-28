Calculadora <- R6Class(
    "Calculadora",
    public = list(
        dados = NULL,

        initialize = function(dados) {
            self$dados <- dados
        },

        soma = function() {
            Reduce(function(a, b) a + b, self$dados)
        },

        media = function() {
            mean(self$dados)
        },

        dobro = function() {
            self$dados * 2
        },

        filtrar = function(valor) {
            self$dados[self$dados > valor]
        }
    )
)
