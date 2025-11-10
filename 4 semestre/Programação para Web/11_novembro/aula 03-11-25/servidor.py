"""
Programação para Web
API GraphQL
Data: 03-11-25
Slide 23: API GraphQL - servidor.py
"""

import strawberry
from fastapi import FastAPI
from strawberry.fastapi import GraphQLRouter

# --- "Banco de Dados" em memória ---

db_livros = [
    {"id": 1, "titulo": "O Senhor dos Anéis", "autor": "J.R.R. Tolkien"},
    {"id": 2, "titulo": "Fundação", "autor": "Isaac Asimov"},
    {"id": 3, "titulo": "Duna", "autor": "Frank Herbert"},
]

# --- Definição dos Tipos GraphQL com Strawberry ---

# 1. Definimos o tipo de objeto que nossa API irá expor
#    As anotações de tipo (str, int) são usadas para gerar o schema GraphQL.

@strawberry.type

class Livro:
    id: int
    titulo: str
    autor: str

# 2. Definimos a "Query", que são os pontos de entrada para consulta de dados.
@strawberry.type
class Query:
    #Este resolve retorna uma lista de todos os livros.
    @strawberry.type
    def livros(self) -> List[Livro]:
        return [Livro(**livro) for livro in db_livros]
    
    # Este resolver busca um único livro por seu ID.
    # Ele aceita um argumento 'livro_id' e pode retornar um Livro ou None.
    @strawberry.type
    def livro(self, livro_id: int) -> Optional[Livro]:
        for livro_data in db_livros:
            if livro_data["id"] == livro_id:
                return Livro(**livro_data)
        return None
    
# -- COnfiguração do Servidos ---

# 3. Criamos o schema GraphQL com a nossa Query.
schema = strawberry.Schema(query=Query)

# 4. Criamos a rota GraphGL usando o Strawberry e o FastAPI.
graphql_app = GraphQLRouter(schema)

# 5. Criamos a aplicação FastAPI principal e incluímos a rota GraphQL.
app = FastAPI()
app.include_router(graphql_app, prefix="/graphql")
