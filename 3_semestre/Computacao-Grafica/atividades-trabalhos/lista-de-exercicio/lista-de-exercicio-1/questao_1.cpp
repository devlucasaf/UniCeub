#include <GL/glut.h>

void desenho() {
    glClear(GL_COLOR_BUFFER_BIT);

    glBegin(GL_POLYGON);
        glColor3f(237.0 / 256.0, 28.0 / 256.0, 36.0 / 256.0);
        glVertex3f(-5.0, -2.0, 0.0);
        glVertex3f(-3.0, -4.0, 0.0);
        glVertex3f(3.0, -4.0, 0.0);
        glVertex3f(5.0, -2.0, 0.0);
    glEnd();

    glBegin(GL_TRIANGLES);
        glColor3f(195.0 / 256.0, 195.0 / 256.0, 195.0 / 256.0);
        glVertex3f(-3.0, -0.5, 0.0);
        glVertex3f(3.0, -0.5, 0.0);
        glVertex3f(-1.0, 4.0, 0.0);
    glEnd();

    glBegin(GL_QUADS);
        glColor3f(128.0 / 256.0, 64.0 / 256.0, 0.0);
        glVertex3f(-1.2, 4.0, 0.0);
        glVertex3f(-1.2, -2.0, 0.0);
        glVertex3f(-0.8, -2.0, 0.0);
        glVertex3f(-0.8, 4.0, 0.0);
    glEnd();

    glBegin(GL_QUADS);
        glColor3f(63.0 / 256.0, 72.0 / 256.0, 204.0 / 256.0);
        glVertex3f(-1.2, 4.5, 0.0);
        glVertex3f(0.0, 4.5, 0.0);
        glVertex3f(0.0, 4.0, 0.0);
        glVertex3f(-1.2, 4.0, 0.0);
    glEnd();

    glutSwapBuffers();
}

int main(int argc, char** argv) {
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGBA);
    glutInitWindowSize(600, 600);
    glutInitWindowPosition(345, 20);
    glutCreateWindow("Barco");
    glutDisplayFunc(desenho);

    gluOrtho2D(-10, 10, -10, 10);
    glClearColor(255.0/256.0, 244.0/256.0, 208.0/256.0, 1.0);

    glutMainLoop();
    return 0;
}
