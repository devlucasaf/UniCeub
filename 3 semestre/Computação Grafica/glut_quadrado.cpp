#include <GL/glut.h>
#include <stdlib.h>
#include <iostream>

using namespace std;

float rotationAngle = 0.0f;

void Desenhar() {
    glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
    glClear(GL_COLOR_BUFFER_BIT);

    glLoadIdentity(); 
    glRotatef(rotationAngle, 0, 0, 1);

    glColor3f(1,0,0);
    glBegin(GL_QUADS);
        glVertex2f(20,20);
        glVertex2f(-20,20);
        glVertex2f(-20,-20);
        glVertex2f(20,-20);
    glEnd();

    glutSwapBuffers();
}

void Keyboard(unsigned char key, int x, int y) {
    if(key == 27) {
        exit(0); 
    }
}

void ajusta_tela() {
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluOrtho2D(-50,50,-50,50);
    glMatrixMode(GL_MODELVIEW);
}

void anima(int timer) {
    rotationAngle += 2; 
    if(rotationAngle > 360) {
        rotationAngle -= 360;
    }
    glutPostRedisplay();    
    glutTimerFunc(30, anima, 1);
}

int main(int argc, char** argv){
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB);
    glutInitWindowPosition(200,100);
    glutInitWindowSize(800,600);
    glutCreateWindow("TESTE");

    ajusta_tela(); 

    glutDisplayFunc(Desenhar);
    glutKeyboardFunc(Keyboard);
    glutTimerFunc(30, anima, 1);

    glutMainLoop();
    return 0;
}
