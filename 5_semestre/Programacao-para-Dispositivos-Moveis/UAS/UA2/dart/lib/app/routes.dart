import 'package:flutter/material.dart';
import '../screens/home/home_page.dart';
import '../screens/login/login_page.dart';

class AppRoutes {
  static Map<String, WidgetBuilder> get routes => { 
    '/': (context) => LoginPage(),
    '/home': (context) => HomePage(),
  };
}