"""            Comentários de várias linhas.

  CEUB  -  FATECS  -  BCC  -  ADS  -  Programação  -  Prof. Barbosa
ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Resolva com lista

- Uma agência de publicidade quer prestar seus serviços somente
para a maior companhia, considerando a quantidade de funcionários.
Para tal, conseguimos um conjunto de dados com o CNPJ (Cadastro
Nacional de Pessoa Jurídica) e a quantidade de funcionários de
várias empresas. Construa o programa que leia esses dados de
entrada e mostre o CNPJ e a quantidade de funcionários da maior
empresa, ou seja, da empresa com maior recursos humanos.

         - Entrada:                         - Saída:
Teste 1: 111, 15; 110, 20; <enter>          Maior empresa: 110, qtd 20
Teste 2: 111, 15; 112, 20; 113, 10; <enter> Maior empresa: 112, qtd 20
Teste 3: <enter>                            Maior empresa: '', qtd 0

- Dicas (algortimo):
crie duas listas vazias
estrutura de repetição   # while, leia e armazene os valores nas listas
    lê cnpj
    testa condição de saída
    inclui cnpj na lista
    lê qtd
    inclui qtd na lista
pegue o maior valor na lista l_qtd      # Depois do while
peque o índice onde está o maior valor
pegue o cnpj do maior valor
tela de saída                           """

l_cnpj = []                          # Duas formas de criar lista vazia
l_qtd = list()
print('Digite <enter> pra sair')
while True:
    cnpj = input("CNPJ: ")           # Recebe os dados
    if cnpj == '':                   # Sai do while com <enter>
        break
    l_cnpj.append(cnpj)              # Armazena o cnpj na lista
    qtd = int(input("Quantidade de funcionários: "))
    l_qtd.append(qtd)                # Armazena a qtd na lista
    # Fim do while
maior_qtd = max(l_qtd)
indice = l_qtd.index(maior_qtd)
# Pega o índice (posição) onde está o maior valor
# indice = l_qtd.index(max(l_qtd))
# Equivalente as duas linhas anteriores
maior_cnpj = l_cnpj[indice]
# Pega o valor armazenado neste índice (posição)
# maior_cnpj = l_cnpj[l_qtd.index(max(l_qtd))]
# Equivalente as  3 anteriores
print(f"Empresa {maior_cnpj} tem maior número de funcionários "
      f"com {maior_qtd}.")
print("Empresa " + maior_cnpj + " tem maior número de funcionários com",
      maior_qtd, ".")
''' ----- ALTERAÇÕES:
a. Digite a condição de saída de primeira e mostre a mensagem: 
   "Nenhum dado foi fornecido."
b. Quantas empresas foram processadas?
c. Mostre também o CNPJ e a quantidade de funcionários da menor empresa.
    ----- DICAS:
...                     # Fim do while                           # a.
if len(l_cnpj) == 0:                # Lista está vazia?
    print("Nenhuma empresa recebida (lista vazia)")
else:
    maior_qtd = max(l_qtd)
    indice = l_qtd.index(maior_qtd)     
    # indice = l_qtd.index(max(l_qtd))  
    maior_cnpj = l_cnpj[indice]         
    # maior_cnpj = l_cnpj[l_qtd.index(max(l_qtd))]  
    print(f"Empresa {maior_cnpj} tem maior número de funcionários com
     {maior_qtd}.")
    # print("Empresa " + maior_cnpj + " tem maior número de 
    funcionários com ", maior_qtd, ".")
print(f'Quantidades de empresas processadas: {len(l_cnpj)}')     # b.
menor_qtd = min(l_qtd)                                           # c.
indice_min = l_qtd.index(menor_qtd)
menor_cnpj = l_cnpj[indice_min]
# menor_cnpj = l_cnpj[l_qtd.index(min(l_qtd))]  
# Equivalente as linhas anteriores
print(f"Empresa {menor_cnpj} tem maior número de funcionários com um 
total {menor_qtd}.")

'''
