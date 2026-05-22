from datetime import datetime

def lin ():  #linhas
    print('-'*50)


class Cliente:
    def __init__(self, nome, ano_nasc):
        self.nome = nome
        self.ano_nasc = ano_nasc
        self.endereco = [] #lista


    def get_nome(self):
        return self.nome
    def set_nome(self, n_nome):
        self.nome = n_nome


    def get_ano_nasc(self):
        return self.ano_nasc
    def set_ano_nasc(self, n_ano_nasc):
        self.ano_nasc = n_ano_nasc
    

    #def calcula_idade(self):
        #return 2025 - self.ano_nascimento

    def calcula_idade(self):
        return datetime.now().year - self.ano_nasc
    
    #---------------------------------------------------
    def insira_endereco(self, cidade, estado='DF'):
        obj_end = Endereco(cidade, estado)
        self.endereco.append(obj_end)    

    def mostrar_endereco(self):
        for obj_end in self.endereco:
            print(obj_end)

    #---------------------------------------------------

    def insira_endereco2(self, obj_end):
        self.endereco.append(obj_end)

    def mostra_endereco2(self):
        if len(self.endereco) !=0:
            for obj_end in self.endereco:
                print(obj_end.get_cidade(), obj_end.get_estado())
        else:
            print("nao existem endereços")

#+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-+-

class Endereco:
    def __init__(self, cidade, estado = 'DF'):
        self.cidade = cidade
        self.estado = estado

    def get_cidade(self):
        return self.cidade
    def set_cidade(self, n_cidade):
        self.cidade = n_cidade

    def get_estado(self):
        return self.estado
    def set_estado(self, n_estado):
        self.estado = n_estado


    def __str__(self):
        return f'Cidade: {self.cidade} \nEstado: {self.estado}' 

if __name__ == '__main__':

    lin()

    cl1 = Cliente("Guilherme", 2004)
    e1 = Endereco("Brasília","DF")
    print(f'Nome: {cl1.get_nome()} \nAno Nascimento: {cl1.get_ano_nasc()} \nIdade: {cl1.calcula_idade()}')
    print(f'{e1.__str__()}')

    print(cl1.mostra_endereco2())

    lin()

