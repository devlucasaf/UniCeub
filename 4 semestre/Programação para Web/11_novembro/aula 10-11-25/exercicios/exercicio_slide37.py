"""
Programação para Web
Aula: 10-11-2025
Slide 37 - Exercício
    Exercício
Desenvolvedor(a) Back-End

Sua responsabilidade é criar a API REST com Flask. A API irá gerenciar uma lista de tarefas em
memória (não é necessário usar um banco de dados para este exercício).

Passos:
Configurar o Projeto: Crie um arquivo api_server.py, importe o Flask e inicialize a aplicação.
Criar Dados Iniciais: Crie uma lista de dicionários em Python para armazenar as tarefas. Cada
tarefa deve ter id, titulo, descricao e concluida (um booleano).
Implementar os Endpoints (Rotas):
    • GET /tarefas: Retorna a lista completa de tarefas.
    • GET /tarefas/<int:tarefa_id>: Retorna uma única tarefa com base no seu id. Se a tarefa não existir,
    retorne um erro 404.
    • POST /tarefas: Adiciona uma nova tarefa à lista. O cliente enviará o titulo e a descricao em JSON. Você
    deve gerar o id e definir concluida como False. Retorne a tarefa recém-criada com o status 201
    Created.
    • PUT /tarefas/<int:tarefa_id>: Atualiza uma tarefa existente. O cliente enviará os campos a serem
    atualizados (ex: concluida: True). Retorne a tarefa atualizada.
    • DELETE /tarefas/<int:tarefa_id>: Remove uma tarefa da lista com base no seu id. Retorne uma
    mensagem de sucesso com o status 200 OK ou 204 No Content.
"""

from flask import Flask, jsonify, request

app = Flask(__name__)

# Lista em memória para armazenar as tarefas
tarefas = [
    {
        "id": 1,
        "titulo": "Estudar Flask",
        "descricao": "Aprender a criar APIs com Flask",
        "concluida": False
    },
    {
        "id": 2,
        "titulo": "Fazer exercício",
        "descricao": "Completar o exercício de Back-End",
        "concluida": True
    }
]

# Contador para gerar IDs únicos
proximo_id = 3

# GET /tarefas - Retorna todas as tarefas
@app.route('/tarefas', methods=['GET'])
def get_tarefas():
    return jsonify(tarefas)

# GET /tarefas/<int:tarefa_id> - Retorna uma tarefa específica
@app.route('/tarefas/<int:tarefa_id>', methods=['GET'])
def get_tarefa(tarefa_id):
    tarefa = next((t for t in tarefas if t['id'] == tarefa_id), None)
    if tarefa is None:
        return jsonify({"erro": "Tarefa não encontrada"}), 404
    return jsonify(tarefa)

# POST /tarefas - Cria uma nova tarefa
@app.route('/tarefas', methods=['POST'])
def criar_tarefa():
    global proximo_id
    
    if not request.json or 'titulo' not in request.json:
        return jsonify({"erro": "Título é obrigatório"}), 400
    
    nova_tarefa = {
        "id": proximo_id,
        "titulo": request.json['titulo'],
        "descricao": request.json.get('descricao', ""),
        "concluida": False
    }
    
    tarefas.append(nova_tarefa)
    proximo_id += 1
    
    return jsonify(nova_tarefa), 201

# PUT /tarefas/<int:tarefa_id> - Atualiza uma tarefa existente
@app.route('/tarefas/<int:tarefa_id>', methods=['PUT'])
def atualizar_tarefa(tarefa_id):
    tarefa = next((t for t in tarefas if t['id'] == tarefa_id), None)
    if tarefa is None:
        return jsonify({"erro": "Tarefa não encontrada"}), 404
    
    if 'titulo' in request.json:
        tarefa['titulo'] = request.json['titulo']
    if 'descricao' in request.json:
        tarefa['descricao'] = request.json['descricao']
    if 'concluida' in request.json:
        tarefa['concluida'] = request.json['concluida']
    
    return jsonify(tarefa)

# DELETE /tarefas/<int:tarefa_id> - Remove uma tarefa
@app.route('/tarefas/<int:tarefa_id>', methods=['DELETE'])
def deletar_tarefa(tarefa_id):
    global tarefas
    tarefa = next((t for t in tarefas if t['id'] == tarefa_id), None)
    if tarefa is None:
        return jsonify({"erro": "Tarefa não encontrada"}), 404
    
    tarefas = [t for t in tarefas if t['id'] != tarefa_id]
    return jsonify({"mensagem": "Tarefa deletada com sucesso"}), 200

if __name__ == '__main__':
    app.run(debug=True)
