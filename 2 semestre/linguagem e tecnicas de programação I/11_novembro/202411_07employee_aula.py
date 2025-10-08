"""            Comentários de várias linhas.
  CEUB  -  FATECS  -  BCC  -  ADS  -  Programação  -  Prof. Barbosa
ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

Teoria:
- Uma classe abstrata deve herdar de ABC (Abstract Base Classes)
- A superclasse abstrata precisa ter pelo menos um método abstrato
- As subclasses concretas têm que implementar todos os métodos
  abstratos da superclasse abstrata.
- Precisa importar o módulo abc (abstract base classes)
from abc import ABC, abstractmethod

- Analise o problema do empregado e o cálculo do salário.

- A superclasse Employee representa um funcionário geral (Fulltime ou
Hourly). Ela deve ser uma classe abstrata e deve ter o método abstrato
calcula salário com a finalidade de obrigar todas as subclasses concretas
do sistema sobrescreverem o método calcula salário com as regras de
negócios (RN) específicas de cada tipo de funcionário.

- Implemente os itens:

1- A superclasse abstrata Employee que herda da classe ABC
2- O construtor com os atributos first_name e Last_name
3- Os métodos gets e sets
4- O método abstrato compute_salary
5- Um objeto da superclasse Employee, conseguiu?
6- O método concreto full_name, ele retorna o nome completo

7- A subclasse FulltimeEmployee
8- O construtor com os atributos first_name, Last_name e base salary
9- Os métodos gets e sets
10- Crie um objeto da subclasse FulltimeEmployee, conseguiu?
11- A Regra de Negócio (RN) do salary é o base salary mais um bônus de 200 reais
12- Crie um objeto da subclasse FulltimeEmployee, conseguiu?
13- Consulte o nome, o nome completo, o salário base e o salário total

14- A subclasse HourlyEmployee
15- O Construtor com os atributos first_name, Last_name, worked_hours, value_hour
16- Alguns métodos gets e sets
17- Crie um objeto da subclasse HourlyEmployee, conseguiu?
18- A Regra de Negócio (RN) do salary é worked_hours vezes value_hour
19- Crie um objeto da subclasse HourlyEmployee, conseguiu?
20- Consulte o nome, o nome completo e o salário total

"""


# O nome de classe começa com letra maiúscula e as outras letras minúsculas.
# Nome de classe: primeira letra de cada palavra com letra maiúscula
from abc import ABC, abstractmethod
class Employee(ABC):
    def __init__(self, first_name, last_name):
        self.first_name = first_name
        self.last_name = last_name
    def get_first_name(self):
        return self.first_name
    def set_first_name(self, name):
        self.first_name = name
    def get_last_name(self):
        return self.last_name
    def full_name(self):
        fullname = f"{self.first_name} {self.last_name}"
        return fullname
        # return f"{self.first_name} {self.last_name}"  # Equivalente
    # Método abstrato, obrigatório pelo menos um na superclasse abstrata
    @abstractmethod                         # Decorator
    def compute_salary(self):
        pass                                # ...


# Sintaxe: from nome_arquivo_sem_extensao impoort NomeClasse
class FulltimeEmployee(Employee):           # class Subclasse(Superclasse):
    def __init__(self, first_name, last_name, base_salary):
        super().__init__(first_name, last_name)  # Chama o construtor da superclasse
        self.base_salary = base_salary
    def get_base_salary(self):                   # Valor do saláio base
        return self.base_salary
    def set_base_salary(self, new_base_salary):
        self.base_salary = new_base_salary
    # Método obrigatório, sobrescreve o método abstrato da superclasse
    def compute_salary(self):               # Cálculo do salário total
        salario_total = self.base_salary + 200   # Bônus de 200 reais
        return salario_total
        # return self.base_salary + 200      # Equivalente as duas linhas anteriores


class HourlyEmployee(Employee):
    def __init__(self, first_name, last_name, hours_worked, hour_value):
        super().__init__(first_name, last_name)  # Chama o construtor da superclasse
        self.hours_worked = hours_worked
        self.hour_value = hour_value
    def get_hours_workded(self):
        return self.hours_worked
    def set_hours_worked(self, new_hours_worded):
        self.hours_worked = new_hours_worded
    def get_hour_value(self):
        return self.hour_value
    def set_hour_value(self, new_hour_value):
        self.hour_value = new_hour_value
    # Método obrigatório, sobrescreve o método abstrato da superclasse abstrata
    def compute_salary(self):
        return self.hours_worked * self.hour_value
