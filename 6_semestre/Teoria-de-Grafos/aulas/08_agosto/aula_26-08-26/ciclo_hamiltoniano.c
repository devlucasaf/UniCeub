#include <stdio.h>
#include <string.h>

#define MAX_VERTICES 100
#define MAX_GRAU     100

static int n = 0;                          
static char nomes[MAX_VERTICES][32];       
static int adj[MAX_VERTICES][MAX_VERTICES];
static int grau[MAX_VERTICES];             
static int lista[MAX_VERTICES][MAX_GRAU];  
static int caminho[MAX_VERTICES + 1];      
static int visitado[MAX_VERTICES];         

static void adicionar_vertice(const char *nome)
{
    if (n >= MAX_VERTICES) {
        return;
    }

    strcpy(nomes[n], nome);
    grau[n] = 0;

    for (int i = 0; i <= n; i++) {
        adj[n][i] = 0;
        adj[i][n] = 0;
    }

    n++;
}

static void adicionar_aresta(int u, int v)
{
    if (u < 0 || v < 0 || u >= n || v >= n || u == v) {
        return;
    }

    if (adj[u][v]) {
        return;
    }

    adj[u][v] = 1;
    adj[v][u] = 1;

    lista[u][grau[u]++] = v;
    lista[v][grau[v]++] = u;
}

static int buscar_vertice(const char *nome)
{
    for (int i = 0; i < n; i++) {
        if (strcmp(nomes[i], nome) == 0) {
            return i;
        }
    }

    return -1;
}

static int backtracking_caminho(int atual, int tam_caminho)
{
    if (tam_caminho == n) {
        return 1;
    }

    for (int i = 0; i < grau[atual]; i++) {
        int vizinho = lista[atual][i];

        if (!visitado[vizinho]) {
            visitado[vizinho] = 1;
            caminho[tam_caminho] = vizinho;

            if (backtracking_caminho(vizinho, tam_caminho + 1)) {
                return 1;
            }

            visitado[vizinho] = 0;
        }
    }

    return 0;
}

static int encontrar_caminho_hamiltoniano(void)
{
    for (int inicio = 0; inicio < n; inicio++) {
        memset(visitado, 0, sizeof(int) * n);

        visitado[inicio] = 1;
        caminho[0] = inicio;

        if (backtracking_caminho(inicio, 1)) {
            return 1;
        }
    }

    return 0;
}

static int backtracking_ciclo(int atual, int inicio, int tam_caminho)
{
    if (tam_caminho == n) {
        if (adj[atual][inicio]) {
            caminho[n] = inicio;   
            return 1;
        }

        return 0;
    }

    for (int i = 0; i < grau[atual]; i++) {
        int vizinho = lista[atual][i];

        if (!visitado[vizinho]) {
            visitado[vizinho] = 1;
            caminho[tam_caminho] = vizinho;

            if (backtracking_ciclo(vizinho, inicio, tam_caminho + 1)) {
                return 1;
            }

            visitado[vizinho] = 0;
        }
    }

    return 0;
}

static int encontrar_ciclo_hamiltoniano(void)
{
    if (n == 0) {
        return 0;
    }

    int inicio = 0;

    memset(visitado, 0, sizeof(int) * n);

    visitado[inicio] = 1;
    caminho[0] = inicio;

    return backtracking_ciclo(inicio, inicio, 1);
}

static void imprimir_caminho(int tam)
{
    for (int i = 0; i < tam; i++) {
        if (i > 0) {
            printf(" -> ");
        }
        printf("%s", nomes[caminho[i]]);
    }
    printf("\n");
}

int main(void)
{
    const char *rotulos[] = {
        "A", "B", "C", "D", "E",
        "F", "G", "H", "I", "J"
    };

    int qtd = 10;

    for (int i = 0; i < qtd; i++) {
        adicionar_vertice(rotulos[i]);
    }

    for (int i = 0; i < qtd; i++) {
        for (int j = i + 1; j < qtd; j++) {
            adicionar_aresta(i, j);
        }
    }

    if (encontrar_caminho_hamiltoniano()) {
        printf("Caminho Hamiltoniano:\n");
        imprimir_caminho(n);
    } else {
        printf("Não existe caminho Hamiltoniano.\n");
    }

    if (encontrar_ciclo_hamiltoniano()) {
        printf("\nCiclo Hamiltoniano:\n");
        imprimir_caminho(n + 1);
    } else {
        printf("Não existe ciclo Hamiltoniano.\n");
    }

    return 0;
}
