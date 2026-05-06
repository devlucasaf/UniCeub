import threading
import time
import random
from datetime import datetime

buffer = []
buffer_maximo = 5

mutex = threading.Lock()
itens = threading.Semaphore(0)        
espaco = threading.Semaphore(buffer_maximo)

def produtor(id):
    while True:
        item = random.randint(1, 100) 

        espaco.acquire()     
        mutex.acquire()                

        buffer.append(item)
        print(f"[{datetime.now().strftime('%H:%M:%S')}] Produtor {id} produziu {item}\n Buffer: {buffer}")

        mutex.release()                
        itens.release()      

        time.sleep(random.random())    

def consumidor(id):
    while True:
        itens.acquire()      
        mutex.acquire()                
        
        item = buffer.pop(0)
        print(f"[({datetime.now().strftime('%H:%M:%S')}] Consumidor {id} consumiu {item}\n Buffer: {buffer}")

        mutex.release()                
        espaco.release()     

        time.sleep(random.random())    

for i in range(3):   
    threading.Thread(target=produtor, args=(i+1,), daemon=True).start()

for i in range(2):   
    threading.Thread(target=consumidor, args=(i+1,), daemon=True).start()

while True:
    time.sleep(1)
