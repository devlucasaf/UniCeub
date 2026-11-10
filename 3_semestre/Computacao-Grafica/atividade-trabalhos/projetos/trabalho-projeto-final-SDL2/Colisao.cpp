// --- DETECÇÃO DE COLISÃO ---

#include "Colisao.h"

bool verificarColisao(RetanguloComTexturaAnimada &jogador, Retangulo &obstaculo) {
    float esquerdaJogador;
    float direitaJogador;
    float inferiorJogador;
    float superiorJogador;
    float esquerdaObstaculo;
    float direitaObstaculo;
    float inferiorObstaculo;
    float superiorObstaculo;

    jogador.obterCaixaDelimitadora(esquerdaJogador, direitaJogador, inferiorJogador, superiorJogador);
    obstaculo.obterCaixaDelimitadora(esquerdaObstaculo, direitaObstaculo, inferiorObstaculo, superiorObstaculo);

    bool sobreposicaoEixoX = (esquerdaJogador < direitaObstaculo) && (direitaJogador > esquerdaObstaculo);
    bool sobreposicaoEixoY = (inferiorJogador < superiorObstaculo) && (superiorJogador > inferiorObstaculo);

    return sobreposicaoEixoX && sobreposicaoEixoY;
}
