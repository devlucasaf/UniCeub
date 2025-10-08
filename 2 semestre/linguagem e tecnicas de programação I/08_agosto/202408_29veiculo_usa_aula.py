# Sintaxe: from nome_arquivo_sem_extensão import NomeClasse
from veiculo_aula import Veiculo
if __name__ == '__main__':                  # Atalho: mai <tab>
    carro1 = Veiculo('HB', 2022, 80000.00)  # chama método __init__ (construtor)
    print("Objeto carro 1:", carro1)        # Teste
    # <veiculo.Veiculo object at 0x0000024149FF6FD0>  # Endereço hexadecimal
    carro2 = Veiculo('Corolla', 2024, 190000.00)
    print("Objeto carro 2:", carro2)
    # <veiculo.Veiculo object at 0x0000024149FF6F70>
    print("- Dados do carro 1:")
    retorno = carro1.get_modelo()           # Usa variável
    print("Modelo:", retorno)
    # print("Modelo:", carro1.get_modelo())  # Consulta
    print("Ano:", carro1.get_ano())
    print(f"Valor: R$ {carro1.get_valor():.2f}")  # Valor com 2 casas decimais
    print("- Dados do carro 2:")
    print("Modelo:", carro2.get_modelo())   # Direto no print
    print("Ano:", carro2.get_ano())
    print(f"Valor: R$ {carro2.get_valor():.2f}")
    carro1.set_modelo('HB20')    # Altera (substitui) o valor do objeto
    print("Modelo atualizado:", carro1.get_modelo())  # Confirma a alterção
    carro2.set_valor(122000.00)  # Altera (substitui) o valor do objeto
    print("Valor atualizado:", carro2.get_valor())    # Confirma a alterção
    carro2.set_valor(-62000.00)  # Argumento (valor) inconsitente
    print("Valor atualizado:", carro2.get_valor())    # Verifica se alterou
    carro1.mostra_dados()
    carro2.mostra_dados()
    carro1.mostra_dados2()
    carro2.mostra_dados2()
    print('Dados concatenados do carro 1:', carro1.retorna_dados())
    print('Dados concatenados do carro 2:', carro2.retorna_dados())
    carro1.aumenta_valor(1900.00)    # Passa o argumento (1900.00)
    print("Valor atualizado:", carro1.get_valor())  # Confirma
    vl_aumento = float(input("Valor aumento: "))    # vl_aumento = 22.22
    carro2.aumenta_valor(vl_aumento)            # Usuário digitou no input
    print("Valor atualizado com input:", carro2.get_valor())  # Confirma
