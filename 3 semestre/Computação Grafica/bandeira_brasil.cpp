/*
    Computação Gráfica
    Código C++ - Bandeira do Brasil
    Código desenvolvido a fins de estudos. Código não foi apresentado em sala!
    Utiliza o OpenGL
*/

#include <stdlib.h>
#include <GL/glut.h>
#include <math.h>

void Desenha(void) {    
    glClear(GL_COLOR_BUFFER_BIT);

    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();
    glScalef(2,2,1);

    // Fundo verde
    glColor3f(0,0.5,0);
    glBegin(GL_QUADS);
        glVertex2f(-40,-20);
        glVertex2f(40,-20);
        glVertex2f(40,20);
        glVertex2f(-40,20);
    glEnd();

    // Losango amarelo
    glColor3f(1,1,0);
    glBegin(GL_POLYGON);
        glVertex2f(0,-15);
        glVertex2f(35,0);
        glVertex2f(0,15);
        glVertex2f(-35,0);
    glEnd();

    // Círculo azul
    float theta, raio = 10;
    glColor3f(0,0,1);
    glBegin(GL_POLYGON);
        for(int i = 0; i < 360; i++){
            theta = i * M_PI / 180;
            glVertex2f(raio * cos(theta), raio * sin(theta));
        }
    glEnd();

    glFlush();
}

void AlteraTamanhoJanela(GLsizei w, GLsizei h) {
    GLsizei largura, altura;

    if(h == 0) {
        h = 1;
    }

    largura = w;
    altura = h;

    glViewport(0, 0, largura, altura);

    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();

    if (largura <= altura) {
        gluOrtho2D (-40.0f, 40.0f, -40.0f*altura/largura, 40.0f*altura/largura);
    }
    else {
        gluOrtho2D (-40.0f*largura/altura, 40.0f*largura/altura, -40.0f, 40.0f);
    }
}

void Inicializa (void) {   
    glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
}

int main(void) {
    glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB); 
    glutInitWindowPosition(5,5); 
    glutInitWindowSize(450,450); 
    glutCreateWindow("Bandeira do Brasil");

    glutDisplayFunc(Desenha);
    glutReshapeFunc(AlteraTamanhoJanela);

    Inicializa();
    glutMainLoop();

    return 0;
}
