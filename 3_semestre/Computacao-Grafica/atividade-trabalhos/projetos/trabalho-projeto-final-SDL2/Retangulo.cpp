// --- RETÂNGULO ESTÁTICO (FUNDO E OBSTÁCULO) ---

#include "Retangulo.h"

Retangulo::Retangulo(float centroXInicial, float centroYInicial, float larguraInicial, float alturaInicial)
    : centroX(centroXInicial),
    centroY(centroYInicial),
    largura(larguraInicial),
    altura(alturaInicial),
    identificadorTextura(0),
    usarTextura(false)
{}

void Retangulo::definirTextura(GLuint textura) {
    identificadorTextura = textura;
    usarTextura = (textura != 0);
}

void Retangulo::desenhar() {
    float metadeLargura = largura / 2.0f;
    float metadeAltura  = altura / 2.0f;

    // --- COR BRANCA PARA NÃO TINGIR A TEXTURA ---
    glColor3f(1.0f, 1.0f, 1.0f);

    if (usarTextura) {
        glEnable(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D, identificadorTextura);
    } else {
        glDisable(GL_TEXTURE_2D);
    }

    glBegin(GL_QUADS);
        if (usarTextura) {
            glTexCoord2f(0.0f, 1.0f);
        }
        glVertex2f(centroX - metadeLargura, centroY - metadeAltura);

        if (usarTextura) {
            glTexCoord2f(1.0f, 1.0f);
        }
        glVertex2f(centroX + metadeLargura, centroY - metadeAltura);

        if (usarTextura) {
            glTexCoord2f(1.0f, 0.0f);
        }
        glVertex2f(centroX + metadeLargura, centroY + metadeAltura);

        if (usarTextura) {
            glTexCoord2f(0.0f, 0.0f);
        }
        glVertex2f(centroX - metadeLargura, centroY + metadeAltura);
    glEnd();

    if (usarTextura) {
        glDisable(GL_TEXTURE_2D);
    }
}

void Retangulo::obterCaixaDelimitadora(float &esquerda, float &direita, float &inferior, float &superior) {
    esquerda = centroX - largura / 2.0f;
    direita  = centroX + largura / 2.0f;
    inferior = centroY - altura / 2.0f;
    superior = centroY + altura / 2.0f;
}
