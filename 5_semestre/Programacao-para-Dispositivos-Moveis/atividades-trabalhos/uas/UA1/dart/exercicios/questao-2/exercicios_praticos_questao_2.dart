// Programação para Dispositivos Móveis
// Unidade de Aprendizagem 1 - Questão 2

// Sintaxe

switch(expressao) {
  case valor 1: {
      // intruções
  }
  break;
  case valor 2: {
      // intruções
  }
  break;
  default: {
      // intruções
  }
  break;
}

import 'dart:io';

void main() {
  print("A: Converter Celsius para Fahrenheit");
  print("B: Converter Fahrenheit para Celsius");

  String opcao;
  do {
    opcao = stdin.readLineSync().toUpperCase();
  } 
  
  while (opcao != "A" && opcao != "B");

  print("Informe a temperatura:");
  String temper = stdin.readLineSync();
  int temperatura = int.parse(temper);

  switch (opcao) {
    case "A":{
      print("$temperatura graus Celsius equivale a ${temperatura * 1.8 + 32} graus Fahrenheit");
      break;
    }
    case "B":{
      print("$temperatura graus Fahrenheit equivale a ${(temperatura - 32) / 1.8} graus Celsius");
      break;
    }
  }
}

// Alternativa B

import 'dart:io';

void main() {
  print("Informe a temperatura:");
  String temper = stdin.readLineSync();
  int temperatura = int.parse(temper);

  print("$temperatura graus Celsius equivale a ${temperatura * 1.8 + 32} graus Fahrenheit");
  print("$temperatura graus Fahrenheit equivale a ${(temperatura - 32) / 1.8} graus Celsius");
}

// Alternativa C

import 'dart:io';

void main() {
  print("A: Converter Celsius para Fahrenheit");
  print("B: Converter Fahrenheit para Celsius");

  String opcao;
  do {
    opcao = stdin.readLineSync().toUpperCase();
  } 
  
  while (opcao != "A" && opcao != "B");

  print("Informe a temperatura:");
  String temper = stdin.readLineSync();
  int temperatura = int.parse(temper);

  switch (opcao) {
    case "A": {
      print(
          "$temperatura graus Celsius equivale a ${temperatura * 1.8 + 32} graus Fahrenheit");
      break;
    }
    case "B": {
      print(
          "$temperatura graus Fahrenheit equivale a ${(temperatura - 32) / 1.8} graus Celsius");
      break;
    }
  }
}

// Alternativa D

import 'dart:io';

void main() {
  print("A: Converter Celsius para Fahrenheit");
  print("B: Converter Fahrenheit para Celsius");

  String opcao;
  do {
    opcao = stdin.readLineSync().toUpperCase();
  } 

  while (opcao != "A" && opcao != "B");

  print("Informe a temperatura:");
  String temperatura = stdin.readLineSync();

  switch (opcao) {
    case "A": {
      print(
          "$temperatura graus Celsius equivale a ${double.parse(temperatura) * 1.8 + 32} graus Fahrenheit");
      break;
    }
    case "B": {
      print(
          "$temperatura graus Fahrenheit equivale a ${(double.parse(temperatura) - 32) / 1.8} graus Celsius");
      break;
    }
  }
}

// Alternativa E

import 'dart:io';

void main() {
  print("A: Converter Celsius para Fahrenheit");
  print("B: Converter Fahrenheit para Celsius");

  String opcao;
  do {
    opcao = stdin.readLineSync().toUpperCase();
  } 
  
  while (opcao != "A" && opcao != "B");

  print("Informe a temperatura:");
  String temper = stdin.readLineSync();
  int temperatura = int.parse(temper);

  switch (opcao) {
    case "A": {
      print(
          "$temperatura graus Celsius equivale a ${temperatura * 1.8 + 32} graus Fahrenheit");
      break;
    }
    case "B": {
      print(
          "$temperatura graus Fahrenheit equivale a ${(temperatura - 32) / 1.8} graus Celsius");
      break;
    }
  }
}
