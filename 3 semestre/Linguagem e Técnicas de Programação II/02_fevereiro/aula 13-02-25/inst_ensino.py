def lin ():  #linhas
    print('-'*50)

class Pessoa(object): #Superclasse

    def __init__(self, nome, depend=0):
        self.nome = nome
        self.depend = depend


    def get_nome (self):
        return self.nome
    def set_nome(self, n_nome):
        self.nome = n_nome

    
    def get_depend (self):
        return self.depend
    def set_depend(self, n_depend):
        if n_depend < 0:
            print('DADOS INCONSISTENTES!')
        else:
            self.depend = n_depend

#--------------------------------------------------------
    
class Professor(Pessoa):

    def __init__(self, nome, depend=0, qtd_turma=0, valor_reais=0):
        super().__init__ (nome, depend)
        self.qtd_turma = qtd_turma
        self.valor_reais = valor_reais


    def get_qtd_turma(self):
        return self.qtd_turma
    def set_qtd_turma(self, n_qtd_turma):
        if n_qtd_turma < 0:
            print('DADOS INCONSISTENTES!')
        else:
            self.qtd_turma = n_qtd_turma

    
#--------------------------------------------------------

lin()

if __name__ == '__main__':

    p1 = Pessoa('Alice', 0)
    print ('- Dados de Pessoa ...')
    print(f'Nome: {p1.get_nome()}')

    p1.set_nome('Bruno')
    print(f'Nome: {p1.get_nome()}')


    lin()


