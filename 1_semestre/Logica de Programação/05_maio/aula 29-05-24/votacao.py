# Lista que armazenará todos os votos digitados
voto_totais = []
# Variável que armazenará o nome do candidato eleito
eleito = 0

# Exibe o menu de candidatos para o usuário
print("""Escolha entre as seguintes opções de candidatos:
22- Leanderson
13-Mario Bitcoin
14-Lucao
0-NULO
9-Encerrar
""")

def eleicao():

    # Loop principal para registrar votos
    while True:
        # Solicita ao usuário que digite seu voto
        voto = int(input("Digite seu voto: "))

        # Verifica se o voto é válido (um dos candidatos ou nulo)
        if voto in [22, 13, 14, 0]:
            print("Voto Confirmado!")

        # Se o usuário digitar 9, encerra a votação
        elif voto == 9:
            print("Votação encerrada!")
            break

        else:
            # Caso o número digitado não corresponda a nenhum candidato
            print("Número digitado não identificado a nenhum candidato! Tente outro número!")

        # Adiciona o voto à lista de votos totais
        voto_totais.append(voto)

        # Atualiza quem está na frente (mais votos) até o momento
        if voto_totais.count(22) > voto_totais.count(13) and voto_totais.count(22) > voto_totais.count(14):
            eleito = "Leanderson"
        elif voto_totais.count(13) > voto_totais.count(22) and voto_totais.count(13) > voto_totais.count(14):
            eleito = "Mario Bitcoin"
        elif voto_totais.count(14) > voto_totais.count(22) and voto_totais.count(14) > voto_totais.count(13):
            eleito = "Lucao"

    # Se houve pelo menos um voto registrado
    if len(voto_totais) != 0:

        # Contabiliza os votos de cada candidato e nulos
        votos_leanderson = voto_totais.count(22)
        votos_mario = voto_totais.count(13)
        votos_lucao = voto_totais.count(14)
        votos_nulo = voto_totais.count(0)
        total_votos = len(voto_totais)

        # Cria um dicionário para facilitar a identificação do candidato com mais votos
        resultados = {
            "Leanderson": votos_leanderson,
            "Mario Bitcoin": votos_mario,
            "Lucao": votos_lucao
            }

        # Determina o candidato eleito (com mais votos)
        eleito = max(resultados, key=resultados.get)
        votos_eleito = resultados[eleito]

        # Calcula a porcentagem de votos de cada candidato e nulos
        porc1 = (voto_totais.count(22) / len(voto_totais)) * 100
        porc2 = (voto_totais.count(13) / len(voto_totais)) * 100
        porc3 = (voto_totais.count(14) / len(voto_totais)) * 100
        porcnull = (voto_totais.count(0) / len(voto_totais)) * 100

        # Caso todos os votos sejam nulos
        if votos_nulo > votos_eleito:
            print(f"Votação anulada! Os votos nulos venceram com {votos_nulo}")
            print("\nVotação recomeçada!\n")

            # Exibe o menu de candidatos para o usuário
            print("""Escolha entre as seguintes opções de candidatos:
            22- Leanderson
            13-Mario Bitcoin
            14-Lucao
            0-NULO
            9-Encerrar
            """)
            
            # Reinicia a lista de votos
            voto_totais.clear()
            eleicao()

        else:
            # Mensagem para apenas 1 voto
            if len(voto_totais) == 1:
                print(f"Nesta eleição houve {len(voto_totais)} voto!")

            # Mensagem para mais de 1 voto
            else:
                print(f"Nesta eleição houve {len(voto_totais)} votos!")
                print(f"Leanderson obteve {voto_totais.count(22)} votos com {porc1:.2f}%")
                print(f"Mario Bitcoin obteve {voto_totais.count(13)} votos com {porc2:.2f}%")
                print(f"Lucao obteve {voto_totais.count(14)} votos com {porc3:.2f}%")
                print(f"Obteve {voto_totais.count(0)} votos nulos com {porcnull:.2f}%")

                # Mostra o candidato eleito e a quantidade de votos que ele obteve
                print(f"O candidato: {eleito} foi eleito com {votos_eleito} votos")

    # Caso nenhum voto tenha sido registrado
    else:
        print("Não se obteve votos")

eleicao()
