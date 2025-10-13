"""             Comentários de várias linhas.

ctl<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Sintaxe de e POO:

#class NomeClasse:
#class NomeClasse():
class NomeClasse(object):
    def __init__ (self, p_1, p_2, ...):        # Método construtor
        self.nome_atributo1 = p_1              # Atributos
        self.nome_atributo2 = p_2
        ...
    def get_nome_atributo1(self):              # Modelo do método get (retorna valor do atributo)
        return self.nome_atributo1
    def set_nome_atributo1(self, novo_valor):  # Modelo do método set (altera valor do atributo)
        self.nome_atributo1 = novo_valor
    # . . . Outros métodos gets e sets
    def outro_metodo(self):                    # Outros métodos da classe (métodos fundionais)
        # script
        [return ...]

if __name__ == '__main__':                      # mai <tab> (tecla de atalho)
    nome_objeto1 = NomeClasse(a_1, a_2, ...)    # Cria (instancia) o primeiro objeto da classe
    nome_objeto2 = NomeClasse(a_1, a_2, ...)    # Cria (instancia) o segundo objeto da classe
    . . .
    r = nome_objeto1.get_nome_atributo1()       # Consulta um atributo, solução 1
    print("Atributo:",r)
    print("Atributo:", nome_objeto1.get_nome_atributo1()) # Consulta um atributo, solução 2
    nome_objeto1.set_nome_atributo1(novo_valor) # Altera um atributo

- Com base nos conceitos de Programação Orientada a Objetos (POO), implemente
a entidade aluno com estas características (atributos):
nome,
mensalidade e
idade.

- Implemente:
1- Crie um novo projeto, um programa .py e a classe Aluno.
2- Crie o construtor da classe com os atributos: nome, mensalidade, idade
3- No main, crie (instancie) pelo menos dois objetos da classe Aluno, teste

4- Crie os métodos (defs) gets (consulta) dos atributos
5- Use (teste) os métodos gets para os objetos criados
6a- Crie os métodos (defs) sets (altera) dos atributos
6b- Use (teste) os métodos sets para os objetos criados
7- Crie o método (def) mostra_dados. Não recebe nada e não retorna nada.
   Mostra todos os atributos. Teste

"""


# O nome de classe começa com letra maiúscula e as outras letras minúsculas.
# Nome de classe: primeira letra de cada palavra com letra maiúscula
# class Aluno:
# class Aluno():
class Aluno(object):    # Três formas equivalentes de criar a classe.
    def __init__(self, nome, mensalidade, idade):  # Método construtor
        self.nome = nome                # Atributos
        self.mensalidade = mensalidade  # self.nome_atributo = parametro
        self.idade = idade
    def get_nome(self):                 # Métodos gets (consulta)
        return self.nome
    def get_mensalidade(self):          # Consulta o valor do atributo e retorna
        return self.mensalidade
    def get_idade(self):
        return self.idade
    def set_nome(self, novo_nome):      # Métodos sets (altera)
        self.nome = novo_nome
    def set_mensalidade(self, novo_valor):  # Altera o valor do atributo e não retorna
        self.mensalidade = novo_valor
    def set_idade(self, nova_idade):
        self.idade = nova_idade
    def mostra_dados(self):                     # Solução 1, com atributos
        print('- Mostra dados:\nNome:', self.nome)
        print('Mensalidade:', self.mensalidade)
        print('Idade:', self.idade)
    def mostra_dados2(self):                   # Solução 2, com métodos
        print('- Nome:', self.get_nome())
        print('Mensalidade:', self.get_mensalidade())
        print('Idade:', self.get_idade())


if __name__ == '__main__':                # Atalho: mai <tab>
    aluno1 = Aluno('Paulo', 1100.00, 21)  # Chama o método __init__ (construtor)
    print(aluno1)  # Mostra o endereço hexadecimal onde o objeto foi armazenado
    # <aluno_4.Aluno object at 0x0000015D8FF16FD0>
    aluno2 = Aluno('Emily', 1300.00, 20)  # Chama o método __init__ (construtor)
    print(aluno2)  # Mostra o objeto da classe Aluno e o endereço hexadecimal
    # <aluno_4.Aluno object at 0x0000015D8FF16F70>
    print("- Aluno1:\nNome:", aluno1.get_nome())  # print(nome_objeto.nome_metodo())
    print("Mensalidade:", aluno1.get_mensalidade())
    print("Idade:", aluno1.get_idade())
    print("- Aluno2:\nNome:", aluno2.get_nome())  # print(nome_objeto.nome_metodo())
    print(f"Mensalidade: {aluno2.get_mensalidade()}")
    print(f'Idade: {aluno2.get_idade()}')
    # nome1 = "Ailton Pinheiro"
    nome1 = input("Novo nome: ")                        # Solução 1
    aluno1.set_nome(nome1)  # nome_objeto.nome_metodo() - não retorna dado
    # aluno1.set_nome(input("Novo nome: "))  # Equivalente as 2 linhas anteriores
    print('Nome atualizado:', aluno1.get_nome())        # Verifica (testa)
    aluno2.set_nome("Alice")                            # Solução 2
    print('Nome atualizado:', aluno2.get_nome())        # Verifica (testa)
    aluno1.set_mensalidade(1200.00)
    print("Mensalidade atualizada:", aluno1.get_mensalidade())  # Verifica (testa)
    aluno1.mostra_dados()  # nome_objeto.nome_metodo() - não retorna dado
    aluno2.mostra_dados()
    aluno1.mostra_dados2()
    aluno2.mostra_dados2()
