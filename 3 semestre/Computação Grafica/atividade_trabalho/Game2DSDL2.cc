/*
Computação Gráfica
Aula: Atividade OpenGL em C++
Atividade em grupo
*/

#include <SDL2/SDL.h>
#include <SDL2/SDL_image.h>
#include <GL/gl.h>
#include <GL/glu.h>
#include <cmath>
#include <iostream>

// =========================
// Configurações gerais
// =========================
const int WINDOW_WIDTH  = 800;
const int WINDOW_HEIGHT = 600;

const float GRAVITY     = -1.8f;   // aceleração "puxando" pra baixo
const float JUMP_SPEED  = 1.2f;    // velocidade inicial do pulo
const float FLOOR_Y     = -0.5f;   // altura do "chão" (coordenadas NDC -1..1)
const float PLAYER_X    = -0.6f;   // posição fixa do player no X
const float OBSTACLE_SPEED = 0.01f; // velocidade do obstáculo indo pra esquerda

// =====================================================================
// Classe simples para carregar textura OpenGL a partir de arquivo SDL2
// =====================================================================
GLuint loadTexture(const char* filePath) {
    SDL_Surface* surface = IMG_Load(filePath);
    if (!surface) {
        std::cerr << "Erro carregando textura " << filePath
                    << ": " << IMG_GetError() << "\n";
        return 0;
    }

    // SDL carrega como BGR(A) em algumas plataformas; vamos converter para RGBA
    SDL_Surface* formatted = SDL_ConvertSurfaceFormat(surface, SDL_PIXELFORMAT_RGBA32, 0);
    SDL_FreeSurface(surface);
    if (!formatted) {
        std::cerr << "Erro convertendo textura para RGBA32\n";
        return 0;
    }

    GLuint texID;
    glGenTextures(1, &texID);
    glBindTexture(GL_TEXTURE_2D, texID);

    glTexImage2D(
        GL_TEXTURE_2D,
        0,
        GL_RGBA,
        formatted->w,
        formatted->h,
        0,
        GL_RGBA,
        GL_UNSIGNED_BYTE,
        formatted->pixels
    );

    // Filtros básicos
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);

    SDL_FreeSurface(formatted);
    return texID;
}

// =====================================================================
// Classe Retangulo
// - desenha um retângulo 2D em coordenadas normalizadas (-1..1)
// - pode ter textura única
// =====================================================================
class Retangulo {
public:
    float x, y;        // centro
    float w, h;        // largura e altura
    GLuint textureID;  // 0 = sem textura
    bool useTexture;

    Retangulo(float cx=0, float cy=0, float cw=0.2f, float ch=0.2f)
        : x(cx), y(cy), w(cw), h(ch), textureID(0), useTexture(false) {}

    void setTexture(GLuint tex) {
        textureID = tex;
        useTexture = (tex != 0);
    }

    // Desenha o retângulo
    void draw() {
        float halfW = w/2.0f;
        float halfH = h/2.0f;

        if (useTexture) {
            glEnable(GL_TEXTURE_2D);
            glBindTexture(GL_TEXTURE_2D, textureID);
        } else {
            glDisable(GL_TEXTURE_2D);
            glColor3f(1.0f, 1.0f, 1.0f); // branco
        }

        glBegin(GL_QUADS);
            if (useTexture) glTexCoord2f(0.0f, 1.0f);
            glVertex2f(x - halfW, y - halfH);

            if (useTexture) glTexCoord2f(1.0f, 1.0f);
            glVertex2f(x + halfW, y - halfH);

            if (useTexture) glTexCoord2f(1.0f, 0.0f);
            glVertex2f(x + halfW, y + halfH);

            if (useTexture) glTexCoord2f(0.0f, 0.0f);
            glVertex2f(x - halfW, y + halfH);
        glEnd();

        if (useTexture) {
            glDisable(GL_TEXTURE_2D);
        }
    }

    // Retorna limites AABB pra colisão
    void getAABB(float &left, float &right, float &bottom, float &top) {
        left   = x - w/2.0f;
        right  = x + w/2.0f;
        bottom = y - h/2.0f;
        top    = y + h/2.0f;
    }
};

// =====================================================================
// Classe RetanguloTexturaAnimado
// - usada pro personagem
// - troca frames de animação em uma spritesheet horizontal
// =====================================================================
class RetanguloTexturaAnimado {
public:
    float x, y;
    float w, h;

    float vy;          // velocidade vertical (pra pulo/gravitacao)

    GLuint textureID;
    int frameCount;    // quantos frames lado a lado
    int currentFrame;
    float frameTimer;  // acumula tempo para trocar frame
    float frameTime;   // tempo entre frames (ex: 0.1s)

