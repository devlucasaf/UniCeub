"""
Atividade de Sistemas Operacionais
Data: 02-09-25
"""

import threading

import time
import random

N = 5
buffer = []
cont = 0

def produtor():
    for l in range(10):
        r = random.randint(0, 100)
        if l == N:
            buffer.insert(l,r)
            time.sleep(0,5)
            print(buffer)
        
        else:
            buffer.insert(l, r)
            print(buffer)

def consumidor():
    for h in range(N):
        if h == N:
            buffer.pop(5)
            print(buffer)

if __name__ == "__main__":
    produtor1 = threading.Thread(target=produtor)
    consumidor1 = threading.Thread(target=consumidor)

    produtor1.start()
    consumidor1.start()