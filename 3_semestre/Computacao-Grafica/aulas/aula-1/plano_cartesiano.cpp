// --- COMPUTAÇÃO GRÁFICA ---
// --- ALGORITMOS DE RASTERIZAÇÃO: RETA E CÍRCULO ---

#include <math.h>
#include <stdio.h>
#include <windows.h>
#include <locale.h>

// --- PROTÓTIPOS ---
void irParaCoordenadaXY(int x, int y);
int converterXParaColunaTela(int x);
int converterYParaLinhaTela(int y);
void desenharPlanoCartesiano(void);
void desenharRetaAnalisadorDiferencialDigital(float *xInicial, float *yInicial, float *xFinal, float *yFinal);
void desenharRetaBresenham(float *xInicial, float *yInicial, float *xFinal, float *yFinal);
void desenharCirculoBresenham(int *raio);
void exibirAnimacao(void);

int main() {
    setlocale(LC_ALL, "Portuguese");

    int opcaoEscolhida = 1;
    int raio;
    float xInicial;
    float xFinal;
    float yInicial;
    float yFinal;

    while (opcaoEscolhida != 0) {
        printf("\n\nEscolha o número referente ao desafio desejado:\n");
        printf("(0) - Sair\n");
        printf("(1) - Imprime uma reta (Algoritmo DDA - Analisador Diferencial Digital)\n");
        printf("(2) - Imprime uma reta (Algoritmo de Bresenham)\n");
        printf("(3) - Imprime o traçado de um círculo (Algoritmo de Bresenham)\n");
        printf("Opção escolhida:\n");
        scanf("%d", &opcaoEscolhida);

        switch (opcaoEscolhida) {
            case 0: {
                printf("\n\tVocê escolheu (0) --> Sair\n");
                break;
            }

            // --- RETA: ANALISADOR DIFERENCIAL DIGITAL ---
            case 1: {
                printf("\nVocê escolheu (1) --> Imprime uma reta (Algoritmo DDA - Analisador Diferencial Digital)\n");

                printf("\nEscolha o valor do x inicial: ");
                scanf("%f", &xInicial);
                printf("Escolha o valor do y inicial: ");
                scanf("%f", &yInicial);
                printf("Escolha o valor do x final: ");
                scanf("%f", &xFinal);
                printf("Escolha o valor do y final: ");
                scanf("%f", &yFinal);

                system("cls");
                exibirAnimacao();
                system("cls");
                desenharPlanoCartesiano();
                desenharRetaAnalisadorDiferencialDigital(&xInicial, &yInicial, &xFinal, &yFinal);
                irParaCoordenadaXY(0, 0);

                printf("\nDeseja desenhar outro plano cartesiano? (1 = sim, 0 = não)");
                printf("\nDigite o valor aqui --> ");
                scanf("%d", &opcaoEscolhida);

                break;
            }

            // --- RETA: BRESENHAM ---
            case 2: {
                printf("\nVocê escolheu (2) --> Imprime uma reta (Algoritmo de Bresenham)\n");

                printf("\nEscolha o valor do x inicial: ");
                scanf("%f", &xInicial);
                printf("Escolha o valor do y inicial: ");
                scanf("%f", &yInicial);
                printf("Escolha o valor do x final: ");
                scanf("%f", &xFinal);
                printf("Escolha o valor do y final: ");
                scanf("%f", &yFinal);

                system("cls");
                exibirAnimacao();
                system("cls");
                desenharPlanoCartesiano();
                desenharRetaBresenham(&xInicial, &yInicial, &xFinal, &yFinal);
                irParaCoordenadaXY(0, 0);

                printf("\nDeseja desenhar outro plano cartesiano? (1 = sim, 0 = não)");
                printf("\nDigite o valor aqui --> ");
                scanf("%d", &opcaoEscolhida);

                break;
            }

            // --- CÍRCULO: BRESENHAM ---
            case 3: {
                printf("\nVocê escolheu (3) --> Imprime o traçado de um círculo (Algoritmo de Bresenham)\n");

                printf("\nEscolha o valor do raio: ");
                scanf("%d", &raio);

                system("cls");
                exibirAnimacao();
                system("cls");
                desenharPlanoCartesiano();
                desenharCirculoBresenham(&raio);
                irParaCoordenadaXY(0, 0);

                printf("\nDeseja desenhar outro plano cartesiano? (1 = sim, 0 = não)");
                printf("\nDigite o valor aqui --> ");
                scanf("%d", &opcaoEscolhida);

                break;
            }

            default: {
                printf("\n((Você escolheu um valor que não existe))\n((Digite novamente))\n\n");
            }
        }
    }

    printf("\nPrograma encerrado\n");

    return 0;
}

