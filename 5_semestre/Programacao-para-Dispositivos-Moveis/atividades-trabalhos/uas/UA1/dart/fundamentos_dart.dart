import 'dart:io';      
import 'dart:math';    

class Aluno {
    String _nome;
    double _nota1;
    double _nota2;

    Aluno(this._nome, this._nota1, this._nota2);

    Aluno.aprovado(this._nome) : _nota1 = 7.0, _nota2 = 7.0;

    String get nome => _nome;
    double get nota1 => _nota1;
    double get nota2 => _nota2;

    set nota1(double valor) {
        if (valor >= 0 && valor <= 10) {
            _nota1 = valor;
        } 
        
        else {
            print('Nota inválida: $valor. Deve estar entre 0 e 10.');
        }
    }

    set nota2(double valor) {
        if (valor >= 0 && valor <= 10) {
            _nota2 = valor;
        } 
        
        else {
            print('Nota inválida: $valor. Deve estar entre 0 e 10.');
        }
    }

    double calcularMedia() {
        return (_nota1 + _nota2) / 2.0;
    }

    bool estaAprovado() {
        return calcularMedia() >= 6.0;
    }

    @override
    String toString() {
        return 'Aluno: $_nome | Notas: $_nota1, $_nota2 | Média: ${calcularMedia().toStringAsFixed(1)} | ${estaAprovado() ? "Aprovado" : "Reprovado"}';
    }
}

int fatorial(int n) {
    if (n <= 0) {
        return 1;
    }
    
    return n * fatorial(n - 1);
}

List<double> geradorDeLista(int quantidade, {double maximo = 50.0}) {
    var rng = Random();                       
    List<double> lista = <double>[];          

    for (var i = 0; i < quantidade; i++) {
        double valor = rng.nextDouble() * maximo; 
        lista.add(valor);
    }
    
    return lista;
}

double encontrarMaior(List<double> lista) {
    if (lista.isEmpty) {
        throw Exception('A lista não pode estar vazia.');
    }

    double maior = lista[0];a
    int posicao = 1;

    while (posicao < lista.length) {
        if (lista[posicao] > maior) {
            maior = lista[posicao];
        }
        posicao++;
    }
    
    return maior;
}

void imprimirMensagem(String? mensagem) {
    String texto = mensagem ?? 'Mensagem não fornecida.';
    print('>>> $texto');    
}

void main() {
    print('\n=== 1. Variáveis e Tipos ===');

    var n1 = 10.0;   
    var n2 = 7.0;    

    double media = (n1 + n2) / 2.0;
    print('Média de $n1 e $n2: $media');

    late String texto;
    texto = 'Dart com null-safety';
    print(texto);

    String? nomeNullable;
    nomeNullable = null;  
    imprimirMensagem(nomeNullable);

    String nomeNaoNullable = 'João';

    print('\n=== 2. Funções e Cálculos ===');

    int numero = 5;
    print('Fatorial de $numero: ${fatorial(numero)}');

    int tamanhoLista = 8;
    List<double> minhaLista = geradorDeLista(tamanhoLista, maximo: 100.0);
    print('Lista gerada: $minhaLista');

    double maiorValor = encontrarMaior(minhaLista);
    print('Maior valor na lista: $maiorValor');

    print('\n=== 3. Manipulação de Coleções ===');

    List<double> maioresQue50 = minhaLista.where((valor) => valor > 50).toList();
    print('Valores > 50: $maioresQue50');

    List<int> valoresInteiros = minhaLista.map((valor) => valor.round()).toList();
    print('Valores arredondados: $valoresInteiros');

    print('\n=== 4. Trabalhando com Classes ===');

    Aluno aluno1 = Aluno('Ana', 8.5, 7.2);
    Aluno aluno2 = Aluno('Bruno', 5.0, 6.5);
    Aluno aluno3 = Aluno.aprovado('Carla');   

    aluno2.nota2 = 7.0;   

    List<Aluno> turma = [aluno1, aluno2, aluno3];

    for (var aluno in turma) {
        print(aluno);
    }

    print('\n=== 5. Função Aninhada ===');

    void mostrarStatus(Aluno a) {
        String status() => a.estaAprovado() ? 'aprovado' : 'reprovado';
        print('${a.nome} está $status() com média ${a.calcularMedia().toStringAsFixed(1)}');
    }

    mostrarStatus(aluno1);
    mostrarStatus(aluno2);
    mostrarStatus(aluno3);

    print('\n=== 6. Entrada/Saída (Console) ===');
    stdout.write('Digite um número inteiro para calcular o fatorial: ');
    String? entrada = stdin.readLineSync();

    if (entrada != null && entrada.isNotEmpty) {
        try {
            int valor = int.parse(entrada);
            print('Fatorial de $valor: ${fatorial(valor)}');
        } 
        
        catch (e) {
            print('Erro: você não digitou um número inteiro válido.');
        }
    } 
    
    else {
        print('Nenhuma entrada fornecida.');
    }

    print('\n=== 7. Lazy Initialization ===');

    late double valorCalculado = _calcularValorCaro();

    print('O programa continua...');
    print('Valor calculado (lazy): $valorCalculado'); 

    print('\n=== 8. Null Safety Avançado ===');

    String? textoNullable;
    String textoFinal = textoNullable ?? 'Valor padrão (coalescência)';
    print(textoFinal);

    List<int>? listaNullable;

    if (listaNullable != null) {
        listaNullable.add(10);
    } 
    
    else {
        print('A lista é nula, não é possível adicionar.');
    }

    print('\n=== 9. Outros Conceitos ===');

    print('Variável global: $mensagemGlobal');

    _funcaoPrivada();

    Object valorQualquer = 42;
    print('Objeto como inteiro: $valorQualquer');
    valorQualquer = 'Agora é string';
    print('Objeto como string: $valorQualquer');

    print('\n=== Fim do programa ===');
}

String mensagemGlobal = 'Esta é uma variável global.';

void _funcaoPrivada() {
    print('Esta função é privada (só pode ser usada dentro deste arquivo).');
}

double _calcularValorCaro() {
    print('  > Executando cálculo caro...');
    sleep(Duration(seconds: 1));
    return 3.14159;
}