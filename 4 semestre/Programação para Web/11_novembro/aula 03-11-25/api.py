"""
Programação para Web
APIs em Python
Data: 03-11-25
Slide 19: Consumo de APIs em Python
"""

import requests

url = "https://jsonplaceholder.typicode.com/posts"
dados = {
    "tittle": "Meu post",
    "body": "Conteúdo",
    "userId": 1
}

resposta = requests.post(url, json=dados)

print(resposta.json())
