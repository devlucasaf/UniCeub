import 'package:flutter/material.dart';

class AppUtils {
  // Mostrar Snackbar
  static void showMessage(BuildContext context, String message) {
    ScaffoldMessenger.of(context).showSnackBar(
      SnackBar(content: Text(message)),
    );
  }

  // Validar email simples
  static bool isEmailValid(String email) {
    return email.contains('@') && email.contains('.');
  }

  // Delay simulado (útil pra testes)
  static Future<void> delay(int seconds) async {
    await Future.delayed(Duration(seconds: seconds));
  }
}