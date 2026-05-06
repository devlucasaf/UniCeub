import math

def calcular_distancia(x1, y1, x2, y2):
    return math.sqrt((x2 - x1) ** 2 + (y2 - y1) ** 2)

def calcular_perimetro(x1, y1, x2, y2, x3, y3):
    a = calcular_distancia(x1, y1, x2, y2)
    b = calcular_distancia(x2, y2, x3, y3)
    c = calcular_distancia(x1, y1, x3, y3)
    return a + b + c

def calcular_area(x1, y1, x2, y2, x3, y3):
    a = calcular_distancia(x1, y1, x2, y2)
    b = calcular_distancia(x2, y2, x3, y3)
    c = calcular_distancia(x1, y1, x3, y3)
    p = (a + b + c) / 2
    return math.sqrt(p * (p - a) * (p - b) * (p - c))

def calc_medidas_do_triangulo(x1, y1, x2, y2, x3, y3):
    perimetro = calcular_perimetro(x1, y1, x2, y2, x3, y3)
    area = calcular_area(x1, y1, x2, y2, x3, y3)
    return perimetro, area

# Caso de teste
x1, y1 = 1, 1
x2, y2 = 4, 4
x3, y3 = 2, 2

perimetro, area = calc_medidas_do_triangulo(x1, y1, x2, y2, x3, y3)
print(f"Perímetro do triângulo: {perimetro}")
print(f"Área do triângulo: {area}")
