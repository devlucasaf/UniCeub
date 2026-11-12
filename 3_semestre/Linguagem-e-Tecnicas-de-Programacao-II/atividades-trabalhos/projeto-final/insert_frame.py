import tkinter as tk

class InsertFrame(tk.Frame):
    """Frame para operações de inserção (INSERT) de dados"""
    def __init__(self, master, **kwargs):
        super().__init__(master, **kwargs)
        self.configure(background="#664983", width=1300, height=600)  # Configurações visuais
        self.widgets = {}  # Dicionário para armazenar os widgets criados