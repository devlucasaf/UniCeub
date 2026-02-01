#include <GL\glut.h>

float lado = 40;

void cubo() {
	glLineWidth(10);
	
	glColor3f(1,0,0);
	glNormal3f(0,0,1);
	glBegin(GL_QUADS);                  
		glVertex3f(lado,lado,lado);     
		glVertex3f(-lado,lado,lado);    
		glVertex3f(-lado,-lado,lado);   
		glVertex3f(lado,-lado,lado);    
	glEnd();
	
	glNormal3f(0,0,-1);
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
		glVertex3f(-lado,-lado,-lado)       
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
	float matSpecular[] = {1,1,1,1};
	glMaterialfv(GL_FRONT, GL_SPECULAR,matSpecular);
	glMaterialf(GL_FRONT, GL_SHININESS,128);
    cubo();

	glRotatef(2,0,1,0);
	
	glutSolidTeapot(lado);
	glFlush();
}

void ajuste(int w, int h) {
	glMatrixMode(GL_PROJECTION);
	glLoadIdentity();
	
	gluPerspective(45,w/h, 0.4, 500);
	
	glMatrixMode(GL_MODELVIEW);
	glLoadIdentity();
	
	gluLookAt(50,0,200,
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

    glEnable(GL_COLOR_MATERIAL); 
    float globalAmb[] = {
		0.1f, 0.1f, 0.1f, 1
	};
	glLightModelfv(GL_LIGHT_MODEL_AMBIENT, globalAmb);

    float light0[4][4] = {
        {0.1f,0.1f,0.1f,1}, 
        {0.8f,0.8f,0.8f,1}, 
        {1,1,1,1},          
        {0,0,0,1}           
	};

    glLightfv(GL_LIGHT0, GL_AMBIENT, &light0[0][0]);
    glLightfv(GL_LIGHT0, GL_DIFFUSE, &light0[1][0]);
    glLightfv(GL_LIGHT0, GL_SPECULAR, &light0[2][0]);
    glLightfv(GL_LIGHT0, GL_POSITION, &light0[3][0]);

	float luzAmbiente[4] = {
		0.2,0.2,0.2,1.0
	};

	float luzDifusa[4] = {
		0.7,0.7,0.7,1.0
	};

	float luzEspecular[4] = {
		1.0,1.0,1.0,1.0
	};

	float posicaoLuz0[4] = {
		300,50,0,1.0
	};

	float posicaoLuz2[4] = {
		0,-100,0,1.0
	};
	
	float especularidade[4] = {
		1.0,1.0,1.0,1.0
	}; 
	
	int especMaterial = 128;
	
	glMaterialfv(GL_FRONT, GL_SPECULAR,especularidade);
	glMaterialf(GL_FRONT, GL_SHININESS,especMaterial);
	
	glLightModelfv(GL_LIGHT_MODEL_AMBIENT, luzAmbiente);
	
	glLightfv(GL_LIGHT0, GL_AMBIENT, luzAmbiente);
	glLightfv(GL_LIGHT0, GL_DIFFUSE, luzDifusa);
	glLightfv(GL_LIGHT0, GL_SPECULAR, luzEspecular);
	glLightfv(GL_LIGHT0, GL_POSITION, posicaoLuz0);
	
	glEnable(GL_COLOR_MATERIAL);
	glEnable(GL_LIGHTING);
	
	glEnable(GL_LIGHT0);
	glEnable(GL_DEPTH_TEST);
}

int main() {
	glutInitDisplayMode(GLUT_SINGLE| GLUT_RGB);
	glutInitWindowPosition(200,100);
	glutInitWindowSize(800,800);
	glutCreateWindow("3D");
	glutDisplayFunc(desenho);
	glutReshapeFunc(ajuste);
	glutTimerFunc(30,anima,1);
	initLight();
	glutMainLoop();	
}
