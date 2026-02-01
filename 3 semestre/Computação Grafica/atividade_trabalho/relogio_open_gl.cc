/*
    Computação Gráfica
    Atividade Avaliativa: Relógio com OpenGL
    Código desenvolvido no CodeBox
*/

#include <GL/glut.h>
#include <math.h>

float anguloHora = 0.0f; 
float anguloMinuto = 0.0f; 
float anguloSegundo = 0.0f; 

void desenharRelogio() {
    glColor3f(0.0f, 1.0f, 0.0f); 
    glBegin(GL_LINE_STRIP);
    
    for (int i = 0; i <= 360; i++) {
        float ang = i * M_PI / 180.0f;
        glVertex2f(cos(ang), sin(ang));
    }
    glEnd();
}

void desenharPonteiros() {
    glPushMatrix();
    glRotatef(anguloHora, 0.0f, 0.0f, 1.0f); 
    glColor3f(1.0f, 0.0f, 0.0f); 
    glBegin(GL_QUADS);
    glVertex2f(-0.05f, 0.0f);
    glVertex2f(0.05f, 0.0f);
    glVertex2f(0.05f, 0.6f); 
    glVertex2f(-0.05f, 0.6f);
    glEnd();
    glPopMatrix();

    glPushMatrix();
    glRotatef(anguloMinuto, 0.0f, 0.0f, 1.0f); 
    glColor3f(0.0f, 0.0f, 1.0f); 
    glBegin(GL_QUADS);
    glVertex2f(-0.02f, 0.0f);
    glVertex2f(0.02f, 0.0f);
    glVertex2f(0.02f, 0.8f); 
    glVertex2f(-0.02f, 0.8f);
    glEnd();
    glPopMatrix();

    glPushMatrix();
    glRotatef(anguloSegundo, 0.0f, 0.0f, 1.0f); 
    glColor3f(1.0f, 1.0f, 0.0f); 
    glBegin(GL_QUADS);
    glVertex2f(-0.01f, 0.0f);
    glVertex2f(0.01f, 0.0f);
    glVertex2f(0.01f, 0.9f); 
    glVertex2f(-0.01f, 0.9f);
    glEnd();
    glPopMatrix();
}

void atualizarPonteiros(int valor) {
    anguloSegundo += 6.0f; 
    if (anguloSegundo >= 360.0f) {
        anguloSegundo = 0.0f;
        anguloMinuto += 6.0f; 
        if (anguloMinuto >= 360.0f) {
            anguloMinuto = 0.0f;
            anguloHora += 0.5f; 
            if (anguloHora >= 360.0f) {
                anguloHora = 0.0f;
            }
        }
    }
    glutPostRedisplay(); 
    glutTimerFunc(1000, atualizarPonteiros, 0);
}

void display() {
    glClear(GL_COLOR_BUFFER_BIT); 
    glLoadIdentity(); 

    desenharRelogio();
    desenharPonteiros();

    glutSwapBuffers(); 
}

void initOpenGL() {
    glClearColor(0.0f, 0.0f, 0.0f, 1.0f); 
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    glOrtho(-1.5, 1.5, -1.5, 1.5, -1.0, 1.0); 
    glMatrixMode(GL_MODELVIEW);
}

int main(int argc, char** argv) {
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB);
    glutInitWindowSize(500, 500);
    glutCreateWindow("Relógio com OpenGL");

    initOpenGL();

    glutDisplayFunc(display); 
    glutTimerFunc(1000, atualizarPonteiros, 0); 
    glutMainLoop(); 

    return 0;
}
