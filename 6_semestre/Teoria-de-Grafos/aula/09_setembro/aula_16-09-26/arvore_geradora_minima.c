#include <stdio.h>
#include <stdlib.h>

#define MAX_VERTICES 100
#define MAX_ARESTAS 500
#define MAX_FILA 1000

typedef struct {
    int origem;
    int destino;
    int peso;
} Aresta;

typedef struct {
    int destino;
    int peso;
} Vizinho;

typedef struct {
    Vizinho vizinhos[MAX_VERTICES];
    int quantidade;
} ListaAdjacencia;

typedef struct {
    int peso;
    int origem;
    int atual;
} ItemFila;

typedef struct {
    int pai[MAX_VERTICES];
} UnionFind;

// --- INICIALIZA A ESTRUTURA UNION-FIND ---
void inicializarUnionFind(UnionFind *uf, int quantidadeVertices) {
    for (int i = 0; i < quantidadeVertices; i++) {
        uf->pai[i] = i;
    }
}

// --- ENCONTRA A RAIZ DE UM VERTICE ---
int encontrar(UnionFind *uf, int vertice) {
    if (uf->pai[vertice] != vertice) {
        uf->pai[vertice] = encontrar(uf, uf->pai[vertice]);
    }

    return uf->pai[vertice];
}

// --- UNE DOIS CONJUNTOS ---
int unir(UnionFind *uf, int verticeA, int verticeB) {
    int raizA = encontrar(uf, verticeA);
    int raizB = encontrar(uf, verticeB);

    if (raizA != raizB) {
        uf->pai[raizB] = raizA;
        return 1;
    }

    return 0;
}

// --- TROCA DUAS ARESTAS ---
void trocarArestas(Aresta *arestaA, Aresta *arestaB) {
    Aresta temporaria = *arestaA;
    *arestaA = *arestaB;
    *arestaB = temporaria;
}

// --- ORDENA AS ARESTAS PELO PESO ---
void ordenarArestas(Aresta arestas[], int quantidadeArestas) {
    for (int i = 0; i < quantidadeArestas - 1; i++) {
        for (int j = 0; j < quantidadeArestas - i - 1; j++) {
            if (arestas[j].peso > arestas[j + 1].peso) {
                trocarArestas(&arestas[j], &arestas[j + 1]);
            }
        }
    }
}

// --- EXIBE A ÁRVORE ATUAL ---
void exibirArvore(Aresta arvore[], int quantidadeArestas, char vertices[]) {
    if (quantidadeArestas == 0) {
        printf("Nenhuma aresta ainda.\n");
        return;
    }

    for (int i = 0; i < quantidadeArestas; i++) {
        printf("%c -- %c : %d\n", vertices[arvore[i].origem], vertices[arvore[i].destino], arvore[i].peso);
    }
}

// --- EXECUTA O ALGORITMO DE KRUSKAL PASSO A PASSO ---
void kruskalPassoAPasso(char vertices[], int quantidadeVertices, Aresta arestas[], int quantidadeArestas) {
    printf("ALGORITMO DE KRUSKAL\n");

    ordenarArestas(
        arestas,
        quantidadeArestas
    );

    printf("\nArestas ordenadas pelo peso:\n\n");

    for (int i = 0; i < quantidadeArestas; i++) {
        printf("%c -- %c: %d\n", vertices[arestas[i].origem], vertices[arestas[i].destino], arestas[i].peso);
    }

    UnionFind uf;

    inicializarUnionFind(&uf, quantidadeVertices);

    Aresta arvore[MAX_ARESTAS];

    int quantidadeArvore = 0;
    int custoTotal = 0;
    int passo = 1;

    for (int i = 0; i < quantidadeArestas; i++) {
        Aresta arestaAtual = arestas[i];

        printf("\n--------------------------------------------------\n");
        printf("PASSO %d\n", passo);

        printf("\nAnalisando aresta: %c -- %c (peso = %d)\n", vertices[arestaAtual.origem], vertices[arestaAtual.destino], arestaAtual.peso);

        if (unir(&uf, arestaAtual.origem, arestaAtual.destino)) {
            printf("Resultado: ACEITA\n");

            arvore[quantidadeArvore] = arestaAtual;
            quantidadeArvore++;

            custoTotal += arestaAtual.peso;
        } else {
            printf("Resultado: REJEITADA\n");
            printf("Motivo: formaria um ciclo.\n");
        }

        printf("\nArvore atual:\n");

        exibirArvore(arvore, quantidadeArvore, vertices);

        printf("Custo acumulado = %d\n", custoTotal);

        passo++;

        if (quantidadeArvore == quantidadeVertices - 1) {
            break;
        }
    }

    printf("RESULTADO FINAL - KRUSKAL\n\n\n");

    exibirArvore(arvore, quantidadeArvore, vertices);

    printf("\nCusto total da AGM = %d\n", custoTotal);
}

