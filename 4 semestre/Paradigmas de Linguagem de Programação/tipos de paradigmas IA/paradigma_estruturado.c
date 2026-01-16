#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define MAX_MORADORES 50
#define MAX_ESPACOS 20
#define MAX_RESERVAS 200

typedef struct {
    int id;
    char nome[60];
    char apartamento[20];
} Morador;

typedef struct {
    int id;
    char nome[60];
    int capacidade;
    int tipo;
} Espaco;

typedef struct {
    int id;
    int moradorId;
    int espacoId;
    int dia;
    int mes;
    int ano;
    int inicioMin;
    int fimMin;
} Reserva;

int min_from_hm(int h, int m) { return h * 60 + m; }

void print_hm(int totalMin) {
    int h = totalMin / 60;
    int m = totalMin % 60;
    printf("%02d:%02d", h, m);
}

int valida_intervalo(int inicioMin, int fimMin) {
    if (inicioMin < 0 || fimMin < 0) return 0;
    if (inicioMin >= 24 * 60 || fimMin > 24 * 60) return 0;
    if (inicioMin >= fimMin) return 0;
    return 1;
}

int valida_data(int d, int m, int a) {
    if (a < 1900 || a > 2100) return 0;
    if (m < 1 || m > 12) return 0;
    if (d < 1 || d > 31) return 0;
    if ((m == 4 || m == 6 || m == 9 || m == 11) && d > 30) return 0;
    if (m == 2) {
        int bissexto = (a % 400 == 0) || (a % 4 == 0 && a % 100 != 0);
        if (d > (bissexto ? 29 : 28)) return 0;
    }
    return 1;
}

int str_vazia(const char *s) {
    if (!s) return 1;
    while (*s) {
        if (*s != ' ' && *s != '\n' && *s != '\t' && *s != '\r') return 0;
        s++;
    }
    return 1;
}

int ler_linha(char *buf, int tam) {
    if (!fgets(buf, tam, stdin)) return 0;
    size_t len = strlen(buf);
    if (len > 0 && buf[len - 1] == '\n') buf[len - 1] = '\0';
    return 1;
}

int buscar_morador(Morador moradores[], int n, int id) {
    for (int i = 0; i < n; i++) if (moradores[i].id == id) return i;
    return -1;
}

int buscar_espaco(Espaco espacos[], int n, int id) {
    for (int i = 0; i < n; i++) if (espacos[i].id == id) return i;
    return -1;
}

int conflito_reserva(Reserva reservas[], int nRes, int espacoId, int d, int m, int a, int ini, int fim) {
    for (int i = 0; i < nRes; i++) {
        Reserva r = reservas[i];
        if (r.espacoId != espacoId) continue;
        if (r.dia != d || r.mes != m || r.ano != a) continue;
        if (ini < r.fimMin && fim > r.inicioMin) return 1;
    }
    return 0;
}

int politica_espaco(Espaco e, int inicioMin, int fimMin) {
    int dur = fimMin - inicioMin;
    if (e.tipo == 1) {
        if (dur > 6 * 60) return 0;
        return 1;
    }
    if (e.tipo == 2) {
        if (inicioMin < 18 * 60) return 0;
        return 1;
    }
    return 1;
}

void listar_moradores(Morador moradores[], int n) {
    printf("\nMoradores:\n");
    if (n == 0) { printf("(vazio)\n"); return; }
    for (int i = 0; i < n; i++) {
        printf("  [%d] %s (Ap: %s)\n", moradores[i].id, moradores[i].nome, moradores[i].apartamento);
    }
}

void listar_espacos(Espaco espacos[], int n) {
    printf("\nEspacos:\n");
    if (n == 0) { printf("(vazio)\n"); return; }
    for (int i = 0; i < n; i++) {
        const char *tipo = (espacos[i].tipo == 1) ? "Churrasqueira" : (espacos[i].tipo == 2) ? "Salao" : "Generico";
        printf("  [%d] %s | cap=%d | %s\n", espacos[i].id, espacos[i].nome, espacos[i].capacidade, tipo);
    }
}

