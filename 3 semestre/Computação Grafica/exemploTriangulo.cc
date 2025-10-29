#include <iostream>
#include <cmath>
#include <iomanip>
using namespace std;

// Estrutura que representa um ponto no plano
struct Ponto {
    double x, y;
};

// Função para calcular a distância entre dois pontos
double distancia(Ponto a, Ponto b) {
    return sqrt(pow(b.x - a.x, 2) + pow(b.y - a.y, 2));
}

// Função para calcular a área de um triângulo usando a fórmula de Heron
double areaTriangulo(Ponto a, Ponto b, Ponto c) {
    double ab = distancia(a, b);
    double bc = distancia(b, c);
    double ca = distancia(c, a);
    double s = (ab + bc + ca) / 2.0;
    return sqrt(s * (s - ab) * (s - bc) * (s - ca));
}

// Função para imprimir as informações do triângulo
void mostrarInformacoes(Ponto a, Ponto b, Ponto c) {
    double ab = distancia(a, b);
    double bc = distancia(b, c);
    double ca = distancia(c, a);
    double perimetro = ab + bc + ca;
    double area = areaTriangulo(a, b, c);

    cout << fixed << setprecision(2);
    cout << "\n===== TRIÂNGULO =====\n";
    cout << "A(" << a.x << ", " << a.y << ")\n";
    cout << "B(" << b.x << ", " << b.y << ")\n";
    cout << "C(" << c.x << ", " << c.y << ")\n";

    cout << "\nLados:\n";
    cout << "AB = " << ab << "\n";
    cout << "BC = " << bc << "\n";
    cout << "CA = " << ca << "\n";

    cout << "\nPerímetro: " << perimetro << endl;
    cout << "Área: " << area << endl;

    // Verifica o tipo do triângulo
    if (fabs(ab - bc) < 1e-6 && fabs(bc - ca) < 1e-6)
        cout << "Tipo: Equilátero\n";
    else if (fabs(ab - bc) < 1e-6 || fabs(bc - ca) < 1e-6 || fabs(ab - ca) < 1e-6)
        cout << "Tipo: Isósceles\n";
    else
        cout << "Tipo: Escaleno\n";
}

int main() {
    Ponto a, b, c;

    cout << "Digite as coordenadas dos três pontos do triângulo:\n";
    cout << "Ponto A (x y): ";
    cin >> a.x >> a.y;
    cout << "Ponto B (x y): ";
    cin >> b.x >> b.y;
    cout << "Ponto C (x y): ";
    cin >> c.x >> c.y;

    mostrarInformacoes(a, b, c);

    return 0;
}
