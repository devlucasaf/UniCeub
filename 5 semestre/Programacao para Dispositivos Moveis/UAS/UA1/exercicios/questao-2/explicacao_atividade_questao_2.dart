
// Código Correto
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
            print("$temperatura graus Celsius equivale a ${temperatura * 1.8 + 32} graus Fahrenheit");
            break;
        }
        case "B": { 
            print("$temperatura graus Fahrenheit equivale a ${(temperatura - 32) / 1.8} graus Celsius");
            break;
        }
    }
}

// Código que imprime sempre as duas conversões (incorreto)
import 'dart:io';

void main() {
    print("Informe a temperatura:");
    String temper = stdin.readLineSync();
    int temperatura = int.parse(temper);

    print("$temperatura graus Celsius equivale a ${temperatura * 1.8 + 32} graus Fahrenheit");
    print("$temperatura graus Fahrenheit equivale a ${(temperatura - 32) / 1.8} graus Celsius");
}

// Código com condição errada no do...while

import 'dart:io';

void main() {
    print("A: Converter Celsius para Fahrenheit");
    print("B: Converter Fahrenheit para Celsius");

    String opcao;
    do {
        opcao = stdin.readLineSync().toUpperCase();
    } 
    
    while (opcao == "A" && opcao == "B"); // errado

    print("Informe a temperatura:");
    String temper = stdin.readLineSync();
    int temperatura = int.parse(temper);

    switch (opcao) {
        case "A":
            print("$temperatura graus Celsius equivale a ${temperatura * 1.8 + 32} graus Fahrenheit");
            break;
        case "B":
            print("$temperatura graus Fahrenheit equivale a ${(temperatura - 32) / 1.8} graus Celsius");
            break;
    }
}

// Código sem conversão de String para int

import 'dart:io';

void main() {
    print("A: Converter Celsius para Fahrenheit");
    print("B: Converter Fahrenheit para Celsius");

    String opcao;
    do {
        opcao = stdin.readLineSync().toUpperCase();
    } while (opcao != "A" && opcao != "B");

    print("Informe a temperatura:");
    String temperatura = stdin.readLineSync(); // não convertido para int

    switch (opcao) {
        case "A":
        print("$temperatura graus Celsius equivale a ${temperatura * 1.8 + 32} graus Fahrenheit");
        break;
        case "B":
        print("$temperatura graus Fahrenheit equivale a ${(temperatura - 32) / 1.8} graus Celsius");
        break;
    }
}

// Código sem break no switch

import 'dart:io';

void main() {
    print("A: Converter Celsius para Fahrenheit");
    print("B: Converter Fahrenheit para Celsius");

    String opcao;
    do {
        opcao = stdin.readLineSync().toUpperCase();
    } while (opcao != "A" && opcao != "B");

    print("Informe a temperatura:");
    String temper = stdin.readLineSync();
    int temperatura = int.parse(temper);

    switch (opcao) {
        case "A":
        print("$temperatura graus Celsius equivale a ${temperatura * 1.8 + 32} graus Fahrenheit");
        // faltou break
        case "B":
        print("$temperatura graus Fahrenheit equivale a ${(temperatura - 32) / 1.8} graus Celsius");
        // faltou break
    }
}
