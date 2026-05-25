#include <iostream>
#include <string>
#include <vector>
#include <cmath>

#include <glad/glad.h>
#include <GLFW/glfw3.h>

static void framebuffer_size_callback(GLFWwindow* window, int width, int height) {
    (void)window;
    glViewport(0, 0, width, height);
}

static GLuint compileShader(GLenum type, const char* src) {
    GLuint shader = glCreateShader(type);
    glShaderSource(shader, 1, &src, nullptr);
    glCompileShader(shader);

    GLint ok = 0;
    glGetShaderiv(shader, GL_COMPILE_STATUS, &ok);

    if (!ok) {
        GLint len = 0;
        glGetShaderiv(shader, GL_INFO_LOG_LENGTH, &len);
        std::string log(len, '\0');
        glGetShaderInfoLog(shader, len, nullptr, log.data());
        std::cerr << "Erro compilando shader: " << log << "\n";
        glDeleteShader(shader);

        return 0;
    }
    return shader;
}

static GLuint createProgram(const char* vsSrc, const char* fsSrc) {
    GLuint vs = compileShader(GL_VERTEX_SHADER, vsSrc);

    if (!vs) {
        return 0;
    }

    GLuint fs = compileShader(GL_FRAGMENT_SHADER, fsSrc);

    if (!fs) {
        glDeleteShader(vs);
        return 0;
    }

    GLuint program = glCreateProgram();
    glAttachShader(program, vs);
    glAttachShader(program, fs);
    glLinkProgram(program);

    glDeleteShader(vs);
    glDeleteShader(fs);

    GLint ok = 0;
    glGetProgramiv(program, GL_LINK_STATUS, &ok);

    if (!ok) {
        GLint len = 0;
        glGetProgramiv(program, GL_INFO_LOG_LENGTH, &len);
        std::string log(len, '\0');
        glGetProgramInfoLog(program, len, nullptr, log.data());
        std::cerr << "Erro linkando programa: " << log << "\n";
        glDeleteProgram(program);

        return 0;
    }

    return program;
}

static void makeOrthoLikeWebGL(float aspect, float out[16]) {
    const float zNear = -1.0f;
    const float zFar  =  1.0f;

    for (int i = 0; i < 16; i++) {
        out[i] = 0.0f;
    }

    out[0]  = 1.0f / aspect;
    out[5]  = 1.0f;
    out[10] = -2.0f / (zFar - zNear);
    out[14] = -(zFar + zNear) / (zFar - zNear);
    out[15] = 1.0f;
}

static void makeIdentity(float out[16]) {
    for (int i = 0; i < 16; i++) {
        out[i] = 0.0f;
    }

    out[0] = out[5] = out[10] = out[15] = 1.0f;
}

int main() {
    if (!glfwInit()) {
        std::cerr << "Falha ao inicializar GLFW.\n";
        return 1;
    }

    glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
    glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
    glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

    GLFWwindow* window = glfwCreateWindow(600, 480, "WebGL Demo (C++ OpenGL)", nullptr, nullptr);
    
    if (!window) {
        std::cerr << "Falha ao criar janela.\n";
        glfwTerminate();
        return 1;
    }

    glfwMakeContextCurrent(window);
    glfwSetFramebufferSizeCallback(window, framebuffer_size_callback);

    if (!gladLoadGLLoader((GLADloadproc)glfwGetProcAddress)) {
        std::cerr << "Falha ao inicializar GLAD.\n";
        glfwDestroyWindow(window);
        glfwTerminate();
        return 1;
    }

    const char* vsSource = R"(
        #version 330 core
        layout(location = 0) in vec2 aVertexPosition;

        uniform mat4 uModelViewMatrix;
        uniform mat4 uProjectionMatrix;

        void main() {
            gl_Position = uProjectionMatrix * uModelViewMatrix * vec4(aVertexPosition, 0.0, 1.0);
        }
    )";

    const char* fsSource = R"(
        #version 330 core
        out vec4 FragColor;
        void main() {
            FragColor = vec4(1.0, 1.0, 1.0, 1.0);
        }
    )";

    GLuint program = createProgram(vsSource, fsSource);
    
    if (!program) {
        glfwDestroyWindow(window);
        glfwTerminate();
        return 1;
    }

    GLint uProjLoc = glGetUniformLocation(program, "uProjectionMatrix");
    GLint uModelLoc = glGetUniformLocation(program, "uModelViewMatrix");

    float positions[] = {
        0.5f,  0.5f,
        -0.5f,  0.5f,
        0.5f, -0.5f,
        -0.5f, -0.5f
    };

    GLuint vao = 0, vbo = 0;
    glGenVertexArrays(1, &vao);
    glGenBuffers(1, &vbo);

    glBindVertexArray(vao);

    glBindBuffer(GL_ARRAY_BUFFER, vbo);
    glBufferData(GL_ARRAY_BUFFER, sizeof(positions), positions, GL_STATIC_DRAW);

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

    glEnable(GL_DEPTH_TEST);
    glDepthFunc(GL_LEQUAL);

    while (!glfwWindowShouldClose(window)) {
        glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
        glClearDepth(1.0f);
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        int w, h;

        glfwGetFramebufferSize(window, &w, &h);

        float aspect = (h == 0) ? 1.0f : (float)w / (float)h;

        float projection[16];
        float modelView[16];
        
        makeOrthoLikeWebGL(aspect, projection);
        makeIdentity(modelView);

        glUseProgram(program);
        glUniformMatrix4fv(uProjLoc, 1, GL_FALSE, projection);
        glUniformMatrix4fv(uModelLoc, 1, GL_FALSE, modelView);

        glBindVertexArray(vao);
        glDrawArrays(GL_TRIANGLE_STRIP, 0, 4);
        glBindVertexArray(0);

        glfwSwapBuffers(window);
        glfwPollEvents();
    }

    glDeleteBuffers(1, &vbo);
    glDeleteVertexArrays(1, &vao);
    glDeleteProgram(program);

    glfwDestroyWindow(window);
    glfwTerminate();
    return 0;
}
