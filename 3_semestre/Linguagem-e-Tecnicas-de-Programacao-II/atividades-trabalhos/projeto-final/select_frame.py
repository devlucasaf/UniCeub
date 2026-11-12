import tkinter as tk
import ttkbootstrap as ttk

class SelectFrame(tk.Frame):
    """Frame para exibir informações de consulta (SELECT)"""
    def __init__(self, master, **kwargs):
        super().__init__(master, **kwargs)
        # Configurações básicas do frame
        self.configure(background="#664983", width=800, height=600)  # Roxo claro como fundo
        self.create_widgets()  # Chama o método para criar os widgets

    def create_widgets(self):
        """Cria os elementos visuais do frame de consulta"""
        # Label para seção de informações do jogo
        self.label_game_information = ttk.Label(self,
                                            text="Informação do Jogo",
                                            background="#664983",  # Cor de fundo
                                            font=("Times New Roman", 20))  # Fonte e tamanho
        self.label_game_information.place(x=20, y=20)  # Posicionamento

        # Label para seção de informações do usuário
        self.label_player_information = ttk.Label(self,
                                                text="Informação do Usuário",
                                                background="#664983",
                                                font=("Times New Roman", 20))
        self.label_player_information.place(x=20, y=360)  # Posicionamento abaixo