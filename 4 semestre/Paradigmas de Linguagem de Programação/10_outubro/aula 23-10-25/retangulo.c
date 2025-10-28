#include <studio.h>

struct retangula{
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