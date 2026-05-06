whitw = "\33[1;49;37m" #branco
yellow = "\33[1;49;31m"  #amarelo
purple = "\33[1;49;32m" #roxo
cyan = "\33[1;49;36m" #ciano
blue = "\33[1;49;34m"
magenta = "\33[1;49;35m"
red = "\33[1;49;31m" 
yyellow = "\33[1;49;34m" 
nc = "\33[m" #sem cor

def lin ():  #linhas
    print("-"*50)

class Veiculo(): #Superclasse

    def __init__(self, modelo, preco, km=0):
        
        self.modelo = modelo
        self.preco = preco
        self.km = km


    # Métodos Get
    
    def get_modelo (self):
        return self.modelo
    
    def get_preco (self):
        return self.preco
    
    def get_km (self):
        return self.km
    
    # Método Set

    def set_modelo(self, novo_modelo):
        self.modelo = novo_modelo

    def set_preco(self, novo_preco):
        if novo_preco < 0:
            print(f"{red}DADOS INCONSISTENTES!{nc}")
        else:
            self.preco = novo_preco

    def set_km(self, novo_km):
        self.km = novo_km

    # +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+

    def atualiza_valor(self, vlr_aumento):
        if vlr_aumento < 0:
            print(f"{red}DADOS INCONSISTENTES!{nc}")
        else:
            self.preco = self.preco + vlr_aumento

    #+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+
    
    def situacao_veiculo(self):
        if self.km == 0:
            print(f"{cyan}VEÍCULO NOVO!{nc}")
            
        elif self.km <= 20000:
            print(f"{cyan}VEÍCULO SEMINOVO!{nc}")

        elif self.km > 20000:
            print(f"{cyan}VEÍCULO USADO!{nc}")

class Carro(Veiculo):

    def __init__(self, modelo, preco, km=0, n_portas=2):
        super().__init__ (modelo, preco, km)
        self.n_portas = n_portas

    def get_n_portas(self):
        return self.n_portas
    
    def set_n_portas(self, novo_n_portas):
        self.n_portas = novo_n_portas

    def mostra_dados(self):
        print(f"Modelo: {yyellow}{self.modelo}{nc}")
        print(f"Preço: {yyellow}R${self.preco}{nc}")
        print(f"Quilometragem: {yyellow}{self.km}{nc}")
        print(f"Número de Portas: {yyellow}{self.n_portas}{nc}")

    def __str__(self):
        s = f"Modelo: {yyellow}{self.modelo}{nc} \nPreço: {yyellow}{self.preco}{nc} \nQuilometragem: {yyellow}{self.km}{nc} \nNúmero de portas: {yyellow}{self.n_portas}{nc}"
        return s

class Moto(Veiculo):

    def __init__(self, modelo, preco, km=0, cilindradas=100):
        super().__init__(modelo, preco, km)
        self.cilindradas = cilindradas

    def get_cilindradas(self):
        return self.cilindradas
    
    def set_cilindradas(self, nova_cilindradas):
        if nova_cilindradas < 125:
            print(f"{red}DADOS INCONSISTENTES!{nc}")
        self.cilindradas = nova_cilindradas

    def mostra_dados(self):
        print(f"Modelo: {yyellow}{self.modelo}{nc}")
        print(f"Preço: {yyellow}R${self.preco}{nc}")
        print(f"Quilometragem: {yyellow}{self.km}{nc}")
        print(f"Cilindradas: {yyellow}{self.cilindradas}cc{nc}")

if __name__ == "__main__":

    car = Carro("Civic Hybrid", 265900.00, 80000, 4)
    car2 = Carro("HR-V", 162300.00, 60000, 4)
    car3 = Carro("aaa", 130000.00, 80000, 4)

    print(car, car2) #teste

    moto1 = Moto("CG 160",17440.00, 90000, 162)
    moto2 = Moto("FZ15", 19690.00, 50000, 150)
    moto3 = Moto("FZ25", 21690.00, 80000, 250)
    
    print(moto1, moto2) #teste
    
    lin()

    print(f" ----------- | {red}AUTOMÓVEL{nc} | -----------  ")
    
    print(f"{red}CARRO 1{nc}")
    print(f"Modelo: {yyellow}{car.get_modelo}{nc}")
    print(f"Preço: {yyellow}R${car.get_preco}{nc}")
    print(f"Quilometragem: {yyellow}{car.get_km}{nc}")
    print(f"Número de Portas: {yyellow}{car.get_n_portas}{nc}")
    
    print(f"\n{red}Alternando preço do Honda Civic Hybrid{nc}")
    car.set_preco(275800.00)
    print(f"Novo preço: {yyellow}R${car.get_preco()}{nc}")
    
    print(f"\n{red}CARRO 2 (MOSTRA DADOS){nc}")
    car2.mostra_dados()

    print(f"\n{red}CARRO 3 (__STR__){nc}")
    print(car3.__str__())

    lin()
    print(f"{red}MOTO 1 (MOSTRA DADOS){nc}")
    moto1.mostra_dados()

    print(f"\n{red}MOTO 2 (MOSTRA DADOS){nc}")
    moto2.mostra_dados()

    print(f"\n{red}MOTO 3 (DICT){nc}")
    print(moto3.__dict__)

    moto3.atualiza_valor(1200)
    print(f"\n{cyan}NOVO PREÇO DA FZ25: {yyellow}{moto3.get_preco()}{nc}")
    moto3.mostra_dados()

    print(f"Moto3")
    moto3.situacao_veiculo()
