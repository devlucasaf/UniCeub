"""
Programação para Web
Aula: 10-11-2025
Aula API
Slide 39 - Exercício

URL Base: http://127.0.0.1:5000                 {
Formato dos Dados: JSON                         "id": 1,
Objeto Tarefa:                                  "titulo": "String",
                                                "descricao": "String",
                                                "concluida": false
                                                }

_________________________________________________________________________________________________________________
| Método |      URL      |        Corpo da Requisição (Exemplo)          | Resposta de Sucesso                  |
|--------|---------------|-----------------------------------------------|--------------------------------------|
|   GET  | /tarefas      |                  (Nenhum)                     | 200 OK com a lista de tarefas        |
|--------|---------------|-----------------------------------------------|--------------------------------------|
|   GET  | /tarefas/<id> |                  (Nenhum)                     | 200 OK com a tarefa específica       |
|--------|---------------|-----------------------------------------------|--------------------------------------|
|   POST | /tarefas      | {"titulo": "Nova Tarefa", "descricao": "..."} | 201 Created com a nova tarefa criada |
|--------|---------------|-----------------------------------------------|--------------------------------------|
|   PUT  | /tarefas/<id> |              {"concluida": true}              | 200 OK com a tarefa atualizada       |
|--------|---------------|-----------------------------------------------|--------------------------------------|
| DELETE | /tarefas/<id> |                  (Nenhum)                     | 200 OK com uma mensagem de sucesso   |
------------------------------------------------------------------------------------------------------------------
"""

# api_server.py (Servidor Corrigido)

from flask import Flask, jsonify, request

app = Flask(__name__)

# Lista em memória para armazenar as tarefas (seguindo o formato exato da especificação)
tarefas = [
    {
        "id": 1,
        "título": "Estudar Flask",
        "descrição": "Aprender a criar APIs com Flask",
        "concluída": False
    },
    {
        "id": 2,
        "título": "Fazer exercício",
        "descrição": "Completar o exercício de Back-End",
        "concluída": True
    }
]

# Contador para gerar IDs únicos
proximo_id = 3

# GET /tarefas - Retorna todas as tarefas
@app.route('/tarefas', methods=['GET'])
def get_tarefas():
    return jsonify(tarefas), 200

# GET /tarefas/<int:tarefa_id> - Retorna uma tarefa específica
@app.route('/tarefas/<int:tarefa_id>', methods=['GET'])
def get_tarefa(tarefa_id):
    tarefa = next((t for t in tarefas if t['id'] == tarefa_id), None)
    if tarefa is None:
        return jsonify({"erro": "Tarefa não encontrada"}), 404
    return jsonify(tarefa), 200

