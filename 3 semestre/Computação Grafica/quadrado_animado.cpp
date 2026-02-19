#include <stdlib.h>
#include <GL/glut.h>
#include <math.h>
#include <iostream>

using namespace std;

float x_scale = 1.0f;
float x_position = 0.0f;   
int state = -1;

void Desenha() {
    glClear(GL_COLOR_BUFFER_BIT);
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();

    glTranslatef(x_position, 0.0f, 0.0f);   
    glScalef(x_scale, x_scale, 1.0f);

    glColor3f(0, 0.5, 0);

    glBegin(GL_QUADS);
        glVertex2f(-10, -10);
        glVertex2f(10, -10);
        glVertex2f(10, 10);
        glVertex2f(-10, 10);
    glEnd();

    glFlush();
}

void AlteraTamanhoJanela(GLsizei w, GLsizei h) {
    GLsizei largura, altura;

    if (h == 0) {
        h = 1;
    }

    largura = w;
    altura = h;

    glViewport(0, 0, largura, altura);

    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();

    if (largura <= altura) {
        gluOrtho2D(-40.0f, 40.0f, -40.0f * altura / largura, 40.0f * altura / largura);
    }

    else {
        gluOrtho2D(-40.0f * largura / altura, 40.0f * largura / altura, -40.0f, 40.0f);
    }
}

void Teclado(unsigned char key, int x, int y) {
    glutPostRedisplay();

    if (key == 'w') {
        x_scale += 0.1f;
    }

    if (key == 's') {
        x_scale -= 0.1f;
    }
}

void Inicializa(void) {
    glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
}

void timer(int) {
    glutPostRedisplay();
    glutTimerFunc(1000 / 60, timer, 0);

    cout << x_scale << '\t';

    switch (state) {
        case 1: {
            cout << "TESTE1" << '\t';

            if (x_position < 20) {
                x_position += 0.3f;
            }

            else {
                state = -1;
            }

            break;
        }

        case -1: {
            if (x_position > -20) {
                x_position -= 0.3f;
            } 
            
            else {
                state = 1;
            }

            break; 
        }
    }
}

int main(int argc, char** argv) {
    glutInit(&argc, argv);  
    glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
    glutInitWindowPosition(5, 5);
    glutInitWindowSize(450, 450);
    glutCreateWindow("Quadrado animado com escala e movimento");

    glutDisplayFunc(Desenha);
    glutReshapeFunc(AlteraTamanhoJanela);
    glutKeyboardFunc(Teclado);
    glutTimerFunc(0, timer, 0);

    Inicializa();
    glutMainLoop();
    
    return 0;
}
