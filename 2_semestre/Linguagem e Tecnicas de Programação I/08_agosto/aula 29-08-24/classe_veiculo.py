# Cores hexadecimais

red = "\33[1;49;31m"
green = "\33[1;49;32m"
yellow = "\33[1;49;33m"
blue = "\33[1;49;34m"
pink = "\33[1;49;35m"
cyan = "\33[1;49;36m"
white = "\33[1;49;37m"
nc = "\33[m"

def linha():
    print("+=+="*69)

class Veiculo(object):
    def __init__(self, modelo, ano, preço):

        self.modelo = modelo
        self.ano = ano
        self.preço = preço

# Método get   
    def get_modelo (self):
        return self.modelo
    
    def get_ano (self):
        return self.ano
    
    def get_preço (self):
        return self.preço

# Método set 

    def set_modelo(self, novo_modelo):
        self.modelo = novo_modelo

    def set_ano(self, novo_ano):
        self.ano = novo_ano

    def set_preço(self, novo_preço):
        if novo_preço < 0:
            print(f'{yellow}\nDADOS INCONSISTENTES!{nc}')
        else:
            self.preço = novo_preço

# Função para mostrar os dados do Veículo 

    def mostra_dados(self):
        print(f'{blue}>>Mostra dados [carro 2]<<{nc}')
        print(f'Modelo: {self.modelo} \nAno: {self.ano} \nPreço: {self.preço}')

# Função para aumentar o preço do veículo 

    def aumenta_valor(self, aumenta_preço): 
        self.preço = aumenta_preço

if __name__ == "__main__":
    carro1 = Veiculo('HB', 2022, 80500.00)
    carro2 = Veiculo('Carola', 2024, 190000.00)

    linha()
    
    print(f'{blue}>>>>>Dados [carro 1]<<<<<{nc}') 
    print(f'Modelo: {carro1.get_modelo()} \nAno: {carro1.get_ano()} \nPreço: {carro1.get_preço()}')
    
    linha()
    
    print(f'{blue}>>Dados [carro 2]<<{nc}') 
    print(f'Modelo: {carro2.get_modelo()} \nAno: {carro2.get_ano()} \nPreço: {carro2.get_preço()}')
    
    linha()
    
    print(f'{cyan}>>ATUALIZAÇÃO [CARRO 1]<<{nc}')
    
    carro1.set_modelo('HB20')
    carro1.set_ano(2020)
    carro1.set_preço(-1)
    
    print(f'Modelo: {carro1.get_modelo()} \nAno: {carro1.get_ano()} \nPreço: {carro1.get_preço()}')
    
    linha()
    
    carro2.mostra_dados()

    linha()

    carro1.aumenta_valor()