// --- POSICIONA O CURSOR DO CONSOLE ---
void irParaCoordenadaXY(int x, int y) {
    COORD coordenada;

    coordenada.X = (short) x;
    coordenada.Y = (short) y;

    SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), coordenada);
}

// --- CONVERSÃO: SISTEMA CARTESIANO -> SISTEMA DA TELA ---
int converterXParaColunaTela(int x) {
    return (x + 118);
}

int converterYParaLinhaTela(int y) {
    return (34 - y);
}

// --- PLANO CARTESIANO ---
void desenharPlanoCartesiano(void) {
    int linhaVertical;
    int colunaHorizontal;

    for (linhaVertical = 14; linhaVertical < 55; linhaVertical++) {
        irParaCoordenadaXY(118, linhaVertical);
        printf("|");
    }

    for (colunaHorizontal = 18; colunaHorizontal < 219; colunaHorizontal++) {
        irParaCoordenadaXY(colunaHorizontal, 34);
        printf("-");
    }

    irParaCoordenadaXY(118, 34);
    printf("X");
}

// --- RETA: ANALISADOR DIFERENCIAL DIGITAL ---
void desenharRetaAnalisadorDiferencialDigital(float *xInicial, float *yInicial, float *xFinal, float *yFinal) {
    if (fabs(*xFinal - *xInicial) >= fabs(*yFinal - *yInicial)) {
        float coeficienteAngular = (*yFinal - *yInicial) / (*xFinal - *xInicial);
        float y = *yInicial;
        int x;

        if (*xInicial > *xFinal) {
            float valorAuxiliarTroca = *xInicial;

            *xInicial = *xFinal;
            *xFinal = valorAuxiliarTroca;
            y = *yFinal;
        }

        for (x = *xInicial; x <= *xFinal; x++) {
            irParaCoordenadaXY(converterXParaColunaTela(x), converterYParaLinhaTela(round(y)));
            printf("*");
            y += coeficienteAngular;
        }
    } else {
        float coeficienteAngularInverso = (*xFinal - *xInicial) / (*yFinal - *yInicial);
        float x = *xInicial;
        int y;

        if (*yInicial > *yFinal) {
            float valorAuxiliarTroca = *yInicial;

            *yInicial = *yFinal;
            *yFinal = valorAuxiliarTroca;
            x = *xFinal;
        }

        for (y = *yInicial; y <= *yFinal; y++) {
            irParaCoordenadaXY(converterXParaColunaTela(round(x)), converterYParaLinhaTela(y));
            printf("*");
            x += coeficienteAngularInverso;
        }
    }
}

// --- CÍRCULO: BRESENHAM ---
void desenharCirculoBresenham(int *raio) {
    int x;
    int y;
    int parametroDecisao;

    x = 0;
    y = *raio;
    parametroDecisao = 1 - *raio;

    while (x <= y) {
        irParaCoordenadaXY(converterXParaColunaTela(+x), converterYParaLinhaTela(+y)); 
        printf("o");

        irParaCoordenadaXY(converterXParaColunaTela(+x), converterYParaLinhaTela(-y)); 
        printf("o");

        irParaCoordenadaXY(converterXParaColunaTela(-x), converterYParaLinhaTela(+y)); 
        printf("o");

        irParaCoordenadaXY(converterXParaColunaTela(-x), converterYParaLinhaTela(-y)); 
        printf("o");

        irParaCoordenadaXY(converterXParaColunaTela(+y), converterYParaLinhaTela(+x)); 
        printf("o");

        irParaCoordenadaXY(converterXParaColunaTela(+y), converterYParaLinhaTela(-x)); 
        printf("o");

        irParaCoordenadaXY(converterXParaColunaTela(-y), converterYParaLinhaTela(+x)); 
        printf("o");

        irParaCoordenadaXY(converterXParaColunaTela(-y), converterYParaLinhaTela(-x)); 
        printf("o");


        if (parametroDecisao <= 0) {
            parametroDecisao = parametroDecisao + 2 * x + 1;
        } else {
            parametroDecisao = parametroDecisao + 2 * x + 1 - 2 * y;
            y--;
        }

        x++;
    }
}

