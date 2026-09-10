#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#define MAX_VERTICES 100

static int n_vertices = 0;   
static int n_arestas  = 0;   

static char rotulos[MAX_VERTICES][8];              
static int  adj[MAX_VERTICES][MAX_VERTICES];       

static void normalizar(char *texto)
{
    char aux[64];
    int  k = 0;

    for (int i = 0; texto[i] != '\0' && k < 63; i++) {
        if (texto[i] != ' ' && texto[i] != '\t') {
            aux[k++] = (char) toupper((unsigned char) texto[i]);
        }
    }
    aux[k] = '\0';
    strcpy(texto, aux);
}

static void gerar_rotulo(int indice, char *destino)
{
    if (indice < 26) {
        sprintf(destino, "%c", (char) ('A' + indice));
    } else {
        sprintf(destino, "V%d", indice + 1);
    }
}

static void reordenar_rotulos(void)
{
    for (int i = 0; i < n_vertices; i++) {
        gerar_rotulo(i, rotulos[i]);
    }
}

static int indice_do_rotulo(const char *rotulo)
{
    for (int i = 0; i < n_vertices; i++) {
        if (strcmp(rotulos[i], rotulo) == 0) {
            return i;
        }
    }
    return -1;
}

static int grau_do_vertice(int v)
{
    int g = 0;
    for (int u = 0; u < n_vertices; u++) {
        if (adj[v][u]) {
            g++;
        }
    }
    return g;
}

static int esta_conexo(void)
{
    if (n_vertices <= 1) {
        return 1;
    }

    int visitado[MAX_VERTICES] = {0};
    int fila[MAX_VERTICES];
    int ini = 0;
    int fim = 0;
    int alcancados = 0;

    visitado[0] = 1;
    fila[fim++] = 0;

    while (ini < fim) {
        int v = fila[ini++];
        alcancados++;

        for (int u = 0; u < n_vertices; u++) {
            if (adj[v][u] && !visitado[u]) {
                visitado[u] = 1;
                fila[fim++] = u;
            }
        }
    }
    return (alcancados == n_vertices);
}

static void ler_linha(char *buffer, int tamanho)
{
    if (fgets(buffer, tamanho, stdin) == NULL) {
        buffer[0] = '\0';
        return;
    }

    size_t len = strlen(buffer);
    if (len > 0 && buffer[len - 1] == '\n') {
        buffer[len - 1] = '\0';
    }
}

static int ler_inteiro(void)
{
    char linha[64];
    int  valor;

    ler_linha(linha, sizeof(linha));
    if (sscanf(linha, "%d", &valor) != 1) {
        return 0;
    }
    return valor;
}

static int ler_vertice(const char *mensagem)
{
    char linha[64];

    printf("%s", mensagem);
    ler_linha(linha, sizeof(linha));
    normalizar(linha);

    if (linha[0] == '\0') {
        return -1;
    }

    return indice_do_rotulo(linha);
}

static void resumo(void)
{
    printf("\n  >> Vértices: %d | Arestas: %d\n", n_vertices, n_arestas);
}

static void adicionar_vertice(void)
{
    if (n_vertices >= MAX_VERTICES) {
        printf("\n  Limite de %d vértices atingido.\n", MAX_VERTICES);
        return;
    }

    reordenar_rotulos();

    int novo = n_vertices;
    gerar_rotulo(novo, rotulos[novo]);

    for (int i = 0; i <= novo; i++) {
        adj[novo][i] = 0;
        adj[i][novo] = 0;
    }

    n_vertices++;
    printf("\n  Vértice '%s' adicionado.\n", rotulos[novo]);
}

static void adicionar_aresta(void)
{
    if (n_vertices < 2) {
        printf("\n  É necessário ter pelo menos 2 vértices.\n");
        return;
    }

    int i = ler_vertice("  Primeiro vértice: ");
    if (i < 0) { 
        printf("\n  Vértice inexistente.\n"); 
        return; 
    }

    int j = ler_vertice("  Segundo vértice:  ");
    if (j < 0) { 
        printf("\n  Vértice inexistente.\n"); 
        return; 
    }

    if (i == j) {
        printf("\n  Não é permitido laço (aresta de um vértice nele mesmo).\n");
        return;
    }

    if (adj[i][j]) {
        printf("\n  A aresta %s-%s já existe.\n", rotulos[i], rotulos[j]);
        return;
    }

    adj[i][j] = 1;
    adj[j][i] = 1;
    n_arestas++;

    printf("\n  Aresta %s-%s criada.\n", rotulos[i], rotulos[j]);
}

static void remover_vertice(void)
{
    if (n_vertices == 0) {
        printf("\n  O grafo está vazio.\n");
        return;
    }

    int v = ler_vertice("  Vértice a excluir: ");
    if (v < 0) { 
        printf("\n  Vértice inexistente.\n"); 
        return; 
    }

    for (int u = 0; u < n_vertices; u++) {
        if (adj[v][u]) {
            n_arestas--;
        }
    }

    for (int r = v; r < n_vertices - 1; r++) {
        for (int c = 0; c < n_vertices; c++) {
            adj[r][c] = adj[r + 1][c];
        }
    }

    for (int r = 0; r < n_vertices - 1; r++) {
        for (int c = v; c < n_vertices - 1; c++) {
            adj[r][c] = adj[r][c + 1];
        }
    }

    n_vertices--;

    for (int k = 0; k <= n_vertices; k++) {
        adj[n_vertices][k] = 0;
        adj[k][n_vertices] = 0;
    }

    reordenar_rotulos();
    printf("\n  Vértice excluído.\n");
}

