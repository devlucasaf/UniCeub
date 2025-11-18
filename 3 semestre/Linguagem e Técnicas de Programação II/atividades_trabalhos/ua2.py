class CatalogoTelefonico:
    def __init__(self):
        self.contatos = {}  # Dicionário para armazenar os contatos
    
    def adicionar_contato(self, nome, telefone):
        """Adiciona um novo colaborador ao catálogo"""
        if nome in self.contatos:
            print(f"Colaborador {nome} já existe no catálogo.")
        else:
            self.contatos[nome] = telefone
            print(f"Colaborador {nome} adicionado com sucesso!")
    
    def buscar_telefone(self, nome):
        """Busca o número de telefone de um colaborador"""
        if nome in self.contatos:
            print(f"Telefone de {nome}: {self.contatos[nome]}")
        else:
            print(f"Colaborador {nome} não encontrado no catálogo.")
    
    def atualizar_telefone(self, nome, novo_telefone):
        """Atualiza o número de telefone de um colaborador"""
        if nome in self.contatos:
            self.contatos[nome] = novo_telefone
            print(f"Telefone de {nome} atualizado para {novo_telefone}")
        else:
            print(f"Colaborador {nome} não encontrado. Não foi possível atualizar.")
    
    def remover_contato(self, nome):
        """Remove um colaborador do catálogo"""
        if nome in self.contatos:
            del self.contatos[nome]
            print(f"Colaborador {nome} removido com sucesso!")
        else:
            print(f"Colaborador {nome} não encontrado. Não foi possível remover.")
    
    def listar_contatos(self):
        """Lista todos os colaboradores e seus telefones"""
        if not self.contatos:
            print("O catálogo telefônico está vazio.")
        else:
            print("\n--- Catálogo Telefônico ---")
            for nome, telefone in self.contatos.items():
                print(f"{nome}: {telefone}")
            print("--------------------------")


# Função para exibir o menu
def exibir_menu():
    print("\n--- Sistema de Catálogo Telefônico ---")
    print("1. Adicionar colaborador")
    print("2. Buscar telefone")
    print("3. Atualizar telefone")
    print("4. Remover colaborador")
    print("5. Listar todos os colaboradores")
    print("6. Sair")


# Exemplo de uso do sistema
if __name__ == "__main__":
    catalogo = CatalogoTelefonico()
    
    while True:
        exibir_menu()
        opcao = input("Escolha uma opção (1-6): ")
        
        if opcao == "1":
            nome = input("Nome do colaborador: ")
            telefone = input("Número de telefone: ")
            catalogo.adicionar_contato(nome, telefone)
        
        elif opcao == "2":
            nome = input("Nome do colaborador a buscar: ")
            catalogo.buscar_telefone(nome)
        
        elif opcao == "3":
            nome = input("Nome do colaborador: ")
            novo_telefone = input("Novo número de telefone: ")
            catalogo.atualizar_telefone(nome, novo_telefone)
        
        elif opcao == "4":
            nome = input("Nome do colaborador a remover: ")
            catalogo.remover_contato(nome)
        
        elif opcao == "5":
            catalogo.listar_contatos()
        
        elif opcao == "6":
            print("Saindo do sistema...")
            break
        
        else:
            print("Opção inválida. Por favor, escolha uma opção de 1 a 6.")
