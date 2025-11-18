#include<math.h> // Operações matemáticas (sqrt, pow, fabs, round, etc.)
#include<stdio.h> // Funções de entrada e saída (printf, scanf)
#include<windows.h> // Funções do Windows para controle do console
#include <locale.h> // Para suporte a caracteres especiais do português

// Protótipos das funções (declarações)
void irParaXY(int x, int y); // Move cursor para coordenada específica
int setX(int x); // Transforma coordenada X do plano cartesiano para coordenada de tela
int setY(int y); // Transforma coordenada Y do plano cartesiano para coordenada de tela
void planoCartesiano(void); // Desenha o plano cartesiano na tela
void dda(float *x1, float *y1, float *x2, float *y2); // Algoritmo DDA para retas
void bresenham(float *x1, float *y1, float *x2, float *y2); // Algoritmo Bresenham para retas
void circulo(int *raio); // Algoritmo Bresenham para círculos
int animacao(); // Animação do "bonequinho"

int main(){
    setlocale(LC_ALL, "Portuguese"); // Configura locale para português
    
    // Variáveis do programa
    int escolha = 1; // Controla o loop do menu
    float xi, xf, yi, yf; // Coordenadas para as retas
    int raio; // Raio para o círculo
    
    // Loop principal do programa
    while(escolha != 0){
        // Menu de opções para o usuário
        printf("\n\nEscolha o número referente ao desafio desejado:\n");
        printf("(0) - Sair\n");
        printf("(1) - Imprime uma reta (Algoritmo DDA)\n");
        printf("(2) - Imprime uma reta (Algoritmo de Bresenham)\n");
        printf("(3) - Imprime o traçado de um círculo (Algoritmo de Bresenham)\n");
        printf("Opção escolhida:\n");
        scanf("%d", &escolha);
        
        switch (escolha) {
            case 0: // Opção para sair do programa
                printf("\n\tVocê escolheu (0) --> Sair\n");
                break;
                
            case 1: // Algoritmo DDA para retas
                printf("\nVocê escolheu (1) --> Imprime uma reta (Algoritmo DDA)\n");
                
                // Entrada das coordenadas da reta
                printf("\nEscolha o valor do x inicial:");
                scanf("%f", &xi);
                printf("Escolha o valor do y inicial:");
                scanf("%f", &yi);
                printf("Escolha o valor do x final:");
                scanf("%f", &xf);
                printf("Escolha o valor do y final:");
                scanf("%f", &yf);
                
                system("cls"); // Limpa a tela
                animacao(); // Executa animação
                system("cls"); // Limpa a tela novamente
                planoCartesiano(); // Desenha o plano cartesiano
                dda(&xi, &yi, &xf, &yf); // Executa algoritmo DDA
                irParaXY(0, 0); // Posiciona cursor no início da tela
                
                // Pergunta se usuário quer continuar
                printf("\nDeseja desenhar outro plano cartesiano? (1 = sim, 0 = não)");
                printf("\nDigita o valor aqui --> ");
                scanf("%d", &escolha);
                break;
                
            case 2: // Algoritmo Bresenham para retas
                printf("\nVocê escolheu (2) --> Imprime uma reta (Algoritmo de Bresenham)\n");
                
                // Entrada das coordenadas da reta
                printf("\nEscolha o valor do x inicial: ");
                scanf("%f", &xi);
                printf("Escolha o valor do y inicial: ");
                scanf("%f", &yi);
                printf("Escolha o valor do x final: ");
                scanf("%f", &xf);
                printf("Escolha o valor do y final: ");
                scanf("%f", &yf);
                
                system("cls");
                animacao();
                system("cls");
                planoCartesiano(); // Desenha o plano cartesiano
                bresenham(&xi, &yi, &xf, &yf); // Executa algoritmo Bresenham
                irParaXY(0, 0);
                
                printf("\nDeseja desenhar outro plano cartesiano? (1 = sim, 0 = nao)");
                printf("\nDigite o valor aqui --> ");
                scanf("%d", &escolha);
                break;
                
            case 3: // Algoritmo Bresenham para círculos
                printf("Voce digitou(3) Escolheu --> Imprime o traçado de um círculo (Algoritmo de Bresenham)\n");
                printf("qual o raio?"); // Pergunta o raio do círculo
                scanf("%d", &raio);
                
                system("cls");
                animacao();
                system("cls");
                planoCartesiano(); // Desenha o plano cartesiano
                circulo(&raio); // Desenha o círculo
                irParaXY(0, 0);
                
                printf("\nDeseja desenhar outro plano cartesiano? (1 = sim, 0 = não)");
                printf("\nDigite o valor aqui --> ");
                scanf("%d", &escolha);
                break;
                
            default: // Opção inválida
                printf("\((nVocê escolheu um valor que não existe))\n((Digite notamente))\n\n");
        }
    }
    
    printf("\nPrograma encerrado\n");
    return 0;
}