static void remover_aresta(void)
{
    if (n_arestas == 0) {
        printf("\n  Não há arestas para excluir.\n");
        return;
    }

    int i = ler_vertice("  Primeiro vértice: ");
    if (i < 0) { 
        printf("\n  Vértice inexistente.\n"); 
        return; 
    }

    int j = ler_vertice("  Segundo vértice:  ");
    if (j < 0) { 
        printf("\n  Vértice inexistente.\n"); 
        return; 
    }

    if (!adj[i][j]) {
        printf("\n  A aresta %s-%s não existe.\n", rotulos[i], rotulos[j]);
        return;
    }

    adj[i][j] = 0;
    adj[j][i] = 0;
    n_arestas--;

    printf("\n  Aresta %s-%s removida.\n", rotulos[i], rotulos[j]);
}

static void listar_grafo(void)
{
    if (n_vertices == 0) {
        printf("\n  (grafo vazio)\n");
        return;
    }

    printf("\n  VÉRTICES (%d):\n", n_vertices);
    for (int i = 0; i < n_vertices; i++) {
        printf("    %-4s  grau = %d\n", rotulos[i], grau_do_vertice(i));
    }

    printf("\n  ARESTAS (%d):\n", n_arestas);
    if (n_arestas == 0) {
        printf("    (nenhuma)\n");
        return;
    }

    for (int i = 0; i < n_vertices; i++) {
        for (int j = i + 1; j < n_vertices; j++) {
            if (adj[i][j]) {
                printf("    %s - %s\n", rotulos[i], rotulos[j]);
            }
        }
    }
}

static void analisar_grafo(void)
{
    printf("\n  ---------- ANÁLISE DO GRAFO ----------\n");

    printf("    Vértices ..........: %d\n", n_vertices);

    printf("    Arestas ...........: %d\n", n_arestas);

    if (n_vertices == 0) {
        printf("    Conexo ............: -\n");
    } else {
        printf("    Conexo ............: %s\n", esta_conexo() ? "Sim" : "Não");
    }

    if (n_vertices == 0) {
        printf("    Maior grau ........: -\n");
    } else {
        int maior = 0;
        for (int i = 0; i < n_vertices; i++) {
            int g = grau_do_vertice(i);
            if (g > maior) {
                maior = g;
            }
        }
        printf("    Maior grau ........: %d\n", maior);
    }
}

static void informacoes_grafo(void)
{
    printf("\n  ---------- INFORMAÇÕES DO GRAFO ----------\n");

    int trivial = (n_vertices == 1 && n_arestas == 0);
    printf("    Grafo trivial .....: %s\n", trivial ? "Sim" : "Não");

    int ciclo = 0;
    if (n_vertices >= 3 && n_arestas == n_vertices) {
        int todos_grau_dois = 1;
        for (int i = 0; i < n_vertices; i++) {
            if (grau_do_vertice(i) != 2) {
                todos_grau_dois = 0;
                break;
            }
        }

        if (todos_grau_dois && esta_conexo()) {
            ciclo = 1;
        }
    }
    printf("    Grafo ciclo .......: %s\n", ciclo ? "Sim" : "Não");

    if (n_vertices > 0) {
        int soma = 0;
        for (int i = 0; i < n_vertices; i++) {
            soma += grau_do_vertice(i);
        }
        printf("    Soma dos graus ....: %d\n", soma);

        int isolados = 0;
        for (int i = 0; i < n_vertices; i++) {
            if (grau_do_vertice(i) == 0) {
                isolados++;
            }
        }
        printf("    Vértices isolados .: %d\n", isolados);
    }
}

static void mostrar_ajuda(void)
{
    printf("\n  ================ COMO USAR ================\n");
    printf("    1. Adicione vértices com a opção 1.\n");
    printf("    2. Crie arestas informando dois rótulos (ex.: A e B).\n");
    printf("    3. Use as opções 3 e 4 para excluir vértices/arestas.\n");
    printf("    4. Consulte as opções 5, 6 e 7 para inspecionar o grafo.\n");
    printf("  ===========================================\n");
}

int main(void)
{
    memset(adj, 0, sizeof(adj));

    printf("=============================================\n");
    printf("        EDITOR DE GRAFOS  -  C puro\n");
    printf("=============================================\n");

    mostrar_ajuda();

    while (1) {
        printf("\n----------------- MENU -----------------\n");
        printf("  1 - Adicionar vértice\n");
        printf("  2 - Adicionar aresta\n");
        printf("  3 - Excluir vértice\n");
        printf("  4 - Excluir aresta\n");
        printf("  5 - Listar grafo\n");
        printf("  6 - Análise do grafo\n");
        printf("  7 - Informações do grafo\n");
        printf("  8 - Como usar\n");
        printf("  0 - Sair\n");
        printf("----------------------------------------\n");
        printf("  Opção: ");

        int opcao = ler_inteiro();

        switch (opcao) {
            case 1:
                adicionar_vertice();
                resumo();
                break;
            case 2:
                adicionar_aresta();
                resumo();
                break;
            case 3:
                remover_vertice();
                resumo();
                break;
            case 4:
                remover_aresta();
                resumo();
                break;
            case 5:
                listar_grafo();
                break;
            case 6:
                analisar_grafo();
                break;
            case 7:
                informacoes_grafo();
                break;
            case 8:
                mostrar_ajuda();
                break;
            case 0:
                printf("\n  Encerrando o editor de grafos. Até logo!\n\n");
                return 0;
            default:
                printf("\n  Opção inválida. Tente novamente.\n");
                break;
        }
    }

    return 0;
}
