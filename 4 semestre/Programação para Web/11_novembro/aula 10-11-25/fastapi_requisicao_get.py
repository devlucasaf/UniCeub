"""
Programação para Web
Aula: 10-11-2025
Slide 42 - FastAPI – Requisição GET
"""

# Exemplo 1: Parâmetros de Rota (Path Parameters)
# O valor do parâmetro é parte do caminho da URL.
# FastAPI usa a dica de tipo (item_id: int) para validar que o valor é um inteiro

@app.get("/items/{item_id}")
def ler_item(item_id: int):
    return {"item_id": item_id, "descricao": f"Você está vendo o item com ID {item_id}."}

# Exemplo 2: Parâmetros de Consulta (Query Parameters)
# Parâmetros que não estão no caminho da rota são automaticamente tradados com query params.
# Valores padrão (ex: = 0) os tornam opcionais.
@app.get("/items")
def listar_items(skip: int = 0, limit: int = 10):
    return {"mensagem": f"Listando {limit} itens, pulando os primeiros {skip}."}