// Função para posicionar o cursor em coordenadas específicas da tela
void irParaXY(int x, int y) {
    COORD coord; // Estrutura para coordenadas do console Windows
    coord.X = (short) x; // Coordenada X (coluna)
    coord.Y = (short) y; // Coordenada Y (linha)
    SetConsoleCursorPosition(GetStdHandle(STD_OUTPUT_HANDLE), coord); // Move cursor
}

// Função para converter coordenada X do plano cartesiano para coordenada de tela
// Centraliza o plano na coluna 118
int setX(int x) {
    return (x + 118); // Desloca X para centralizar
}

// Função para converter coordenada Y do plano cartesiano para coordenada de tela
// Inverte Y (pois na tela Y cresce para baixo) e centraliza na linha 34
int setY(int y) {
    return (34 - y); // Inverte e centraliza Y
}

// Função que desenha o plano cartesiano na tela
void planoCartesiano(void) {
    int vertical, horizontal;
    
    // Desenha eixo Y (vertical)
    for (vertical = 14; vertical < 55; vertical++) {
        irParaXY(118, vertical); // Posição fixa X=118 (centro horizontal)
        printf("|"); // Caractere vertical
    }
    
    // Desenha eixo X (horizontal)
    for (horizontal = 18; horizontal < 219; horizontal++) {
        irParaXY(horizontal, 34); // Posição fixa Y=34 (centro vertical)
        printf("-"); // Caractere horizontal
    }
    
    // Marca a origem do plano cartesiano
    irParaXY(118, 34);
    printf("X"); // Ponto central (origem)
}

// Implementação do algoritmo DDA (Digital Differential Analyzer) para retas
void dda(float *x1, float *y1, float *x2, float *y2) {
    // Caso 1: Inclinação <= 45° (variação em X é maior ou igual à variação em Y)
    if (fabs(*x2 - *x1) >= fabs(*y2 - *y1)) {
        float m = (*y2 - *y1) / (*x2 - *x1); // Calcula coeficiente angular
        float y = *y1; // Valor inicial de Y
        int x;
        
        // Garante que percorremos do menor X para o maior X
        if (*x1 > *x2) {
            float b = *x1;
            *x1 = *x2;
            *x2 = b;
            y = *y2; // Ajusta Y inicial
        }
        
        // Percorre todos os pontos em X
        for (x = *x1; x <= *x2; x++) {
            irParaXY(setX(x), setY(round(y))); // Posiciona cursor
            printf("*"); // Desenha pixel
            y += m; // Incrementa Y usando o coeficiente angular
        }
    } 
    // Caso 2: Inclinação > 45° (variação em Y é maior que variação em X)
    else {
        float m = (*x2 - *x1) / (*y2 - *y1); // Inverso do coeficiente angular
        float x = *x1; // Valor inicial de X
        int y;
        
        // Garante que percorremos do menor Y para o maior Y
        if (*y1 > *y2) {
            float b = *y1;
            *y1 = *y2;
            *y2 = b;
            x = *x2; // Ajusta X inicial
        }
        
        // Percorre todos os pontos em Y
        for (y = *y1; y <= *y2; y++) {
            irParaXY(setX(round(x)), setY(y)); // Posiciona cursor
            printf("*"); // Desenha pixel
            x += m; // Incrementa X usando o inverso do coeficiente angular
        }
    }
}

