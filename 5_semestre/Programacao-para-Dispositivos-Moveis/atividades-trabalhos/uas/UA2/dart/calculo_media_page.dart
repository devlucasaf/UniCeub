import 'package:flutter/material.dart';

void main() {
    runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: const DesafioPage(),
    );
  }
}

class DesafioPage extends StatelessWidget {
  const DesafioPage({super.key});

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
        title: const Text('Desafio'),
        backgroundColor: Colors.blue,
      ),
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          children: [
            const TextField(
              decoration: InputDecoration(
                  labelText: 'Digite o valor da Nota 01 (N1)',
                ),
              keyboardType: TextInputType.number,
            ),
            const SizedBox(height: 10), 
            
            const TextField(
              decoration: InputDecoration(
                labelText: 'Digite o valor da Nota 02 (N2)',
              ),
              keyboardType: TextInputType.number,
            ),
            const SizedBox(height: 20),
              
            TextButton(
              onPressed: () {},
              child: const Text(
                'Calcular Média',
                style: TextStyle(fontSize: 18, color: Colors.blue),
              ),
            ),
          ],
        ),
      ),
    );
  }
}