// --- RETÂNGULO COM TEXTURA ANIMADA E FÍSICA (JOGADOR) ---

#include "RetanguloComTexturaAnimada.h"

RetanguloComTexturaAnimada::RetanguloComTexturaAnimada(float centroXInicial, float centroYInicial, float larguraInicial, float alturaInicial)
    : centroX(centroXInicial),
    centroY(centroYInicial),
    largura(larguraInicial),
    altura(alturaInicial),
    velocidadeVertical(0.0f),
    identificadorTextura(0),
    quantidadeQuadros(1),
    quadroAtual(0),
    tempoAcumuladoQuadro(0.0f),
    duracaoQuadro(0.1f)
{}

void RetanguloComTexturaAnimada::definirTextura(GLuint textura, int quantidadeQuadrosTextura) {
    identificadorTextura = textura;
    quantidadeQuadros = (quantidadeQuadrosTextura > 0 ? quantidadeQuadrosTextura : 1);
}

void RetanguloComTexturaAnimada::atualizarAnimacao(float tempoDecorrido) {
    tempoAcumuladoQuadro += tempoDecorrido;

    if (tempoAcumuladoQuadro >= duracaoQuadro) {
        tempoAcumuladoQuadro = 0.0f;
        quadroAtual = (quadroAtual + 1) % quantidadeQuadros;
    }
}

void RetanguloComTexturaAnimada::atualizarFisica(float tempoDecorrido) {
    velocidadeVertical += GRAVIDADE * tempoDecorrido;

    centroY += velocidadeVertical * tempoDecorrido;

    float metadeAltura = altura / 2.0f;
    float baseRetangulo = centroY - metadeAltura;

    if (baseRetangulo <= POSICAO_Y_CHAO) {
        float diferenca = POSICAO_Y_CHAO - baseRetangulo;
        centroY += diferenca;
        velocidadeVertical = 0.0f;
    }
}

bool RetanguloComTexturaAnimada::estaNoChao() const {
    float metadeAltura = altura / 2.0f;
    float baseRetangulo = centroY - metadeAltura;

    return (baseRetangulo <= POSICAO_Y_CHAO + 0.0001f);
}

void RetanguloComTexturaAnimada::pular() {
    if (estaNoChao()) {
        velocidadeVertical = VELOCIDADE_PULO;
    }
}

void RetanguloComTexturaAnimada::desenhar() {
    float metadeLargura = largura / 2.0f;
    float metadeAltura  = altura / 2.0f;

    // --- COR BRANCA PARA NÃO TINGIR A TEXTURA ---
    glColor3f(1.0f, 1.0f, 1.0f);

    glEnable(GL_TEXTURE_2D);
    glBindTexture(GL_TEXTURE_2D, identificadorTextura);

    // --- RECORTE DO QUADRO ATUAL NA FOLHA DE SPRITES ---
    float larguraQuadroTextura = 1.0f / quantidadeQuadros;
    float coordenadaTexturaInicialU = larguraQuadroTextura * quadroAtual;
    float coordenadaTexturaFinalU   = coordenadaTexturaInicialU + larguraQuadroTextura;

    glBegin(GL_QUADS);
        glTexCoord2f(coordenadaTexturaInicialU, 1.0f);
        glVertex2f(centroX - metadeLargura, centroY - metadeAltura);

        glTexCoord2f(coordenadaTexturaFinalU, 1.0f);
        glVertex2f(centroX + metadeLargura, centroY - metadeAltura);

        glTexCoord2f(coordenadaTexturaFinalU, 0.0f);
        glVertex2f(centroX + metadeLargura, centroY + metadeAltura);

        glTexCoord2f(coordenadaTexturaInicialU, 0.0f);
        glVertex2f(centroX - metadeLargura, centroY + metadeAltura);
    glEnd();

    glDisable(GL_TEXTURE_2D);
}

void RetanguloComTexturaAnimada::obterCaixaDelimitadora(float &esquerda, float &direita, float &inferior, float &superior) {
    esquerda = centroX - largura / 2.0f;
    direita  = centroX + largura / 2.0f;
    inferior = centroY - altura / 2.0f;
    superior = centroY + altura / 2.0f;
}