void listar_reservas(Reserva reservas[], int nRes, Morador moradores[], int nMor, Espaco espacos[], int nEsp) {
    printf("\nReservas:\n");
    if (nRes == 0) { printf("(vazio)\n"); return; }
    for (int i = 0; i < nRes; i++) {
        Reserva r = reservas[i];
        int im = buscar_morador(moradores, nMor, r.moradorId);
        int ie = buscar_espaco(espacos, nEsp, r.espacoId);
        const char *nm = (im >= 0) ? moradores[im].nome : "Desconhecido";
        const char *ne = (ie >= 0) ? espacos[ie].nome : "Desconhecido";
        printf("  [%d] %s -> %s em %02d/%02d/%04d ", r.id, nm, ne, r.dia, r.mes, r.ano);
        print_hm(r.inicioMin);
        printf(" - ");
        print_hm(r.fimMin);
        printf("\n");
    }
}

int cadastrar_morador(Morador moradores[], int *nMor, int *nextId) {
    if (*nMor >= MAX_MORADORES) return 0;

    char nome[60];
    char ap[20];

    printf("Nome do morador: ");
    if (!ler_linha(nome, sizeof(nome)) || str_vazia(nome)) return 0;

    printf("Apartamento: ");
    if (!ler_linha(ap, sizeof(ap)) || str_vazia(ap)) strcpy(ap, "N/A");

    Morador m;
    m.id = (*nextId)++;
    strncpy(m.nome, nome, sizeof(m.nome) - 1);
    m.nome[sizeof(m.nome) - 1] = '\0';
    strncpy(m.apartamento, ap, sizeof(m.apartamento) - 1);
    m.apartamento[sizeof(m.apartamento) - 1] = '\0';

    moradores[*nMor] = m;
    (*nMor)++;

    printf("Morador cadastrado com id %d\n", m.id);
    return 1;
}

int cadastrar_espaco(Espaco espacos[], int *nEsp, int *nextId) {
    if (*nEsp >= MAX_ESPACOS) return 0;

    char nome[60];
    int cap = 0;
    int tipo = 0;

    printf("Nome do espaco: ");
    if (!ler_linha(nome, sizeof(nome)) || str_vazia(nome)) return 0;

    printf("Capacidade: ");
    if (scanf("%d", &cap) != 1) return 0;

    printf("Tipo (0=Generico, 1=Churrasqueira, 2=Salao): ");
    if (scanf("%d", &tipo) != 1) return 0;

    int c;
    while ((c = getchar()) != '\n' && c != EOF) {}

    Espaco e;
    e.id = (*nextId)++;
    strncpy(e.nome, nome, sizeof(e.nome) - 1);
    e.nome[sizeof(e.nome) - 1] = '\0';
    e.capacidade = (cap < 1) ? 1 : cap;
    e.tipo = (tipo < 0 || tipo > 2) ? 0 : tipo;

    espacos[*nEsp] = e;
    (*nEsp)++;

    printf("Espaco cadastrado com id %d\n", e.id);
    return 1;
}

