"""
Programação para Web
Aula: 10-11-2025
API em Python
SLide 41 - FastAPI - Servidor

Nestes exemplos criaremos uma aplicação para gestão de itens.
"""

from fastapi import FastAPI
from pydantic import BaseModel

# Inicializa a aplicação FastAPI
app = FastAPI()

# Modelo de dados usando Pydantic para o Exemplo 3
# FastAPI usará este modelo para validar o corpo da requisição
class Item(BaseModel):
    nome: str
    descricao: str
    preco: float
    disponivel: bool = True

# Rota principal para referência
@app.get("/")
def index():
    return {"mensagem": "Exemplos de envio de parâmetros com FastAPI!"}
