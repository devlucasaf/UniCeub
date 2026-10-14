import 'package:flutter/material.dart';

class FormExemploFlutter extends StatefulWidget {
  const FormExemploFlutter({super.key});

  @override
  State<FormExemploFlutter> createState() => _FormExemploFlutterState();
}

class _FormExemploFlutterState extends State<FormExemploFlutter> {
  final TextEditingController nomeController = TextEditingController();

  bool aceitaTermos = false;
  String genero = 'Masculino';
  String? cidade = 'São Paulo';

  final List<String> cidades = [
    'São Paulo',
    'Rio de Janeiro',
    'Belo Horizonte'
  ];

  void enviarFormulario() {
    print('Nome: ${nomeController.text}');
    print('Aceita termos: $aceitaTermos');
    print('Gênero: $genero');
    print('Cidade: $cidade');
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: const Text('Formulário Flutter')),
      body: Padding(
        padding: const EdgeInsets.all(16),
        child: ListView(
          children: [
            // Campo de texto
            TextField(
              controller: nomeController,
              decoration: const InputDecoration(
                labelText: 'Nome',
                border: OutlineInputBorder(),
              ),
            ),

            const SizedBox(height: 16),

            // Checkbox
            CheckboxListTile(
              title: const Text('Aceito os termos'),
              value: aceitaTermos,
              onChanged: (value) {
                setState(() {
                  aceitaTermos = value!;
                });
              },
            ),

            // Radio Buttons
            const Text('Gênero:'),
            RadioListTile(
              title: const Text('Masculino'),
              value: 'Masculino',
              groupValue: genero,
              onChanged: (value) {
                setState(() {
                  genero = value.toString();
                });
              },
            ),
            RadioListTile(
              title: const Text('Feminino'),
              value: 'Feminino',
              groupValue: genero,
              onChanged: (value) {
                setState(() {
                  genero = value.toString();
                });
              },
            ),

            const SizedBox(height: 16),

            // Dropdown
            DropdownButtonFormField<String>(
              value: cidade,
              items: cidades.map((cidade) {
                return DropdownMenuItem(
                  value: cidade,
                  child: Text(cidade),
                );
              }).toList(),
              onChanged: (value) {
                setState(() {
                  cidade = value;
                });
              },
              decoration: const InputDecoration(
                labelText: 'Cidade',
                border: OutlineInputBorder(),
              ),
            ),

            const SizedBox(height: 20),

            // Botão
            ElevatedButton(
              onPressed: enviarFormulario,
              child: const Text('Enviar'),
            ),
          ],
        ),
      ),
    );
  }
}