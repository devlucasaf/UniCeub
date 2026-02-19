#include <GL/glut.h>
#include <cmath>

float posY = 0.0f;
float moveX = 0.01f;
float angle = 0.0f;

void quadradoSimples() {
    glColor3f(0.0f, 0.0f, 1.0f);

    glBegin(GL_QUADS);
        glVertex2f(-0.5f, -0.5f);
        glVertex2f( 0.5f, -0.5f);
        glVertex2f( 0.5f,  0.5f);
        glVertex2f(-0.5f,  0.5f);
    glEnd();
}

void simbolo() {
    glColor3f(1.0f, 1.0f, 0.0f);

    glBegin(GL_TRIANGLES);
        glVertex2f( 0.0f,  0.6f);
        glVertex2f(-0.6f, -0.6f);
        glVertex2f( 0.6f, -0.6f);
    glEnd();
}

, posY, 0.0f);

        glBegin(GL_QUADS);
            glVertex2f(-0.2f, -0.2f);
            glVertex2f( 0.2f, -0.2f);
            glVertex2f( 0.2f,  0.2f);
            glVertex2f(-0.2f,  0.2f);
        glEnd();
    glPopMatrix();
}

void quadradoRoxoMovendo() {
    static float x = -1.0f;

    glColor3f(0.5f, 0.0f, 0.5f);

    glPushMatrix();
        glTranslatef(x, -0.5f, 0.0f);

        glBegin(GL_QUADS);
            glVertex2f(-0.1f, -0.1f);
            glVertex2f( 0.1f, -0.1f);
            f);
        glEnd();
    glPopMatrix();

    x += moveX;

    if (x > 1.0f || x < -1.0f) {
        moveX = -moveX;
    }
}

void circuloGirando() {
    glPushMatrix();
        glRotatef(angle, 0.0f, 0.0f, 1.0f);

        int numSegments = 100;

        glBegin(GL_TRIANGLE_FAN);
            for (int i = 0; i <= numSegments; i++) {
                float t = 2.0f * M_PI * i / numSegments;

                glColor3f(
                    (sin(t) + 1.0f) / 2.0f,
                    (cos(t) + 1.0f) / 2.0f,
                    0.5f
                );

                glVertex2f(cos(t) * 0.3f, sin(t) * 0.3f);
            }
        glEnd();
    glPopMatrix();

    angle += 1.0f;
}

void display() {
    glClear(GL_COLOR_BUFFER_BIT);

    quadradoSimples();
    simbolo();
    desenhaQuadradoVermelho();
    quadradoRoxoMovendo();
    circuloGirando();

    glutSwapBuffers();
}

void idle() {
    glutPostRedisplay();
}

void keyBoard(unsigned char key, int, int) {
    if (key == 32) { // barra de espaço
        posY += 0.05f;
    }
}

int main(int argc, char** argv) {
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB);
    glutInitWindowSize(500, 500);
    glutCreateWindow("Lista de Exercícios OpenGL");

    glutDisplayFunc(display);
    glutIdleFunc(idle);
    glutKeyboardFunc(keyBoard);

    glClearColor(1.0f, 1.0f, 1.0f, 1.0f);

    glutMainLoop();
    return 0;
}
