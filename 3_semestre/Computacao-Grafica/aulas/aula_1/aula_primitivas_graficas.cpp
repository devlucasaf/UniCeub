// --- COMPUTAÇÃO GRÁFICA ---
// --- AULA: PRIMITIVAS GRÁFICAS, SISTEMAS DE REFERÊNCIA E TRANSFORMAÇÕES GRÁFICAS ---

#include <SFML/Graphics.hpp>
#include <cmath>

// --- EIXOS DO SISTEMA DE REFERÊNCIA DO MUNDO ---
sf::VertexArray criarEixosGlobais(float comprimento = 400.f) {
    sf::VertexArray eixosGlobais(sf::Lines, 4);

    eixosGlobais[0].position = { -comprimento, 0.f };
    eixosGlobais[1].position = {  comprimento, 0.f };
    eixosGlobais[0].color = sf::Color::Red;
    eixosGlobais[1].color = sf::Color::Red;

    eixosGlobais[2].position = { 0.f, -comprimento };
    eixosGlobais[3].position = { 0.f,  comprimento };
    eixosGlobais[2].color = sf::Color::Green;
    eixosGlobais[3].color = sf::Color::Green;

    return eixosGlobais;
}

// --- PRIMITIVA: LINHA ---
sf::VertexArray criarLinha() {
    sf::VertexArray linha(sf::Lines, 2);

    linha[0].position = { -50.f, 0.f };
    linha[1].position = {  50.f, 0.f };
    linha[0].color = sf::Color::Cyan;
    linha[1].color = sf::Color::Cyan;

    return linha;
}

// --- PRIMITIVA: TRIÂNGULO ---
sf::ConvexShape criarTriangulo() {
    sf::ConvexShape triangulo(3);

    triangulo.setPoint(0, { 0.f, -60.f });
    triangulo.setPoint(1, { 60.f, 60.f });
    triangulo.setPoint(2, { -60.f, 60.f });
    triangulo.setFillColor(sf::Color(255, 200, 50));
    triangulo.setOutlineColor(sf::Color::Black);
    triangulo.setOutlineThickness(2.f);

    return triangulo;
}

// --- PRIMITIVA: RETÂNGULO ---
sf::RectangleShape criarRetangulo() {
    sf::RectangleShape retangulo({ 140.f, 90.f });

    retangulo.setOrigin(retangulo.getSize() / 2.f);
    retangulo.setFillColor(sf::Color(120, 180, 255));
    retangulo.setOutlineColor(sf::Color::Black);
    retangulo.setOutlineThickness(2.f);

    return retangulo;
}

