// --- RETÂNGULO COM TEXTURA ANIMADA E FÍSICA ---

#pragma once

#include <SDL2/SDL_opengl.h>

#include "Configuracoes.h"

class RetanguloComTexturaAnimada {
public:
    float centroX;
    float centroY;
    float largura;
    float altura;
    float velocidadeVertical;

    GLuint identificadorTextura;

    int quantidadeQuadros;
    int quadroAtual;
    float tempoAcumuladoQuadro;
    float duracaoQuadro;

    RetanguloComTexturaAnimada(
        float centroXInicial = POSICAO_X_JOGADOR,
        float centroYInicial = POSICAO_Y_CHAO + 0.15f,
        float larguraInicial = 0.2f,
        float alturaInicial = 0.2f
    );

    void definirTextura(GLuint textura, int quantidadeQuadrosTextura);

    // --- AVANÇA O QUADRO DA ANIMAÇÃO ---
    void atualizarAnimacao(float tempoDecorrido);

    // --- APLICA GRAVIDADE E COLISÃO COM O CHÃO ---
    void atualizarFisica(float tempoDecorrido);

    bool estaNoChao() const;

    void pular();

    void desenhar();

    // --- CAIXA DELIMITADORA ALINHADA AOS EIXOS (AABB) ---
    void obterCaixaDelimitadora(float &esquerda, float &direita, float &inferior, float &superior);
};