"""
Programação para Web
Aula: 10-11-2025
Slide 48 - Exercício

Cenário: Você irá construir uma API que mantém um catálogo de livros em memória (uma lista de
dicionários). A API deverá permitir listar todos os livros, buscar um livro específico por ID e adicionar
um novo livro ao catálogo.
Instruções:
Configuração:
    • Instale o FastAPI e o Uvicorn: pip install "fastapi[all]"
    • Crie um arquivo chamado main.py.
Código Inicial:
    • Comece com a estrutura básica: importe o FastAPI e o BaseModel do Pydantic.
    • Crie uma pequena lista de dicionários para servir como seu "banco de dados" em memória.
    • Defina uma classe Livro que herda de BaseModel para representar a estrutura de um livro (ex: id: int,
titulo: str, autor: str, ano: int).
Implementar os Endpoints:
    • GET /livros/:
    • Deve retornar a lista completa de livros.
    • Desafio: Adicione um parâmetro de consulta (query parameter) opcional chamado autor. Se
ele for fornecido, a rota deve retornar apenas os livros daquele autor.
"""

from fastapi import FastAPI, HTTPException, Query
from pydantic import BaseModel
from typing import List, Optional

# Inicializa a aplicação FastAPI
app = FastAPI(
    title="Catálogo de Livros API",
    description="Uma API para gerenciar um catálogo de livros",
    version="1.0.0"
)

# "Banco de dados" em memória
livros_db = [
    {
        "id": 1,
        "titulo": "Dom Casmurro",
        "autor": "Machado de Assis",
        "ano": 1899
    },
    {
        "id": 2,
        "titulo": "O Cortiço",
        "autor": "Aluísio Azevedo",
        "ano": 1890
    },
    {
        "id": 3,
        "titulo": "Memórias Póstumas de Brás Cubas",
        "autor": "Machado de Assis",
        "ano": 1881
    },
    {
        "id": 4,
        "titulo": "Iracema",
        "autor": "José de Alencar",
        "ano": 1865
    },
    {
        "id": 5,
        "titulo": "O Guarani",
        "autor": "José de Alencar",
        "ano": 1857
    }
]

# Contador para gerar IDs únicos
proximo_id = 6

# Modelo Pydantic para validação de dados
class Livro(BaseModel):
    id: int
    titulo: str
    autor: str
    ano: int

class LivroCreate(BaseModel):
    titulo: str
    autor: str
    ano: int

# Endpoint: GET /livros/
@app.get("/livros/", response_model=List[Livro])
def listar_livros(autor: Optional[str] = Query(None, description="Filtrar livros por autor")):
    """
    Retorna a lista completa de livros.
    
    Parâmetros:
    - autor: Filtro opcional para retornar apenas livros de um autor específico
    
    Retorna:
    - Lista de livros (todos ou filtrados por autor)
    """
    try:
        if autor:
            # Filtra livros pelo autor (case insensitive)
            livros_filtrados = [
                livro for livro in livros_db 
                if livro["autor"].lower() == autor.lower()
            ]
            return livros_filtrados
        else:
            # Retorna todos os livros
            return livros_db
            
    except Exception as e:
        raise HTTPException(status_code=500, detail=f"Erro interno do servidor: {str(e)}")

# Endpoint: GET /livros/{livro_id}
@app.get("/livros/{livro_id}", response_model=Livro)
def buscar_livro_por_id(livro_id: int):
    """
    Busca um livro específico pelo ID.
    
    Parâmetros:
    - livro_id: ID do livro a ser buscado
    
    Retorna:
    - Livro encontrado
    
    Erros:
    - 404: Se o livro não for encontrado
    """
    livro = next((livro for livro in livros_db if livro["id"] == livro_id), None)
    
    if livro is None:
        raise HTTPException(
            status_code=404, 
            detail=f"Livro com ID {livro_id} não encontrado"
        )
    
    return livro

# Endpoint: POST /livros/
@app.post("/livros/", response_model=Livro, status_code=201)
def adicionar_livro(livro: LivroCreate):
    """
    Adiciona um novo livro ao catálogo.
    
    Parâmetros:
    - livro: Dados do novo livro (sem ID)
    
    Retorna:
    - Livro criado com ID gerado automaticamente
    """
    global proximo_id
    
    try:
        # Cria o novo livro
        novo_livro = {
            "id": proximo_id,
            "titulo": livro.titulo,
            "autor": livro.autor,
            "ano": livro.ano
        }
        
        # Adiciona ao "banco de dados"
        livros_db.append(novo_livro)
        proximo_id += 1
        
        return novo_livro
        
    except Exception as e:
        raise HTTPException(status_code=500, detail=f"Erro ao adicionar livro: {str(e)}")

# Endpoint: GET / (rota raiz)
@app.get("/")
def root():
    """
    Rota raiz com informações da API
    """
    return {
        "message": "Bem-vindo à API de Catálogo de Livros",
        "endpoints": {
            "listar_livros": "GET /livros/",
            "buscar_livro": "GET /livros/{id}",
            "adicionar_livro": "POST /livros/"
        },
        "total_livros": len(livros_db)
    }

# Execução do servidor
if __name__ == "__main__":
    import uvicorn
    uvicorn.run("main:app", host="127.0.0.1", port=8000, reload=True)
