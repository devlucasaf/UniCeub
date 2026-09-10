#include <stdio.h>
#include <string.h>

#define MAX_VERTICES     26
#define MAX_VIZINHOS     26
#define MAX_PROFUNDIDADE 20
#define MAX_RESULTADOS   100000

static int  num_vertices = 0;
static char rotulos[MAX_VERTICES];
static int  adj[MAX_VERTICES][MAX_VIZINHOS];
static int  num_vizinhos[MAX_VERTICES];
static int  atual[MAX_PROFUNDIDADE];                 
static int  resultados[MAX_RESULTADOS][MAX_PROFUNDIDADE];
static int  tamanhos[MAX_RESULTADOS];
static int  total_resultados = 0;

static void adicionar_vertice(char r)
{
    if (num_vertices >= MAX_VERTICES) {
        return;
    }

    rotulos[num_vertices] = r;
    num_vizinhos[num_vertices] = 0;
    num_vertices++;
}

static int buscar(char r)
{
    for (int i = 0; i < num_vertices; i++) {
        if (rotulos[i] == r) {
            return i;
        }
    }
    return -1;
}

static void adicionar_aresta(char a, char b)
{
    int i = buscar(a);
    int j = buscar(b);

    if (i < 0 || j < 0 || i == j) {
        return;
    }

    adj[i][num_vizinhos[i]++] = j;
    adj[j][num_vizinhos[j]++] = i;
}

static int visitado[MAX_VERTICES];

static void dfs_caminhos(int at, int destino, int prof)
{
    atual[prof] = at;

    if (at == destino) {
        if (total_resultados < MAX_RESULTADOS) {
            memcpy(resultados[total_resultados],
                    atual,
                   (prof + 1) * sizeof(int));
            tamanhos[total_resultados] = prof + 1;
            total_resultados++;
        }
        return;
    }

    for (int k = 0; k < num_vizinhos[at]; k++) {
        int v = adj[at][k];

        if (!visitado[v]) {
            visitado[v] = 1;
            dfs_caminhos(v, destino, prof + 1);

            visitado[v] = 0;
        }
    }
}

static int encontrar_caminhos(int origem, int destino)
{
    total_resultados = 0;

    memset(visitado, 0, sizeof(visitado));

    visitado[origem] = 1;
    dfs_caminhos(origem, destino, 0);

    return total_resultados;
}

static int aresta_usada[MAX_VERTICES][MAX_VERTICES];

static void dfs_trilhas(int at, int destino, int prof)
{
    atual[prof] = at;

    if (at == destino) {
        if (total_resultados < MAX_RESULTADOS) {
            memcpy(resultados[total_resultados],
                    atual,
                   (prof + 1) * sizeof(int));
            tamanhos[total_resultados] = prof + 1;
            total_resultados++;
        }
        return;
    }

    for (int k = 0; k < num_vizinhos[at]; k++) {
        int v = adj[at][k];

        if (!aresta_usada[at][v]) {

            aresta_usada[at][v] = 1;
            aresta_usada[v][at] = 1;

            dfs_trilhas(v, destino, prof + 1);

            aresta_usada[at][v] = 0;
            aresta_usada[v][at] = 0;
        }
    }
}

static int encontrar_trilhas(int origem, int destino)
{
    total_resultados = 0;

    memset(aresta_usada, 0, sizeof(aresta_usada));

    dfs_trilhas(origem, destino, 0);

    return total_resultados;
}

static void imprimir_lista(const char *titulo, const char *singular)
{
    printf("\n%s:\n", titulo);
    printf("----------------------------------------\n");

    for (int i = 0; i < total_resultados; i++) {

        printf("%s %d: ", singular, i + 1);

        for (int k = 0; k < tamanhos[i]; k++) {
            if (k > 0) {
                printf(" -> ");
            }
            printf("%c", rotulos[resultados[i][k]]);
        }
        printf("\n");
    }
}

int main(void)
{
    const char vertices[] = { 'A', 'B', 'C', 'D', 'E' };
    const int  n = (int) (sizeof(vertices) / sizeof(vertices[0]));

    for (int i = 0; i < n; i++) {
        adicionar_vertice(vertices[i]);
    }

    for (int i = 0; i < n; i++) {
        for (int j = i + 1; j < n; j++) {
            adicionar_aresta(vertices[i], vertices[j]);
        }
    }


    int origem  = buscar('A');
    int destino = buscar('E');

    if (origem < 0 || destino < 0) {
        printf("Origem ou destino inválidos.\n");
        return 1;
    }

    encontrar_caminhos(origem, destino);
    imprimir_lista("CAMINHOS", "Caminho");
    encontrar_trilhas(origem, destino);
    imprimir_lista("TRILHAS", "Trilha");

    return 0;
}