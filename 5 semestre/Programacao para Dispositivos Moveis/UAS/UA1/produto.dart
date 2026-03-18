class Produto {
    int _codigo;
    String _nome;
    double _preco;
    int _quantidade;

    Produto(this._codigo, this._nome, this._preco, this._quantidade);

    int get codigo => _codigo;
    set codigo(int value) {
        _codigo = value;
    }

    String get nome => _nome;
    set nome(String value) {
        _nome = value;
    }

    double get preco => _preco;
    set preco(double value) {
        if (value >= 0) {
        _preco = value;
        }
    }

    int get quantidade => _quantidade;
    set quantidade(int value) {
        if (value >= 0) {
        _quantidade = value;
        }
    }

    double calcularPrecoTotal() {
        return _preco * _quantidade;
    }

    @override
    String toString() {
        return '($_codigo) $_nome: $_preco x $_quantidade = ${calcularPrecoTotal()}';
    }
}

void main() {
    Produto produto1 = Produto(1, "Teclado", 19.9, 2);
    print(produto1);

    Produto produto2 = Produto(2, "Mouse", 25.5, 3);
    print(produto2);

    produto1.quantidade = 5;
    produto1.preco = 20.0;

    print("Após alteração:");
    print(produto1);
}