// --- RETA: BRESENHAM ---
void desenharRetaBresenham(float *xInicial, float *yInicial, float *xFinal, float *yFinal) {
    float diferencaX;
    float diferencaY;
    float parametroDecisao;
    float incrementoLeste;
    float incrementoNordeste;
    float x;
    float y;
    float xLimite;
    float yLimite;
    int sentidoPasso;

    diferencaX = fabs(*xFinal - *xInicial);
    diferencaY = fabs(*yFinal - *yInicial);

    // --- RETA MAIS HORIZONTAL: PERCORRE O EIXO X ---
    if (diferencaY <= diferencaX) {
        parametroDecisao   = 2 * diferencaY - diferencaX;
        incrementoLeste    = 2 * diferencaY;
        incrementoNordeste = 2 * diferencaY - 2 * diferencaX;

        if (*xInicial <= *xFinal) {
            x = *xInicial;
            y = *yInicial;
            xLimite = *xFinal;
            yLimite = *yFinal;
        } else {
            x = *xFinal;
            y = *yFinal;
            xLimite = *xInicial;
            yLimite = *yInicial;
        }

        sentidoPasso = (yLimite >= y) ? 1 : -1;

        while (x <= xLimite) {
            irParaCoordenadaXY(converterXParaColunaTela(round(x)), converterYParaLinhaTela(round(y)));
            printf("*");

            if (parametroDecisao <= 0) {
                parametroDecisao = parametroDecisao + incrementoLeste;
            } else {
                parametroDecisao = parametroDecisao + incrementoNordeste;
                y = y + sentidoPasso;
            }

            x++;
        }
    // --- RETA MAIS VERTICAL: PERCORRE O EIXO Y ---
    } else {
        parametroDecisao = 2 * diferencaX - diferencaY;
        incrementoLeste = 2 * diferencaX;
        incrementoNordeste = 2 * diferencaX - 2 * diferencaY;

        if (*yInicial <= *yFinal) {
            x = *xInicial;
            y = *yInicial;
            xLimite = *xFinal;
            yLimite = *yFinal;
        } else {
            x = *xFinal;
            y = *yFinal;
            xLimite = *xInicial;
            yLimite = *yInicial;
        }

        sentidoPasso = (xLimite >= x) ? 1 : -1;

        while (y <= yLimite) {
            irParaCoordenadaXY(converterXParaColunaTela(round(x)), converterYParaLinhaTela(round(y)));
            printf("*");

            if (parametroDecisao <= 0) {
                parametroDecisao = parametroDecisao + incrementoLeste;
            } else {
                parametroDecisao = parametroDecisao + incrementoNordeste;
                x = x + sentidoPasso;
            }

            y++;
        }
    }
}

// --- ANIMAÇÃO DE ABERTURA ---
void exibirAnimacao(void) {
    int quadroAtual = 0;

    while (quadroAtual++ < 18) {
        irParaCoordenadaXY(quadroAtual, 0);
        printf(" o");
        printf("\n");
        irParaCoordenadaXY(quadroAtual, 20 - quadroAtual);
        printf(" X");
        printf("\n");

        if (quadroAtual % 2 == 0) {
            irParaCoordenadaXY(quadroAtual, 1);
            printf(" |");
            irParaCoordenadaXY(quadroAtual, 2);
            printf("/ \\o");
        } else {
            irParaCoordenadaXY(quadroAtual, 1);
            printf("-|-");
            irParaCoordenadaXY(quadroAtual, 2);
            printf(" | o");
        }

        Sleep(100);
        system("cls");
    }

    irParaCoordenadaXY(18, 1);
    printf("  --o");
    irParaCoordenadaXY(18, 2);
    printf("_/ \\");
    Sleep(500);
    system("cls");

    irParaCoordenadaXY(18, 2);
    printf(">->o");
    Sleep(1000);
}
