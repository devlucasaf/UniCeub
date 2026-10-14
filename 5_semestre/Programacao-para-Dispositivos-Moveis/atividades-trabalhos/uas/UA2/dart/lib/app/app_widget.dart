import 'package:flutter/material.dart';
import 'routes.dart';
import '../core/themes/app_theme.dart';

class MyApp extends StatelessWidget {
  const MyApp({super.key});

  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Flutter App',

      // Remove a faixa "debug"
      debugShowCheckedModeBanner: false,

      // Tema global
      theme: AppTheme.lightTheme,

      // Rota inicial
      initialRoute: '/',

      // Rotas do app
      routes: AppRoutes.routes,
    );
  }
}