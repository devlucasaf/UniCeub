library(R6)

source("R/calculadora.R")
source("R/geracao_dados.R")
source("R/operacoes.R")
source("R/transformacoes.R")

dados <- gerar_dados(50)

calc <- Calculadora$new(dados)

resultado_soma <- calc$soma()
resultado_media <- calc$media()
resultado_dobro <- calc$dobro()
resultado_filtrado <- calc$filtrar(50)

pares <- filtrar_pares(round(dados))
impares <- filtrar_impares(round(dados))

soma_manual <- somar_elementos(dados)
produto_manual <- multiplicar_elementos(dados[1:10])

processados <- processar(dados)

vetor1 <- dados + 10
vetor2 <- dados * 3
vetor3 <- dados / 2

resultado1 <- vetor1 + vetor2
resultado2 <- vetor2 - vetor3
resultado3 <- vetor1 * vetor3

acumulador <- 0

for (i in 1:length(dados)) {
    acumulador <- acumulador + dados[i]
}

contador <- 0

while (contador < 10) {
    contador <- contador + 1
}

resultado_triplo <- aplicar_funcao(dados, triplo)

lista_resultados <- list(
    soma = resultado_soma,
    media = resultado_media,
    dobro = resultado_dobro,
    filtrado = resultado_filtrado,
    pares = pares,
    impares = impares,
    soma_manual = soma_manual,
    produto_manual = produto_manual,
    processados = processados
)

matriz <- matrix(
    dados[1:25],
    nrow = 5
)

soma_matriz <- apply(matriz, 1, sum)
media_matriz <- apply(matriz, 2, mean)

df <- data.frame(
    valores = dados,
    dobro = dados * 2,
    triplo = dados * 3
)

df$quadrado <- df$valores^2

df_filtrado <- df[df$valores > 50, ]

ordenado <- df[order(df$valores), ]

invertido <- reverter(dados)

concatenado <- c(dados, invertido)

dados_normalizados <- normalizar(dados)

desvio <- sd(dados)
variancia <- var(dados)

resumo <- summary(dados)

logaritmo <- log(dados)
raiz <- sqrt(dados)

comparacao <- dados > 50

indices <- which(dados > 50)

subconjunto <- dados[indices]

duplicado <- rep(dados, each = 2)

amostra <- sample(dados, 10)

sequencia <- seq(1, 100, by = 2)

resultado_composto <- transformar(
    dados,
    funcao_composta
)

mapear_lista <- lapply(
    lista_resultados,
    function(x) x
)

reduzir_lista <- Reduce(
    function(a, b) a,
    lista_resultados
)

dados_ordenados <- sort(dados)

maximo <- max(dados)
minimo <- min(dados)

intervalo <- maximo - minimo

dados_escalados <- escala(dados)

verificacao <- all(dados > 0)

algum <- any(dados > 90)

contagem <- length(dados)

fatias <- split(
    dados,
    dados > 50
)

media_grupos <- lapply(fatias, mean)

resultado_final <- list(
    lista_resultados = lista_resultados,
    matriz = matriz,
    df = df,
    df_filtrado = df_filtrado,
    ordenado = ordenado,
    invertido = invertido,
    concatenado = concatenado,
    dados_normalizados = dados_normalizados,
    desvio = desvio,
    variancia = variancia,
    resumo = resumo,
    logaritmo = logaritmo,
    raiz = raiz,
    comparacao = comparacao,
    subconjunto = subconjunto,
    duplicado = duplicado,
    amostra = amostra,
    sequencia = sequencia,
    resultado_composto = resultado_composto,
    mapear_lista = mapear_lista,
    reduzir_lista = reduzir_lista,
    dados_ordenados = dados_ordenados,
    maximo = maximo,
    minimo = minimo,
    intervalo = intervalo,
    dados_escalados = dados_escalados,
    verificacao = verificacao,
    algum = algum,
    contagem = contagem,
    media_grupos = media_grupos
)

print(resultado_final)
