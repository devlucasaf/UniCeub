import 'package:flutter/material.dart';
import 'login_controller.dart';
import '../../widgets/custom_button.dart';

class LoginPage extends StatelessWidget {
  final controller = LoginController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Login')),
      body: Center(
        child: CustomButton(
          text: 'Entrar',
          onPressed: () {
            controller.login(context);
          },
        ),
      ),
    );
  }
}