/*
Paradigmas de Linguagem de Programação
Aula: 23-10-25
Slide 27: Linguagem Procedural
*/

#include <studio.h>

struct retangulo{
    float base;
    float altura;
    float area;
};

typedef struct retangulo Retangulo;

float calculaAreaRetangulo(Retangulo r){
    return r.base * r.altura;
}

int main(){
    Retangulo r;
    r.base = 10;
    r.altura = 10;
    r.area = calculaAreaRetangulo(r);
    
    printf("Area = %f\n", r.area);

    return 0;
}