// Implementação do algoritmo de Bresenham para círculos
void circulo(int *raio){
    int x, y, p; // Variáveis do algoritmo
    x = 0; // Inicia no ponto (0, raio)
    y = *raio;
    p = 1 - *raio; // Parâmetro de decisão inicial

    // Loop para desenhar 1/8 do círculo (os outros 7/8 são por simetria)
    while (x <= y) {
        // Desenha os 8 pontos simétricos do círculo
        // Utiliza simetria nos 8 octantes do plano cartesiano
        irParaXY(setX(+x), setY(+y)); printf("o"); // Octante 1
        irParaXY(setX(+x), setY(-y)); printf("o"); // Octante 4
        irParaXY(setX(-x), setY(+y)); printf("o"); // Octante 2
        irParaXY(setX(-x), setY(-y)); printf("o"); // Octante 3
        irParaXY(setX(+y), setY(+x)); printf("o"); // Octante 8
        irParaXY(setX(+y), setY(-x)); printf("o"); // Octante 5
        irParaXY(setX(-y), setY(+x)); printf("o"); // Octante 7
        irParaXY(setX(-y), setY(-x)); printf("o"); // Octante 6

        // Atualiza parâmetro de decisão e coordenadas
        if (p <= 0) {
            p = p + 2*x + 1; // Move apenas para a direita
        } else {
            p = p + 2*x + 1 - 2*y; // Move para baixo e direita
            y--; // Decrementa Y
        }
        x++; // Sempre incrementa X
    }
}

// Implementação do algoritmo de Bresenham para retas
void bresenham(float *x1, float *y1, float *x2, float *y2){
    float d_x, d_y, p, incE, incNE, x, y;

    // Calcula diferenças absolutas
    d_x = fabs(*x2 - *x1); // Variação em X
    d_y = fabs(*y2 - *y1); // Variação em Y
    p = 2 * d_y - d_x; // Parâmetro de decisão inicial
    
    // Caso 1: Inclinação <= 45°
    if (d_y <= d_x) {
        incE = 2 * d_y; // Incremento para movimento E (leste)
        incNE = 2 * d_y - 2 * d_x; // Incremento para movimento NE (nordeste)

        // Garante que percorremos da esquerda para direita
        if (*x1 <= *x2) {
            x = *x1;
            y = *y1;
        } else {
            x = *x2;
            y = *y2;
            *x2 = *x1;
            *y2 = *y1;
        }

        // Percorre a reta
        while (x <= *x2) {
            irParaXY(setX(x), setY(y));
            printf("*");

            // Decide próximo pixel
            if (p <= 0) {
                p = p + incE; // Move para E
            } else {
                p = p + incNE; // Move para NE
                y = y + ((*y2 >= *y1) ? 1 : -1); // Incrementa ou decrementa Y
            }
            x++; // Sempre incrementa X
        }
    } 
    // Caso 2: Inclinação > 45°
    else {
        incE = 2 * d_x; // Incremento para movimento N (norte)
        incNE = 2 * d_x - 2 * d_y; // Incremento para movimento NE (nordeste)

        // Garante que percorremos de baixo para cima
        if (*y1 <= *y2) {
            x = *x1;
            y = *y1;
        } else {
            x = *x2;
            y = *y2;
            *y2 = *y1;
            *x2 = *x1;
        }

        // Percorre a reta
        while (y <= *y2) {
            irParaXY(setX(round(x)), setY(round(y)));
            printf("°"); // Usa caractere diferente para distinguir

            // Decide próximo pixel
            if (p <= 0) {
                p = p + incE; // Move para N
            } else {
                p = p + incNE; // Move para NE
                x = x + ((*x2 >= *x1) ? 1 : -1); // Incrementa ou decrementa X
            }
            y++; // Sempre incrementa Y
        }
    }
}

// Função de animação - mostra um "bonequinho" caindo
int animacao(){
    int i = 0;
    
    // Loop de animação com 18 frames
    while (i++ < 18){
        // Desenha partes do boneco em posições diferentes
        irParaXY(i, 0);
        printf(" o"); // Cabeça
        printf("\n");
        irParaXY(i, 20 - i);
        printf(" X"); // Alvo
        printf("\n");
        
        // Alterna entre duas poses para criar efeito de movimento
        if (i % 2 == 0){
            irParaXY(i, 1);
            printf(" |"); // Braços para cima
            irParaXY(i, 2);
            printf("/ \\o"); // Pernas abertas
        } else {
            irParaXY(i, 1);
            printf("-|-"); // Braços para os lados
            irParaXY(i, 2);
            printf(" | o"); // Pernas juntas
        }
        
        sleep(0, 0001); // Pequena pausa entre frames
        system("cls"); // Limpa tela para próximo frame
    }

    // Frames finais da animação
    irParaXY(18, 1);
    printf("  --o"); // Posição final
    irParaXY(18, 2);
    printf("_/ \\"); // Pernas finais
    sleep(0, 5); // Pausa
    system("cls"); // Limpa tela
    
    irParaXY(18, 2);
    printf(">->o"); // Último frame
    sleep(1); // Pausa final
}
