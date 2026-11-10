// --- RETÂNGULO ESTÁTICO (FUNDO E OBSTÁCULO) ---

#pragma once

#include <SDL2/SDL_opengl.h>

class Retangulo {
public:
    float centroX;
    float centroY;
    float largura;
    float altura;
    GLuint identificadorTextura;
    bool usarTextura;

    Retangulo(
        float centroXInicial = 0.0f, 
        float centroYInicial = 0.0f,
        float larguraInicial = 0.2f, 
        float alturaInicial = 0.2f
    );

    void definirTextura(GLuint textura);

    void desenhar();

    // --- CAIXA DELIMITADORA ALINHADA AOS EIXOS ---
    void obterCaixaDelimitadora(float &esquerda, float &direita, float &inferior, float &superior);
};