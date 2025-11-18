#include <windows.h>
#include <glut.h>

// Estrutura para armazenar um vértice 2D
struct VERTEX {
    int x;  // Coordenada x do vértice
    int y;  // Coordenada y do vértice
};

// Estrutura para armazenar um objeto composto por múltiplos vértices
struct OBJECT {
    VERTEX *vertices;   // Array de vértices do objeto
    int nvertices;      // Número de vértices no objeto
};

OBJECT *object;  // Variável global para armazenar o objeto

// Função para criar e inicializar um objeto
OBJECT *create_object() {
    // Aloca memória para o objeto
    OBJECT *obj = (OBJECT *)malloc(sizeof(OBJECT));
    
    // Define o número de vértices do objeto (forma em "L")
    obj->nvertices = 6;
    // Aloca memória para o array de vértices
    obj->vertices = (VERTEX *)malloc(obj->nvertices * sizeof(VERTEX));
    
    // Define as coordenadas dos vértices que formam uma figura em "L"
    obj->vertices[0].x = 10;   obj->vertices[0].y = 200;  // Canto superior esquerdo
    obj->vertices[1].x = 40;   obj->vertices[1].y = 200;  // Canto superior direito
    obj->vertices[2].x = 40;   obj->vertices[2].y = 50;   // Degrau superior
    obj->vertices[3].x = 100;  obj->vertices[3].y = 50;   // Degrau inferior direito
    obj->vertices[4].x = 100;  obj->vertices[4].y = 0;    // Canto inferior direito
    obj->vertices[5].x = 10;   obj->vertices[5].y = 0;    // Canto inferior esquerdo
    
    return obj;  // Retorna o objeto criado
}

// Função para calcular o centróide (centro geométrico) do objeto
VERTEX calculate_centroid(OBJECT *obj) {
    int i;
    VERTEX cent;  // Variável para armazenar o centróide
    cent.x = 0;   // Inicializa x do centróide
    cent.y = 0;   // Inicializa y do centróide
    
    // Soma todas as coordenadas x e y dos vértices
    for (i = 0; i < obj->nvertices; i++) {
        cent.x += obj->vertices[i].x;
        cent.y += obj->vertices[i].y;
    }
    
    // Calcula a média para obter o centróide
    cent.x /= obj->nvertices;
    cent.y /= obj->nvertices;
    return cent;  // Retorna o centróide calculado
}

// Função de inicialização do OpenGL
void init(void) {
    glClearColor(1.0, 1.0, 1.0, 0.0);  // Define cor de fundo como branco
    glMatrixMode(GL_PROJECTION);        // Seleciona matriz de projeção
    glLoadIdentity();                   // Carrega matriz identidade
    // Define projeção ortogonal 2D (-500 a 500 em x e y)
    gluOrtho2D(-500.0, 500.0, -500.0, 500.0);
    object = create_object();           // Cria o objeto principal
}

