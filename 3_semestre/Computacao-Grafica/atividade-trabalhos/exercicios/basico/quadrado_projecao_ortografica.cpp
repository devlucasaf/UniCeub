// --- COMPUTAÇÃO GRÁFICA ---
// --- QUADRADO COM PROJEÇÃO ORTOGRÁFICA ---

#include <iostream>
#include <string>
#include <vector>
#include <cmath>

#include <glad/glad.h>
#include <GLFW/glfw3.h>

// --- AJUSTA A ÁREA DE DESENHO AO REDIMENSIONAR A JANELA ---
static void aoRedimensionarFramebuffer(GLFWwindow* janela, int largura, int altura) {
    (void)janela;
    glViewport(0, 0, largura, altura);
}

// --- COMPILAÇÃO DE SHADER ---
static GLuint compilarShader(GLenum tipoShader, const char* codigoFonte) {
    GLuint shader = glCreateShader(tipoShader);
    glShaderSource(shader, 1, &codigoFonte, nullptr);
    glCompileShader(shader);

    GLint compilacaoBemSucedida = 0;
    glGetShaderiv(shader, GL_COMPILE_STATUS, &compilacaoBemSucedida);

    if (!compilacaoBemSucedida) {
        GLint tamanhoRegistro = 0;
        glGetShaderiv(shader, GL_INFO_LOG_LENGTH, &tamanhoRegistro);
        std::string registroErro(tamanhoRegistro, '\0');
        glGetShaderInfoLog(shader, tamanhoRegistro, nullptr, registroErro.data());
        std::cerr << "Erro ao compilar o shader: " << registroErro << "\n";
        glDeleteShader(shader);

        return 0;
    }

    return shader;
}

// --- CRIAÇÃO E LIGAÇÃO DO PROGRAMA DE SHADERS ---
static GLuint criarProgramaShaders(const char* codigoFonteVertice, const char* codigoFonteFragmento) {
    GLuint shaderVertice = compilarShader(GL_VERTEX_SHADER, codigoFonteVertice);

    if (!shaderVertice) {
        return 0;
    }

    GLuint shaderFragmento = compilarShader(GL_FRAGMENT_SHADER, codigoFonteFragmento);

    if (!shaderFragmento) {
        glDeleteShader(shaderVertice);
        return 0;
    }

    GLuint programaShaders = glCreateProgram();
    glAttachShader(programaShaders, shaderVertice);
    glAttachShader(programaShaders, shaderFragmento);
    glLinkProgram(programaShaders);

    glDeleteShader(shaderVertice);
    glDeleteShader(shaderFragmento);

    GLint ligacaoBemSucedida = 0;
    glGetProgramiv(programaShaders, GL_LINK_STATUS, &ligacaoBemSucedida);

    if (!ligacaoBemSucedida) {
        GLint tamanhoRegistro = 0;
        glGetProgramiv(programaShaders, GL_INFO_LOG_LENGTH, &tamanhoRegistro);
        std::string registroErro(tamanhoRegistro, '\0');
        glGetProgramInfoLog(programaShaders, tamanhoRegistro, nullptr, registroErro.data());
        std::cerr << "Erro ao ligar o programa de shaders: " << registroErro << "\n";
        glDeleteProgram(programaShaders);

        return 0;
    }

    return programaShaders;
}

// --- MATRIZ DE PROJEÇÃO ORTOGRÁFICA ---
static void criarMatrizProjecaoOrtografica(float proporcaoTela, float matrizResultado[16]) {
    const float planoProximo = -1.0f;
    const float planoDistante = 1.0f;

    for (int indice = 0; indice < 16; indice++) {
        matrizResultado[indice] = 0.0f;
    }

    matrizResultado[0]  = 1.0f / proporcaoTela;
    matrizResultado[5]  = 1.0f;
    matrizResultado[10] = -2.0f / (planoDistante - planoProximo);
    matrizResultado[14] = -(planoDistante + planoProximo) / (planoDistante - planoProximo);
    matrizResultado[15] = 1.0f;
}

// --- MATRIZ IDENTIDADE ---
static void criarMatrizIdentidade(float matrizResultado[16]) {
    for (int indice = 0; indice < 16; indice++) {
        matrizResultado[indice] = 0.0f;
    }

    matrizResultado[0] = matrizResultado[5] = matrizResultado[10] = matrizResultado[15] = 1.0f;
}

