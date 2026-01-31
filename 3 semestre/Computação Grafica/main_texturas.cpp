#include <GL/glut.h>

float lado = 40;

void quadrado(float SIZE) {
    glColor3f(1.0, 1.0, 1.0);
    glBegin(GL_QUADS);
        glVertex3f(-SIZE, -SIZE, 0.0);
        glVertex3f(SIZE, -SIZE, 0.0);
        glVertex3f(SIZE, SIZE, 0.0);
        glVertex3f(-SIZE, SIZE, 0.0);
    glEnd();
}

void desenho() {
    glClearColor(0,0,0,0);
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    quadrado(40);
    glFlush();
}

void ajuste(int w, int h) {
    if(h == 0) h = 1;
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluPerspective(45.0, (float)w/(float)h, 0.4, 500.0);
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();
    gluLookAt(0,0,200, 0,0,0, 0,1,0);
}

int main(int argc, char** argv) {
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB | GLUT_DEPTH);
    glutInitWindowPosition(200,100);
    glutInitWindowSize(800,800);
    glutCreateWindow("3D");
    glEnable(GL_DEPTH_TEST);
    glutDisplayFunc(desenho);
    glutReshapeFunc(ajuste);
    glutMainLoop();
}
