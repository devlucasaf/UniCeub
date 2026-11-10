// --- DETECÇÃO DE COLISÃO ---

#pragma once

#include "Retangulo.h"
#include "RetanguloComTexturaAnimada.h"

// --- VERIFICA SE AS CAIXAS DELIMITADORAS DO JOGADOR E DO OBSTÁCULO SE SOBREPÕEM ---
bool verificarColisao(RetanguloComTexturaAnimada &jogador, Retangulo &obstaculo);
