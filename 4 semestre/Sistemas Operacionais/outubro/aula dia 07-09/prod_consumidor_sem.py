#Situação Problema com uso de semáforos para garantir a consistência do saldo
#Aula 07/10/2025 - Semáforos
#Prof. Me. Michel Junio

import threading
import time

#variavies globais acessadas pelas threads (Deposito e Saque)
saldo = 0
mutex = threading.Semaphore(1) #mutex: nome padrão dado ao semáforo do tipo binário, ou seja, que assume apenas valores 1 ou 0.

def deposito(valor):
    global saldo, mutex
    mutex.acquire() #sleep (primitiva ver slides)
    saldo_local = saldo
    time.sleep(0)
    saldo_local = saldo_local + valor
    saldo = saldo_local
    mutex.release() #wakeup (primitiva ver slides)
    print(f"Saldo Deposito: {saldo}")


def saque(valor):
    global saldo, mutex
    mutex.acquire() #sleep (primitiva ver slides)
    saldo_local = saldo
    saldo_local = saldo_local - valor
    saldo = saldo_local
    mutex.release() #wakeup (primitiva ver slides)
    print(f"Saldo Saque: {saldo}")

if __name__ == '__main__':
    print(f'Saldo Inicial: {saldo}')
    valor_deposito = 1000
    valor_saque = 500
    d1 = threading.Thread(target=deposito, args=(valor_deposito,))
    s1 = threading.Thread(target=saque, args=(valor_saque,))

    #Inicializa as threads
    d1.start()
    s1.start()

    #Processo pai aguarda finalização das threads
    d1.join()
    s1.join()

    print(f'Saldo Esperado: 500 - Saldo Final: {saldo}')