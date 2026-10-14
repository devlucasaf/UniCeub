library(R6)

Calculadora <- R6Class("Calculadora",
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

gerar_dados <- function(n) {
  runif(n, 1, 100)
}

transformar <- function(vetor, func) {
  sapply(vetor, func)
}

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
  x^2
}

cubo <- function(x) {
  x^3
}

processar <- function(vetor) {
  v1 <- transformar(vetor, quadrado)
  v2 <- transformar(vetor, cubo)
  list(v1 = v1, v2 = v2)
}

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

aplicar_funcao <- function(vetor, func) {
  resultado <- c()
  for (i in vetor) {
    resultado <- c(resultado, func(i))
  }
  resultado
}

triplo <- function(x) {
  x * 3
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

matriz <- matrix(dados[1:25], nrow = 5)

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

reverter <- function(vetor) {
  rev(vetor)
}

invertido <- reverter(dados)

concatenado <- c(dados, invertido)

normalizar <- function(vetor) {
  (vetor - min(vetor)) / (max(vetor) - min(vetor))
}

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

funcao_composta <- function(x) {
  quadrado(cubo(x))
}

resultado_composto <- transformar(dados, funcao_composta)

mapear_lista <- lapply(lista_resultados, function(x) x)

reduzir_lista <- Reduce(function(a, b) a, lista_resultados)

dados_ordenados <- sort(dados)

maximo <- max(dados)
minimo <- min(dados)

intervalo <- maximo - minimo

escala <- function(x) {
  (x - mean(x)) / sd(x)
}

dados_escalados <- escala(dados)

verificacao <- all(dados > 0)

algum <- any(dados > 90)

contagem <- length(dados)

fatias <- split(dados, dados > 50)

media_grupos <- lapply(fatias, mean)

resultado_final <- list(
  lista_resultados,
  matriz,
  df,
  df_filtrado,
  ordenado,
  invertido,
  concatenado,
  dados_normalizados,
  desvio,
  variancia,
  resumo,
  logaritmo,
  raiz,
  comparacao,
  subconjunto,
  duplicado,
  amostra,
  sequencia,
  resultado_composto,
  mapear_lista,
  reduzir_lista,
  dados_ordenados,
  maximo,
  minimo,
  intervalo,
  dados_escalados,
  verificacao,
  algum,
  contagem,
  media_grupos
)

print(resultado_final)