somar_elementos <- function(vetor) {
    total <- 0

    for (i in vetor) {
        total <- total + i
    }

    total
}

multiplicar_elementos <- function(vetor) {
    resultado <- 1

    for (i in vetor) {
        resultado <- resultado * i
    }

    resultado
}

filtrar_pares <- function(vetor) {
    vetor[vetor %% 2 == 0]
}

filtrar_impares <- function(vetor) {
    vetor[vetor %% 2 != 0]
}

quadrado <- function(x) {
    x * x
}

cubo <- function(x) {
    x * x * x
}

triplo <- function(x) {
    x * 3
}
