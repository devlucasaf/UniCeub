
#include <stdio.h>
#include <limits.h>

#define MAX_VERTICES 100
#define INF 1000000000

typedef struct {
    int distancia;
    int vertice;
} NoFila;

typedef struct {
    NoFila itens[MAX_VERTICES * MAX_VERTICES];
    int tamanho;
} FilaPrioridade;

static void inicializar_fila(FilaPrioridade *f)
{
    f->tamanho = 0;
}

static void trocar(NoFila *a, NoFila *b)
{
    NoFila temp = *a;
    *a = *b;
    *b = temp;
}

static void subir(FilaPrioridade *f, int i)
{
    while (i > 0) {
        int pai = (i - 1) / 2;
        if (f->itens[pai].distancia <= f->itens[i].distancia) {
            break;
        }
        trocar(&f->itens[pai], &f->itens[i]);
        i = pai;
    }
}

static void descer(FilaPrioridade *f, int i)
{
    while (1) {
        int esq = 2 * i + 1;
        int dir = 2 * i + 2;
        int menor = i;

        if (esq < f->tamanho && f->itens[esq].distancia < f->itens[menor].distancia) {
            menor = esq;
        }

        if (dir < f->tamanho && f->itens[dir].distancia < f->itens[menor].distancia) {
            menor = dir;
        }

        if (menor == i) {
            break;
        }

        trocar(&f->itens[i], &f->itens[menor]);
        i = menor;
    }
}

static void inserir_fila(FilaPrioridade *f, int distancia, int vertice)
{
    if (f->tamanho >= MAX_VERTICES * MAX_VERTICES) {
        return;
    }

    f->itens[f->tamanho].distancia = distancia;
    f->itens[f->tamanho].vertice = vertice;
    f->tamanho++;

    subir(f, f->tamanho - 1);
}

static NoFila remover_min(FilaPrioridade *f)
{
    NoFila min = f->itens[0];
    f->itens[0] = f->itens[f->tamanho - 1];
    f->tamanho--;
    descer(f, 0);
    return min;
}

static int fila_vazia(FilaPrioridade *f)
{
    return f->tamanho == 0;
}

static void dijkstra(int grafo[MAX_VERTICES][MAX_VERTICES], int n, int origem, int distancias[], int anteriores[])
{
    for (int i = 0; i < n; i++) {
        distancias[i] = INF;
        anteriores[i] = -1;
    }

    distancias[origem] = 0;

    FilaPrioridade fila;
    inicializar_fila(&fila);
    inserir_fila(&fila, 0, origem);

    while (!fila_vazia(&fila)) {

        NoFila atual = remover_min(&fila);

        int distancia_atual = atual.distancia;
        int vertice_atual = atual.vertice;

        if (distancia_atual > distancias[vertice_atual]) {
            continue;
        }

        for (int vizinho = 0; vizinho < n; vizinho++) {
            if (grafo[vertice_atual][vizinho] == INF) {
                continue;
            }

            if (vertice_atual == vizinho) {
                continue;
            }

            int peso = grafo[vertice_atual][vizinho];
            int nova_distancia = distancia_atual + peso;

            if (nova_distancia < distancias[vizinho]) {
                distancias[vizinho] = nova_distancia;
                anteriores[vizinho] = vertice_atual;

                inserir_fila(&fila, nova_distancia, vizinho);
            }
        }
    }
}

static int caminho_minimo(int anteriores[], int origem, int destino, int caminho[])
{
    int temp[MAX_VERTICES];
    int tam = 0;
    int atual = destino;

    while (atual != -1) {
        temp[tam++] = atual;
        atual = anteriores[atual];
    }

    /* Inverte o vetor */
    for (int i = 0; i < tam; i++) {
        caminho[i] = temp[tam - 1 - i];
    }

    if (tam == 0 || caminho[0] != origem) {
        return 0;
    }

    return tam;
}

int main(void)
{
    int n = 5;
    char rotulos[] = { 'A', 'B', 'C', 'D', 'E' };

    int grafo[MAX_VERTICES][MAX_VERTICES];

    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            grafo[i][j] = (i == j) ? 0 : INF;
        }
    }
    
    grafo[0][1] = grafo[1][0] = 4;   
    grafo[0][2] = grafo[2][0] = 2;   
    grafo[1][2] = grafo[2][1] = 1;   
    grafo[1][3] = grafo[3][1] = 5;   
    grafo[2][3] = grafo[3][2] = 8;   
    grafo[2][4] = grafo[4][2] = 10;  
    grafo[3][4] = grafo[4][3] = 2;   

    int origem = 0; 

    int distancias[MAX_VERTICES];
    int anteriores[MAX_VERTICES];

    dijkstra(grafo, n, origem, distancias, anteriores);

    printf("Menores caminhos partindo de %c:\n\n", rotulos[origem]);

    for (int destino = 0; destino < n; destino++) {
        int caminho[MAX_VERTICES];
        int tam = caminho_minimo(anteriores, origem, destino, caminho);

        printf("%c -> %c: ", rotulos[origem], rotulos[destino]);

        if (distancias[destino] == INF) {
            printf("distância = infinito, caminho = sem caminho\n");
        } else {
            printf("distância = %d, caminho = ", distancias[destino]);

            if (tam == 0) {
                printf("sem caminho");
            } else {
                for (int i = 0; i < tam; i++) {
                    if (i > 0) {
                        printf(" -> ");
                    }
                    printf("%c", rotulos[caminho[i]]);
                }
            }
            printf("\n");
        }
    }

    return 0;
}
