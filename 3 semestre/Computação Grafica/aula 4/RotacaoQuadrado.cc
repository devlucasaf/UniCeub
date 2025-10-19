#include <GL/glut.h>
#include <cmath>

float angulo = 0.0f; // Ângulo de rotação

void desenharQuadrado() {
    glClear(GL_COLOR_BUFFER_BIT);

    glPushMatrix();
        // Aplica a rotação em torno do centro do quadrado
        glTranslatef(0.0f, 0.0f, 0.0f); // Move para o centro (origem)
        glRotatef(angulo, 0.0f, 0.0f, -1.0f); // Rotação sentido horário
        glBegin(GL_QUADS);
            glColor3f(0.2f, 0.6f, 1.0f); // Azul claro
            glVertex2f(-0.5f, -0.5f);
            glVertex2f( 0.5f, -0.5f);
            glVertex2f( 0.5f,  0.5f);
            glVertex2f(-0.5f,  0.5f);
        glEnd();
    glPopMatrix();

    glutSwapBuffers();
}

void atualizar(int valor) {
    angulo -= 2.0f; // diminui o ângulo (sentido horário)
    if (angulo <= -360.0f)
        angulo = 0.0f;

    glutPostRedisplay(); // Redesenha
    glutTimerFunc(16, atualizar, 0); // Chama novamente em 16 ms (~60 FPS)
}

void inicializar() {
    glClearColor(0.0, 0.0, 0.0, 1.0); // Fundo preto
}

int main(int argc, char** argv) {
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB);
    glutInitWindowSize(500, 500);
    glutCreateWindow("Rotação de Quadrado - Sentido Horário");

    inicializar();

    glutDisplayFunc(desenharQuadrado);
    glutTimerFunc(0, atualizar, 0);

    glutMainLoop();
    return 0;
}
