import 'package:flutter/material.dart';

void main() {
    runApp(MyApp());
}

// Widget principal (imutável)
class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Exemplo StatefulWidget',
      home: CounterPage(),
    );
  }
}

// StatefulWidget (configuração)
class CounterPage extends StatefulWidget {
  @override
  _CounterPageState createState() => _CounterPageState();
}

// State (estado mutável + lógica)
class _CounterPageState extends State<CounterPage> {
  int _contador = 0;

  // Método para incrementar o contador
  void _incrementar() {
    setState(() {
    _contador++;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: Text('Contador Flutter'),
      ),
      body: Center(
        child: Text(
          'Valor: $_contador',
          style: TextStyle(fontSize: 24),
        ),
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: _incrementar,
        child: Icon(Icons.add),
      ),
    );
  }
}