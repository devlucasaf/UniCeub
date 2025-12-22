"""
Programação para Web
Exercício APIs
Data: 03-11-25
Slide 20: Exercício
"""

import requests

# Requisição GET
response = requests.get("https://api.agify.io/?name=ana")

if response.status_code == 200:
    data = response.json()
    print(f"Nome: {data['name']}")
    print(f"Idade estimada: {data['age']}")
else:
    print("Erro na requisição GET.")

# Simulação de envio POST
url_post = "https://jsonplaceholder.typicode.com/users"
user_data = {
    "name": "Ana",
    "age": data['age'],
    "email": "ana@example.com"
}

response_post = requests.post(url_post, json=user_data)

if response_post.status_code == 201:
    print("Usuário cadastrado com sucesso (simulação).")
    print("Resposta da API falsa:", response_post.json())
else:
    print("Erro ao simular o cadastro.")
