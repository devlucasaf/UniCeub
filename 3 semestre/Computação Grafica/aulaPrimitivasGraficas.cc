// Computação Gráfica
// Aula: Primitivas gráficas, sistemas de referência e transformações gráficas

#include <SFML/Graphics.hpp>
#include <cmath>

// Utilitário simples para criar um eixo X/Y no mundo
sf::VertexArray makeAxes(float length = 400.f)
{
    sf::VertexArray axes(sf::Lines, 4);
    // Eixo X (vermelho)
    axes[0].position = { -length, 0.f };
    axes[1].position = {  length, 0.f };
    axes[0].color = sf::Color::Red;
    axes[1].color = sf::Color::Red;
    // Eixo Y (verde)
    axes[2].position = { 0.f, -length };
    axes[3].position = { 0.f,  length };
    axes[2].color = sf::Color::Green;
    axes[3].color = sf::Color::Green;
    return axes;
}

sf::VertexArray makeLine()
{
    sf::VertexArray line(sf::Lines, 2);
    line[0].position = { -50.f, 0.f };
    line[1].position = {  50.f, 0.f };
    line[0].color = sf::Color::Cyan;
    line[1].color = sf::Color::Cyan;
    return line;
}

sf::ConvexShape makeTriangle()
{
    sf::ConvexShape tri(3);
    tri.setPoint(0, { 0.f, -60.f });
    tri.setPoint(1, { 60.f, 60.f });
    tri.setPoint(2, { -60.f, 60.f });
    tri.setFillColor(sf::Color(255, 200, 50));
    tri.setOutlineColor(sf::Color::Black);
    tri.setOutlineThickness(2.f);
    return tri;
}

sf::RectangleShape makeRectangle()
{
    sf::RectangleShape rect({ 140.f, 90.f });
    rect.setOrigin(rect.getSize() / 2.f);
    rect.setFillColor(sf::Color(120, 180, 255));
    rect.setOutlineColor(sf::Color::Black);
    rect.setOutlineThickness(2.f);
    return rect;
}

