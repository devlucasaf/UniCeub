// Código correto

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

// Código sem laço de repetição

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

// Código sem feedback de “alto” ou “baixo”

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
  } 
  
  while (palpite != resposta);
}

// Código com leitura fora do laço

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

// Código que sorteia novo número a cada tentativa

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
