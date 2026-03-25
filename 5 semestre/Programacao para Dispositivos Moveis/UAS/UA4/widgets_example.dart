import 'package:flutter/material.dart';

void main() {
    runApp(const MyApp());
}

class MyApp extends StatelessWidget {
    const MyApp({super.key});

    @override
    Widget build(BuildContext context) {
        return const MaterialApp(
            home: WidgetExample(),
        );
    }
}

class WidgetExample extends StatefulWidget {
    const WidgetExample({super.key});

    @override
    State<WidgetExample> createState() => _WidgetExampleState();
}

class _WidgetExampleState extends State<WidgetExample> {
    // Checkbox (múltiplas opções)
    bool opcao1 = false;
    bool opcao2 = false;

    // Switch (liga/desliga)
    bool wifiLigado = false;

    // RadioButton (escolha única)
    String? escolha = 'A';

    // Dropdown
    String? paisSelecionado = 'Brasil';

    final List<String> paises = ['Brasil', 'Argentina', 'Chile', 'Uruguai'];

    @override
    Widget build(BuildContext context) {
        return Scaffold(
            appBar: AppBar(title: const Text('Exemplo de Widgets')),
            body: Padding(
                padding: const EdgeInsets.all(16),
                child: ListView(
                    children: [
                        // CHECKBOX
                        const Text('Checkbox (múltipla escolha):'),
                        CheckboxListTile(
                            title: const Text('Opção 1'),
                            value: opcao1,
                            onChanged: (value) {
                                setState(() {
                                opcao1 = value!;
                                });
                            },
                        ),
                        CheckboxListTile(
                            title: const Text('Opção 2'),
                            value: opcao2,
                            onChanged: (value) {
                                setState(() {
                                opcao2 = value!;
                                });
                            },
                        ),

                        const Divider(),

                        // SWITCH
                        const Text('Switch (liga/desliga):'),
                            SwitchListTile(
                            title: const Text('Wi-Fi'),
                            value: wifiLigado,
                            onChanged: (value) {
                                setState(() {
                                wifiLigado = value;
                                });
                            },
                        ),

                        const Divider(),

                        // RADIO BUTTON
                        const Text('RadioButton (escolha única):'),
                            RadioListTile(
                            title: const Text('Opção A'),
                            value: 'A',
                            groupValue: escolha,
                            onChanged: (value) {
                                setState(() {
                                escolha = value.toString();
                                });
                            },
                        ),
                        RadioListTile(
                            title: const Text('Opção B'),
                            value: 'B',
                            groupValue: escolha,
                            onChanged: (value) {
                                setState(() {
                                escolha = value.toString();
                                });
                            },
                        ),

                        const Divider(),

                        // DROPDOWN
                        const Text('DropdownButton (lista):'),
                        DropdownButton<String>(
                            value: paisSelecionado,
                            isExpanded: true,
                            items: paises.map((String pais) {
                                return DropdownMenuItem(
                                    value: pais,
                                    child: Text(pais),
                                );
                            }).toList(),
                            onChanged: (value) {
                                setState(() {
                                    paisSelecionado = value;
                                });
                            },
                        ),
                    ],
                ),
            ),
        );
    }
}