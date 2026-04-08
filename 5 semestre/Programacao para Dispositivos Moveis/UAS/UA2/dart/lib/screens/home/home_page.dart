import 'package:flutter/material.dart';
import 'home_controller.dart';

class HomePage extends StatelessWidget {
  final controller = HomeController();

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(title: Text('Home')),
      body: Center(
        child: Text(controller.getWelcomeMessage()),
      ),
    );
  }
}