from pydantic import BaseModel

from analise_valores import AnaliseValores

class AnaliseCronologica(BaseModel):
    historico: list[AnaliseValores]
    resumo: str
    estilo: str
