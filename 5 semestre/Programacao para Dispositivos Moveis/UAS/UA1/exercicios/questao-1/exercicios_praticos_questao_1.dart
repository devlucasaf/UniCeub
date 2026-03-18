// Programação para Dispositivos Móveis
// Unidade de Aprendizagem 1 - Questão 1-
// 18-03-2026

import 'dart:math';
import 'dart:io';

// Alternativa A
void main() {
    int palpite;
    Random rand = new Random();
    int resposta = rand.nextInt(100);
    do {
        print("Tente adivinhar o número:");
        String temp = stdin.readLineSync();
        palpite = int.parse(temp);

        if (palpite < resposta) {
            print("Valor muito baixo!");
        } 
        
        else if (palpite > resposta) {
            print("Valor muito alto!");
        }
    } 
    
    while (palpite != resposta);
        print("Acertou!");
}

// Alternativa B

import 'dart:math'; 
import 'dart:io'; 

void main() { 
    int palpite; 

    Random rand = new Random(); 
    int resposta = rand.nextInt(100); 
    print("Tente adivinhar o número:"); 
    String temp = stdin.readLineSync(); 
    palpite = int.parse(temp); 

    if (palpite < resposta) { 
        print("Valor muito baixo!"); 
    } 
    
    else if (palpite > resposta) { 
        print("Valor muito alto!"); 
    } 

    else {
        print("Acertou!"); 
    }
}

// Alternativa C

import 'dart:math'; 
import 'dart:io';

void main() {
    int palpite;

    Random rand = new Random();

    int resposta = rand.nextInt(100);

    do {
        print("Tente adivinhar o número:");
        String temp = stdin.readLineSync();

        palpite = int.parse(temp);

        if (palpite == resposta) {
            print("Acertou!");
        }

        while (palpite != resposta);
    }
}

// Alternativa D

import 'dart:math'; 
import 'dart:io'; 

void main() { 
    int palpite; 

    Random rand = new Random();

    int resposta = rand.nextInt(100); 
    print("Tente adivinhar o número:"); 
    
    String temp = stdin.readLineSync(); 
    palpite = int.parse(temp); 
    
    do { 
        if (palpite < resposta) { 
            print("Valor muito baixo!"); 
        } 
        
        else if (palpite > resposta) { 
            print("Valor muito alto!"); 
        } 
    } 
    
    while (palpite != resposta); 
    
    print("Acertou!"); 
}

// Alternativa E

import 'dart:math'; 
import 'dart:io'; 

void main() { 
    int resposta; 
    int palpite; 
    
    do { 
        Random rand = new Random(); 
        
        resposta = rand.nextInt(100); 
        print("Tente adivinhar o número:"); 
        
        String temp = stdin.readLineSync(); 
        
        palpite = int.parse(temp); 
        
        if (palpite < resposta) { 
            print("Valor muito baixo!"); 
        } 
        
        else if (palpite > resposta) { 
            print("Valor muito alto!"); 
        } 
    } 
    
    while (palpite != resposta); 
    
    print("Acertou!"); 
}
