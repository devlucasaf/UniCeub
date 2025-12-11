import threading
import time

saldo = 1000

def retirar(valor):
    global saldo
    temp = saldo
    time.sleep(0.5)
    temp = temp - valor
    saldo = temp
    print(f"Retirada: Novo saldo = {saldo}")

def depositar(valor):
    global saldo
    temp = saldo
    time.sleep(0.1)
    temp = temp + valor
    saldo = temp
    print(f"Retirada: Novo saldo = {saldo}")

if __name__ == "__main__":
    t1 = threading.Thread(target=retirar, args=(700,))
    t2 = threading.Thread(target=depositar, args=(200,))

    print(f"Saldo inicial: {saldo}")

    t2.start()
    t1.start()

    t1.join()
    t2.join()

    print(f"Saldo final: {saldo}")