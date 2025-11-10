"""
Programação para Web
APIs em Python
Data: 03-11-25
Slide 25: API GraphQL – cliente.py
"""

import requests
import json

# URL do nosso servidor GraphQL
URL_GRAPHQL = "http://127.0.0.1:8000/graphql"

def fazer_requisicao(query):
    """Função auxiliar para enviar uma query GraphQL e tratar a resposta."""

    try:
        response = requests.post(URL_GRAPHQL, json={"query": query})
        response.raise_for_status()
        return response.json()
    except requests.exceptions.RequestException as e:
        print(f"Erro ao fazer a requisição: {e}")
        return None

def listar_todos_os_livros():
    """Cria e envia uma query para buscar todos os livros."""
    print("\n--- Buscando todos os livros ---")

    # A query GraphQL para buscar o ID e o título de todos os livros
    query = """
        query {
            livros {
                id
                titulo
            }
        }
    """
    resultado = fazer_requisicao(query)

    if resultado and "data" in resultado:
        print(json.dumps(resultado["data"], indent=2))

def buscar_livro_por_id(id_do_livro):
    """Cria e envia uma query para buscar um livro específico."""
    print(f"\n--- Buscando livro com ID: {id_do_livro} ---")

    # Query para buscar um livro específico, pedindo todos os campos.
    # Note como a variável da query {$livroId} é definida.

    query = f"""
        query {{
            livro(livroId: {id_do_livro}) {{
                id
                titulo
                autor
            }}
        }}
    """

    resultado = fazer_requisicao(query)

    if resultado and "data" in resultado:
        print(json.dumps(resultado["data"], indent=2))

# --- Execução do Cliente ---

if __name__ == "__main__":
    listar_todos_os_livros()
    buscar_livro_por_id(2)
    buscar_livro_por_id(99) # Testando um ID que não existe
