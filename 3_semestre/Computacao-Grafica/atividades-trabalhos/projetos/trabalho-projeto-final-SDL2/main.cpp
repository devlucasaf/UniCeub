// --- COMPUTAÇÃO GRÁFICA ---
// --- AULA: ATIVIDADE OPENGL EM C++ ---
// --- ATIVIDADE EM GRUPO ---

#include <SDL2/SDL.h>
#include <SDL2/SDL_image.h>
#include <SDL2/SDL_opengl.h>
#include <iostream>

#include "Configuracoes.h"
#include "Textura.h"
#include "Retangulo.h"
#include "RetanguloComTexturaAnimada.h"
#include "Colisao.h"
#include "Cenario.h"

int main(int argc, char* argv[]) {
    (void)argc;
    (void)argv;

    // --- INICIALIZAÇÃO DA SDL ---
    if (SDL_Init(SDL_INIT_VIDEO | SDL_INIT_TIMER) != 0) {
        std::cerr << "Erro em SDL_Init: " << SDL_GetError() << "\n";
        return 1;
    }

    if (!(IMG_Init(IMG_INIT_PNG | IMG_INIT_JPG) & (IMG_INIT_PNG | IMG_INIT_JPG))) {
        std::cerr << "Erro em IMG_Init: " << IMG_GetError() << "\n";
        SDL_Quit();
        return 1;
    }

    SDL_Window* janela = SDL_CreateWindow(
        "Jogo de Corrida em OpenGL",
        SDL_WINDOWPOS_CENTERED, SDL_WINDOWPOS_CENTERED,
        LARGURA_JANELA, ALTURA_JANELA,
        SDL_WINDOW_OPENGL | SDL_WINDOW_SHOWN
    );

    if (!janela) {
        std::cerr << "Erro em SDL_CreateWindow: " << SDL_GetError() << "\n";
        IMG_Quit();
        SDL_Quit();
        return 1;
    }

    SDL_GLContext contextoOpenGL = SDL_GL_CreateContext(janela);

    if (!contextoOpenGL) {
        std::cerr << "Erro em SDL_GL_CreateContext: " << SDL_GetError() << "\n";
        SDL_DestroyWindow(janela);
        IMG_Quit();
        SDL_Quit();
        return 1;
    }

    // --- SINCRONIZAÇÃO VERTICAL ---
    SDL_GL_SetSwapInterval(1);

    // --- CONFIGURAÇÃO DO OPENGL ---
    glViewport(0, 0, LARGURA_JANELA, ALTURA_JANELA);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();

    glMatrixMode(GL_MODELVIEW);

    glDisable(GL_DEPTH_TEST);

    // --- TRANSPARÊNCIA DAS IMAGENS PNG ---
    glEnable(GL_BLEND);
    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

    // --- CARREGAMENTO DAS TEXTURAS ---
    GLuint texturaJogador   = carregarTextura("player.png");
    GLuint texturaFundo     = carregarTextura("bg.png");
    GLuint texturaObstaculo = carregarTextura("obstaculo.png");

    if (!texturaJogador) {
        std::cout << "Aviso: player.png não foi carregada. O personagem vai ficar branco.\n";
    }

    if (!texturaFundo) {
        std::cout << "Aviso: bg.png não foi carregada. O fundo vai ficar com cor sólida.\n";
    }

    if (!texturaObstaculo) {
        std::cout << "Aviso: obstaculo.png não foi carregada. O obstáculo vai ficar branco.\n";
    }

    // --- OBJETOS DO JOGO ---
    Retangulo fundo(0.0f, 0.0f, 2.0f, 2.0f);
    fundo.definirTextura(texturaFundo);

    RetanguloComTexturaAnimada jogador(POSICAO_X_JOGADOR, POSICAO_Y_CHAO + 0.15f, 0.2f, 0.2f);
    jogador.definirTextura(texturaJogador, /* quantidadeQuadros = */ 4);

    Retangulo obstaculo(0.8f, POSICAO_Y_CHAO + 0.1f, 0.15f, 0.2f);
    obstaculo.definirTextura(texturaObstaculo);

    // --- ESTADO DO JOGO ---
    bool jogoEmExecucao = true;
    bool fimDeJogo = false;

    Uint64 contadorAnterior   = SDL_GetPerformanceCounter();
    Uint64 frequenciaContador = SDL_GetPerformanceFrequency();

    // --- LAÇO PRINCIPAL ---
    while (jogoEmExecucao) {
        Uint64 contadorAtual = SDL_GetPerformanceCounter();
        double tempoDecorrido = (double)(contadorAtual - contadorAnterior) / (double)frequenciaContador;
        contadorAnterior = contadorAtual;

        // --- TRATAMENTO DE EVENTOS ---
        SDL_Event evento;

        while (SDL_PollEvent(&evento)) {
            if (evento.type == SDL_QUIT) {
                jogoEmExecucao = false;
            }

            if (evento.type == SDL_KEYDOWN && !evento.key.repeat) {
                if (evento.key.keysym.sym == SDLK_ESCAPE) {
                    jogoEmExecucao = false;
                }

                if (!fimDeJogo && evento.key.keysym.sym == SDLK_UP) {
                    jogador.pular();
                }
            }
        }

        // --- ATUALIZAÇÃO DA LÓGICA ---
        if (!fimDeJogo) {
            jogador.atualizarFisica((float)tempoDecorrido);
            jogador.atualizarAnimacao((float)tempoDecorrido);

            obstaculo.centroX -= VELOCIDADE_OBSTACULO * (float)tempoDecorrido;

            if (obstaculo.centroX < LIMITE_ESQUERDO_OBSTACULO) {
                obstaculo.centroX = POSICAO_REINICIO_OBSTACULO;
            }

            if (verificarColisao(jogador, obstaculo)) {
                std::cout << "FIM DE JOGO!\n";
                fimDeJogo = true;
            }
        }

        // --- DESENHO DA CENA ---
        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        glClear(GL_COLOR_BUFFER_BIT);

        glLoadIdentity();

        if (fundo.identificadorTextura != 0) {
            fundo.desenhar();
        } else {
            desenharFundoCorSolida();
        }

        desenharLinhaChao();

        obstaculo.desenhar();

        jogador.desenhar();

        SDL_GL_SwapWindow(janela);
    }

    // --- LIBERAÇÃO DE RECURSOS ---
    glDeleteTextures(1, &texturaJogador);
    glDeleteTextures(1, &texturaFundo);
    glDeleteTextures(1, &texturaObstaculo);

    SDL_GL_DeleteContext(contextoOpenGL);
    SDL_DestroyWindow(janela);
    IMG_Quit();
    SDL_Quit();

    return 0;
}
