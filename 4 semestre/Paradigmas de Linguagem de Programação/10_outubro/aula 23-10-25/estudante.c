/*
Paradigmas de Linguagem de Programação
Aula: 23-10-25
Slide 24: Exemplo Classe Estudante
*/

#include <stdio.h>

void chorarNota() {
    while(1) {
        printf("ponto extra\n");
    }
}

void pedirProvaEmDupla() {
    while(1) {
        printf("Professor, podemos fazer a prova em dupla?\n");
    }
}

void pedirAdiamentoDoTrabalho() {
    while(1) {
        printf("Professor, adia o trabalho, não consegui terminar\n");
    }
}

int main() {
    chorarNota();
    pedirProvaEmDupla();
    pedirAdiamentoDoTrabalho();

    return 0;
}