// --- ADICIONA UM ITEM NA FILA DE PRIORIDADE ---
void adicionarNaFila(ItemFila fila[], int *tamanhoFila, int peso, int origem, int atual) {
    if (*tamanhoFila >= MAX_FILA) {
        printf("Erro: a fila de prioridade esta cheia.\n");
        return;
    }

    int posicao = *tamanhoFila;

    fila[posicao].peso = peso;
    fila[posicao].origem = origem;
    fila[posicao].atual = atual;

    (*tamanhoFila)++;

    while (posicao > 0) {
        int pai = (posicao - 1) / 2;

        if (fila[pai].peso <= fila[posicao].peso) {
            break;
        }

        ItemFila temporario = fila[pai];
        fila[pai] = fila[posicao];
        fila[posicao] = temporario;

        posicao = pai;
    }
}

// --- REMOVE O ITEM DE MENOR PESO DA FILA ---
ItemFila removerMenorDaFila(ItemFila fila[], int *tamanhoFila) {
    ItemFila menor = fila[0];

    (*tamanhoFila)--;

    fila[0] = fila[*tamanhoFila];

    int posicao = 0;

    while (1) {
        int filhoEsquerdo = posicao * 2 + 1;
        int filhoDireito = posicao * 2 + 2;
        int menorPosicao = posicao;

        if (filhoEsquerdo < *tamanhoFila && fila[filhoEsquerdo].peso < fila[menorPosicao].peso) {
            menorPosicao = filhoEsquerdo;
        }

        if (filhoDireito < *tamanhoFila && fila[filhoDireito].peso < fila[menorPosicao].peso) {
            menorPosicao = filhoDireito;
        }

        if (menorPosicao == posicao) {
            break;
        }

        ItemFila temporario = fila[posicao];
        fila[posicao] = fila[menorPosicao];
        fila[menorPosicao] = temporario;

        posicao = menorPosicao;
    }

    return menor;
}

// --- INICIALIZA O GRAFO ---
void inicializarGrafo(ListaAdjacencia grafo[], int quantidadeVertices) {
    for (int i = 0; i < quantidadeVertices; i++) {
        grafo[i].quantidade = 0;
    }
}

// --- ADICIONA UMA ARESTA DIRECIONADA ---
void adicionarVizinho(ListaAdjacencia grafo[], int origem, int destino, int peso) {
    int posicao = grafo[origem].quantidade;

    grafo[origem].vizinhos[posicao].destino = destino;
    grafo[origem].vizinhos[posicao].peso = peso;

    grafo[origem].quantidade++;
}

// --- ADICIONA UMA ARESTA NAO DIRECIONADA ---
void adicionarArestaNoGrafo(
    ListaAdjacencia grafo[],
    int origem,
    int destino,
    int peso
) {
    adicionarVizinho(
        grafo,
        origem,
        destino,
        peso
    );

    adicionarVizinho(
        grafo,
        destino,
        origem,
        peso
    );
}

