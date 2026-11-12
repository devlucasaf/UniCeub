// --- COMPUTAÇÃO GRÁFICA ---
// --- CUBO 3D EM ROTAÇÃO COM PROJEÇÃO EM PERSPECTIVA ---

#include <glad/glad.h>
#include <GLFW/glfw3.h>
#include <glm/glm.hpp>
#include <glm/gtc/matrix_transform.hpp>
#include <glm/gtc/type_ptr.hpp>
#include <iostream>

// --- SHADER DE VÉRTICE ---
const char* codigoFonteShaderVertice = R"(
    #version 330 core
    layout (location = 0) in vec3 aPosicaoVertice;

    uniform mat4 uMatrizModelo;
    uniform mat4 uMatrizVisao;
    uniform mat4 uMatrizProjecao;

    void main()
    {
        gl_Position = uMatrizProjecao * uMatrizVisao * uMatrizModelo * vec4(aPosicaoVertice, 1.0);
    }
)";

// --- SHADER DE FRAGMENTO ---
const char* codigoFonteShaderFragmento = R"(
    #version 330 core
    out vec4 corFragmento;

    void main()
    {
        corFragmento = vec4(0.3, 0.8, 1.0, 1.0);
    }
)";

// --- VÉRTICES DO CUBO ---
float verticesCubo[] = {
    // --- FACE TRASEIRA ---
    -0.5f, -0.5f, -0.5f,   0.5f, -0.5f, -0.5f,   0.5f,  0.5f, -0.5f,
    0.5f,  0.5f, -0.5f,  -0.5f,  0.5f, -0.5f,  -0.5f, -0.5f, -0.5f,

    // --- FACE FRONTAL ---
    -0.5f, -0.5f,  0.5f,   0.5f, -0.5f,  0.5f,   0.5f,  0.5f,  0.5f,
    0.5f,  0.5f,  0.5f,  -0.5f,  0.5f,  0.5f,  -0.5f, -0.5f,  0.5f,

    // --- FACE ESQUERDA ---
    -0.5f,  0.5f,  0.5f,  -0.5f,  0.5f, -0.5f,  -0.5f, -0.5f, -0.5f,
    -0.5f, -0.5f, -0.5f,  -0.5f, -0.5f,  0.5f,  -0.5f,  0.5f,  0.5f,

    // --- FACE DIREITA ---
    0.5f,  0.5f,  0.5f,   0.5f,  0.5f, -0.5f,   0.5f, -0.5f, -0.5f,
    0.5f, -0.5f, -0.5f,   0.5f, -0.5f,  0.5f,   0.5f,  0.5f,  0.5f,

    // --- FACE INFERIOR ---
    -0.5f, -0.5f, -0.5f,   0.5f, -0.5f, -0.5f,   0.5f, -0.5f,  0.5f,
    0.5f, -0.5f,  0.5f,  -0.5f, -0.5f,  0.5f,  -0.5f, -0.5f, -0.5f,

    // --- FACE SUPERIOR ---
    -0.5f,  0.5f, -0.5f,   0.5f,  0.5f, -0.5f,   0.5f,  0.5f,  0.5f,
    0.5f,  0.5f,  0.5f,  -0.5f,  0.5f,  0.5f,  -0.5f,  0.5f, -0.5f
};

// --- TRATAMENTO DO TECLADO ---
void processarEntradaTeclado(GLFWwindow* janela) {
    if (glfwGetKey(janela, GLFW_KEY_ESCAPE) == GLFW_PRESS) {
        glfwSetWindowShouldClose(janela, true);
    }
}

