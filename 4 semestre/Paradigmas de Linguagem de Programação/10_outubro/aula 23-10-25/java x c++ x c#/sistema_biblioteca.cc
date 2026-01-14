/*
Paradigmas de Linguagem de Programação
Aula: 23-10-25
Slide 9: Diferença entre Java vs C++ vs C#
Exemplo em C++ - Sistema de Biblioteca
*/

#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

class Livro {
private:
    string titulo;
    string autor;
    bool emprestado;

public:
    Livro(string t, string a) : titulo(t), autor(a), emprestado(false) {}

    string getTitulo() const { return titulo; }
    string getAutor() const { return autor; }
    bool isEmprestado() const { return emprestado; }

    void emprestar() { emprestado = true; }
    void devolver() { emprestado = false; }

    void exibir() const {
        cout << titulo << " - " << autor << (emprestado ? " (Emprestado)" : " (Disponível)") << endl;
    }
};

class Biblioteca {
private:
    vector<Livro> livros;

public:
    void adicionarLivro(string titulo, string autor) {
        livros.push_back(Livro(titulo, autor));
    }

    void listarLivros() const {
        for (size_t i = 0; i < livros.size(); ++i) {
            cout << i + 1 << ". ";
            livros[i].exibir();
        }
    }

    void buscarPorTitulo(string titulo) const {
        for (const auto& livro : livros) {
            if (livro.getTitulo() == titulo) {
                cout << "Encontrado: ";
                livro.exibir();
                return;
            }
        }
        cout << "Livro não encontrado." << endl;
    }

    void emprestarLivro(int indice) {
        if (indice >= 0 && indice < livros.size()) {
            if (!livros[indice].isEmprestado()) {
                livros[indice].emprestar();
                cout << "Livro emprestado com sucesso." << endl;
            } 
            else {
                cout << "Livro já está emprestado." << endl;
            }
        } 
        else {
            cout << "Índice inválido." << endl;
        }
    }

    void devolverLivro(int indice) {
        if (indice >= 0 && indice < livros.size()) {
            if (livros[indice].isEmprestado()) {
                livros[indice].devolver();
                cout << "Livro devolvido com sucesso." << endl;
            } else {
                cout << "Livro não está emprestado." << endl;
            }
        } else {
            cout << "Índice inválido." << endl;
        }
    }
};

int main() {
    Biblioteca biblioteca;
    int opcao;

    do {
        cout << "\n--- Menu ---\n";
        cout << "1. Adicionar livro\n";
        cout << "2. Listar livros\n";
        cout << "3. Buscar por título\n";
        cout << "4. Emprestar livro\n";
        cout << "5. Devolver livro\n";
        cout << "0. Sair\n";
        cout << "Escolha uma opção: ";
        cin >> opcao;
        cin.ignore();

        switch (opcao) {
            case 1: {
                string titulo, autor;
                cout << "Título: ";
                getline(cin, titulo);
                cout << "Autor: ";
                getline(cin, autor);
                biblioteca.adicionarLivro(titulo, autor);
                break;
            }
            case 2:
                biblioteca.listarLivros();
                break;
            case 3: {
                string busca;
                cout << "Digite o título: ";
                getline(cin, busca);
                biblioteca.buscarPorTitulo(busca);
                break;
            }
            case 4: {
                biblioteca.listarLivros();
                int indice;
                cout << "Digite o número do livro para emprestar: ";
                cin >> indice;
                biblioteca.emprestarLivro(indice - 1);
                break;
            }
            case 5: {
                biblioteca.listarLivros();
                int indice;
                cout << "Digite o número do livro para devolver: ";
                cin >> indice;
                biblioteca.devolverLivro(indice - 1);
                break;
            }
            case 0:
                cout << "Encerrando..." << endl;
                break;
            default:
                cout << "Opção inválida." << endl;
        }
    } while (opcao != 0);

    return 0;
}
