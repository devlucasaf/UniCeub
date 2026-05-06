import 'package:flutter/material.dart';

void main() => runApp(const MyApp());

/// StatelessWidget – defines the overall app structure.
/// It does not change after being built.
class MyApp extends StatelessWidget {
  const MyApp({Key? key}) : super(key: key);

  static const String _title = 'Formulário de Pedido';

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: _title,
      theme: ThemeData(primarySwatch: Colors.blue),
      home: Scaffold(
        appBar: AppBar(title: const Text(_title)),
        // The body is a StatefulWidget because the form data changes.
        body: const MyForm(),
      ),
    );
  }
}

/// StatefulWidget – its state can change over time (user interactions).
class MyForm extends StatefulWidget {
  const MyForm({Key? key}) : super(key: key);

  @override
  State<MyForm> createState() => _MyFormState();
}

/// The actual state of the form.
class _MyFormState extends State<MyForm> {
  // Controllers and variables that store user input
  final TextEditingController _productController = TextEditingController();
  double _quantity = 1; // for Slider
  String? _deliveryType; // for Radio buttons
  String _region = 'Centro'; // for DropdownButton
  bool _acceptPromotions = false; // for Checkbox

  // List of options for the DropdownButton
  final List<String> _regions = ['Centro', 'Norte', 'Sul', 'Leste', 'Oeste'];

  // For Radio buttons we define an enum to have clear values
  enum Delivery { pickup, delivery, mail }

  Delivery? _selectedDelivery;

  @override
  Widget build(BuildContext context) {
    // The layout is built with Column to arrange widgets vertically.
    return Padding(
      padding: const EdgeInsets.all(16.0),
      child: Column(
        crossAxisAlignment: CrossAxisAlignment.start,
        children: [
          // ----- Product Name (TextField) -----
          const Text(
            'Nome do produto:',
            style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold),
          ),
          const SizedBox(height: 8),
          TextField(
            controller: _productController,
            decoration: const InputDecoration(
              hintText: 'Ex: Hambúrguer artesanal',
              border: OutlineInputBorder(),
            ),
          ),
          const SizedBox(height: 20),

          // ----- Quantity (Slider) -----
          const Text(
            'Quantidade:',
            style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold),
          ),
          Row(
            children: [
              Expanded(
                child: Slider(
                  value: _quantity,
                  min: 1,
                  max: 10,
                  divisions: 9, // gives 10 possible values (1..10)
                  label: _quantity.round().toString(),
                  onChanged: (double newValue) {
                    setState(() {
                      _quantity = newValue;
                    });
                  },
                ),
              ),
              Text(
                '${_quantity.round()}',
                style: const TextStyle(fontSize: 16),
              ),
            ],
          ),
          const SizedBox(height: 20),

          // ----- Delivery Type (Radio buttons) -----
          const Text(
            'Tipo de entrega:',
            style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold),
          ),
          Column(
            children: [
              ListTile(
                title: const Text('Retirar na loja'),
                leading: Radio<Delivery>(
                  value: Delivery.pickup,
                  groupValue: _selectedDelivery,
                  onChanged: (Delivery? value) {
                    setState(() {
                      _selectedDelivery = value;
                      _deliveryType = 'Retirar na loja';
                    });
                  },
                ),
              ),
              ListTile(
                title: const Text('Entregar no local'),
                leading: Radio<Delivery>(
                  value: Delivery.delivery,
                  groupValue: _selectedDelivery,
                  onChanged: (Delivery? value) {
                    setState(() {
                      _selectedDelivery = value;
                      _deliveryType = 'Entregar no local';
                    });
                  },
                ),
              ),
              ListTile(
                title: const Text('Correios'),
                leading: Radio<Delivery>(
                  value: Delivery.mail,
                  groupValue: _selectedDelivery,
                  onChanged: (Delivery? value) {
                    setState(() {
                      _selectedDelivery = value;
                      _deliveryType = 'Correios';
                    });
                  },
                ),
              ),
            ],
          ),
          const SizedBox(height: 20),

          const Text(
            'Região:',
            style: TextStyle(fontSize: 16, fontWeight: FontWeight.bold),
          ),
          DropdownButton<String>(
            value: _region,
            items: _regions.map((String region) {
              return DropdownMenuItem<String>(
                value: region,
                child: Text(region),
              );
            }).toList(),
            onChanged: (String? newValue) {
              setState(() {
                _region = newValue!;
              });
            },
          ),
          const SizedBox(height: 20),

          Row(
            children: [
              Checkbox(
                value: _acceptPromotions,
                onChanged: (bool? value) {
                  setState(() {
                    _acceptPromotions = value ?? false;
                  });
                },
              ),
              const Expanded(
                child: Text('Desejo receber promoções por e-mail'),
              ),
            ],
          ),
          const SizedBox(height: 30),

          Center(
            child: TextButton(
              style: TextButton.styleFrom(
                backgroundColor: Colors.blue,
                padding: const EdgeInsets.symmetric(horizontal: 32, vertical: 12),
              ),
              onPressed: () {
                final product = _productController.text.trim();

                if (product.isEmpty) {
                  // Simple validation
                  ScaffoldMessenger.of(context).showSnackBar(
                    const SnackBar(content: Text('Por favor, informe o nome do produto')),
                  );

                  return;
                }

                if (_selectedDelivery == null) {
                  ScaffoldMessenger.of(context).showSnackBar(
                    const SnackBar(content: Text('Por favor, selecione o tipo de entrega')),
                  );
                  return;
                }

                print('=== Dados do pedido ===');
                print('Produto: $product');
                print('Quantidade: ${_quantity.round()}');
                print('Tipo de entrega: $_deliveryType');
                print('Região: $_region');
                print('Aceita promoções: ${_acceptPromotions ? "Sim" : "Não"}');
              },
              child: const Text(
                'Cadastrar',
                style: TextStyle(color: Colors.white, fontSize: 16),
              ),
            ),
          ),
        ],
      ),
    );
  }

  @override
  void dispose() {
    _productController.dispose();
    super.dispose();
  }
}