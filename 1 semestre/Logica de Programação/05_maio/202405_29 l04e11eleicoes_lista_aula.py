"""            Comentários de várias linhas.

  CEUB  -  FATECS  -  BCC  -  ADS  -  Programação  -  Prof. Barbosa
ctlr<d>, duplica linha. ctrl<y>, apaga linha. ctrl</>, comenta linha.

- Resolva com lista

- Elabore um programa calcular a quantidade de votos em uma eleição
presidência com três candidatos. Calcule a quantidade de votos de cada
candidato, bem como a quantidade de votos nulos e a quantidade de votos
em branco.
Os votos serão informados através de códigos, onde:
1, 2 e 3 representam os votos para os respectivos candidatos;
5 representa votos nulos;
6 representa votos em branco.

Teste 1: Entradas: 1, 2, 3, 5, 6, 9
         Saída: Cand1=1 Cand2=1 Cand3=1 Nulo=1 Branco=1

- Dicas (algoritmo):
cria lista vazia
estrutura de repetição
    lê voto
    testa condição de saída
    inclui voto na lista
usa as funções de lista
tela de saída
"""
l_voto = []
while True:
    voto =int(input("[1] Candidato 1\n[2] Candidato 2\n[3] Candidato 3"
                     "\n[5] Voto nulo\n[6] Voto em branco"
                     "\n[9] Encerrar\nDigite voto: "))
    if voto == 9:
        break
    l_voto.append(voto)

ct_candidato1 = l_voto.count(1)
ct_candidato2 = l_voto.count(2)
ct_candidato3 = l_voto.count(3)
ct_nulo = l_voto.count(5)
ct_branco = l_voto.count(6)
print("Candidato1 = ", ct_candidato1, " votos.")
print("Candidato2 = ", ct_candidato2, " votos.")
print("Candidato3 = ", ct_candidato3, " votos.")
print("Votos nulos = ", ct_nulo)
print("Votos em branco = ", ct_branco)
""" --- ALTERAÇÕES:
a. Calcule e mostre a porcentagem de votos nulos e a porcentagem 
   de votos em branco.
Saída:
Cand1=1 Cand2=1 Cand3=1 Nulo=1 Branco=1 pct_nulo=20% pct_branco=20%
b. Mostra as porcentagens com duas casas decimais e o símbolo de 
   porcentagem (%) no final da mensagem.   
c. Mostre uma mensagem de erro se eleitor digitar valor inválido.
d. Digite a condição de saída (voto = 9) de primeira e mostre
   somente esta mensagem "Ninguém votou."
e. Mostre quem venceu as eleições.
    --- DICAS:
ct_total = len(l_voto)        # Quantos itens tem na lista          # a.
pct_nulo = ct_nulo / ct_total * 100
pct_branco = ct_branco / ct_total * 100
print('Porcentagem nulo: ', pct_nulo,'%')
print('Porcentagem branco: ', pct_branco,'%')
# print(f'Porcentagem branco: {pct_branco}%')   # f-string
   
print(f'Porcentagem de nulo: {pt_nulo:.2f}%')                       # b.
print(f'Porcentagem em branco: {pt_branco:.2f}%')

while True:                                                         # c.
    voto = int(input("[1] Candidato 1\n[2] Candidato 2\n[3] Candidato 3"
                     "\n[5] Voto nulo\n[6] Voto em branco"
                     "\n[9] Encerrar\nDigite voto: "))
    if voto == 9:                 #  \n  Pula para próxima linha
        break
    # if voto in [1, 2, 3, 5, 6]:
    if voto == 1 or voto == 2 or voto == 3 or voto == 5 or voto == 6:
        l_voto.append(voto)  # Armazena valor do voto no final da lista
    else:
        print('Opção inválida.')
    # Fim da estrutura de repetição                                 # d.
if len(l_voto) == 0:
    print('Ninguém votou.')
else:
    # Toda a tela de saída acima

"""
