#include <GL\glut.h>
#include <iostream>
#define STB_IMAGE_IMPLEMENTATION
#include "stb_image.h"

using namespace std;

float lado = 20;
GLuint texID[3];

double movx = 0;
double movy = 0;
double movz =200;
double velocidadeCamera = 5.0f;

void quadrado(float SIZE, GLuint texid)
{
	glColor3f(1.0, 1.0, 1.0);
	glBindTexture(GL_TEXTURE_2D, texid);
	
	glBegin(GL_QUADS);
		glTexCoord2f(0,0);	glVertex3f(-SIZE, -SIZE, 0.0);
		glTexCoord2f(1,0);	glVertex3f(SIZE, -SIZE, 0.0);
		glTexCoord2f(1,1);	glVertex3f(SIZE, SIZE, 0.0);
		glTexCoord2f(0,1);	glVertex3f(-SIZE, SIZE, 0.0);
		
		glTexCoord3f(0,1,0); glVertex3f(SIZE, SIZE, 0);
		glTexCoord3f(1,1,1); glVertex3f(SIZE, SIZE, -SIZE*2);
		glTexCoord3f(1,0,1); glVertex3f(SIZE, -SIZE, -SIZE*2);
		glTexCoord3f(0,0,0); glVertex3f(SIZE, -SIZE, 0);
		
		glTexCoord3f(0,1,0); glVertex3f(-SIZE, SIZE, 0); 
		glTexCoord3f(1,1,1); glVertex3f(-SIZE, SIZE, -SIZE*2);
		glTexCoord3f(1,0,1); glVertex3f(-SIZE, -SIZE, -SIZE*2);
		glTexCoord3f(0,0,0); glVertex3f(-SIZE, -SIZE, 0);
		
		glTexCoord2f(0,0);	glVertex3f(-SIZE, -SIZE, -SIZE*2);
		glTexCoord2f(1,0);	glVertex3f(SIZE, -SIZE, -SIZE*2);
		glTexCoord2f(1,1);	glVertex3f(SIZE, SIZE, -SIZE*2);
		glTexCoord2f(0,1);	glVertex3f(-SIZE, SIZE, -SIZE*2);
		
		glTexCoord3f(0,0,0); glVertex3f(-SIZE, SIZE, 0);
		glTexCoord3f(1,0,0); glVertex3f(-SIZE, SIZE, -SIZE*2);
		glTexCoord3f(1,1,0); glVertex3f(SIZE, SIZE, -SIZE*2);
		glTexCoord3f(0,1,0); glVertex3f(SIZE, SIZE, 0);
		
		glTexCoord3f(0,0,0); glVertex3f(-SIZE, -SIZE, 0);
		glTexCoord3f(1,0,0); glVertex3f(-SIZE, -SIZE, -SIZE*2);
		glTexCoord3f(1,1,0); glVertex3f(SIZE, -SIZE, -SIZE*2);
		glTexCoord3f(0,1,0); glVertex3f(SIZE, -SIZE, 0);
		
	glEnd();
}

void desenho(){
	glClearColor(0,0,0,0);
	glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	
	quadrado(lado, texID[0]);
	glTranslatef(-50,0,0);
	
	quadrado(lado, texID[1]);	
	glTranslatef(100,0,0);
	
	quadrado(lado, texID[2]);
	
	glFlush();
}

void ajuste(int w, int h){
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	
	gluPerspective(45,w/h, 0.4, 500);
	
}

void carregarTextura(GLuint tex_id, string filepath){
	unsigned char* imgData;
	int largura, altura, canais;
	
	stbi_set_flip_vertically_on_load(true);
	
	imgData = stbi_load(filepath.c_str(), &largura, &altura,&canais, 4);
	
	if(imgData){
		glBindTexture(GL_TEXTURE_2D, tex_id);
		glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA8, largura, 
		altura, 0, GL_RGBA, GL_UNSIGNED_BYTE, imgData);
		
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_REPEAT);
		glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
		
		stbi_image_free(imgData);
	}
    else{
		cout<<"ERRO!No foi possvel carregar a imagem "<<filepath.c_str()<<endl;
	}
}

void initializeTexture(){
	glGenTextures(3, texID);
	glEnable(GL_TEXTURE_2D);
	glTexEnvf(GL_TEXTURE_ENV, GL_TEXTURE_ENV_MODE, GL_MODULATE);
	carregarTextura(texID[0], "images/brick.png");
	carregarTextura(texID[1], "images/wood.png");
	carregarTextura(texID[2], "images/painted_brick.png");
	
	glEnable(GL_DEPTH_TEST);
}

void initializeLight(){
	glEnable(GL_LIGHTING);
	glEnable(GL_LIGHT0);
	
	float luzAmbiente[4] = {0.3,0.3,0.3,1};
	float luzDifusa[4] = {0.7,0.7,0.7,1};
	float luzEspecular[4] = {1,1,1,1};
	float posicaoLuz0[4] = {0,20,0,1};
	float especularidade[4] = {1,1,1,1};
	int especMaterial = 128;
	
	glMaterialfv(GL_FRONT, GL_SPECULAR, especularidade);
	glMaterialf(GL_FRONT, GL_SHININESS, especMaterial);
	
	glLightModelfv(GL_LIGHT_MODEL_AMBIENT, luzAmbiente);
	
	glLightfv(GL_LIGHT0, GL_AMBIENT, luzAmbiente);
	glLightfv(GL_LIGHT0, GL_DIFFUSE, luzDifusa);
	glLightfv(GL_LIGHT0, GL_SPECULAR, luzEspecular);
	glLightfv(GL_LIGHT0, GL_POSITION, posicaoLuz0);
	
}

void teclado(unsigned char key, int x, int  y){
	glutPostRedisplay();
	
	if(key == 'w')
		movz-=velocidadeCamera;
	if(key == 's')
		movz+=velocidadeCamera;
		
	if(key == 'a')
		movx-=velocidadeCamera;
	if(key == 'd')
		movx+=velocidadeCamera;
		
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
	
	gluLookAt(0,movy,movz,
			movx,0,0,
			0,1,0);
		
}

int main(){
	glutInitDisplayMode(GLUT_SINGLE| GLUT_RGB);
	glutInitWindowPosition(200,100);
	glutInitWindowSize(800,800);
	glutCreateWindow("3D");
	glutDisplayFunc(desenho);
	glutReshapeFunc(ajuste);
	glutKeyboardFunc(teclado);
	initializeTexture();
	initializeLight();
	glutMainLoop();	
}