int main() {
    sf::RenderWindow janela(sf::VideoMode(1000, 700), L"CG em C++ (SFML): Primitivas, Referências e Transformações");
    janela.setFramerateLimit(120);

    auto eixosGlobais = criarEixosGlobais();
    auto linha = criarLinha();
    auto triangulo = criarTriangulo();
    auto retangulo = criarRetangulo();

    sf::Transform transformacaoModeloLinha = sf::Transform::Identity;

    triangulo.setOrigin(0.f, 0.f);
    retangulo.setOrigin(retangulo.getOrigin());

    // --- ESTADO DAS TRANSFORMAÇÕES ---
    sf::Vector2f posicao(0.f, 0.f);

    float anguloRotacao = 0.f;
    float fatorEscala   = 1.f;

    // --- SISTEMA DE REFERÊNCIA DA CÂMERA ---
    sf::View camera = janela.getDefaultView();

    bool cameraAlternativaAtiva = false;

    enum class Primitiva { Triangulo, Linha, Retangulo } primitivaAtual = Primitiva::Triangulo;

    while (janela.isOpen()) {
        // --- TRATAMENTO DE EVENTOS ---
        sf::Event evento{};
        while (janela.pollEvent(evento)) {
            if (evento.type == sf::Event::Closed) {
                janela.close();
            }

            if (evento.type == sf::Event::KeyPressed) {
                if (evento.key.code == sf::Keyboard::Escape) {
                    janela.close();
                }

                if (evento.key.code == sf::Keyboard::Num1) {
                    primitivaAtual = Primitiva::Triangulo;
                }

                if (evento.key.code == sf::Keyboard::Num2) {
                    primitivaAtual = Primitiva::Linha;
                }

                if (evento.key.code == sf::Keyboard::Num3) {
                    primitivaAtual = Primitiva::Retangulo;
                }

                if (evento.key.code == sf::Keyboard::V) {
                    cameraAlternativaAtiva = !cameraAlternativaAtiva;
                    if (cameraAlternativaAtiva) {
                        camera = janela.getDefaultView();
                        camera.zoom(0.7f);
                        camera.move({ 120.f, -60.f });
                    } else {
                        camera = janela.getDefaultView();
                    }
                    janela.setView(camera);
                }

                if (evento.key.code == sf::Keyboard::R) {
                    posicao = { 0.f, 0.f };
                    anguloRotacao = 0.f;
                    fatorEscala = 1.f;
                    transformacaoModeloLinha = sf::Transform::Identity;
                }
            }
        }

        // --- VELOCIDADES POR QUADRO ---
        float deslocamentoPorQuadro = 200.f * (1.f / 120.f);
        float rotacaoPorQuadro = 90.f  * (1.f / 120.f);
        float escalaPorQuadro = 1.0f  + 0.5f * (1.f / 120.f);

        // --- TRANSLAÇÃO ---
        if (sf::Keyboard::isKeyPressed(sf::Keyboard::Left)) {
            posicao.x -= deslocamentoPorQuadro;
        }

        if (sf::Keyboard::isKeyPressed(sf::Keyboard::Right)) {
            posicao.x += deslocamentoPorQuadro;
        }

        if (sf::Keyboard::isKeyPressed(sf::Keyboard::Up)) {
            posicao.y -= deslocamentoPorQuadro;
        }

        if (sf::Keyboard::isKeyPressed(sf::Keyboard::Down)) {
            posicao.y += deslocamentoPorQuadro;
        }

        // --- ROTAÇÃO ---
        if (sf::Keyboard::isKeyPressed(sf::Keyboard::Q)) {
            anguloRotacao -= rotacaoPorQuadro;
        }

        if (sf::Keyboard::isKeyPressed(sf::Keyboard::E)) {
            anguloRotacao += rotacaoPorQuadro;
        }

        // --- ESCALA ---
        if (sf::Keyboard::isKeyPressed(sf::Keyboard::Z)) {
            fatorEscala /= escalaPorQuadro;
        }

        if (sf::Keyboard::isKeyPressed(sf::Keyboard::X)) {
            fatorEscala *= escalaPorQuadro;
        }

        // --- MATRIZ DE MODELO (LOCAL -> MUNDO) ---
        sf::Transform transformacaoModelo = sf::Transform::Identity;
        transformacaoModelo.translate(posicao);
        transformacaoModelo.rotate(anguloRotacao);
        transformacaoModelo.scale(fatorEscala, fatorEscala);

        janela.clear(sf::Color(30, 30, 40));

        janela.draw(eixosGlobais);

        // --- EIXOS DO SISTEMA DE REFERÊNCIA LOCAL ---
        sf::VertexArray eixosLocais(sf::Lines, 4);

        eixosLocais[0].position = transformacaoModelo.transformPoint({ -40.f, 0.f });
        eixosLocais[1].position = transformacaoModelo.transformPoint({  40.f, 0.f });
        eixosLocais[2].position = transformacaoModelo.transformPoint({ 0.f, -40.f });
        eixosLocais[3].position = transformacaoModelo.transformPoint({ 0.f,  40.f });
        eixosLocais[0].color = sf::Color(200, 80, 80);
        eixosLocais[1].color = sf::Color(200, 80, 80);
        eixosLocais[2].color = sf::Color(80, 200, 120);
        eixosLocais[3].color = sf::Color(80, 200, 120);

        janela.draw(eixosLocais);

        // --- DESENHO DA PRIMITIVA SELECIONADA ---
        switch (primitivaAtual) {
            case Primitiva::Triangulo: {
                sf::RenderStates estadosRenderizacao;
                estadosRenderizacao.transform = transformacaoModelo;
                janela.draw(triangulo, estadosRenderizacao);
                break;
            }
            case Primitiva::Linha: {
                sf::RenderStates estadosRenderizacao;
                estadosRenderizacao.transform = transformacaoModelo;
                janela.draw(linha, estadosRenderizacao);
                break;
            }
            case Primitiva::Retangulo: {
                retangulo.setPosition(posicao);
                retangulo.setRotation(anguloRotacao);
                retangulo.setScale(fatorEscala, fatorEscala);
                janela.draw(retangulo);
                break;
            }
        }

        // --- TEXTO DE AJUDA ---
        static sf::Font fonte;
        static bool fonteCarregada = false;

        if (!fonteCarregada) {
            fonteCarregada = fonte.loadFromFile("C:/Windows/Fonts/arial.ttf");
        }

        if (fonteCarregada) {
            sf::Text textoAjuda;

            textoAjuda.setFont(fonte);
            textoAjuda.setCharacterSize(16);
            textoAjuda.setFillColor(sf::Color::White);
            textoAjuda.setPosition(10.f, 10.f);
            textoAjuda.setString(
                L"Primitivas: [1] Triângulo  [2] Linha  [3] Retângulo\n"
                L"Transformações (Local -> Mundo): Setas (translação), Q/E (rotação), Z/X (escala)\n"
                L"Sistema de referência da câmera: [V] alterna a visão\n"
                L"Reiniciar [R]  |  Sair [Esc]"
            );

            janela.draw(textoAjuda);
        }

        janela.display();
    }

    return 0;
}
