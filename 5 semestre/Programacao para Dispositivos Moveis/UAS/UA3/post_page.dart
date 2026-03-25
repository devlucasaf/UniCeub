import 'package:flutter/material.dart';

void main() {
    runApp(MyApp());
}

class MyApp extends StatelessWidget {
    @override
    Widget build(BuildContext context) {
        return MaterialApp(
            debugShowCheckedModeBanner: false,
            home: PostPage(),
        );
    }
}

class PostPage extends StatelessWidget {
    @override
    Widget build(BuildContext context) {
        return Scaffold(
            appBar: AppBar(
                title: Text("Post de Notícia"),
            ),
            body: SingleChildScrollView(
                child: Column(
                    crossAxisAlignment: CrossAxisAlignment.start,
                    children: [
                        // Banner
                        Stack(
                        children: [
                            Image.network(
                            "https://images.unsplash.com/photo-1518770660439-4636190af475",
                            width: double.infinity,
                            height: 200,
                            fit: BoxFit.cover,
                            ),
                        ],
                        ),

                        // Conteúdo
                        Padding(
                            padding: EdgeInsets.all(16),
                            child: Column(
                                crossAxisAlignment: CrossAxisAlignment.start,
                                children: [
                                    // Linha com imagem + título
                                    Row(
                                        children: [
                                            Image.network(
                                                "https://cdn-icons-png.flaticon.com/512/732/732212.png",
                                                width: 40,
                                            ),
                                            SizedBox(width: 10),
                                            Expanded(
                                                child: Text(
                                                "Leiautes criativos com Flutter",
                                                style: TextStyle(
                                                    fontSize: 18,
                                                    fontWeight: FontWeight.bold,
                                                ),
                                                ),
                                            ),
                                        ],
                                    ),

                                    SizedBox(height: 10),

                                    // Data
                                    Text(
                                        "04/08/2021 — 17:55",
                                        style: TextStyle(color: Colors.grey),
                                    ),

                                    SizedBox(height: 10),

                                    // Descrição
                                    Text(
                                        "Conheça o Flutter, um framework para desenvolvimento mobile, web e desktop.",
                                        style: TextStyle(fontSize: 16),
                                    ),
                                ],
                            ),
                        ),
                    ],
                ),
            ),
        );
    }
}