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
        // Barra superior com o título "Desafio"
        appBar: AppBar(
            title: const Text('Desafio'),
            backgroundColor: Colors.blue,
        ),
        body: Padding(
                padding: const EdgeInsets.all(16.0),
                child: Column(
                    children: [
                        // Campo de entrada para a Nota 01
                        const TextField(
                            decoration: InputDecoration(
                                labelText: 'Digite o valor da Nota 01 (N1)',
                            ),
                            keyboardType: TextInputType.number,
                        ),
                        const SizedBox(height: 10), // Espaçamento entre os campos
                        
                        // Campo de entrada para a Nota 02
                        const TextField(
                            decoration: InputDecoration(
                                labelText: 'Digite o valor da Nota 02 (N2)',
                            ),
                            keyboardType: TextInputType.number,
                        ),
                        const SizedBox(height: 20),
                        
                        // Botão para calcular a média
                        TextButton(
                            onPressed: () {
                                // Ação vazia conforme solicitado no desafio
                            },
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