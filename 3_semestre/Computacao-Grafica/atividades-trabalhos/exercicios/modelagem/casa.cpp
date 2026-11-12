// --- COMPUTAÇÃO GRÁFICA ---
// --- DESENHO DE UMA CASA COM LINHAS ---

#include <iostream>
#include <string>

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

// --- DESENHA UMA FORMA COMO SEQUÊNCIA DE LINHAS CONECTADAS ---
static void desenharForma(GLuint programaShaders, const float* vertices, int quantidadeVertices) {
    GLuint objetoArrayVertices = 0;
    GLuint objetoBufferVertices = 0;

    glGenVertexArrays(1, &objetoArrayVertices);
    glGenBuffers(1, &objetoBufferVertices);

    glBindVertexArray(objetoArrayVertices);
    glBindBuffer(GL_ARRAY_BUFFER, objetoBufferVertices);
    glBufferData(GL_ARRAY_BUFFER, (size_t)quantidadeVertices * 2 * sizeof(float), vertices, GL_STATIC_DRAW);

    glVertexAttribPointer(
        0,                  
        2,                  
        GL_FLOAT,           
        GL_FALSE,           
        2 * sizeof(float),  
        (void*)0            
    );

    glEnableVertexAttribArray(0);

    glUseProgram(programaShaders);
    glBindVertexArray(objetoArrayVertices);
    glDrawArrays(GL_LINE_STRIP, 0, quantidadeVertices);

    glBindVertexArray(0);
    glDeleteBuffers(1, &objetoBufferVertices);
    glDeleteVertexArrays(1, &objetoArrayVertices);
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

    GLFWwindow* janela = glfwCreateWindow(400, 400, "Formas em OpenGL", nullptr, nullptr);

    if (!janela) {
        std::cerr << "Falha ao criar a janela.\n";
        glfwTerminate();
        return 1;
    }

    glfwMakeContextCurrent(janela);
    glfwSetFramebufferSizeCallback(janela, aoRedimensionarFramebuffer);

    // --- CARREGAMENTO DAS FUNÇÕES OPENGL (GLAD) ---
    if (!gladLoadGLLoader((GLADloadproc)glfwGetProcAddress)) {
        std::cerr << "Falha ao inicializar o GLAD.\n";
        glfwDestroyWindow(janela);
        glfwTerminate();
        return 1;
    }

    // --- SHADER DE VÉRTICE ---
    const char* codigoFonteShaderVertice = R"(
        #version 330 core
        layout(location = 0) in vec2 aCoordenadas;

        void main() {
            gl_Position = vec4(aCoordenadas, 0.0, 1.0);
        }
    )";

    // --- SHADER DE FRAGMENTO ---
    const char* codigoFonteShaderFragmento = R"(
        #version 330 core
        out vec4 corFragmento;

        void main() {
            corFragmento = vec4(0.0, 0.0, 0.0, 1.0);
        }
    )";

    GLuint programaShaders = criarProgramaShaders(codigoFonteShaderVertice, codigoFonteShaderFragmento);

    if (!programaShaders) {
        glfwDestroyWindow(janela);
        glfwTerminate();
        return 1;
    }

    // --- TELHADO ---
    float verticesTelhado[] = {
        0.6f, -0.47f,
        0.0f,  0.0f,
        -0.6f, -0.47f
    };

    // --- PAREDES COM PORTA ---
    float verticesParedesComPorta[] = {
        0.5f, -0.5f,
        0.5f, -1.0f,
        0.1f, -1.0f,
        0.1f, -0.7f,
        -0.1f, -0.7f,
        -0.1f, -1.0f,
        -0.5f, -1.0f,
        -0.5f, -0.5f
    };

    // --- JANELA DA DIREITA ---
    float verticesJanelaDireita[] = {
        0.2f, -0.6f,
        0.4f, -0.6f,
        0.4f, -0.8f,
        0.2f, -0.8f,
        0.2f, -0.6f
    };

    // --- JANELA DA ESQUERDA ---
    float verticesJanelaEsquerda[] = {
        -0.2f, -0.6f,
        -0.4f, -0.6f,
        -0.4f, -0.8f,
        -0.2f, -0.8f,
        -0.2f, -0.6f
    };

    // --- LAÇO DE RENDERIZAÇÃO ---
    while (!glfwWindowShouldClose(janela)) {
        glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        glClear(GL_COLOR_BUFFER_BIT);

        desenharForma(programaShaders, verticesTelhado, 3);
        desenharForma(programaShaders, verticesParedesComPorta, 8);
        desenharForma(programaShaders, verticesJanelaDireita, 5);
        desenharForma(programaShaders, verticesJanelaEsquerda, 5);

        glfwSwapBuffers(janela);
        glfwPollEvents();
    }

    // --- LIBERAÇÃO DE RECURSOS ---
    glDeleteProgram(programaShaders);
    glfwDestroyWindow(janela);
    glfwTerminate();

    return 0;
}
