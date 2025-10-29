white = "\33[1;49;37m" #branco
yellow = "\33[1;49;31m"  #amarelo
purple = "\33[1;49;32m" #roxo
cyan = "\33[1;49;36m" #ciano
blue = "\33[1;49;34m"
magenta = "\33[1;49;35m"
nc = "\33[m" #sem cor

def linha(): 
    print(f"+=+="*69)

class Point():
    def __init__(self, x=0.0, y=0.0):
        self.x = x
        self.y = y

#--------------------------------------------------------

    def get_x (self):
        return self.x 
    
    def get_y (self):
        return self.y

#--------------------------------------------------------

    def set_x (self, novo_x):
        
        if type (novo_x) in (int, float):
            self.x = novo_x  
        
        else:
            print(f"{yellow}\nERRO! Precisa ser {purple}'int'{yellow} ou {purple}'float'{yellow}.")   


    def set_y (self, novo_y):
        self.y = novo_y

#--------------------------------------------------------

    def __str__(self):
        s = f"({self.x}, {self.y})"
        return s

if __name__ == "__main__":

    p1 = Point()
    p2 = Point(2, 3)
    p3 = Point(5)
    p4 = Point(y=6)
    #--------------------------------------------------------

    linha()

    print(f"{purple}X = {p1.get_x()}{nc}")
    print(f"{purple}Y = {p1.get_y()}{nc}")


    print(f"{cyan}\nAlterando valor de [X, Y] do ponto 1:")
    
    p1.set_x(4)
    p1.set_y(6)
    print(f"PONTO 1: {purple}{p1.__str__()}")
    
    #--------------------------------------------------------

    print(f"\n{cyan}Outros pontos:")

    print(f"\nPONTO 2: {purple}{p2.__str__()}")

    print(f"{cyan}\nPONTO 3: {purple}{p3.__str__()}")

    print(f"{cyan}\nPONTO 4: {purple}{p4.__str__()}{nc}")

    linha()  