    RetanguloTexturaAnimado(float cx=-0.6f, float cy=FLOOR_Y + 0.15f,
                            float cw=0.2f, float ch=0.2f)
        : x(cx), y(cy), w(cw), h(ch),
            vy(0.0f),
            textureID(0),
            frameCount(1),
            currentFrame(0),
            frameTimer(0.0f),
            frameTime(0.1f)
    {}

    void setTexture(GLuint tex, int frames) {
        textureID = tex;
        frameCount = (frames > 0 ? frames : 1);
    }

    void updateAnimation(float dt) {
        frameTimer += dt;
        if (frameTimer >= frameTime) {
            frameTimer = 0.0f;
            currentFrame = (currentFrame + 1) % frameCount;
        }
    }

    // Atualiza física vertical (gravidade/pulo)
    void updatePhysics(float dt) {
        // aplica gravidade
        vy += GRAVITY * dt;

        // atualiza posição
        y += vy * dt;

        // checa chão
        float halfH = h/2.0f;
        float bottom = y - halfH;

        if (bottom <= FLOOR_Y) {
            // encosta no chão
            float diff = FLOOR_Y - bottom;
            y += diff;      // reposiciona exatamente no chão
            vy = 0.0f;      // para
        }
    }

    bool isOnGround() const {
        float halfH = h/2.0f;
        float bottom = y - halfH;
        return (bottom <= FLOOR_Y + 0.0001f);
    }

    void jump() {
        if (isOnGround()) {
            vy = JUMP_SPEED;
        }
    }

    void draw() {
        float halfW = w/2.0f;
        float halfH = h/2.0f;

        glEnable(GL_TEXTURE_2D);
        glBindTexture(GL_TEXTURE_2D, textureID);

        // Calcula coordenadas de textura do frame atual
        float frameWidth = 1.0f / frameCount;
        float u0 = frameWidth * currentFrame;
        float u1 = u0 + frameWidth;

        glBegin(GL_QUADS);
            glTexCoord2f(u0, 1.0f);
            glVertex2f(x - halfW, y - halfH);

            glTexCoord2f(u1, 1.0f);
            glVertex2f(x + halfW, y - halfH);

            glTexCoord2f(u1, 0.0f);
            glVertex2f(x + halfW, y + halfH);

            glTexCoord2f(u0, 0.0f);
            glVertex2f(x - halfW, y + halfH);
        glEnd();

        glDisable(GL_TEXTURE_2D);
    }

    void getAABB(float &left, float &right, float &bottom, float &top) {
        left   = x - w/2.0f;
        right  = x + w/2.0f;
        bottom = y - h/2.0f;
        top    = y + h/2.0f;
    }
};

// =========================
// Função de colisão AABB
// =========================
bool checkCollision(RetanguloTexturaAnimado &a, Retangulo &b) {
    float la, ra, ba, ta;
    float lb, rb, bb, tb;
    a.getAABB(la, ra, ba, ta);
    b.getAABB(lb, rb, bb, tb);

    bool overlapX = (la < rb) && (ra > lb);
    bool overlapY = (ba < tb) && (ta > bb);
    return overlapX && overlapY;
}

// =========================
// Render do chão (linha)
// =========================
void drawFloorLine() {
    glDisable(GL_TEXTURE_2D);
    glColor3f(0.2f, 0.8f, 0.2f); // verde chão

    glBegin(GL_LINES);
        glVertex2f(-1.0f, FLOOR_Y);
        glVertex2f( 1.0f, FLOOR_Y);
    glEnd();
}

