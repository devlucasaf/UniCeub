# Sintaxe:
# from nome_arquivo_sem_extensão import NomeClasse
from pessoa1_aula import Pessoa
if __name__ == '__main__':                      # Atalho: mai <tab>
    pessoa1 = Pessoa("Carlos", 71, 1.80, 2000)  # Chama o construtor __init__
    print("Objeto criado:", pessoa1)            # Chama o método __str__
    # print(pessoa1.__str__()) # Chama o método __str__
    # Saída do print anterior:    <__main__.Pessoa object at 0x0000014E7C0F9FD0>
    pessoa2 = Pessoa("Rogerio", 77, 1.82, 1995)  # Chama o construtor __init__
    print("Objeto criado:", pessoa2)
    nome = pessoa1.get_nome()                   # Usando variável
    print("- Pessoa 1:\nNome:", nome)
    # print("- Pessoa 1:\nNome:", pessoa1.get_nome())  # Igual as duas anteriores
    print("\nPeso:", pessoa1.get_peso())     # Consulta
    print("Ano Nascimento:", pessoa1.get_ano_nascimento())  # Direto no print
    pessoa1.set_ano_nascimento(2005)
    print("Ano Nascimento alterado:", pessoa1.get_ano_nascimento())  # Confirma
    pessoa1.set_nome("Rogério")                  # Tipo do argumento correto
    print('Nome alterado:', pessoa1.get_nome())  # Teste
    pessoa1.set_nome(2.3)                      # Tipo do argumento errado
    print('Nome:', pessoa1.get_nome())         # Teste
    print("- Pessoa 2:\nNome:", pessoa2.get_nome())
    print("\nPeso:", pessoa2.get_peso())     # Consulta
    print('IMC:', pessoa1.imc())               # IMC: 21.91358024691358
    print(f'IMC: {pessoa1.imc()}')             # IMC: 21.91358024691358
    print(f'IMC: {pessoa2.imc()}')             # IMC: 21.91358024691358
    print(f'IMC: {pessoa1.imc():.2f}')         # IMC: 21.91, f-string
    print(f'IMC: {pessoa2.imc():.2f}')         # IMC: 21.91, f-string
    pessoa3 = Pessoa("Maria", 63, 1.65, 2004)  # Chama método __init__(construtor)
    print("- Pessoa 3:\nNome: ", pessoa3.get_nome())        # Consulta
    print("dta_nascimento: ", pessoa3.get_ano_nascimento())
    # pessoa4 = Pessoa("Ana", 61, 1.69)           # Passando só três argumentos
    # print('- Pessoa 4:\nData Nascimento:', pessoa3.get_ano_nascimento())
    # print(pessoa4.__str__())        # Linhas equivalentes, __str__() é opcional
    # print(pessoa4)
    print(pessoa1)
    pessoa1.set_ano_nascimento(2003)
    print("Idade, pessoa 1:", pessoa1.calcula_idade())
    print("Idade, pessoa 2:", pessoa2.calcula_idade())
    print("Idade, pessoa 3:", pessoa3.calcula_idade())
