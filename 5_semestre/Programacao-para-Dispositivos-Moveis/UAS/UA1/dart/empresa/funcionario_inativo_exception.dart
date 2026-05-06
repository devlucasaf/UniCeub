class FuncionarioInativoException implements Exception {
  final String mensagem;
  FuncionarioInativoException(this.mensagem);
  @override
  String toString() => mensagem;
}