// Função principal de desenho que aplica várias transformações
void desenha(OBJECT* obj) {
    glMatrixMode(GL_MODELVIEW);  // Seleciona matriz de modelview
    glLoadIdentity();            // Carrega matriz identidade
    glClear(GL_COLOR_BUFFER_BIT); // Limpa o buffer de cor
    
    // 1ª FORMA: OBJETO ORIGINAL (VERDE)
    glBegin(GL_LINE_LOOP);       // Inicia desenho de polígono em wireframe
    glColor3f(0.0, 1.0, 0.0);   // Define cor verde
    for (int i = 0; i < 6; i++) {
        glPushMatrix();  // ⚠️ DESNECESSÁRIO - deveria ser removido
        glVertex2i(obj->vertices[i].x, obj->vertices[i].y);  // Vértice original
        glPopMatrix();   // ⚠️ DESNECESSÁRIO - deveria ser removido
    }
    glEnd();
    glEndList();  // ⚠️ USO INCORRETO - não há glNewList() correspondente
    
    // 2ª FORMA: REFLEXÃO NO EIXO Y (VERMELHO)
    glBegin(GL_LINE_LOOP);
    glColor3f(1.0, 0.0, 0.0);   // Define cor vermelha
    for (int i = 0; i < 6; i++) {
        glPushMatrix();  // ⚠️ DESNECESSÁRIO
        // Reflexão: multiplica x por -1 (espelha no eixo Y)
        glVertex2i((obj->vertices[i].x) * (-1), obj->vertices[i].y);
        glPopMatrix();   // ⚠️ DESNECESSÁRIO
    }
    glEnd();
    glEndList();  // ⚠️ USO INCORRETO
    
    // 3ª FORMA: ESCALA 0.5 (AZUL) com translação
    glTranslatef(100.0, 0, 0);  // Translada 100 unidades em x
    glBegin(GL_LINE_LOOP);
    glColor3f(0.0, 0.0, 1.0);   // Define cor azul
    for (int i = 0; i < 6; i++) {
        glPushMatrix();  // ⚠️ DESNECESSÁRIO
        // Escala: reduz tamanho pela metade
        glVertex2f((obj->vertices[i].x) * (0.5), (obj->vertices[i].y) * (0.5));
        glPopMatrix();   // ⚠️ DESNECESSÁRIO
    }
    glEnd();
    glEndList();  // ⚠️ USO INCORRETO
    
    // 4ª FORMA: TRANSPOSIÇÃO (AZUL CLARO) com translação
    glTranslatef(100.0, 0.0, 0);  // Translada mais 100 unidades em x
    glBegin(GL_LINE_LOOP);
    glColor3f(0.5, 0.5, 1.0);   // Define cor azul claro
    for (int i = 0; i < 6; i++) {
        glPushMatrix();  // ⚠️ DESNECESSÁRIO
        // Transposição: troca x por y (como matriz transposta)
        glVertex2f((obj->vertices[i].y), (obj->vertices[i].x));
        glPopMatrix();   // ⚠️ DESNECESSÁRIO
    }
    glEnd();
    glEndList();  // ⚠️ USO INCORRETO
    
    // 5ª FORMA: CISALHAMENTO HORIZONTAL (MAGENTA) com translação
    glTranslatef(-100.0, -250.0, 0);  // Translada para nova posição
    glBegin(GL_LINE_LOOP);
    glColor3f(1.0, 0.0, 1.0);   // Define cor magenta
    for (int i = 0; i < 6; i++) {
        glPushMatrix();  // ⚠️ DESNECESSÁRIO
        // Cisalhamento: x é deslocado baseado em y
        glVertex2f((obj->vertices[i].x) + (obj->vertices[i].y) * 0.58, (obj->vertices[i].y));
        glPopMatrix();   // ⚠️ DESNECESSÁRIO
    }
    glEnd();
    glEndList();  // ⚠️ USO INCORRETO
    
    // 6ª FORMA: ROTAÇÃO DE ~30° (AMARELO) com translação
    glTranslatef(-300.0, 0, 0);  // Translada para esquerda
    glBegin(GL_LINE_LOOP);
    glColor3f(1.0, 1.0, 0.0);   // Define cor amarela
    for (int i = 0; i < 6; i++) {
        glPushMatrix();  // ⚠️ DESNECESSÁRIO
        // Rotação: usa matriz de rotação 2D (cos30°≈0.87, sin30°=0.5)
        glVertex2f((obj->vertices[i].x)*0.87 + (obj->vertices[i].y)*-0.5, 
                   (obj->vertices[i].x)*0.5 + (obj->vertices[i].y)*0.87);
        glPopMatrix();   // ⚠️ DESNECESSÁRIO
    }
    glEnd();
    glEndList();  // ⚠️ USO INCORRETO
    
    // 7ª FORMA: ESCALA E ROTAÇÃO COMBINADAS (CIANO) com translação
    glTranslatef(200.0, 0, 0);  // Translada para direita
    glBegin(GL_LINE_LOOP);
    glColor3f(0.0, 1.0, 1.0);   // Define cor ciano
    for (int i = 0; i < 6; i++) {
        glPushMatrix();  // ⚠️ DESNECESSÁRIO
        // Combinação: escala 0.75 e rotação de 45° (cos45°=sin45°≈0.71)
        glVertex2f((obj->vertices[i].x)*0.71*0.75 + (obj->vertices[i].y)*-0.71*0.75, 
                   (obj->vertices[i].x)*0.71*0.75 + (obj->vertices[i].y)*0.71*0.75);
        glPopMatrix();   // ⚠️ DESNECESSÁRIO
    }
    glEnd();
    glEndList();  // ⚠️ USO INCORRETO
    
    // 8ª FORMA: REFLEXÃO VERTICAL E CISALHAMENTO (BRANCO)
    glBegin(GL_LINE_LOOP);
    glColor3f(1.0, 1.0, 1.0);   // Define cor branca
    for (int i = 0; i < 6; i++) {
        glPushMatrix();  // ⚠️ DESNECESSÁRIO
        // Reflexão vertical (y * -1) com cisalhamento
        glVertex2f((obj->vertices[i].x) + (obj->vertices[i].y)*0.47, 
                   (obj->vertices[i].y)*-1);
        glPopMatrix();   // ⚠️ DESNECESSÁRIO
    }
    glEnd();
    glEndList();  // ⚠️ USO INCORRETO
    
    glFlush();  // Força execução dos comandos OpenGL
}

// Função callback de display - chamada quando a janela precisa ser redesenhada
void display(void) {
    desenha(object);  // Chama função principal de desenho
}

// Função de inicialização adicional
void Inicializa() {
    // Define a cor de fundo da janela como preta
    glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
}

// Função callback chamada quando o tamanho da janela é alterado
void AlteraTamanhoJanela(GLsizei w, GLsizei h) {
    // Evita divisão por zero
    if (h == 0) h = 1;
    
    // Especifica as dimensões da Viewport
    glViewport(0, 0, w, h);
    
    // Inicializa o sistema de coordenadas
    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    
    // Estabelece a janela de seleção mantendo aspect ratio
    if (w <= h)
        gluOrtho2D(-500.0f, 500.0f, -500.0f, 500.0f*h / w);
    else
        gluOrtho2D(-500.0f, 500.0f*w / h, -500.0f, 500.0f);
}

// Função principal
int main() {
    // Inicializa GLUT
    glutInitDisplayMode(GLUT_SINGLE | GLUT_RGB);  // Modo de display: single buffer, RGB
    glutInitWindowSize(800, 800);                 // Tamanho da janela: 800x800
    glutInitWindowPosition(10, 10);               // Posição inicial da janela
    glutCreateWindow("Exercicio Malvezzi");       // Cria janela com título
    
    init();                           // Chama inicialização
    glutDisplayFunc(display);         // Registra função de display
    glutReshapeFunc(AlteraTamanhoJanela); // Registra função de redimensionamento
    Inicializa();                     // Chama inicialização adicional
    glutMainLoop();                   // Inicia loop principal do GLUT
    
    return 0;
}
