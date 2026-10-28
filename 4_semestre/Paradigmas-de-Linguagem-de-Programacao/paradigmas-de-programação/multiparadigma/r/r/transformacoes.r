transformar <- function(vetor, func) {
    sapply(vetor, func)
}

processar <- function(vetor) {
    v1 <- transformar(vetor, quadrado)
    v2 <- transformar(vetor, cubo)

    list(
        v1 = v1,
        v2 = v2
    )
}

aplicar_funcao <- function(vetor, func) {
    resultado <- c()

    for (i in vetor) {
        resultado <- c(resultado, func(i))
    }

    resultado
}

reverter <- function(vetor) {
    rev(vetor)
}

normalizar <- function(vetor) {
    (vetor - min(vetor)) / (max(vetor) - min(vetor))
}

funcao_composta <- function(x) {
    quadrado(cubo(x))
}

escala <- function(x) {
    (x - mean(x)) / sd(x)
}