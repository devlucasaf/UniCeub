import time

def eh_primo(n):
    if n < 2:
        return False
    for i in range(2, int(n**0.5) + 1):
        if n % i == 0:
            return False
    return True

numeros = [
    400000000000063,
    400000000000129,
    400000000000147,
    400000000000187,
    400000000000201,
    400000000000231,
    400000000000241,
    400000000000247,
    400000000000253,
    400000000000261,
    400000000000339,
    400000000000357,
    400000000000361,
    400000000000399,
    400000000000507,
    900000060000001,  
    900001380000529, 
    900002220001369, 
    900002460001681,  
    900002940002401,  
]

t0 = time.perf_counter()
for idx, num in enumerate(numeros, start=1):
    if eh_primo(num):
        print(f"{num} : EH_PRIMO")
    else:
        print(f"{num} : N_EH_PRIMO")

t1 = time.perf_counter()
print(f"Tempo sequencial: {t1 - t0:.2f} segundos\n")