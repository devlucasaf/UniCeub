#include <GL/glut.h>
#include <cmath>

float angulo = 0.0f; 

void desenharQuadrado() {
    glClear(GL_COLOR_BUFFER_BIT);

    glPushMatrix();
        
        glTranslatef(0.0f, 0.0f, 0.0f); 
        glRotatef(angulo, 0.0f, 0.0f, -1.0f); 
        glBegin(GL_QUADS);
            glColor3f(0.2f, 0.6f, 1.0f); 
            glVertex2f(-0.5f, -0.5f);
            glVertex2f( 0.5f, -0.5f);
            glVertex2f( 0.5f,  0.5f);
            glVertex2f(-0.5f,  0.5f);
        glEnd();
    glPopMatrix();

    glutSwapBuffers();
}

void atualizar(int valor) {
    angulo -= 2.0f; 
    if (angulo <= -360.0f) {
        angulo = 0.0f;
    }

    glutPostRedisplay(); 
    glutTimerFunc(16, atualizar, 0); 
}

void inicializar() {
    glClearColor(0.0, 0.0, 0.0, 1.0); 
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
