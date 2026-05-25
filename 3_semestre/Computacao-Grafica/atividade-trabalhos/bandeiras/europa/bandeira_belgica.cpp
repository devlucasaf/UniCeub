#include <GL/glut.h>

void Desenha(void) {    
    glClear(GL_COLOR_BUFFER_BIT);

    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();

    float larguraFaixa = 80.0f / 3.0f;
    float x1 = -40.0f + larguraFaixa;
    float x2 = -40.0f + 2.0f * larguraFaixa;

    glColor3f(0.0f, 0.0f, 0.0f);
    glBegin(GL_QUADS);
        glVertex2f(-40.0f, -40.0f);
        glVertex2f(x1, -40.0f);
        glVertex2f(x1, 40.0f);
        glVertex2f(-40.0f, 40.0f);
    glEnd();

    glColor3f(1.0f, 1.0f, 0.0f);
    glBegin(GL_QUADS);
        glVertex2f(x1, -40.0f);
        glVertex2f(x2, -40.0f);
        glVertex2f(x2, 40.0f);
        glVertex2f(x1, 40.0f);
    glEnd();

    glColor3f(1.0f, 0.0f, 0.0f);
    glBegin(GL_QUADS);
        glVertex2f(x2, -40.0f);
        glVertex2f(40.0f, -40.0f);
        glVertex2f(40.0f, 40.0f);
        glVertex2f(x2, 40.0f);
    glEnd();

    glFlush();
}

void AlteraTamanhoJanela(GLsizei w, GLsizei h) {
    glViewport(0, 0, w, h);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluOrtho2D(-40.0f, 40.0f, -40.0f, 40.0f);
}

void Inicializa(void) {   
    glClearColor(1.0f, 1.0f, 1.0f, 1.0f); // fundo branco (não será visto, pois as faixas cobrem tudo)
}

int main(int argc, char** argv) {
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB); 
    glutInitWindowPosition(5, 5); 
    glutInitWindowSize(450, 450); 
    glutCreateWindow("Bandeira da Bélgica");

    glutDisplayFunc(Desenha);
    glutReshapeFunc(AlteraTamanhoJanela);

    Inicializa();
    glutMainLoop();

    return 0;
}