import 'package:flutter/material.dart';

void main() {
  runApp(LayoutExampleApp());
}

class LayoutExampleApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      debugShowCheckedModeBanner: false,
      home: HomePage(),
    );
  }
}

class HomePage extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    // Responsividade básica
    double largura = MediaQuery.of(context).size.width;
    bool isMobile = largura < 600;

    return Scaffold(
      appBar: AppBar(title: Text("Exemplo de Layout Flutter")),
      body: Padding(
        padding: const EdgeInsets.all(16),
        child: isMobile ? layoutVertical() : layoutHorizontal(),
      ),
    );
  }

  // Layout para celular (Column)
  Widget layoutVertical() {
    return Column(
      crossAxisAlignment: CrossAxisAlignment.start,
      children: [
        header(),
        SizedBox(height: 20),
        Expanded(child: content()),
        SizedBox(height: 20),
        footer(),
      ],
    );
  }

    // Layout para tablet/web (Row)
  Widget layoutHorizontal() {
    return Row(
    children: [
      Expanded(child: content()),
      SizedBox(width: 20),
      Expanded(
        child: Column(
            children: [
              header(),
              SizedBox(height: 20),
              footer(),
            ],
          ),
        ),
      ],
    );
  }

  // HEADER
  Widget header() {
    return Container(
      padding: EdgeInsets.all(16),
      color: Colors.blue,
      child: Text(
        "Cabeçalho",
        style: TextStyle(color: Colors.white, fontSize: 18),
      ),
    );
  }

  // CONTEÚDO
  Widget content() {
    return Container(
      color: Colors.grey[300],
      child: Center(
        child: Text(
          "Conteúdo Principal",
          style: TextStyle(fontSize: 16),
        ),
      ),
    );
  }

  // RODAPÉ
  Widget footer() {
    return Container(
      padding: EdgeInsets.all(16),
      color: Colors.black,
      child: Text(
        "Rodapé",
        style: TextStyle(color: Colors.white),
      ),
    );
  }
}