"""
Programação para Web
Aula: 10-11-2025
Slide 49 - Exercício

Implementar os Endpoints:
    • GET /livros/{livro_id}:
        • Deve aceitar um parâmetro de rota (path parameter) livro_id (que deve ser um
        inteiro).
        • A função deve buscar e retornar o livro correspondente ao ID. Se o livro não for
        encontrado, deve retornar um erro HTTP 404.
    • POST /livros/:
        • Deve aceitar um corpo de requisição (request body) com os dados de um novo livro,
        usando o modelo Pydantic Livro para validação.
        • A função deve adicionar o novo livro à lista em memória e retorná-lo na resposta com
        um status HTTP 201.
Como Testar:
    • Rode o servidor com o comando: uvicorn main:app --reload
    • Abra seu navegador e acesse a documentação interativa em http://127.0.0.1:8000/docs.
    • Use a interface do Swagger UI para testar cada um dos endpoints que você criou: liste todos
    os livros, filtre por autor, busque um por ID e adicione um novo.
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
                if autor.lower() in livro["autor"].lower()
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
    - livro_id: ID do livro a ser buscado (path parameter)
    
    Retorna:
    - Livro encontrado
    
    Erros:
    - 404: Se o livro não for encontrado
    """
    # Busca o livro pelo ID
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
    - livro: Dados do novo livro (request body) usando modelo Pydantic
    
    Retorna:
    - Livro criado com ID gerado automaticamente e status HTTP 201
    """
    global proximo_id
    
    try:
        # Valida se o título já existe (opcional)
        livro_existente = next((l for l in livros_db if l["titulo"].lower() == livro.titulo.lower()), None)
        if livro_existente:
            raise HTTPException(
                status_code=400, 
                detail=f"Já existe um livro com o título '{livro.titulo}'"
            )
        
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
        
    except HTTPException:
        # Re-lança exceções HTTP específicas
        raise
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

# Endpoint: GET /health (para verificar se a API está rodando)
@app.get("/health")
def health_check():
    """
    Verifica o status da API
    """
    return {"status": "healthy", "total_livros": len(livros_db)}