# POST /tarefas - Cria uma nova tarefa
@app.route('/tarefas', methods=['POST'])
def criar_tarefa():
    global proximo_id
    
    if not request.json or 'título' not in request.json:
        return jsonify({"erro": "Título é obrigatório"}), 400
    
    nova_tarefa = {
        "id": proximo_id,
        "título": request.json['título'],
        "descrição": request.json.get('descrição', ""),
        "concluída": False
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
    
    # Atualiza apenas os campos fornecidos
    if 'título' in request.json:
        tarefa['título'] = request.json['título']
    if 'descrição' in request.json:
        tarefa['descrição'] = request.json['descrição']
    if 'concluída' in request.json:
        tarefa['concluída'] = request.json['concluída']
    
    return jsonify(tarefa), 200

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

# +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

# api_cliente.py (Cliente Corrigido)

import requests
import json

# URL base da API
BASE_URL = "http://127.0.0.1:5000"

def listar_tarefas():
    """
    Faz uma requisição GET para /tarefas e imprime a lista de tarefas recebida.
    """
    try:
        response = requests.get(f"{BASE_URL}/tarefas")
        
        if response.status_code == 200:
            tarefas = response.json()
            print("\n=== LISTA DE TAREFAS ===")
            if tarefas:
                for tarefa in tarefas:
                    status = "✓" if tarefa['concluída'] else "✗"
                    print(f"ID: {tarefa['id']} | {status} {tarefa['título']}")
                    print(f"   Descrição: {tarefa['descrição']}")
                    print("-" * 50)
            else:
                print("Nenhuma tarefa encontrada.")
        else:
            print(f"Erro ao listar tarefas: {response.status_code}")
            
    except requests.exceptions.ConnectionError:
        print("Erro: Não foi possível conectar à API. Verifique se o servidor está rodando.")
    except Exception as e:
        print(f"Erro inesperado: {e}")

def adicionar_nova_tarefa(titulo, descricao=""):
    """
    Faz uma requisição POST para /tarefas enviando título e descrição.
    Imprime a tarefa que foi criada.
    """
    try:
        dados = {
            "título": titulo,
            "descrição": descricao
        }
        
        response = requests.post(
            f"{BASE_URL}/tarefas",
            json=dados,
            headers={'Content-Type': 'application/json'}
        )
        
        if response.status_code == 201:
            tarefa_criada = response.json()
            print("\n✅ TAREFA CRIADA COM SUCESSO!")
            print(f"ID: {tarefa_criada['id']}")
            print(f"Título: {tarefa_criada['título']}")
            print(f"Descrição: {tarefa_criada['descrição']}")
            print(f"Status: {'Concluída' if tarefa_criada['concluída'] else 'Pendente'}")
        else:
            print(f"Erro ao criar tarefa: {response.status_code}")
            if response.text:
                print(f"Detalhes: {response.text}")
                
    except requests.exceptions.ConnectionError:
        print("Erro: Não foi possível conectar à API. Verifique se o servidor está rodando.")
    except Exception as e:
        print(f"Erro inesperado: {e}")

def marcar_tarefa_como_concluida(tarefa_id):
    """
    Faz uma requisição PUT para /tarefas/<id> enviando {'concluída': True}.
    Imprime a tarefa atualizada.
    """
    try:
        dados = {
            "concluída": True
        }
        
        response = requests.put(
            f"{BASE_URL}/tarefas/{tarefa_id}",
            json=dados,
            headers={'Content-Type': 'application/json'}
        )
        
        if response.status_code == 200:
            tarefa_atualizada = response.json()
            print("\n✅ TAREFA MARCADA COMO CONCLUÍDA!")
            print(f"ID: {tarefa_atualizada['id']}")
            print(f"Título: {tarefa_atualizada['título']}")
            print(f"Descrição: {tarefa_atualizada['descrição']}")
            print(f"Status: {'Concluída' if tarefa_atualizada['concluída'] else 'Pendente'}")
        elif response.status_code == 404:
            print(f"❌ Erro: Tarefa com ID {tarefa_id} não encontrada.")
        else:
            print(f"Erro ao atualizar tarefa: {response.status_code}")
            
    except requests.exceptions.ConnectionError:
        print("Erro: Não foi possível conectar à API. Verifique se o servidor está rodando.")
    except Exception as e:
        print(f"Erro inesperado: {e}")

def deletar_tarefa(tarefa_id):
    """
    Faz uma requisição DELETE para /tarefas/<id> e imprime a resposta do servidor.
    """
    try:
        response = requests.delete(f"{BASE_URL}/tarefas/{tarefa_id}")
        
        if response.status_code == 200:
            resultado = response.json()
            print(f"\n✅ {resultado['mensagem']}")
        elif response.status_code == 404:
            print(f"❌ Erro: Tarefa com ID {tarefa_id} não encontrada.")
        else:
            print(f"Erro ao deletar tarefa: {response.status_code}")
            
    except requests.exceptions.ConnectionError:
        print("Erro: Não foi possível conectar à API. Verifique se o servidor está rodando.")
    except Exception as e:
        print(f"Erro inesperado: {e}")

def menu_principal():
    """
    Menu interativo para o usuário
    """
    while True:
        print("\n" + "="*50)
        print("          SISTEMA DE GERENCIAMENTO DE TAREFAS")
        print("="*50)
        print("1. Listar todas as tarefas")
        print("2. Adicionar nova tarefa")
        print("3. Marcar tarefa como concluída")
        print("4. Deletar tarefa")
        print("5. Sair")
        print("-"*50)
        
        opcao = input("Escolha uma opção (1-5): ").strip()
        
        if opcao == "1":
            listar_tarefas()
            
        elif opcao == "2":
            print("\n--- NOVA TAREFA ---")
            titulo = input("Título da tarefa: ").strip()
            if not titulo:
                print("❌ O título é obrigatório!")
                continue
            descricao = input("Descrição (opcional): ").strip()
            adicionar_nova_tarefa(titulo, descricao)
            
        elif opcao == "3":
            print("\n--- MARCAR TAREFA COMO CONCLUÍDA ---")
            try:
                tarefa_id = int(input("ID da tarefa a marcar como concluída: "))
                marcar_tarefa_como_concluida(tarefa_id)
            except ValueError:
                print("❌ Por favor, digite um ID válido (número inteiro).")
                
        elif opcao == "4":
            print("\n--- DELETAR TAREFA ---")
            try:
                tarefa_id = int(input("ID da tarefa a deletar: "))
                # Confirmação para evitar deleções acidentais
                confirmacao = input(f"Tem certeza que deseja deletar a tarefa {tarefa_id}? (s/N): ").strip().lower()
                if confirmacao == 's' or confirmacao == 'sim':
                    deletar_tarefa(tarefa_id)
                else:
                    print("Operação cancelada.")
            except ValueError:
                print("❌ Por favor, digite um ID válido (número inteiro).")
                
        elif opcao == "5":
            print("Saindo do sistema... Até logo!")
            break
            
        else:
            print("❌ Opção inválida! Por favor, escolha uma opção de 1 a 5.")
        
        input("\nPressione Enter para continuar...")

if __name__ == "__main__":
    print("Cliente da API de Tarefas")
    print("URL da API:", BASE_URL)
    
    # Teste rápido da conexão
    try:
        response = requests.get(f"{BASE_URL}/tarefas", timeout=5)
        if response.status_code == 200:
            print("✅ Conexão com a API estabelecida com sucesso!")
            menu_principal()
        else:
            print(f"❌ API retornou status {response.status_code}")
    except requests.exceptions.ConnectionError:
        print("❌ Não foi possível conectar à API. Verifique se o servidor Flask está rodando.")
        print("   Execute: python api_server.py")
    except Exception as e:
        print(f"❌ Erro inesperado: {e}")
