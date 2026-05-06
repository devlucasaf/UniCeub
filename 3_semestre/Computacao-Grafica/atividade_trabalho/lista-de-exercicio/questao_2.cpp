#include <GL/glut.h>
#include <math.h>

void desenho() {
    glClear(GL_COLOR_BUFFER_BIT);

    float raio = 4.0;
    int num_segmentos = 50;

    glBegin(GL_TRIANGLE_FAN);
        glColor3f(0.0/256.0, 0.0/256.0, 51.0/256.0);
        glVertex2f(0.0, 0.0);
        for (int i = 0; i <= num_segmentos; ++i) {
            float angulo = i * 2.0f * 3.14159f / num_segmentos;
            float x = raio * cos(angulo);
            float y = raio * sin(angulo);
            glVertex2f(x, y);
        }
    glEnd();

    raio = 3.0;
    num_segmentos = 50;

    glBegin(GL_TRIANGLE_FAN);
        glColor3f(0.0/256.0, 0.0/256.0, 153.0/256.0);
        glVertex2f(0.0, 0.0);
        for (int i = 0; i <= num_segmentos; ++i) {
            float angulo = i * 2.0f * 3.14159f / num_segmentos;
            float x = raio * cos(angulo);
            float y = raio * sin(angulo);
            glVertex2f(x, y);
        }
    glEnd();

    raio = 2.0;
    num_segmentos = 50;

    glBegin(GL_TRIANGLE_FAN);
        glColor3f(0.0/256.0, 0.0/256.0, 51.0/256.0);
        glVertex2f(0.0, 0.0);
        for (int i = 0; i <= num_segmentos; ++i) {
            float angulo = i * 2.0f * 3.14159f / num_segmentos;
            float x = raio * cos(angulo);
            float y = raio * sin(angulo);
            glVertex2f(x, y);
        }
    glEnd();

    raio = 1.0;
    num_segmentos = 50;

    glBegin(GL_TRIANGLE_FAN);
        glColor3f(1.0, 1.0, 1.0);
        glVertex2f(0.0, 0.0);
        for (int i = 0; i <= num_segmentos; ++i) {
            float angulo = i * 2.0f * 3.14159f / num_segmentos;
            float x = raio * cos(angulo);
            float y = raio * sin(angulo);
            glVertex2f(x, y);
        }
    glEnd();

    glFlush();
}

int main(int argc, char** argv) {
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_SINGLE | GLUT_RGBA);
    glutInitWindowSize(600, 600);
    glutInitWindowPosition(345, 20);
    glutCreateWindow("mama");
    glutDisplayFunc(desenho);
    gluOrtho2D(-7, 7, -7, 7);
    glClearColor(1.0, 1.0, 1.0, 1.0);
    glutMainLoop();
    return 0;
}