int main()
{
    sf::RenderWindow window(sf::VideoMode(1000, 700), "CG em C++ (SFML): Primitivas, Referencias e Transformacoes");
    window.setFramerateLimit(120);

    // Sistema de referencia MUNDO: eixos desenhados na origem (0,0)
    auto axes = makeAxes();

    // Primitivas basicas (no sistema LOCAL de cada objeto)
    auto line = makeLine();
    auto tri  = makeTriangle();
    auto rect = makeRectangle();

    // Origem local dos shapes no centro (facilita rotacao/escala em torno do proprio centro)
    // Linha (VertexArray) nao tem transform, entao usaremos um sf::Transform para ela
    sf::Transform lineModel = sf::Transform::Identity;

    tri.setOrigin(0.f, 0.f);        // ja estah centrado pelo desenho dos pontos
    rect.setOrigin(rect.getOrigin()); // ja centrado acima

    // Parametros do modelo (transformacao LOCAL -> MUNDO)
    sf::Vector2f position(0.f, 0.f);
    float rotation = 0.f;           // graus
    float scale = 1.f;              // escala uniforme

    // View (sistema de referencia da CAMERA): controla MUNDO -> CAMERA
    sf::View view = window.getDefaultView();
    bool altView = false;           // alterna entre duas configuracoes de camera

    // Estado da primitiva selecionada
    enum class Primitive { Triangle, Line, Rectangle } current = Primitive::Triangle;

    while (window.isOpen())
    {
        // Eventos
        sf::Event ev{};
        while (window.pollEvent(ev))
        {
            if (ev.type == sf::Event::Closed)
                window.close();
            if (ev.type == sf::Event::KeyPressed)
            {
                if (ev.key.code == sf::Keyboard::Escape) window.close();

                // Seleciona a primitiva
                if (ev.key.code == sf::Keyboard::Num1) current = Primitive::Triangle;
                if (ev.key.code == sf::Keyboard::Num2) current = Primitive::Line;
                if (ev.key.code == sf::Keyboard::Num3) current = Primitive::Rectangle;

                // Alterna view
                if (ev.key.code == sf::Keyboard::V)
                {
                    altView = !altView;
                    if (altView)
                    {
                        // Zoom in e pan leve: simula mudar o sistema de referencia da camera
                        view = window.getDefaultView();
                        view.zoom(0.7f);           // aproxima
                        view.move({ 120.f, -60.f }); // desloca a camera
                    }
                    else
                    {
                        view = window.getDefaultView();
                    }
                    window.setView(view);
                }

                // Reset
                if (ev.key.code == sf::Keyboard::R)
                {
                    position = {0.f, 0.f};
                    rotation = 0.f;
                    scale    = 1.f;
                    lineModel = sf::Transform::Identity;
                }
            }
        }

        // Entrada continua (apoia segurar teclas)
        float move = 200.f * (1.f / 120.f); // aproximadamente por frame
        float rot  = 90.f  * (1.f / 120.f); // graus por frame
        float scl  = 1.0f  + 0.5f * (1.f / 120.f);

        if (sf::Keyboard::isKeyPressed(sf::Keyboard::Left))  position.x -= move;
        if (sf::Keyboard::isKeyPressed(sf::Keyboard::Right)) position.x += move;
        if (sf::Keyboard::isKeyPressed(sf::Keyboard::Up))    position.y -= move;
        if (sf::Keyboard::isKeyPressed(sf::Keyboard::Down))  position.y += move;
        if (sf::Keyboard::isKeyPressed(sf::Keyboard::Q))     rotation -= rot;
        if (sf::Keyboard::isKeyPressed(sf::Keyboard::E))     rotation += rot;
        if (sf::Keyboard::isKeyPressed(sf::Keyboard::Z))     scale /= scl;
        if (sf::Keyboard::isKeyPressed(sf::Keyboard::X))     scale *= scl;

        // Monta as transformacoes LOCAL->MUNDO (Model)
        // Em CG: M = T * R * S (ordem importa!)
        sf::Transform model = sf::Transform::Identity;
        model.translate(position);
        model.rotate(rotation);
        model.scale(scale, scale);

        // Render
        window.clear(sf::Color(30, 30, 40));

        // Desenha eixos do MUNDO (ajuda a visualizar referencias)
        window.draw(axes);

        // Desenha uma cruz local (sistema local do objeto), para enxergar origem local
        sf::VertexArray localAxes(sf::Lines, 4);
        localAxes[0].position = model.transformPoint({-40.f, 0.f});
        localAxes[1].position = model.transformPoint({ 40.f, 0.f});
        localAxes[2].position = model.transformPoint({ 0.f,-40.f});
        localAxes[3].position = model.transformPoint({ 0.f, 40.f});
        localAxes[0].color = sf::Color(200,80,80);
        localAxes[1].color = sf::Color(200,80,80);
        localAxes[2].color = sf::Color(80,200,120);
        localAxes[3].color = sf::Color(80,200,120);
        window.draw(localAxes);

        // Seleciona e desenha a primitiva com a transformacao de modelo
        switch (current)
        {
            case Primitive::Triangle:
            {
                sf::RenderStates rs; rs.transform = model;
                window.draw(tri, rs);
                break;
            }
            case Primitive::Line:
            {
                // Como VertexArray nao eh um Drawable com transform interno, passamos RenderStates
                sf::RenderStates rs; rs.transform = model;
                window.draw(line, rs);
                break;
            }
            case Primitive::Rectangle:
            {
                rect.setPosition(position);
                rect.setRotation(rotation);
                rect.setScale(scale, scale);
                window.draw(rect);
                break;
            }
        }

        // HUD simples (texto informativo)
        static sf::Font font;
        static bool fontLoaded = false;
        if (!fontLoaded)
        {
            // Carrega uma fonte padrao do sistema (tentativa simples)
            // Caso nao funcione no seu SO, substitua pelo caminho de uma fonte .ttf local
            fontLoaded = font.loadFromFile("C:/Windows/Fonts/arial.ttf");
        }
        if (fontLoaded)
        {
            sf::Text txt;
            txt.setFont(font);
            txt.setCharacterSize(16);
            txt.setFillColor(sf::Color::White);
            txt.setPosition(10.f, 10.f);
            txt.setString(
                "Primitivas: [1] Triangulo  [2] Linha  [3] Retangulo\n"
                "Transformacoes (Local->Mundo): Setas, Q/E (rot), Z/X (escala)\n"
                "Sistema de referencia da camera: [V] alterna view\n"
                "Reset [R]  |  Esc [Sair]"
            );
            window.draw(txt);
        }

        window.display();
    }

    return 0;
}
