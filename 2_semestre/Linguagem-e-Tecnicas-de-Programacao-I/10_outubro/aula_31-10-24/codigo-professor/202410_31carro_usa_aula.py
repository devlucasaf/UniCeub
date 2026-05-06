# Sintaxe: from nome_arquivo_sem_extensao import NomeClasse1, NomeClasse2, NomeClasse3
from carro_aula import Carro, Economico, Intermediario
if __name__ == '__main__':
    # o_carro = Carro('Modelo')                       # TypeError:
    # Can't instantiate abstract class Carro with abstract method retorna_preco_diaria
    Carro.set_preco_base(200)
    print("Preço base de locação:", Carro.get_preco_base())
    # print('Qtd. carros cadastrados:', Carro.get_qtd_carro())
    o_eco = Economico('Uno')                          # Objeto da subclasse Economico
    print(f'- O carro {o_eco.get_modelo()} com:')     # Método de superclasse
    # print(f'Preço básico: {o_eco.get_preco_base()}')  # Método de classe, equivalentes
    print(f'Preço básico: {Carro.get_preco_base()}')  # Método de classe
    print('Diária: R$ {:.2f}' .format(o_eco.preco_diaria()))  # Método da subclasse
    o_int = Intermediario('HB20')
    print(f'- O carro {o_int.get_modelo()} com:')
    print(f'Preço básico: {o_int.get_preco_base()}')
    print('Diária: R$ {:.2f}' .format(o_int.preco_diaria()))
    Carro.set_preco_base(200.00)        # NomeSuperclasse.nome_metodo_classe(argumento)
    print(f'- O carro {o_eco.get_modelo()} com:')
    print(f'Preço básico: {o_eco.get_preco_base()}')
    print('Diária: R$ {:.2f}' .format(o_eco.preco_diaria()))
    o_int = Intermediario('HB20')
    print(f'- O carro {o_int.get_modelo()} com:')
    print(f'Preço básico: {o_int.get_preco_base()}')
    print('Diária: R$ {:.2f}' .format(o_int.preco_diaria()))
    print(f'Preço básico: {Carro.get_preco_base()}')
