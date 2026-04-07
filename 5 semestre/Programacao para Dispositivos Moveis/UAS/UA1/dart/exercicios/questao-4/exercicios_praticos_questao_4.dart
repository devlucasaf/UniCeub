// Alternativa A

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

// Alternativa B

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

// Alternativa C

import 'dart:math';

void main() {
  const int ITERACOES = 1000;
  double serie = 1.0;
  double denominador = 3.0;
  double sinal = -1.0;

  for (int i = 0; i < ITERACOES; i++) {
    serie += (sinal * (1 / denominador));
    denominador += 2.0;
  }

  double pi = 4 * serie;
  print("Valor calculado de PI: $pi");
}

// Alternativa D

import 'dart:math';

void main() {
  const int ITERACOES = 1000;
  double serie = 1.0;
  double denominador = 3.0;
  double sinal = -1.0;

  for (int i = 0; i < ITERACOES; i++) {
    serie += (sinal * (1 / denominador));
    denominador++;
    sinal *= -1.0;
  }

  double pi = 4 * serie;
  print("Valor calculado de PI: $pi");
}

// Alternativa E

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
