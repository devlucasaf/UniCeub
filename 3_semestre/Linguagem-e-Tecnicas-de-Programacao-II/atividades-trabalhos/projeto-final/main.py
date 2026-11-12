from select_frame import SelectFrame
from insert_frame import InsertFrame
from update_frame import UpdateFrame
from delete_frame import DeleteFrame
from cart_frame import CartFrame
import tkinter as tk
import ttkbootstrap as ttk

# Exemplo de uso
if __name__ == "__main__":
    root = tk.Tk()
    root.geometry("1300x600")
    
    # Criando instâncias das classes
    select_frame = SelectFrame(root)
    select_frame.pack()
    
    # insert_frame = InsertFrame(root)
    # update_frame = UpdateFrame(root)
    # delete_frame = DeleteFrame(root)
    # cart_frame = CartFrame(root)
    
    root.mainloop()