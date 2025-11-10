"""
Programação para Web
Aula: 10-11-2025
Slide 44 - FastAPI - Requisição POST

É possível utilizar classes para realizar requisições, entretanto, estas
classes precisam ser definidas e passadas como json nas requisições.
"""

# Modelo de dados usando Pydantic para o exemplo 3
# FastAPI usará este modelo para validar o corpo da requisição

class Item(BaseModel):
    nome: str
    descricao: str
    preco: float
    disponivel: bool = True

# Exemplo 3: Corpo da Requisição (Request Body com JSON)
# Ao declarar um parâmetro com um modelo Pydantic (item: Item),
# FastAPI automaticamente espera um corpo JSON com aquela estrutura.
@app.post("/items")
def criar_item(item: Item):
    print(f"Item recebido: {item.dict()}")

    # Aqui você adicionaria a lógica para salvar o item no banco de dados...

    return {
        "mensagem": "Item criado com sucesso!",
        "item_criado": item
    }
