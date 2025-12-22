import graphene

# Dados simulados
livros_data = [
    {"id": 1, "titulo": "Livro A", "autor_id": 1},
    {"id": 2, "titulo": "Livro B", "autor_id": 1},
    {"id": 3, "titulo": "Livro C", "autor_id": 2},
]

autores_data = [
    {"id": 1, "nome": "Ana"},
    {"id": 2, "nome": "André"},
    {"id": 3, "nome": "Bruno"},
]

# Tipos GraphQL
class Livro(graphene.ObjectType):
    id = graphene.Int()
    titulo = graphene.String()

class Autor(graphene.ObjectType):
    id = graphene.Int()
    nome = graphene.String()
    livros = graphene.List(Livro)

    def resolve_livros(parent, info):
        return [Livro(**livro) for livro in livros_data if livro["autor_id"] == parent.id]

# Consulta
class Query(graphene.ObjectType):
    autores = graphene.List(Autor, nome_inicial=graphene.String())

    def resolve_autores(root, info, nome_inicial=None):
        if nome_inicial:
            filtrados = [autor for autor in autores_data if autor["nome"].startswith(nome_inicial)]
        else:
            filtrados = autores_data
        return [Autor(**autor) for autor in filtrados]

# Schema
schema = graphene.Schema(query=Query)

# Teste da consulta
query_string = '''
{
    autores(nomeInicial: "An") {
        nome
        livros {
        titulo
        }
    }
}
'''

result = schema.execute(query_string)
print(result.data)