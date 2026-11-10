// --- CARREGAMENTO DE TEXTURAS ---

#pragma once

#include <SDL2/SDL_opengl.h>

// --- CARREGA UMA IMAGEM E DEVOLVE O IDENTIFICADOR DA TEXTURA ---
GLuint carregarTextura(const char* caminhoArquivo);
