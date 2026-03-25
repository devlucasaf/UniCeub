import 'package:flutter/material.dart';

void main() => runApp(MyApp());

class MyApp extends StatelessWidget {
    @override
    Widget build(BuildContext context) {
        var title = 'Desafio';

        return MaterialApp(
        title: title,
        home: Scaffold(
                appBar: AppBar(
                title: Text(title),
                ),
                body: Column(children: [
                TextField(
                    decoration: InputDecoration(
                        border: OutlineInputBorder(),
                        hintText: 'Digite o valor da Nota 01 (N1)'),
                ),
                TextField(
                    decoration: InputDecoration(
                        border: OutlineInputBorder(),
                        hintText: 'Digite o valor da Nota 02 (N2)'),
                ),
                TextButton(
                    onPressed: () {},
                    child: Text("Calcular MÃ©dia", style: TextStyle(fontSize: 14)))
                ]),
            ),
        );
    }
}