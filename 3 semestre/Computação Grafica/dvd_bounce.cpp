#include <GL/glut.h>
#include <SOIL/SOIL.h>

float xPos = 0.0f;
float yPos = 0.0f;
float xSpeed = 0.01f;
float ySpeed = 0.015f;
float size = 0.3f;
GLuint texture;

void display() {
    glClear(GL_COLOR_BUFFER_BIT);
    glEnable(GL_TEXTURE_2D);
    glBindTexture(GL_TEXTURE_2D, texture);

    glBegin(GL_QUADS);
        glTexCoord2f(0.0f, 0.0f); glVertex2f(xPos, yPos);
        glTexCoord2f(1.0f, 0.0f); glVertex2f(xPos + size, yPos);
        glTexCoord2f(1.0f, 1.0f); glVertex2f(xPos + size, yPos + size);
        glTexCoord2f(0.0f, 1.0f); glVertex2f(xPos, yPos + size);
    glEnd();

    glDisable(GL_TEXTURE_2D);
    glutSwapBuffers();
}

void update(int value) {
    xPos += xSpeed;
    yPos += ySpeed;

    if (xPos + size > 1.0f || xPos < -1.0f) {
        xSpeed = -xSpeed;
    }
    if (yPos + size > 1.0f || yPos < -1.0f) {
        ySpeed = -ySpeed;
    }

    glutPostRedisplay();
    glutTimerFunc(16, update, 0);
}

void initTexture() {
    texture = SOIL_load_OGL_texture("dvd_logo.png", SOIL_LOAD_AUTO, SOIL_CREATE_NEW_ID, SOIL_FLAG_INVERT_Y);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
    glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
}

int main(int argc, char** argv) {
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB);
    glutInitWindowSize(600, 600);
    glutCreateWindow("DVD Bounce - OpenGL");
    glClearColor(0.0f, 0.0f, 0.0f, 1.0f);

    initTexture();
    glutDisplayFunc(display);
    glutTimerFunc(0, update, 0);
    glutMainLoop();
    return 0;
}
