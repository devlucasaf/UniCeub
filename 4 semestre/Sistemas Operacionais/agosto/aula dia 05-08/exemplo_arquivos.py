import os

fd = os.open("saida.txt", os.O_CREAT | os.O_WRONLY)

#só aceita bytes
os.write(fd, b"Escrevendo direto com syscall write!\n")
os.close(fd)

fd = os.open("saida.txt", os.O_RDONLY)
conteudo = os.read(fd, 100)
print("Conteúdo lido:", conteudo.decode())
os.close(fd)
