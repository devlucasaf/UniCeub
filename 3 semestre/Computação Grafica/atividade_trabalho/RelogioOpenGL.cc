// Atividade de criação de um relógio simples usando OpenGL

#include <GL/glut.h>
#include <math.h>

float anguloHora = 0.0f; // Posição do ponteiro das horas
float anguloMinuto = 0.0f; // Posição do ponteiro dos minutos
float anguloSegundo = 0.0f; // Posição do ponteiro dos segundos

// Função para desenhar o círculo (relógio)
void desenharRelogio() {
    glColor3f(0.0f, 1.0f, 0.0f); // Cor do círculo (verde)
    glBegin(GL_LINE_STRIP);
    for (int i = 0; i <= 360; i++) {
        float ang = i * M_PI / 180.0f;
        glVertex2f(cos(ang), sin(ang));
    }
    glEnd();
}

// Função para desenhar os ponteiros
void desenharPonteiros() {
    // Ponteiro das horas
    glPushMatrix();
    glRotatef(anguloHora, 0.0f, 0.0f, 1.0f); // Gira o ponteiro
    glColor3f(1.0f, 0.0f, 0.0f); // Cor do ponteiro das horas (vermelho)
    glBegin(GL_QUADS);
    glVertex2f(-0.05f, 0.0f);
    glVertex2f(0.05f, 0.0f);
    glVertex2f(0.05f, 0.6f); // Comprimento do ponteiro das horas
    glVertex2f(-0.05f, 0.6f);
    glEnd();
    glPopMatrix();

    // Ponteiro dos minutos
    glPushMatrix();
    glRotatef(anguloMinuto, 0.0f, 0.0f, 1.0f); // Gira o ponteiro
    glColor3f(0.0f, 0.0f, 1.0f); // Cor do ponteiro dos minutos (azul)
    glBegin(GL_QUADS);
    glVertex2f(-0.02f, 0.0f);
    glVertex2f(0.02f, 0.0f);
    glVertex2f(0.02f, 0.8f); // Comprimento do ponteiro dos minutos
    glVertex2f(-0.02f, 0.8f);
    glEnd();
    glPopMatrix();

    // Ponteiro dos segundos
    glPushMatrix();
    glRotatef(anguloSegundo, 0.0f, 0.0f, 1.0f); // Gira o ponteiro
    glColor3f(1.0f, 1.0f, 0.0f); // Cor do ponteiro dos segundos (amarelo)
    glBegin(GL_QUADS);
    glVertex2f(-0.01f, 0.0f);
    glVertex2f(0.01f, 0.0f);
    glVertex2f(0.01f, 0.9f); // Comprimento do ponteiro dos segundos
    glVertex2f(-0.01f, 0.9f);
    glEnd();
    glPopMatrix();
}

// Função para atualizar os ângulos dos ponteiros
void atualizarPonteiros(int valor) {
    anguloSegundo += 6.0f; // O ponteiro dos segundos gira 6° por intervalo
    if (anguloSegundo >= 360.0f) {
        anguloSegundo = 0.0f;
        anguloMinuto += 6.0f; // O ponteiro dos minutos gira 6° a cada 60 segundos
        if (anguloMinuto >= 360.0f) {
            anguloMinuto = 0.0f;
            anguloHora += 0.5f; // O ponteiro das horas gira 0.5° a cada 60 minutos
            if (anguloHora >= 360.0f) {
                anguloHora = 0.0f;
            }
        }
    }
    glutPostRedisplay(); // Redesenha a cena
    glutTimerFunc(1000, atualizarPonteiros, 0); // Chama a função novamente após 1 segundo
}

// Função para desenhar a cena
void display() {
    glClear(GL_COLOR_BUFFER_BIT); // Limpa a tela
    glLoadIdentity(); // Reinicia a matriz de transformações

    // Desenha o relógio e os ponteiros
    desenharRelogio();
    desenharPonteiros();

    glutSwapBuffers(); // Troca os buffers
}

// Função para configurar a janela
void initOpenGL() {
    glClearColor(0.0f, 0.0f, 0.0f, 1.0f); // Cor de fundo preta
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    glOrtho(-1.5, 1.5, -1.5, 1.5, -1.0, 1.0); // Define a área de visualização
    glMatrixMode(GL_MODELVIEW);
}

int main(int argc, char** argv) {
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB);
    glutInitWindowSize(500, 500);
    glutCreateWindow("Relógio com OpenGL");

    initOpenGL();

    glutDisplayFunc(display); // Função de renderização
    glutTimerFunc(1000, atualizarPonteiros, 0); // Inicia a atualização dos ponteiros
    glutMainLoop(); // Inicia o loop de eventos do GLUT

    return 0;
}