// --- EXIBE OS VERTICES VISITADOS ---
void exibirVisitados(int visitados[], char vertices[], int quantidadeVertices) {
    printf("Vertices visitados: [");

    int primeiro = 1;

    for (int i = 0; i < quantidadeVertices; i++) {
        if (visitados[i]) {
            if (!primeiro) {
                printf(", ");
            }

            printf("%c", vertices[i]);
            primeiro = 0;
        }
    }

    printf("]\n");
}

// ========================
// PRIM
// ========================

// --- EXECUTA O ALGORITMO DE PRIM PASSO A PASSO ---
void primPassoAPasso(ListaAdjacencia grafo[], char vertices[], int quantidadeVertices, int inicio) {
    printf("ALGORITMO DE PRIM\n\n");

    int visitados[MAX_VERTICES] = {0};

    Aresta arvore[MAX_ARESTAS];

    int quantidadeArvore = 0;
    int custoTotal = 0;

    ItemFila fila[MAX_FILA];
    int tamanhoFila = 0;

    adicionarNaFila(fila, &tamanhoFila, 0, -1, inicio);

    int passo = 1;

    while (tamanhoFila > 0) {
        ItemFila itemAtual = removerMenorDaFila(fila, &tamanhoFila);

        int peso = itemAtual.peso;
        int origem = itemAtual.origem;
        int atual = itemAtual.atual;

        if (visitados[atual]) {
            continue;
        }

        printf("\n--------------------------------------------------\n");
        printf("PASSO %d\n", passo);

        if (origem == -1) {
            printf(
                "\nVertice inicial escolhido: %c\n",
                vertices[atual]
            );
        } else {
            printf("\nMenor aresta disponivel:\n");

            printf("%c -- %c (peso = %d)\n", vertices[origem], vertices[atual], peso);
        }

        visitados[atual] = 1;

        if (origem != -1) {
            arvore[quantidadeArvore].origem = origem;
            arvore[quantidadeArvore].destino = atual;
            arvore[quantidadeArvore].peso = peso;

            quantidadeArvore++;
            custoTotal += peso;

            printf("Resultado: ARESTA ADICIONADA\n");
        }

        exibirVisitados(visitados, vertices, quantidadeVertices);

        printf("\nArvore atual:\n");

        exibirArvore(arvore, quantidadeArvore, vertices);

        printf("Custo acumulado = %d\n", custoTotal);

        printf("\nNovas arestas candidatas:\n");

        for (int i = 0; i < grafo[atual].quantidade; i++) {
            int vizinho = grafo[atual].vizinhos[i].destino;
            int pesoAresta = grafo[atual].vizinhos[i].peso;

            if (!visitados[vizinho]) {
                adicionarNaFila(fila, &tamanhoFila, pesoAresta, atual, vizinho);

                printf("%c -- %c (peso = %d)\n", vertices[atual], vertices[vizinho], pesoAresta);
            }
        }

        passo++;
    }

    printf("RESULTADO FINAL - PRIM\n\n\n");

    exibirArvore(arvore, quantidadeArvore, vertices);

    printf("\nCusto total da AGM = %d\n", custoTotal);
}

int main(void) {
    char vertices[] = {
        'A',
        'B',
        'C',
        'D',
        'E'
    };

    int quantidadeVertices = sizeof(vertices) / sizeof(vertices[0]);

    Aresta arestas[] = {
        {0, 1, 2},
        {0, 2, 4},
        {1, 2, 1},
        {1, 3, 5},
        {2, 3, 3},
        {2, 4, 6},
        {3, 4, 2}
    };

    int quantidadeArestas = sizeof(arestas) / sizeof(arestas[0]);

    ListaAdjacencia grafo[MAX_VERTICES];

    inicializarGrafo(grafo, quantidadeVertices);

    for (int i = 0; i < quantidadeArestas; i++) {
        adicionarArestaNoGrafo(grafo, arestas[i].origem, arestas[i].destino, arestas[i].peso);
    }

    kruskalPassoAPasso(vertices, quantidadeVertices, arestas, quantidadeArestas);
    primPassoAPasso(grafo, vertices, quantidadeVertices, 0);

    return 0;
}