int main() {
    // --- INICIALIZAÇÃO DO GLFW ---
    if (!glfwInit()) {
        std::cerr << "Falha ao inicializar o GLFW.\n";
        return 1;
    }

    glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
    glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
    glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

    GLFWwindow* janela = glfwCreateWindow(600, 480, "Demonstração WebGL (C++ OpenGL)", nullptr, nullptr);

    if (!janela) {
        std::cerr << "Falha ao criar a janela.\n";
        glfwTerminate();
        return 1;
    }

    glfwMakeContextCurrent(janela);
    glfwSetFramebufferSizeCallback(janela, aoRedimensionarFramebuffer);

    // --- CARREGAMENTO DAS FUNÇÕES OPENGL ---
    if (!gladLoadGLLoader((GLADloadproc)glfwGetProcAddress)) {
        std::cerr << "Falha ao inicializar o GLAD.\n";
        glfwDestroyWindow(janela);
        glfwTerminate();
        return 1;
    }

    // --- SHADER DE VÉRTICE ---
    const char* codigoFonteShaderVertice = R"(
        #version 330 core
        layout(location = 0) in vec2 aPosicaoVertice;

        uniform mat4 uMatrizModeloVisao;
        uniform mat4 uMatrizProjecao;

        void main() {
            gl_Position = uMatrizProjecao * uMatrizModeloVisao * vec4(aPosicaoVertice, 0.0, 1.0);
        }
    )";

    // --- SHADER DE FRAGMENTO ---
    const char* codigoFonteShaderFragmento = R"(
        #version 330 core
        out vec4 corFragmento;

        void main() {
            corFragmento = vec4(1.0, 1.0, 1.0, 1.0);
        }
    )";

    GLuint programaShaders = criarProgramaShaders(codigoFonteShaderVertice, codigoFonteShaderFragmento);

    if (!programaShaders) {
        glfwDestroyWindow(janela);
        glfwTerminate();
        return 1;
    }

    GLint localizacaoMatrizProjecao   = glGetUniformLocation(programaShaders, "uMatrizProjecao");
    GLint localizacaoMatrizModeloVisao = glGetUniformLocation(programaShaders, "uMatrizModeloVisao");

    // --- VÉRTICES DO QUADRADO ---
    float posicoesVertices[] = {
        0.5f,  0.5f,
        -0.5f,  0.5f,
        0.5f, -0.5f,
        -0.5f, -0.5f
    };

    // --- OBJETO DE ARRAY DE VÉRTICES E OBJETO DE BUFFER DE VÉRTICES ---
    GLuint objetoArrayVertices = 0;
    GLuint objetoBufferVertices = 0;

    glGenVertexArrays(1, &objetoArrayVertices);
    glGenBuffers(1, &objetoBufferVertices);

    glBindVertexArray(objetoArrayVertices);

    glBindBuffer(GL_ARRAY_BUFFER, objetoBufferVertices);
    glBufferData(GL_ARRAY_BUFFER, sizeof(posicoesVertices), posicoesVertices, GL_STATIC_DRAW);

    glVertexAttribPointer(
        0,                  
        2,                  
        GL_FLOAT,           
        GL_FALSE,           
        2 * sizeof(float),  
        (void*)0            
    );

    glEnableVertexAttribArray(0);

    glBindVertexArray(0);

    // --- TESTE DE PROFUNDIDADE ---
    glEnable(GL_DEPTH_TEST);
    glDepthFunc(GL_LEQUAL);

    // --- LAÇO DE RENDERIZAÇÃO ---
    while (!glfwWindowShouldClose(janela)) {
        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        glClearDepth(1.0f);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        int larguraFramebuffer;
        int alturaFramebuffer;

        glfwGetFramebufferSize(janela, &larguraFramebuffer, &alturaFramebuffer);

        float proporcaoTela = (alturaFramebuffer == 0) ? 1.0f : (float)larguraFramebuffer / (float)alturaFramebuffer;

        float matrizProjecao[16];
        float matrizModeloVisao[16];

        criarMatrizProjecaoOrtografica(proporcaoTela, matrizProjecao);
        criarMatrizIdentidade(matrizModeloVisao);

        glUseProgram(programaShaders);
        glUniformMatrix4fv(localizacaoMatrizProjecao, 1, GL_FALSE, matrizProjecao);
        glUniformMatrix4fv(localizacaoMatrizModeloVisao, 1, GL_FALSE, matrizModeloVisao);

        glBindVertexArray(objetoArrayVertices);
        glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);
        glBindVertexArray(0);

        glfwSwapBuffers(janela);
        glfwPollEvents();
    }

    // --- LIBERAÇÃO DE RECURSOS ---
    glDeleteBuffers(1, &objetoBufferVertices);
    glDeleteVertexArrays(1, &objetoArrayVertices);
    glDeleteProgram(programaShaders);

    glfwDestroyWindow(janela);
    glfwTerminate();

    return 0;
}
