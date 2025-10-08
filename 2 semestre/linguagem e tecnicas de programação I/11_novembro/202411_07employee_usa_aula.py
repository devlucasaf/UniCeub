# Sintaxe: from nome_arquivo_sem_extensao import NomeClasse1, NomeClasse2, NomeClasse3
from employee_aula import Employee, FulltimeEmployee, HourlyEmployee
if __name__ == '__main__':
    # obj_employee = Employee('Emily', 'Pereira')       # TypeError:
    # Can't instantiate abstract class Employee with abstract method compute_salary
    obj_fulltime = FulltimeEmployee('John', 'Doe', 6000.0)  # Cria objeto, chama __init__
    print('- Fulltime Employee:\n', obj_fulltime)
    print('Nome:', obj_fulltime.get_first_name())
    print('Nome completo:', obj_fulltime.full_name())       # Usa o método
    print('Salário fixo:', obj_fulltime.get_base_salary())
    print('Salário total:', obj_fulltime.compute_salary())
    obj_hourly = HourlyEmployee('Rogério', 'Alves', 20, 200.0)
    print('\n- Hourly Employee:')
    print(obj_hourly)
    print('Nome:', obj_hourly.get_first_name())
    print('Nome completo:', obj_hourly.full_name())
    print('Valor da hora:', obj_hourly.get_hour_value())
    print('Salário total:', obj_hourly.compute_salary())
