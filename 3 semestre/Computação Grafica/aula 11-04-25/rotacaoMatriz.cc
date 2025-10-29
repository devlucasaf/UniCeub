#include <iostream>
#include <vector>
#include <cmath>
#include <iomanip>

#define PI 3.14159265
#define N 2

using namespace std;

int main() {
    int numDePontos;
    double teta;
    vector<double> pontoRotacao(N), vet(N), vetResultado(N);

    cout << fixed << setprecision(2);

    cout << "Digite o ponto de eixo de rotacao:\n";
    cout << "Digite o valor de X: ";
    cin >> pontoRotacao[0];
    cout << "Digite o valor de Y: ";
    cin >> pontoRotacao[1];

    cout << "\nDigite quantos graus o ponto vai ser rotacionado: ";
    cin >> teta;
    teta = (teta * PI / 180.0);

    // matriz de rotacao
    double A[N][N] = {
        {cos(teta), -sin(teta)},
        {sin(teta),  cos(teta)}
    };

    cout << "\nDigite o numero de pontos a rotacionar: ";
    cin >> numDePontos;

    for (int w = 0; w < numDePontos; ++w) {
        cout << "\nPonto " << w + 1 << ":\n";
        cout << "Digite o valor de X: ";
        cin >> vet[0];
        cout << "Digite o valor de Y: ";
        cin >> vet[1];

        cout << "\nCoordenada Original Ponto " << w + 1 << ":\n";
        cout << "Ponto " << w + 1 << ": (" << vet[0] << ", " << vet[1] << ")\n";

        // Translada para a origem
        for (int z = 0; z < N; ++z)
            vet[z] -= pontoRotacao[z];

        // Aplica a matriz de rotação
        for (int i = 0; i < N; ++i) {
            double soma = 0;
            for (int j = 0; j < N; ++j)
                soma += A[i][j] * vet[j];
            vetResultado[i] = soma;
        }

        // Retorna à posição original
        for (int z = 0; z < N; ++z)
            vetResultado[z] += pontoRotacao[z];

        cout << "\nResultado da rotacao:\n";
        cout << "Ponto " << w + 1 << ": (" << vetResultado[0] << ", " << vetResultado[1] << ")\n";
    }

    return 0;
}
