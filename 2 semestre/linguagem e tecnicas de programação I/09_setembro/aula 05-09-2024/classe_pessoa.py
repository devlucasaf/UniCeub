white = "\33[1;49;37m" 
yellow = "\33[1;49;31m"  
purple = "\33[1;49;32m" 
cyan = "\33[1;49;36m" 
blue = "\33[1;49;34m"
magenta = "\33[1;49;35m"
nc = "\33[m" 

def linha(): #mostra linhahas
    print(f'{white}>{"+=+="*69}<{nc}')

class Pessoa(object):
    def __init__ (self, nome, peso, altura, ano_nasc=2000):
        self.nome = nome
        self.peso = peso
        self.altura = altura
        self.ano_nasc = ano_nasc

# Método Get

    def get_nome (self):
        return self.nome
    
    def get_peso (self):
        return self.peso
    
    def get_altura (self):
        return self.altura
    
    def get_ano_nasc (self):
        return self.ano_nasc
    
# Método Set

    def set_nome (self, novo_nome):
        self.nome = novo_nome

    def set_peso (self, novo_peso):
        self.peso = novo_peso

    def set_altura (self, nova_altura):
        self.altura = nova_altura

    def set_ano_nasc (self, novo_ano_nasc):
        if type(novo_ano_nasc) == int:
            self.ano_nasc = novo_ano_nasc

        else:
            print(f"{yellow}Dados Inconsistentes!{nc}")

#--------------------------------------------------

    def imc(self):
        return self.peso / (self.altura**2)    

def mostrar_dados():
    print(f"Nome: {p1.get_nome()}")
    print(f"Peso: {p1.get_peso()}Kg")
    print(f"Altura = {p1.get_altura()}")
    print(f"Ano de Nascimento = {p1.get_ano_nasc()}")
    print(f"O IMC do(a) {p1.get_nome()} é {p1.imc():.2f}")

# Main

if __name__ == '__main__':

    p1 = Pessoa("Leanderson", 50, 1.72, 2004)
    p2 = Pessoa("Cannabis", 60, 1.70, 2005)
    p3 = Pessoa("Joelinton", 51, 1.66)

    linha()
    
    print(f"{purple}>>>Pessoa 1<<<{nc}")
    print(f"Nome: {p1.get_nome()}")
    print(f"Peso: {p1.get_peso()}Kg")
    print(f"Altura = {p1.get_altura()}")
    print(f"Ano de Nascimento = {p1.get_ano_nasc()}")
    print(f"O IMC do(a) {p1.get_nome()} é {p1.imc():.2f}")

    linha()

    print(f"{purple}>>>Pessoa 2<<<{nc}")
    print(f"Nome: {p2.get_nome()}")
    print(f"Peso: {p2.get_peso()}Kg")
    print(f"Altura = {p2.get_altura()}")
    print(f"Ano de Nascimento = {p2.get_ano_nasc()}")
    print(f"O IMC do(a) {p2.get_nome()} é {p2.imc():.2f}")

    linha()

    print(f"{purple}>>>Pessoa 3<<<{nc}")
    print(f"Nome: {p3.get_nome()}")
    print(f"Peso: {p3.get_peso()}Kg")
    print(f"Altura = {p3.get_altura()}")
    print(f"Ano de Nascimento = {p3.get_ano_nasc()}")
    print(f"O IMC do(a) {p3.get_nome()} é {p3.imc():.2f}")

    linha()

    p1.set_ano_nasc = 2000

    print(f"{purple}>>>Pessoa 1<<<{nc}")
    print(f"Nome: {p1.get_nome()}")
    print(f"Peso: {p1.get_peso()}Kg")
    print(f"Altura = {p1.get_altura()}")
    print(f"Ano de Nascimento = {p1.get_ano_nasc()}")
    print(f"O IMC do(a) {p1.get_nome()} é {p1.imc():.2f}")
