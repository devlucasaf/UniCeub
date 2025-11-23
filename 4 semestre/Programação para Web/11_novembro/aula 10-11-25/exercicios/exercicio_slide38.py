"""
Programação para Web
Aula: 10-11-2025
Slide 38 - Exercício

Desenvolvedor(a) Cliente

Sua responsabilidade é criar um script Python (api_client.py) que consome a API criada
pela Pessoa A.

Passos:
Configurar o Projeto: Crie um arquivo api_client.py e importe a biblioteca requests.
Definir a URL Base: Crie uma variável para a URL da API (ex: http://127.0.0.1:5000).
Implementar Funções para Consumir a API:
    • listar_tarefas(): Faz uma requisição GET para /tarefas e imprime a lista de tarefas
    recebida.
    • adicionar_nova_tarefa(titulo, descricao): Faz uma requisição POST para /tarefas
    enviando um dicionário com o título e a descrição. Imprime a tarefa que foi criada.
    • marcar_tarefa_como_concluida(tarefa_id): Faz uma requisição PUT para /tarefas/<id>
    enviando {'concluida': True}. Imprime a tarefa atualizada.
    • deletar_tarefa(tarefa_id): Faz uma requisição DELETE para /tarefas/<id> e imprime a
    resposta do servidor.
"""

# Exemplo de uso direto
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
                    status = "✓" if tarefa['concluida'] else "✗"
                    print(f"ID: {tarefa['id']} | {status} {tarefa['titulo']}")
                    print(f"   Descrição: {tarefa['descricao']}")
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
            "titulo": titulo,
            "descricao": descricao
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
            print(f"Título: {tarefa_criada['titulo']}")
            print(f"Descrição: {tarefa_criada['descricao']}")
            print(f"Status: {'Concluída' if tarefa_criada['concluida'] else 'Pendente'}")
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
    Faz uma requisição PUT para /tarefas/<id> enviando {'concluida': True}.
    Imprime a tarefa atualizada.
    """
    try:
        dados = {
            "concluida": True
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
            print(f"Título: {tarefa_atualizada['titulo']}")
            print(f"Descrição: {tarefa_atualizada['descricao']}")
            print(f"Status: {'Concluída' if tarefa_atualizada['concluida'] else 'Pendente'}")
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

# Funções para uso programático (sem menu interativo)
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

# Exemplo de uso direto
if __name__ == "__main__":
    # Listar tarefas
    listar_tarefas()
    
    # Adicionar nova tarefa
    adicionar_nova_tarefa("Estudar Python", "Revisar conceitos de OOP")
    
    # Marcar tarefa como concluída
    marcar_tarefa_como_concluida(1)
    
    # Deletar tarefa
    deletar_tarefa(2)
