class Formatters {
  // Exemplo: nome com primeira letra maiúscula
  static String capitalize(String text) {
    if (text.isEmpty) {
      return text;
    }
    return text[0].toUpperCase() + text.substring(1);
  }

  // Exemplo: formatar telefone simples
  static String formatPhone(String phone) {
    if (phone.length != 11) {
      return phone;
    }
    return "(${phone.substring(0, 2)}) ${phone.substring(2, 7)}-${phone.substring(7)}";
  }
}