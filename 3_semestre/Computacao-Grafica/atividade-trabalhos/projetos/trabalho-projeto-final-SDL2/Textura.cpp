// --- CARREGAMENTO DE TEXTURAS ---

#include "Textura.h"

#include <SDL2/SDL.h>
#include <SDL2/SDL_image.h>
#include <iostream>

GLuint carregarTextura(const char* caminhoArquivo) {
    SDL_Surface* superficieOriginal = IMG_Load(caminhoArquivo);

    if (!superficieOriginal) {
        std::cerr << "Erro ao carregar a textura " << caminhoArquivo
                    << ": " << IMG_GetError() << "\n";
        return 0;
    }

    SDL_Surface* superficieConvertida = SDL_ConvertSurfaceFormat(superficieOriginal, SDL_PIXELFORMAT_RGBA32, 0);
    SDL_FreeSurface(superficieOriginal);

    if (!superficieConvertida) {
        std::cerr << "Erro ao converter a textura para RGBA32\n";
        return 0;
    }

    GLuint identificadorTextura;
    glGenTextures(1, &identificadorTextura);
    glBindTexture(GL_TEXTURE_2D, identificadorTextura);

    glTexImage2D(
        GL_TEXTURE_2D,
        0,
        GL_RGBA,
        superficieConvertida->w,
        superficieConvertida->h,
        0,
        GL_RGBA,
        GL_UNSIGNED_BYTE,
        superficieConvertida->pixels
    );

    // --- FILTROS BÁSICOS ---
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

    SDL_FreeSurface(superficieConvertida);

    return identificadorTextura;
}
