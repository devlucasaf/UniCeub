import 'package:flutter/material.dart';

void main() {
    runApp(MediaQueryExampleApp());
}

class MediaQueryExampleApp extends StatelessWidget {
    @override
    Widget build(BuildContext context) {
        return MaterialApp(
            debugShowCheckedModeBanner: false,
            home: MediaQueryPage(),
        );
    }
}

class MediaQueryPage extends StatelessWidget {
    @override
    Widget build(BuildContext context) {
        // Pegando informações da tela
        final media = MediaQuery.of(context);

        double largura = media.size.width;
        double altura = media.size.height;
        bool isLandscape = media.orientation == Orientation.landscape;

        return Scaffold(
            appBar: AppBar(title: Text("Exemplo com MediaQuery")),
            body: Padding(
                padding: const EdgeInsets.all(16),
                child: isLandscape
                    ? Row(
                        children: [
                        Expanded(child: buildCard("Largura: $largura")),
                        SizedBox(width: 16),
                        Expanded(child: buildCard("Altura: $altura")),
                        ],
                    )
                    : Column(
                        children: [
                        buildCard("Largura: $largura"),
                        SizedBox(height: 16),
                        buildCard("Altura: $altura"),
                        ],
                    ),
            ),
        );
    }

    Widget buildCard(String texto) {
        return Container(
            height: 150,
            alignment: Alignment.center,
            decoration: BoxDecoration(
                color: Colors.blue,
                borderRadius: BorderRadius.circular(12),
            ),
            child: Text(
                texto,
                style: TextStyle(color: Colors.white, fontSize: 18),
            ),
        );
    }
}