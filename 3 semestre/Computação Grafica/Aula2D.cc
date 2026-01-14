#include <GL\glut.h>
#include <stdlib.h>
#include <iostream>

using namespace std;

#define ESC 27

float x_position;

void quadrado(){
	glColor3f(0,0,1);
	glBegin(GL_QUADS);
		glVertex2f(20,20);
		glVertex2f(-20,20);
		glVertex2f(-20,-20);
		glVertex2f(20,-20);
	glEnd();
}

void desenho(){
	glClearColor(1,0,0,1);
	glClear(GL_COLOR_BUFFER_BIT);
	
	glTranslatef(x_position, 0,0);
	
	quadrado();		
	
	glFlush();
}

void ajustar(int w, int h){
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
	
	gluOrtho2D(-100,100,-100,100);
}

void teclado(unsigned char key, int x, int y){
	cout<<(int)key<<endl;
	if(key == ESC){
		exit(0);
	}
}

void anima(int timer){
	glutPostRedisplay();
	x_position+=0.1f;
	
	glutTimerFunc(30,anima,1);
}

int main(){
	glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);
	glutInitWindowPosition(200,100);
	glutInitWindowSize(600,600);
	glutCreateWindow("Animation");
	glutDisplayFunc(desenho);
	glutReshapeFunc(ajustar);
	glutKeyboardFunc(teclado);
	glutTimerFunc(30,anima,1);	
	glutMainLoop();
	return 0;
}
