import 'package:flutter/material.dart';

class StatefulWidgetExemplo extends StatefulWidget {
  const StatefulWidgetExemplo({super.key});

  @override
  State<StatefulWidgetExemplo> createState() =>
      _StatefulWidgetExemploState();
}

class _StatefulWidgetExemploState extends State<StatefulWidgetExemplo> {
  int contador = 0; // ESTADO do widget

  void incrementar() {
    setState(() {
      contador++; // altera o estado
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Exemplo de StatefulWidget'),
      ),
      body: Center(
        child: Column(
          mainAxisAlignment: MainAxisAlignment.center,
          children: [
            const Text(
              'Você clicou:',
              style: TextStyle(fontSize: 20),
            ),
            Text(
              '$contador',
              style: const TextStyle(fontSize: 40),
            ),
            const SizedBox(height: 20),
            ElevatedButton(
              onPressed: incrementar,
              child: const Text('Incrementar'),
            ),
          ],
        ),
      ),
    );
  }
}