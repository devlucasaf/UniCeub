import '../services/api_service.dart';

class UserRepository {
  final ApiService apiService;

  UserRepository(this.apiService);

  Future<String> getUser() {
    return apiService.fetchData();
  }
}