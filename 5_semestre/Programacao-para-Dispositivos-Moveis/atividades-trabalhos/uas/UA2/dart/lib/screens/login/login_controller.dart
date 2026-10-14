import 'package:flutter/material.dart';

class LoginController {
  void login(BuildContext context) {
    Navigator.pushNamed(context, '/home');
  }
}