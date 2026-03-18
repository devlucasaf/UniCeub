import 'dart:math';

void main() {
    const int TENTATIVAS = 1000000;
    int corretas = 0;
    Random rand = new Random();

    for (int i = 0; i < TENTATIVAS; i++) {
        int portaPremio = rand.nextInt(3) + 1;
        int escolha = 1;
        int eliminada;

        if (portaPremio == 2) {
            eliminada = 3; // porta 3 eliminada
        } 
        
        else if (portaPremio == 3) {
            eliminada = 2; // porta 2 eliminada
        } 
        
        else { // portaPremio == 1
            eliminada = rand.nextInt(2) + 2; // porta 2 ou 3 eliminada randomicamente
        }

        if (eliminada == 2) {
            escolha = 3; // mudar escolha da porta 1 para porta 3
        } 
        
        else if (eliminada == 3) {
            escolha = 2; // mudar escolha da porta 1 para porta 2
        }

        if (escolha == portaPremio) {
            corretas++;
        }
    }

    print("A porcentagem de escolhas corretas foi ${(corretas / TENTATIVAS) * 100}%");
}

// Alternativa B

if (portaPremio == 2) {
    eliminada = 3; //porta 3 eliminada
} 

else if (portaPremio == 3) {
    eliminada = 2; //porta 2 eliminada
} 

else { //portaPremio 1
    eliminada = rand.nextInt(2) + 2; //porta 2 ou 3 eliminada randomicamente
}

// Alternativa D

if (eliminada == 2) {
    escolha = 3; //mudar escolha da porta 1 para porta 3
} 

else if (eliminada == 3) {
    escolha = 2; //mudar escolhada porta 1 para porta 2
}

if (escolha == portaPremio) {
    corretas++;
}

// Alternativa E

// A instrução:
print("A porcentagem de escolhas corretas foi ${(corretas / TENTATIVAS) * 100}%");
// está incorreta pois deveria na verdade ser:
print("A porcentagem de escolhas corretas foi ${(TENTATIVAS/corretas) * 100}%");