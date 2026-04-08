class ApiService {
  Future<String> fetchData() async {
    await Future.delayed(Duration(seconds: 2));
    return "Dados da API";
  }
}