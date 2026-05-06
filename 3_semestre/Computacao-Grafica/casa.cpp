#include <iostream>
#include <string>

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
        std::cerr << log << "\n";
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
        std::cerr << log << "\n";
        glDeleteProgram(program);

        return 0;
    }

    return program;
}

static void createShape(GLuint program, const float* vertices, int vertexCount) {
    GLuint vao = 0, vbo = 0;
    glGenVertexArrays(1, &vao);
    glGenBuffers(1, &vbo);

    glBindVertexArray(vao);
    glBindBuffer(GL_ARRAY_BUFFER, vbo);
    glBufferData(GL_ARRAY_BUFFER, (size_t)vertexCount * 2 * sizeof(float), vertices, GL_STATIC_DRAW);

    glVertexAttribPointer(0, 2, GL_FLOAT, GL_FALSE, 2 * sizeof(float), (void*)0);
    glEnableVertexAttribArray(0);

    glUseProgram(program);
    glBindVertexArray(vao);
    glDrawArrays(GL_LINE_STRIP, 0, vertexCount);

    glBindVertexArray(0);
    glDeleteBuffers(1, &vbo);
    glDeleteVertexArrays(1, &vao);
}

int main() {
    if (!glfwInit()) {
        return 1;
    }

    glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
    glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 3);
    glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);

    GLFWwindow* window = glfwCreateWindow(400, 400, "Formas em OpenGL", nullptr, nullptr);
    if (!window) {
        glfwTerminate();
        return 1;
    }

    glfwMakeContextCurrent(window);
    glfwSetFramebufferSizeCallback(window, framebuffer_size_callback);

    if (!gladLoadGLLoader((GLADloadproc)glfwGetProcAddress)) {
        glfwDestroyWindow(window);
        glfwTerminate();
        return 1;
    }

    const char* vsSource = R"(
        #version 330 core
        layout(location = 0) in vec2 coordinates;
        void main() {
            gl_Position = vec4(coordinates, 0.0, 1.0);
        }
    )";

    const char* fsSource = R"(
        #version 330 core
        out vec4 FragColor;
        void main() {
            FragColor = vec4(0.0, 0.0, 0.0, 1.0);
        }
    )";

    GLuint program = createProgram(vsSource, fsSource);
    if (!program) {
        glfwDestroyWindow(window);
        glfwTerminate();
        return 1;
    }

    float triangleVertices[] = {
        0.6f, -0.47f,
        0.0f,  0.0f,
        -0.6f, -0.47f
    };

    float squareVertices[] = {
        0.5f, -0.5f,
        0.5f, -1.0f,
        0.1f, -1.0f,
        0.1f, -0.7f,
        -0.1f, -0.7f,
        -0.1f, -1.0f,
        -0.5f, -1.0f,
        -0.5f, -0.5f
    };

    float verticesJanela[] = {
        0.2f, -0.6f,
        0.4f, -0.6f,
        0.4f, -0.8f,
        0.2f, -0.8f,
        0.2f, -0.6f
    };

    float verticesJanela2[] = {
        -0.2f, -0.6f,
        -0.4f, -0.6f,
        -0.4f, -0.8f,
        -0.2f, -0.8f,
        -0.2f, -0.6f
    };

    while (!glfwWindowShouldClose(window)) {
        glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        glClear(GL_COLOR_BUFFER_BIT);

        createShape(program, triangleVertices, 3);
        createShape(program, squareVertices, 8);
        createShape(program, verticesJanela, 5);
        createShape(program, verticesJanela2, 5);

        glfwSwapBuffers(window);
        glfwPollEvents();
    }

    glDeleteProgram(program);
    glfwDestroyWindow(window);
    glfwTerminate();
    
    return 0;
}
