// Alternativa Correta

import 'dart:math';

void main() {
    const int ITERACOES = 1000;
    double serie = 1.0;
    double denominador = 3.0;
    double sinal = -1.0;

    for (int i = 0; i < ITERACOES; i++) {
        serie += (sinal * (1 / denominador));
        denominador += 2.0;
        sinal *= -1.0;
    }

    double pi = 4 * serie;
    print("Valor calculado de PI: $pi");
}

// Alternativa com poucas iterações

import 'dart:math';

void main() {
    const int ITERACOES = 10;
    double serie = 1.0;
    double denominador = 3.0;
    double sinal = -1.0;

    for (int i = 0; i < ITERACOES; i++) {
        serie += (sinal * (1 / denominador));
        denominador += 2.0;
        sinal *= -1.0;
    }

    double pi = 4 * serie;
    print("Valor calculado de PI: $pi");
}

// Alternativa sem alternância de sinal

import 'dart:math';

void main() {
    const int ITERACOES = 1000;
    double serie = 1.0;
    double denominador = 3.0;
    double sinal = -1.0;

    for (int i = 0; i < ITERACOES; i++) {
        serie += (sinal * (1 / denominador));
        denominador += 2.0;
        // faltou sinal *= -1.0;
    }

    double pi = 4 * serie;
    print("Valor calculado de PI: $pi");
}

// Alternativa com denominador incorreto

import 'dart:math';

void main() {
    const int ITERACOES = 1000;
    double serie = 1.0;
    double denominador = 3.0;
    double sinal = -1.0;

    for (int i = 0; i < ITERACOES; i++) {
        serie += (sinal * (1 / denominador));
        denominador++; // errado, deveria ser +2
        sinal *= -1.0;
    }

    double pi = 4 * serie;
    print("Valor calculado de PI: $pi");
}

// Alternativa sem multiplicar por 4

import 'dart:math';

void main() {
    const int ITERACOES = 1000;
    double pi = 1.0;
    double denominador = 3.0;
    double sinal = -1.0;

    for (int i = 0; i < ITERACOES; i++) {
        pi += (sinal * (1 / denominador));
        denominador += 2.0;
        sinal *= -1.0;
    }

    print("Valor calculado de PI: $pi");
}

