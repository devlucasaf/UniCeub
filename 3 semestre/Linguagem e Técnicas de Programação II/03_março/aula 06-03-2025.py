from datetime import datetime
# class Cliente(object):  # Três formas de criar uma classe
# class Cliente():
class Cliente:   # Nome de classe no singular, pois é um modelo
    def __init__(self, nome, ano_nascimento):
        self.nome = nome
        self.ano_nascimento = ano_nascimento
        self.enderecos = []  # Nome de lista no plural, pode ter vários conteúdos
    def get_nome(self):
        return self.nome
    def set_nome(self, novo_nome):
        self.nome = novo_nome
    def get_ano_nascimento(self):
        return self.ano_nascimento
    def set_ano_nascimento(self, novo_ano):
        self.ano_nascimento = novo_ano
    # def calcula_idade(self):      # Uso o valor do ano estático
    #     ano_atual = 2025
    #     return ano_atual - self.ano_nascimento
    def calcula_idade(self):        # Pega o ano do sistema operacional
        ano_atual = datetime.now().year
        return ano_atual - self.ano_nascimento
    # Insere endereço com strings (cidade e estado)
    def insere_endereco(self, cidade, estado='DF'):  # Recebe duas strings
        o_endereco = Endereco(cidade, estado)  # Chama o construtor da classe Endereco
        self.enderecos.append(o_endereco)      # Armazena na lista
        # self.enderecos.append(Endereco(cidade, estado))
        # Equivalente as 2 anteriores
    # Mostra endereços simples
    def mostra_enderecos(self):
        print(f'- Endereços do cliente:')
        for o_endereco in self.enderecos:    # Percorre a lista self.enderecos
            # print(o_endereco.get_cidade(), "-", o_endereco.get_estado())  # Não usa o str
            print(o_endereco)                # Usa o método str
    # Insere endereço passando um objeto Endereco
    def insere_endereco2(self, o_endereco):  # Recebe um objeto da classe Endereco
        self.enderecos.append(o_endereco)    # Armazena na lista
    # def insere_endereco2(self, endereco_obj):  # Com crítica
    #     if isinstance(endereco_obj, Endereco):
    #         self.enderecos.append(endereco_obj)
    #     else:
    #         print("Erro: O argumento deve ser um objeto da classe Endereco.")
    def mostra_enderecos2(self):
        print(f'- Endereços do cliente {self.nome}:')
        for o_endereco in self.enderecos:
            print(o_endereco.get_cidade(), "-", o_endereco.get_estado())
            # print(f" - {o_endereco}")
    # Mostra endereços com melhor formatação
    def mostra_enderecos3(self):
        # if len(self.enderecos) == 0: # if self.enderecos == []:  # lista vazia?
        if not self.enderecos:  # lista vazia?
            print("Cliente não tem endereço cadastrado.")
        else:
            print(f'- Endereços do cliente {self.nome}')
            for o_endereco in self.enderecos:
                print(o_endereco.get_cidade(), o_endereco.get_estado())
                # print(f" - {o_endereco}")
    # Remove endereço passando um objeto
    def remove_endereco_objeto(self, o_endereco):
        self.enderecos.remove(o_endereco)
    # def remove_endereco(self, endereco_obj):
    #     if endereco_obj in self.enderecos:
    #         self.enderecos.remove(endereco_obj)
    #         print("Endereço removido com sucesso.")
    #     else:
    #         print("Erro: Endereço não encontrado.")
    # Remove endereço pela cidade


# Três formas de criar uma classe
# class Endereco(object):
# class Endereco():
class Endereco:  # Nome de classe no singular, pois é um modelo
    def __init__(self, cidade, estado='DF'):
        self.cidade = cidade
        self.estado = estado
    def get_cidade(self):
        return self.cidade
    def set_cidade(self, nova_cidade):
        self.cidade = nova_cidade
    def get_estado(self):
        return self.estado
    def set_estado(self, novo_estado):
        self.estado = novo_estado
    def __str__(self):
        return f"{self.cidade} - {self.estado}"

# Sintaxe: from nome_arquivo_sem_extensao import NomeClasse1, NomeClasse2
from cliente_aula import Cliente, Endereco
if __name__ == '__main__':
    cliente1 = Cliente('Luis', 2005)  # Chama construtor de Cliente
    print('- Nome:', cliente1.get_nome())
    print('Ano Nascimento:', cliente1.get_ano_nascimento())
    print('Idade:', cliente1.calcula_idade(), "anos.")
    print(f'Idade: {cliente1.calcula_idade()} anos.')  # f-string
    cliente1.insere_endereco('Belo Horizonte', 'MG')  # Chama método, passa 2 strings
    cliente1.mostra_enderecos()
    cliente2 = Cliente('Maria', 2007)
    cliente2.insere_endereco('Salvador', 'BA')
    cliente2.insere_endereco('Rio de Janeiro', 'RJ')
    print('- Nome:', cliente2.get_nome())
    cliente2.mostra_enderecos()
    endereco = Endereco('Brasília', 'DF')  # Chama construtor da classe Endereco
    cliente2.insere_endereco2(endereco)    # Chama o método, passa um objeto de Endereco
    print('- Nome:', cliente2.get_nome())
    cliente2.mostra_enderecos()
    cliente2.mostra_enderecos2()           # Equivalente as duas linhas anteriores
    # Cria o cliente 3
    cliente3 = Cliente('João', 2006)
    cliente3.insere_endereco('São Paulo', 'SP')
    print('- Nome:', cliente3.get_nome())
    cliente3.mostra_enderecos()
    cliente3.mostra_enderecos2()           # Equivalente as duas linhas anteriores
    cliente2.mostra_enderecos3()
    cliente2.remove_endereco_objeto(endereco)
    cliente2.mostra_enderecos3()

