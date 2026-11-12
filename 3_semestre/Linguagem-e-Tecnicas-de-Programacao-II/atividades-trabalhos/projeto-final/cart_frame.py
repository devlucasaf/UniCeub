import tkinter as tk
import ttkbootstrap as ttk

class CartFrame(tk.Frame):
    """Frame para exibir o carrinho de compras"""
    def __init__(self, master, **kwargs):
        super().__init__(master, **kwargs)
        self.configure(background="#664983", width=470, height=600)  # Configurações visuais
        self.create_widgets()  # Chama o método para criar os widgets

    def create_widgets(self):
        """Cria os elementos visuais do frame do carrinho"""
        self.label_cart_information = ttk.Label(self,
                                            text="Carrinho",
                                            background="#442e73",  # Roxo escuro
                                            font=("Times New Roman", 20))  # Fonte e tamanho
        self.label_cart_information.place(x=200, y=20)  # Posicionamento centralizado