// =========================
// FUNÇÃO PRINCIPAL
// =========================
int main(int argc, char* argv[]) {
    // ------------------------
    // Inicialização SDL
    // ------------------------
    if (SDL_Init(SDL_INIT_VIDEO | SDL_INIT_TIMER) != 0) {
        std::cerr << "Erro SDL_Init: " << SDL_GetError() << "\n";
        return 1;
    }

    // habilitar PNG/JPG
    if (!(IMG_Init(IMG_INIT_PNG | IMG_INIT_JPG) & (IMG_INIT_PNG | IMG_INIT_JPG))) {
        std::cerr << "Erro IMG_Init: " << IMG_GetError() << "\n";
        return 1;
    }

    // cria janela com contexto OpenGL
    SDL_Window* window = SDL_CreateWindow(
        "Runner OpenGL",
        SDL_WINDOWPOS_CENTERED, SDL_WINDOWPOS_CENTERED,
        WINDOW_WIDTH, WINDOW_HEIGHT,
        SDL_WINDOW_OPENGL | SDL_WINDOW_SHOWN
    );
    if (!window) {
        std::cerr << "Erro SDL_CreateWindow: " << SDL_GetError() << "\n";
        return 1;
    }

    SDL_GLContext glContext = SDL_GL_CreateContext(window);
    if (!glContext) {
        std::cerr << "Erro SDL_GL_CreateContext: " << SDL_GetError() << "\n";
        return 1;
    }

    SDL_GL_SetSwapInterval(1); // vsync

    // ------------------------
    // Configuração OpenGL 2D simples
    // ------------------------
    glViewport(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    // Usar coordenadas normalizadas (-1..1)
    // Isso já é o padrão se deixarmos identidade no PROJECTION e desenharmos em [-1,1].
    glMatrixMode(GL_MODELVIEW);

    glDisable(GL_DEPTH_TEST);

    // ------------------------
    // Carregar texturas
    // Troque "player.png", "bg.png", "cactus.png" pelos arquivos reais do seu projeto.
    // player.png deve ser uma spritesheet horizontal com N frames.
    // ------------------------
    GLuint texPlayer = loadTexture("player.png");
    GLuint texBG     = loadTexture("bg.png");
    GLuint texObs    = loadTexture("obstaculo.png");

    if (!texPlayer) std::cout << "Aviso: player.png nao carregada. Personagem vai ficar branco.\n";
    if (!texBG)     std::cout << "Aviso: bg.png nao carregada. Background vai ficar color sólido.\n";
    if (!texObs)    std::cout << "Aviso: obstaculo.png nao carregada. Obstaculo vai ficar branco.\n";

    // ------------------------
    // Criar objetos de jogo
    // ------------------------

    // Background: retângulo cobrindo a tela toda (-1,-1) até (1,1) => centro (0,0), tam 2x2
    Retangulo background(0.0f, 0.0f, 2.0f, 2.0f);
    background.setTexture(texBG); // se texBG=0, desenha branco

    // Jogador
    RetanguloTexturaAnimado player(PLAYER_X, FLOOR_Y + 0.15f, 0.2f, 0.2f);
    player.setTexture(texPlayer, /*frames=*/4); // ajuste p/ qtd de frames da spritesheet

    // Obstáculo (ex: cacto)
    Retangulo obstacle(0.8f, FLOOR_Y + 0.1f, 0.15f, 0.2f);
    obstacle.setTexture(texObs); // se 0, branco

    // ------------------------
    // Loop principal
    // ------------------------
    bool rodando = true;
    bool gameOver = false;
    Uint64 lastTicks = SDL_GetPerformanceCounter();
    Uint64 freq = SDL_GetPerformanceFrequency();

    while (rodando) {
        // ------ calcular dt ------
        Uint64 now = SDL_GetPerformanceCounter();
        double dt = (double)(now - lastTicks) / (double)freq;
        lastTicks = now;

        // ------ eventos ------
        SDL_Event e;
        while (SDL_PollEvent(&e)) {
            if (e.type == SDL_QUIT)
                rodando = false;

            if (e.type == SDL_KEYDOWN && !e.key.repeat) {
                if (e.key.keysym.sym == SDLK_ESCAPE) {
                    rodando = false;
                }
                if (!gameOver && e.key.keysym.sym == SDLK_UP) {
                    player.jump();
                }
            }
        }

        if (!gameOver) {
            // ------ atualizar física player ------
            player.updatePhysics((float)dt);
            player.updateAnimation((float)dt);

            // ------ mover obstáculo ------
            obstacle.x -= OBSTACLE_SPEED;

            // se obstáculo saiu da tela à esquerda, reaparece à direita
            if (obstacle.x < -1.2f) {
                obstacle.x = 1.2f;
            }

            // ------ checar colisão ------
            if (checkCollision(player, obstacle)) {
                std::cout << "GAME OVER!\n";
                gameOver = true;
            }
        }

        // ------ desenhar cena ------
        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        glClear(GL_COLOR_BUFFER_BIT);

        glLoadIdentity();

        // desenhar fundo
        if (background.textureID != 0) {
            background.draw();
        } else {
            // fallback: cor azulada
            glDisable(GL_TEXTURE_2D);
            glColor3f(0.3f, 0.5f, 0.9f);
            glBegin(GL_QUADS);
                glVertex2f(-1.0f, -1.0f);
                glVertex2f( 1.0f, -1.0f);
                glVertex2f( 1.0f,  1.0f);
                glVertex2f(-1.0f,  1.0f);
            glEnd();
        }

        // chão
        drawFloorLine();

        // obstáculo
        obstacle.draw();

        // player
        player.draw();

        SDL_GL_SwapWindow(window);
    }

    // ------------------------
    // Limpeza
    // ------------------------
    glDeleteTextures(1, &texPlayer);
    glDeleteTextures(1, &texBG);
    glDeleteTextures(1, &texObs);

    SDL_GL_DeleteContext(glContext);
    SDL_DestroyWindow(window);
    IMG_Quit();
    SDL_Quit();

    return 0;
}
