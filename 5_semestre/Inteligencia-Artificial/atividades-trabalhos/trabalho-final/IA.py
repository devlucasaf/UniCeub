from google import genai
from google.genai import types
import json
import os
import matplotlib.pyplot as plt

from analise_cronologica import AnaliseCronologica
from relatorio_pdf import gerar_pdf

# Lógica da IA--------------------------------------------------------------------------------------------------------------------------------------------------

def analise(caminho_pdf):
    # Inicializalização do cliente 
    client = genai.Client(api_key="") 
    
    # Envio dos arquivos ao cliente
    arquivo = client.files.upload(file=caminho_pdf) 
    
    # Prompt que é enviado ao Gemini
    objetivo = (
        "Analise o documento e extraia os dados principais de variação ou distribuição."
        "Para cada dado encontrado, preencha a estrutura:"
        "- 'rotulo': Identifique o nome da categoria, produto ou mês correspondente (ex: 'Abril', 'Filmes', 'Roupas')."
        "- 'valor': Identifique o valor numérico ou percentual. Se o texto indicar uma 'queda' ou prejuízo, salve como NEGATIVO. Se indicar 'aumento' ou ganho, POSITIVO."
        "- 'tipo': Descreva brevemente o tipo do dado."
        "No campo 'resumo', faça uma síntese dos dados analisados."
        "No campo 'estilo', defina se será melhor um gráfico de 'pizza' ou 'barras':"
        "- Escolha 'barras' se os dados mostrarem uma evolução no tempo (meses/anos) ou contiverem valores negativos."
        "- Escolha 'pizza' se os dados representarem a divisão/distribuição de categorias de um todo (composição) e forem todos positivos."
    )
    
    # Chamada que envia o arquivo (teste.pdf) e objetivo (separar os valores)
    response = client.models.generate_content(
        model = "gemini-2.5-flash",
        contents = [arquivo, objetivo],
        config = types.GenerateContentConfig(
            response_schema = AnaliseCronologica,
            response_mime_type = "application/json",
            system_instruction = "Seja preciso e extraia os valores textuais ou numéricos conforme solicitado."
        )
    )
    
    # Converte o JSON em Python para mais fácil visualização
    return response.parsed

# Funções para geração de gráficos--------------------------------------------------------------------------------------------------------------------------------------------------

def grafico_barras(dados, caminho_saida="Grafico_Final.png"):
    historico = dados.historico
    
    meses = [ponto.rotulo.capitalize() for ponto in historico]
    valores = [ponto.valor for ponto in historico]
    
    plt.figure(figsize=(8,5), layout="constrained")
    
    cores = ["limegreen" if val >= 0 else "indianred" for val in valores]
    
    barras = plt.bar(meses, valores, linestyle="-", color=cores, edgecolor="black", label="Variação %")
    
    plt.axhline(0, color="gray", linestyle="--", linewidth=0.8)
    
    for barra in barras:
        val = barra.get_height()
        
        pos_y = val + (max(valores) * 0.02 if val >= 0 else min(valores) * 0.02)
        
        plt.text(barra.get_x() + barra.get_width() / 2, pos_y, f"{val}%", ha="center", va="bottom" if val >= 0 else "top", fontsize=9, fontweight="bold")
            
    #plt.scatter(meses[i], val, color=cor, zorder=5) 

    plt.title('Evolução das Variações Percentuais no Tempo', fontsize=12, fontweight='bold')
    plt.xlabel('Meses', fontsize=10)
    plt.ylabel('Variação (%)', fontsize=10)
    plt.grid(True, linestyle=':', alpha=0.6)

    # Define os limites do eixo Y para dar folga para os textos
    margem = (max(valores) - min(valores)) * 0.2 if valores else 15
    plt.ylim(min(valores) - margem, max(valores) + margem)
    
    plt.savefig(caminho_saida)
    plt.show()
    plt.close()
    return caminho_saida

def grafico_pizza(dados, caminho_saida="Grafico_Pizza.png"):
    historico = dados.historico
    
    nomes = [ponto.rotulo.capitalize() for ponto in historico]
    valores = [ponto.valor for ponto in historico]

    valores_pos = [abs(v) for v in valores]

    plt.figure(figsize=(6,6))

    plt.pie(valores_pos, labels=nomes, autopct='%1.1f%%', startangle=140, wedgeprops={'edgecolor': 'black', 'linewidth': 2}, colors=plt.cm.Set1.colors)

    plt.title("Distribuição Proporcional por Período", fontsize=12, fontweight='bold')
    
    plt.tight_layout()
    plt.savefig(caminho_saida)
    plt.show()
    plt.close()
    return caminho_saida

# Método inicial--------------------------------------------------------------------------------------------------------------------------------------------------
if __name__ == "__main__":
    # Local do arquivo PDF que será lido
    caminhopdf = r"C:\VSCode\GitHub\IA\leitor-de-documentos-ia\teste_pizza.pdf" 
    
    resultado = analise(caminhopdf)
    
    estilo_escolhido = resultado.estilo.lower()
    print(estilo_escolhido)
    
    if "pizza" in estilo_escolhido:
        grafico = grafico_pizza(resultado)
        
    else:
        grafico = grafico_barras(resultado)
        
    gerar_pdf(resultado, grafico)