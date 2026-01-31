#include <GL/glut.h>

float lado = 40;

void cubo() {
    glColor3f(1,0,0);
    glBegin(GL_QUADS);
        glVertex3f(lado,lado,lado);
        glVertex3f(-lado,lado,lado);
        glVertex3f(-lado,-lado,lado);
        glVertex3f(lado,-lado,lado);
    glEnd();
    
    glColor3f(0,1,0);
    glBegin(GL_QUADS);
        glVertex3f(lado,lado,-lado);
        glVertex3f(lado,-lado,-lado);
        glVertex3f(-lado,-lado,-lado);
        glVertex3f(-lado,lado,-lado);
    glEnd();
    
    glColor3f(0,0,1);
    glBegin(GL_QUADS); 
        glVertex3f(-lado,lado,lado);
        glVertex3f(-lado,lado,-lado);
        glVertex3f(-lado,-lado,-lado);
        glVertex3f(-lado,-lado,lado);
    glEnd();
    
    glColor3f(1,1,0);
    glBegin(GL_QUADS); 
        glVertex3f(lado,lado,lado);
        glVertex3f(lado,-lado,lado);
        glVertex3f(lado,-lado,-lado);
        glVertex3f(lado,lado,-lado);
    glEnd();
    
    glColor3f(0,1,1);
    glBegin(GL_QUADS); 
        glVertex3f(-lado,lado,-lado);
        glVertex3f(-lado,lado,lado);
        glVertex3f(lado,lado,lado);
        glVertex3f(lado,lado,-lado);
    glEnd();
    
    glColor3f(0.5f,0.5f,0);
    glBegin(GL_QUADS); 
        glVertex3f(-lado,-lado,-lado);
        glVertex3f(lado,-lado,-lado);
        glVertex3f(lado,-lado,lado);
        glVertex3f(-lado,-lado,lado);
    glEnd();
}

void desenho() {
    glClearColor(0,0,0,0);
    glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
    
    glRotatef(2,0,1,0); 
    
    cubo(); 
    
    glutSwapBuffers(); 
}

void ajuste(int w, int h) {
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    
    gluPerspective(45,(float)w/h, 0.4, 500);
    
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();
    
    gluLookAt(0,50,200,
            0,0,0,
            0,1,0);
}

void anima(int value) {
    glutPostRedisplay();
    glutTimerFunc(30,anima,1);
}

void initLight() {
    glEnable(GL_LIGHTING);
    glEnable(GL_LIGHT0);
    glEnable(GL_LIGHT1);
    glEnable(GL_COLOR_MATERIAL);
    
    float luzAmbiente[4] = {0.1,0.1,0.1,1};
    float luzDifusa[4] = {0.7,0.7,0.7,1};
    float luzEspecular[4] = {1,1,1,1};
    float posicaoLuz0[4] = {500,20,0,1};
    float posicaoLuz1[4] = {-500,20,0,1};
    float especularidade[4] = {1,1,1,1};
    int especMaterial = 128;
    
    glMaterialfv(GL_FRONT, GL_SPECULAR, especularidade);
    glMaterialf(GL_FRONT, GL_SHININESS, especMaterial);
    
    glLightModelfv(GL_LIGHT_MODEL_AMBIENT, luzAmbiente);
    
    glLightfv(GL_LIGHT0, GL_AMBIENT, luzAmbiente);
    glLightfv(GL_LIGHT0, GL_DIFFUSE, luzDifusa);
    glLightfv(GL_LIGHT0, GL_SPECULAR, luzEspecular);
    glLightfv(GL_LIGHT0, GL_POSITION, posicaoLuz0);
    
    glLightfv(GL_LIGHT1, GL_AMBIENT, luzAmbiente);
    glLightfv(GL_LIGHT1, GL_DIFFUSE, luzDifusa);
    glLightfv(GL_LIGHT1, GL_SPECULAR, luzEspecular);
    glLightfv(GL_LIGHT1, GL_POSITION, posicaoLuz1);
    
    glEnable(GL_DEPTH_TEST);
}

int main(int argc, char** argv) {
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_DOUBLE | GLUT_RGB | GLUT_DEPTH);
    glutInitWindowPosition(200,100);
    glutInitWindowSize(800,800);
    glutCreateWindow("Iluminacao - Cubo");
    
    glutDisplayFunc(desenho);
    glutReshapeFunc(ajuste);
    glutTimerFunc(30,anima,1);
    
    initLight();
    glutMainLoop(); 
}
