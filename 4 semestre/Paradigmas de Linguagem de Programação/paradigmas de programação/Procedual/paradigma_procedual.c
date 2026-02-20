/*
    Paradigmas de Linguagens de Programação
    Paradigma Procedural - Sistema de Gerenciamento de Alunos
    Estudo prático sobre paradigma procedural 
	*CÓDIGO DESENVOLVIDO A FIM DE ESTUDOS! NÃO FOI PASSADO EM SALA!
*/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define MAX_ALUNOS 100
#define MAX_NOME 50

typedef struct {
    char nome[MAX_NOME];
    int idade;
    float notas[3];
} Aluno;

Aluno alunos[MAX_ALUNOS];
int totalAlunos = 0;

void adicionarAluno() {
    if (totalAlunos >= MAX_ALUNOS) {
        printf("Limite de alunos atingido!\n");
        return;
    }

    Aluno novo;
    printf("Digite o nome do aluno: ");
    fgets(novo.nome, MAX_NOME, stdin);
    novo.nome[strcspn(novo.nome, "\n")] = '\0'; 

    printf("Digite a idade do aluno: ");
    scanf("%d", &novo.idade);

    for (int i = 0; i < 3; i++) {
        printf("Digite a nota %d: ", i + 1);
        scanf("%f", &novo.notas[i]);
    }

    getchar(); // limpar buffer
    alunos[totalAlunos] = novo;
    totalAlunos++;

    printf("Aluno adicionado com sucesso!\n\n");
}

void listarAlunos() {
    if (totalAlunos == 0) {
        printf("Nenhum aluno cadastrado.\n\n");
        return;
    }

    printf("=== Lista de Alunos ===\n");
    for (int i = 0; i < totalAlunos; i++) {
        printf("Aluno %d:\n", i + 1);
        printf("Nome: %s\n", alunos[i].nome);
        printf("Idade: %d\n", alunos[i].idade);
        printf("Notas: %.2f, %.2f, %.2f\n\n",
                alunos[i].notas[0],
                alunos[i].notas[1],
                alunos[i].notas[2]);
    }
}

void calcularMediaAluno() {
    if (totalAlunos == 0) {
        printf("Nenhum aluno cadastrado.\n\n");
        return;
    }

    int indice;
    printf("Digite o número do aluno (1 a %d): ", totalAlunos);
    scanf("%d", &indice);

    if (indice < 1 || indice > totalAlunos) {
        printf("Aluno inválido.\n\n");
        return;
    }

    Aluno a = alunos[indice - 1];
    
    float soma = 0;

    for (int i = 0; i < 3; i++) {
        soma += a.notas[i];
    }
    
    float media = soma / 3.0;

    printf("Média do aluno %s: %.2f\n\n", a.nome, media);
}

void mostrarMenu() {
    printf("=== Sistema de Alunos ===\n");
    printf("1. Adicionar aluno\n");
    printf("2. Listar alunos\n");
    printf("3. Calcular média de um aluno\n");
    printf("4. Sair\n");
    printf("Escolha uma opção: ");
}

int main() {
    int opcao;

    do {
        mostrarMenu();
        scanf("%d", &opcao);
        getchar(); 

        switch (opcao) {
            case 1: {
                adicionarAluno();
                break;
            }

            case 2: {
                listarAlunos();
                break;
            }

            case 3: {
                calcularMediaAluno();
                break;
            }

            case 4: {
                printf("Encerrando o programa...\n");
                break;
            }

            default: {
                printf("Opção inválida!\n\n");
            }
        }
    } 
    while (opcao != 4);

    return 0;
}
