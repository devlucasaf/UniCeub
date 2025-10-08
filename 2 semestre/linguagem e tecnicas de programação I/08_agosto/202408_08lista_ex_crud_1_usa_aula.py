# Sintaxe: from nome_arquivo import *
# Obs.: nome_arquivo sem a extensão
from lista_ex_crud_1_aula import *
if __name__ == '__main__':  # Atalho: mai <tab>
    while True:             # Solução 1
        op = menu()         # A variável op recebe o valor da função menu
        if op == 'c':
            # print('create()')
            create()      # Chama a função create
        elif op == 'r':
            # print('read()')
            read()        # Chama a função read
        elif op == 'u':
            # print('update()')
            update()      # Chama a função update
        elif op == 'd':
            # print('delete()')
            delete()      # Chama a função delete
        else:
            break
