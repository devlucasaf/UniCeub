// Alternativa incorreta (tipo de retorno errado)

void calcularMedia(double n1, double n2) {
    double media = (n1 + n2) / 2;
}

// Alternativa incorreta (apenas imprime)

void calcularMedia(double n1, double n2) {
    print((n1 + n2) / 2);
}

// Alternativa incorreta (sem parâmetros)

void calcularMedia() {
    double n1;
    double n2;
    return (n1 + n2) / 2;
}

// Alternativa incorreta (cálculo errado)

double calcularMedia(double n1, double n2) {
    double media = n1 + n2 / 2;
    return media;
}

// Alternativa correta

double calcularMedia(double n1, double n2) {
    double media = (n1 + n2) / 2;
    return media;
}
