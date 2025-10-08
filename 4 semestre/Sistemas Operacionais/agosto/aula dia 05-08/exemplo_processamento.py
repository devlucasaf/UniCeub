import os
from multiprocessing import Process


def imprime_1000():
    print("PID do processo pai 1000:", os.getppid())
    print("PID dentro do for 1000:", os.getpid())
    for i in range(0, 1000):
        print(i)

def imprime_2000():
    print("PID do processo pai 2000:", os.getppid())
    print("PID dentro do for 2000:", os.getpid())
    for i in range(1000, 2000):
        print(i)

if __name__ == "__main__":
    print("PID do processo pai:", os.getppid())
    print("PID do processo:", os.getpid())

    p1 = Process(target=imprime_1000)
    p2 = Process(target=imprime_2000)
    p1.start()
    p2.start()
    p1.join()
    p2.join()
    print("Encerrar o programa")