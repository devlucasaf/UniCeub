import 'package:flutter/material.dart';

void main() => runApp(const MyApp());

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Demonstração Flutter',
      theme: ThemeData(
        primarySwatch: Colors.blue,
        visualDensity: VisualDensity.adaptivePlatformDensity,
      ),
      home: const HomePage(),
      debugShowCheckedModeBanner: false, 
    );
  }
}

class HomePage extends StatefulWidget {
  const HomePage({super.key});

  @override
  State<HomePage> createState() => _HomePageState();
}

class _HomePageState extends State<HomePage> {
  int _counter = 0;

  void _incrementCounter() {
    setState(() {
      _counter++;
    });
  }

  void _resetCounter() {
    setState(() {
      _counter = 0;
    });
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: const MeuAppBar(
        title: Text('App Demo - Flutter Fundamentos'),
      ),

      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.stretch,
          children: [
            Container(
              height: 100,
              decoration: BoxDecoration(
                color: Colors.blue.shade100,
                borderRadius: BorderRadius.circular(12),
                boxShadow: const [
                  BoxShadow(
                    color: Colors.black12,
                    blurRadius: 4,
                    offset: Offset(2, 2),
                  ),
                ],
              ),
              child: const Center(
                child: Text(
                  'Container Decorado',
                  style: TextStyle(
                    fontSize: 20,
                    fontWeight: FontWeight.bold,
                    color: Colors.blue,
                  ),
                ),
              ),
            ),
            const SizedBox(height: 20),

            const Text(
              'Layout com Row e Column:',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.w500),
            ),
            const SizedBox(height: 8),
            Row(
              mainAxisAlignment: MainAxisAlignment.spaceEvenly,
              children: [
                _buildIconCard(Icons.thumb_up, 'Curtir', Colors.green),
                _buildIconCard(Icons.comment, 'Comentar', Colors.orange),
                _buildIconCard(Icons.share, 'Compartilhar', Colors.blue),
              ],
            ),
            const SizedBox(height: 20),

            const Text(
              'Expanded ocupando espaço:',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.w500),
            ),
            const SizedBox(height: 8),
            Container(
              height: 80,
              color: Colors.grey.shade200,
              child: Row(
                children: [
                  Container(width: 50, color: Colors.red),
                  const Expanded(
                    child: Center(
                      child: Text(
                        'Expanded ocupa o espaço restante',
                        textAlign: TextAlign.center,
                      ),
                    ),
                  ),
                  Container(width: 50, color: Colors.red),
                ],
              ),
            ),
            const SizedBox(height: 20),

            const Text(
              'Exemplo de Stack:',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.w500),
            ),
            const SizedBox(height: 8),
            SizedBox(
              height: 120,
              child: Stack(
                alignment: Alignment.center,
                children: [
                  Container(
                    color: Colors.blue.shade200,
                    width: double.infinity,
                  ),
                  const Text(
                    'Texto no centro',
                    style: TextStyle(
                      fontSize: 16,
                      fontWeight: FontWeight.bold,
                      backgroundColor: Colors.white70,
                    ),
                  ),
                  Positioned(
                    bottom: 8,
                    right: 8,
                    child: Icon(Icons.star, color: Colors.amber.shade700),
                  ),
                ],
              ),
            ),
            const SizedBox(height: 20),

            const Text(
              'Contador interativo:',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.w500),
            ),
            const SizedBox(height: 8),
            Row(
              mainAxisAlignment: MainAxisAlignment.center,
              children: [
                IconButton(
                  icon: const Icon(Icons.remove_circle_outline),
                  iconSize: 40,
                  onPressed: _resetCounter,
                  tooltip: 'Zerar contador',
                ),
                const SizedBox(width: 20),
                Text(
                  '$_counter',
                  style: const TextStyle(fontSize: 48, fontWeight: FontWeight.bold),
                ),
                const SizedBox(width: 20),
                IconButton(
                  icon: const Icon(Icons.add_circle_outline),
                  iconSize: 40,
                  onPressed: _incrementCounter,
                  tooltip: 'Incrementar',
                ),
              ],
            ),
            const SizedBox(height: 20),

            const Text(
              'Widget customizado (MeuAppBar reutilizado no corpo):',
              style: TextStyle(fontSize: 18, fontWeight: FontWeight.w500),
            ),
            const SizedBox(height: 8),
            const MeuAppBar(
              title: Text('Barra customizada dentro do corpo'),
            ),
          ],
        ),
      ),

      floatingActionButton: FloatingActionButton(
        onPressed: _incrementCounter,
        tooltip: 'Incrementar',
        child: const Icon(Icons.add),
      ),
    );
  }

  Widget _buildIconCard(IconData icon, String label, Color color) {
    return Card(
      elevation: 2,
      shape: RoundedRectangleBorder(borderRadius: BorderRadius.circular(12)),
      child: Padding(
        padding: const EdgeInsets.symmetric(horizontal: 12, vertical: 8),
        child: Column(
          mainAxisSize: MainAxisSize.min,
          children: [
            Icon(icon, color: color, size: 32),
            const SizedBox(height: 4),
            Text(label, style: TextStyle(color: color)),
          ],
        ),
      ),
    );
  }
}

class MeuAppBar extends StatelessWidget {
  final Widget title;
  final Color? backgroundColor;

  const MeuAppBar({
    required this.title,
    this.backgroundColor,
    super.key,
  });

  @override
  Widget build(BuildContext context) {
    return Container(
      height: 56.0,
      padding: const EdgeInsets.symmetric(horizontal: 8.0),
      decoration: BoxDecoration(
        color: backgroundColor ?? Colors.blue[700],
      ),
      child: Row(
        children: [
          const IconButton(
            icon: Icon(Icons.menu),
            tooltip: 'Menu',
            onPressed: null, 
          ),

          Expanded(child: title),

          const IconButton(
            icon: Icon(Icons.search),
            tooltip: 'Pesquisar',
            onPressed: null,
          ),
        ],
      ),
    );
  }
}