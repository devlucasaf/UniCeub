#include <windows.h>
#include <GL/glut.h>

struct VERTEX {
    int x;
    int y;
};

struct OBJECT {
    VERTEX *vertices;
    int nvertices;
};

OBJECT *object;

OBJECT *create_object() {
    OBJECT *obj = (OBJECT *)malloc(sizeof(OBJECT));

    obj->nvertices = 6;
    obj->vertices = (VERTEX *)malloc(obj->nvertices * sizeof(VERTEX));
    obj->vertices[0].x = 10;   obj->vertices[0].y = 200;
    obj->vertices[1].x = 40;   obj->vertices[1].y = 200;
    obj->vertices[2].x = 40;   obj->vertices[2].y = 50;
    obj->vertices[3].x = 100;  obj->vertices[3].y = 50;
    obj->vertices[4].x = 100;  obj->vertices[4].y = 0;
    obj->vertices[5].x = 10;   obj->vertices[5].y = 0;

    return obj;
}

VERTEX calculate_centroid(OBJECT *obj) {
    int i;

    VERTEX cent;

    cent.x = 0;
    cent.y = 0;

    for (i = 0; i < obj->nvertices; i++) {
        cent.x += obj->vertices[i].x;
        cent.y += obj->vertices[i].y;
    }

    cent.x /= obj->nvertices;
    cent.y /= obj->nvertices;
    
    return cent;
}

void init(void) {
    glClearColor(0.0, 0.0, 0.0, 1.0);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    gluOrtho2D(-500.0, 500.0, -500.0, 500.0);
    object = create_object();
}

void desenha(OBJECT* obj) {
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();
    glClear(GL_COLOR_BUFFER_BIT);

    glBegin(GL_LINE_LOOP);
    glColor3f(0.0, 1.0, 0.0);

    for (int i = 0; i < 6; i++) {
        glVertex2i(obj->vertices[i].x, obj->vertices[i].y);
    }

    glEnd();

    glBegin(GL_LINE_LOOP);
    glColor3f(1.0, 0.0, 0.0);

    for (int i = 0; i < 6; i++) {
        glVertex2i((obj->vertices[i].x) * (-1), obj->vertices[i].y);
    }

    glEnd();

    glTranslatef(100.0, 0, 0);
    glBegin(GL_LINE_LOOP);
    glColor3f(0.0, 0.0, 1.0);

    for (int i = 0; i < 6; i++) {
        glVertex2f((obj->vertices[i].x) * (0.5), (obj->vertices[i].y) * (0.5));
    }

    glEnd();

    glTranslatef(100.0, 0.0, 0);
    glBegin(GL_LINE_LOOP);
    glColor3f(0.5, 0.5, 1.0);

    for (int i = 0; i < 6; i++) {
        glVertex2f((obj->vertices[i].y), (obj->vertices[i].x));
    }

    glEnd();

    glTranslatef(-100.0, -250.0, 0);
    glBegin(GL_LINE_LOOP);
    glColor3f(1.0, 0.0, 1.0);

    for (int i = 0; i < 6; i++) {
        glVertex2f((obj->vertices[i].x) + (obj->vertices[i].y) * 0.58, (obj->vertices[i].y));
    }

    glEnd();

    glTranslatef(-300.0, 0, 0);
    glBegin(GL_LINE_LOOP);
    glColor3f(1.0, 1.0, 0.0);

    for (int i = 0; i < 6; i++) {
        glVertex2f((obj->vertices[i].x)*0.87 + (obj->vertices[i].y)*-0.5,
                    (obj->vertices[i].x)*0.5 + (obj->vertices[i].y)*0.87);
    }

    glEnd();

    glTranslatef(200.0, 0, 0);
    glBegin(GL_LINE_LOOP);
    glColor3f(0.0, 1.0, 1.0);

    for (int i = 0; i < 6; i++) {
        glVertex2f((obj->vertices[i].x)*0.71*0.75 + (obj->vertices[i].y)*-0.71*0.75,
                    (obj->vertices[i].x)*0.71*0.75 + (obj->vertices[i].y)*0.71*0.75);
    }

    glEnd();

    glBegin(GL_LINE_LOOP);
    glColor3f(1.0, 1.0, 1.0);

    for (int i = 0; i < 6; i++) {
        glVertex2f((obj->vertices[i].x) + (obj->vertices[i].y)*0.47,
                    (obj->vertices[i].y)*-1);
    }
    
    glEnd();

    glFlush();
}

void display(void) {
    desenha(object);
}

void AlteraTamanhoJanela(GLsizei w, GLsizei h) {
    if (h == 0) {
        h = 1;
    }

    glViewport(0, 0, w, h);
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    
    if (w <= h) {
        gluOrtho2D(-500.0f, 500.0f, -500.0f, 500.0f*h / w);
    }
    else {
        gluOrtho2D(-500.0f, 500.0f*w / h, -500.0f, 500.0f);
    }
}

int main(int argc, char** argv) {
    glutInit(&argc, argv);
    glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
    glutInitWindowSize(800, 800);
    glutInitWindowPosition(10, 10);
    glutCreateWindow("Exercicio Malvezzi");
    init();
    glutDisplayFunc(display);
    glutReshapeFunc(AlteraTamanhoJanela);
    glutMainLoop();
    return 0;
}
