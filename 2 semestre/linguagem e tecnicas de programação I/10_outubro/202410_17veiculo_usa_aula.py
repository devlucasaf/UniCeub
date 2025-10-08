# Sintaxe: from nome_arquivo_sem_extensao import NomeClasse ou
# from nome_arquivo_sem_extensao import NomeClasse1, NomeClasse2, NomeClasse3
from veiculo_aula import Veiculo, Carro, Moto
if __name__ == '__main__':
    v1 = Veiculo (100000.00, "Yaris", 20000)
    print("- Veiculo 1:", v1)
    c1 = Carro(99000.00, "Civic", 1000, 4)  # Instancia objeto da subclasse
    print('- Carro 1:\n', c1)               # print(c1.__str__())
    print(f'Valor: {c1.get_valor():.2f}')   # Duas casas decimais
    print('Modelo:', c1.get_modelo())
    print('Km:', c1.get_km())
    print('Qtd. portas', c1.get_qtd_portas())
    c2 = Carro(100000.00, 'Corolla', 5000)  # Três argumentos
    print('- Carro 2:\n', c2)
    print(f'Valor: {c2.get_valor():.2f}')
    print('Modelo:', c2.get_modelo())
    print('Km:', c2.get_km())
    print('Qtd. portas', c2.get_qtd_portas())
    c3 = Carro(70000.00, 'HB20')            # Dois argumentos
    print('- Carro 3:\n', c3)
    print(f'Valor: {c3.get_valor():.2f}')
    print('Modelo:', c3.get_modelo())
    print('Km:', c3.get_km())
    print('Qtd. portas', c3.get_qtd_portas())
    c3.set_valor(88000.00)
    print(f'Preço alterado: {c3.get_valor():.2f}')
    m1 = Moto(22000.00, "CBS", 12000, 400)  # Quatro argumentos
    print('- Moto 1:\n', m1)
    # <Veiculo_aula2.veiculo_moto.Moto object at 0x000002CFD014E0D0>
    print('Modelo:', m1.get_modelo())
    print('Cilindradas:', m1.get_cilindradas())
    m2 = Moto(32000.00, "CBS", 0)           # Três argumentos
    print('- Moto 2:\n', m2)
    print('Modelo:', m2.get_modelo())
    print('Cilindradas:', m2.get_cilindradas())
    m2.set_valor(44000.00)
    print(f'Preço alterado: {c3.get_valor():.2f}')
    print("Dados do carro e moto num dicionário (vars ou __dict__)")
    print(vars(c1))                         # Método do Python
    print(c1.__dict__)                      # Método do Python
    # {'valor': 990000, 'modelo': 'Civic', 'km': 1000, 'qtd_portas': 4}
    print(vars(m1))
    print(m1.__dict__)
    # {'valor': 22000, 'modelo': 'CBS', 'km': 12000, 'cilindradas': 400}
    c1.atualiza_valor(1000.0)               # Argumento correto
    c1.atualiza_valor(1000)                 # Argumento correto
    c1.atualiza_valor(-1000)                # Argumento errado, msg erro
    print(c1.__dict__)                      # Teste
    m1.atualiza_valor(450.00)               # Argumento correto
    print(m1.__dict__)                      # Teste
    c1.situacao()
    m2.situacao()