int main() {
    // --- INICIALIZAÇÃO DO GLFW ---
    if (!glfwInit()) {
        std::cerr << "Erro ao inicializar o GLFW\n";
        return -1;
    }

    glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
    glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
    glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

    GLFWwindow* janela = glfwCreateWindow(800, 600, "Perspectiva 3D em C++", nullptr, nullptr);

    if (!janela) {
        std::cerr << "Erro ao criar a janela GLFW\n";
        glfwTerminate();
        return -1;
    }

    glfwMakeContextCurrent(janela);

    // --- CARREGAMENTO DAS FUNÇÕES OPENGL (GLAD) ---
    if (!gladLoadGLLoader((GLADloadproc)glfwGetProcAddress)) {
        std::cerr << "Erro ao inicializar o GLAD\n";
        glfwDestroyWindow(janela);
        glfwTerminate();
        return -1;
    }

    int operacaoBemSucedida;
    char registroErro[512];

    // --- COMPILAÇÃO DO SHADER DE VÉRTICE ---
    unsigned int shaderVertice = glCreateShader(GL_VERTEX_SHADER);

    glShaderSource(shaderVertice, 1, &codigoFonteShaderVertice, NULL);
    glCompileShader(shaderVertice);
    glGetShaderiv(shaderVertice, GL_COMPILE_STATUS, &operacaoBemSucedida);

    if (!operacaoBemSucedida) {
        glGetShaderInfoLog(shaderVertice, 512, NULL, registroErro);
        std::cerr << "Erro no shader de vértice:\n" << registroErro << std::endl;
    }

    // --- COMPILAÇÃO DO SHADER DE FRAGMENTO ---
    unsigned int shaderFragmento = glCreateShader(GL_FRAGMENT_SHADER);

    glShaderSource(shaderFragmento, 1, &codigoFonteShaderFragmento, NULL);
    glCompileShader(shaderFragmento);
    glGetShaderiv(shaderFragmento, GL_COMPILE_STATUS, &operacaoBemSucedida);

    if (!operacaoBemSucedida) {
        glGetShaderInfoLog(shaderFragmento, 512, NULL, registroErro);
        std::cerr << "Erro no shader de fragmento:\n" << registroErro << std::endl;
    }

    // --- LIGAÇÃO DO PROGRAMA DE SHADERS ---
    unsigned int programaShaders = glCreateProgram();

    glAttachShader(programaShaders, shaderVertice);
    glAttachShader(programaShaders, shaderFragmento);
    glLinkProgram(programaShaders);
    glGetProgramiv(programaShaders, GL_LINK_STATUS, &operacaoBemSucedida);

    if (!operacaoBemSucedida) {
        glGetProgramInfoLog(programaShaders, 512, NULL, registroErro);
        std::cerr << "Erro ao ligar o programa de shaders:\n" << registroErro << std::endl;
    }

    glDeleteShader(shaderVertice);
    glDeleteShader(shaderFragmento);

    // --- OBJETO DE ARRAY DE VÉRTICES E OBJETO DE BUFFER DE VÉRTICES ---
    unsigned int objetoBufferVertices;
    unsigned int objetoArrayVertices;

    glGenVertexArrays(1, &objetoArrayVertices);
    glGenBuffers(1, &objetoBufferVertices);

    glBindVertexArray(objetoArrayVertices);

    glBindBuffer(GL_ARRAY_BUFFER, objetoBufferVertices);
    glBufferData(GL_ARRAY_BUFFER, sizeof(verticesCubo), verticesCubo, GL_STATIC_DRAW);

    glVertexAttribPointer(
        0,                  
        3,                  
        GL_FLOAT,           
        GL_FALSE,           
        3 * sizeof(float),  
        (void*)0            
    );

    glEnableVertexAttribArray(0);

    // --- TESTE DE PROFUNDIDADE ---
    glEnable(GL_DEPTH_TEST);

    // --- LAÇO DE RENDERIZAÇÃO ---
    while (!glfwWindowShouldClose(janela)) {
        processarEntradaTeclado(janela);

        glClearColor(0.1f, 0.12f, 0.15f, 1.0f);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        glUseProgram(programaShaders);

        // --- MATRIZ DE MODELO: ROTAÇÃO DO CUBO AO LONGO DO TEMPO ---
        glm::mat4 matrizModelo = glm::rotate(
            glm::mat4(1.0f),
            (float)glfwGetTime(),
            glm::vec3(0.5f, 1.0f, 0.0f)
        );

        // --- MATRIZ DE VISÃO: CÂMERA AFASTADA 3 UNIDADES ---
        glm::mat4 matrizVisao = glm::translate(
            glm::mat4(1.0f),
            glm::vec3(0.0f, 0.0f, -3.0f)
        );

        // --- MATRIZ DE PROJEÇÃO: PERSPECTIVA COM CAMPO DE VISÃO DE 60 GRAUS ---
        glm::mat4 matrizProjecao = glm::perspective(
            glm::radians(60.0f),
            800.0f / 600.0f,
            0.1f,
            100.0f
        );

        glUniformMatrix4fv(glGetUniformLocation(programaShaders, "uMatrizModelo"),
                            1, GL_FALSE, glm::value_ptr(matrizModelo));
        glUniformMatrix4fv(glGetUniformLocation(programaShaders, "uMatrizVisao"),
                            1, GL_FALSE, glm::value_ptr(matrizVisao));
        glUniformMatrix4fv(glGetUniformLocation(programaShaders, "uMatrizProjecao"),
                            1, GL_FALSE, glm::value_ptr(matrizProjecao));

        glBindVertexArray(objetoArrayVertices);
        glDrawArrays(GL_TRIANGLES, 0, 36);

        glfwSwapBuffers(janela);
        glfwPollEvents();
    }

    // --- LIBERAÇÃO DE RECURSOS ---
    glDeleteVertexArrays(1, &objetoArrayVertices);
    glDeleteBuffers(1, &objetoBufferVertices);
    glDeleteProgram(programaShaders);

    glfwDestroyWindow(janela);
    glfwTerminate();

    return 0;
}