int criar_reserva(Reserva reservas[], int *nRes, int *nextId,
                Morador moradores[], int nMor, Espaco espacos[], int nEsp) {
    if (*nRes >= MAX_RESERVAS) return 0;

    int moradorId, espacoId;
    int d, m, a;
    int hi, mi, hf, mf;

    listar_moradores(moradores, nMor);
    printf("\nID do morador: ");
    if (scanf("%d", &moradorId) != 1) return 0;

    listar_espacos(espacos, nEsp);
    printf("\nID do espaco: ");
    if (scanf("%d", &espacoId) != 1) return 0;

    printf("Data (dd mm aaaa): ");
    if (scanf("%d %d %d", &d, &m, &a) != 3) return 0;

    printf("Horario inicio (hh mm): ");
    if (scanf("%d %d", &hi, &mi) != 2) return 0;

    printf("Horario fim (hh mm): ");
    if (scanf("%d %d", &hf, &mf) != 2) return 0;

    int c;
    while ((c = getchar()) != '\n' && c != EOF) {}

    if (!valida_data(d, m, a)) {
        printf("Data invalida\n");
        return 0;
    }

    int ini = min_from_hm(hi, mi);
    int fim = min_from_hm(hf, mf);
    if (!valida_intervalo(ini, fim)) {
        printf("Horario invalido\n");
        return 0;
    }

    int im = buscar_morador(moradores, nMor, moradorId);
    if (im < 0) {
        printf("Morador nao encontrado\n");
        return 0;
    }

    int ie = buscar_espaco(espacos, nEsp, espacoId);
    if (ie < 0) {
        printf("Espaco nao encontrado\n");
        return 0;
    }

    Espaco e = espacos[ie];
    if (!politica_espaco(e, ini, fim)) {
        printf("Regra do espaco bloqueou a reserva\n");
        return 0;
    }

    if (conflito_reserva(reservas, *nRes, espacoId, d, m, a, ini, fim)) {
        printf("Conflito com outra reserva\n");
        return 0;
    }

    Reserva r;
    r.id = (*nextId)++;
    r.moradorId = moradorId;
    r.espacoId = espacoId;
    r.dia = d;
    r.mes = m;
    r.ano = a;
    r.inicioMin = ini;
    r.fimMin = fim;

    reservas[*nRes] = r;
    (*nRes)++;

    printf("Reserva criada com id %d\n", r.id);
    return 1;
}

void seed(Morador moradores[], int *nMor, int *morId, Espaco espacos[], int *nEsp, int *espId) {
    Morador a = { (*morId)++, "Ana", "A-101" };
    Morador b = { (*morId)++, "Bruno", "B-202" };
    moradores[(*nMor)++] = a;
    moradores[(*nMor)++] = b;

    Espaco s = { (*espId)++, "Salao Principal", 80, 2 };
    Espaco c = { (*espId)++, "Churrasqueira 1", 20, 1 };
    espacos[(*nEsp)++] = s;
    espacos[(*nEsp)++] = c;
}

int menu() {
    int op = -1;
    printf("\n===== Sistema Estruturado (C) =====\n");
    printf("1) Cadastrar morador\n");
    printf("2) Cadastrar espaco\n");
    printf("3) Criar reserva\n");
    printf("4) Listar moradores\n");
    printf("5) Listar espacos\n");
    printf("6) Listar reservas\n");
    printf("0) Sair\n");
    printf("Opcao: ");
    if (scanf("%d", &op) != 1) return -1;

    int c;
    while ((c = getchar()) != '\n' && c != EOF) {}
    return op;
}

int main() {
    Morador moradores[MAX_MORADORES];
    Espaco espacos[MAX_ESPACOS];
    Reserva reservas[MAX_RESERVAS];

    int nMor = 0, nEsp = 0, nRes = 0;
    int nextMorId = 1, nextEspId = 1, nextResId = 1;

    seed(moradores, &nMor, &nextMorId, espacos, &nEsp, &nextEspId);

    for (;;) {
        int op = menu();

        if (op == 0) break;

        if (op == 1) {
            if (!cadastrar_morador(moradores, &nMor, &nextMorId)) printf("Falha ao cadastrar morador\n");
        } 
        else if (op == 2) {
            if (!cadastrar_espaco(espacos, &nEsp, &nextEspId)) printf("Falha ao cadastrar espaco\n");
        } 
        else if (op == 3) {
            if (nMor == 0 || nEsp == 0) {
                printf("Cadastre ao menos 1 morador e 1 espaco antes\n");
            } else {
                if (!criar_reserva(reservas, &nRes, &nextResId, moradores, nMor, espacos, nEsp))
                    printf("Falha ao criar reserva\n");
            }
        } 
        else if (op == 4) {
            listar_moradores(moradores, nMor);
        } 
        else if (op == 5) {
            listar_espacos(espacos, nEsp);
        } 
        else if (op == 6) {
            listar_reservas(reservas, nRes, moradores, nMor, espacos, nEsp);
        } 
        else {
            printf("Opcao invalida\n");
        }
    }

    printf("Encerrado.\n");
    return 0;
}
