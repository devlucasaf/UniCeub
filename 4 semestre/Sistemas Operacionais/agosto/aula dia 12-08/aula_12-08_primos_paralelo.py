from multiprocessing import Process
import math
import os
import time

def eh_primo(n):
    if n < 2: return False
    for i in range(2, int(math.sqrt(n)) + 1):
        if n % i == 0: return False
    return True

def worker(nums, start_idx):
    pid = os.getpid()
    for i, n in enumerate(nums, start=start_idx):
        print(f"Processo {pid} -> {n} : {'EH_PRIMO' if eh_primo(n) else 'N_EH_PRIMO'}")

if __name__ == "__main__":
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
    
    meio = len(numeros) // 2
    t0 = time.perf_counter()
    p1 = Process(target=worker, args=(numeros[:meio], 1))
    p2 = Process(target=worker, args=(numeros[meio:], meio + 1))

    p1.start() 
    p2.start()
    p1.join() 
    p2.join()
    
    t1 = time.perf_counter()
    print(f"Tempo Paralelo: {t1 - t0:.2f} segundos\n")
