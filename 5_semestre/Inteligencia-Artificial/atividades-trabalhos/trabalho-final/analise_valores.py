from pydantic import BaseModel

# Estrutura dos valores que serão adquiridos
class AnaliseValores(BaseModel):
    rotulo: str
    valor: float
    tipo: str
