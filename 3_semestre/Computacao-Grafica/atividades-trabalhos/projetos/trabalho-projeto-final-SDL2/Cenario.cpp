// --- ELEMENTOS FIXOS DO CENÁRIO ---

#include "Cenario.h"

#include <SDL2/SDL_opengl.h>

#include "Configuracoes.h"

void desenharLinhaChao() {
    glDisable(GL_TEXTURE_2D);
    glColor3f(0.2f, 0.8f, 0.2f);

    glBegin(GL_LINES);
        glVertex2f(-1.0f, POSICAO_Y_CHAO);
        glVertex2f( 1.0f, POSICAO_Y_CHAO);
    glEnd();
}

void desenharFundoCorSolida() {
    glDisable(GL_TEXTURE_2D);
    glColor3f(0.3f, 0.5f, 0.9f);

    glBegin(GL_QUADS);
        glVertex2f(-1.0f, -1.0f);
        glVertex2f( 1.0f, -1.0f);
        glVertex2f( 1.0f,  1.0f);
        glVertex2f(-1.0f,  1.0f);
    glEnd();
}
