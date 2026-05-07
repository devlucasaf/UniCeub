Teste a IA diretamente nos seus apps favoritos … Use o Gemini para criar rascunhos e editar conteúdo e acesse o Gemini Pro com a IA de última geração do Google
# -----------------------> IMPORTAÇÕES DE BIBLIOTECAS <-----------------------

from PIL import Image  # Para manipulação de imagens
from PIL import ImageTk  # Para integração de imagens com Tkinter
from FrameClass import *  # Importa classes personalizadas de frames
from ButtonClass import AppButton  # Classe personalizada para botões
import warnings  # Para gerenciar avisos

warnings.filterwarnings("ignore", category=UserWarning)  # Ignora avisos específicos

# -----------------------> DEFINIÇÃO DE CORES <-----------------------

# Cores Hexadecimais
purple = "#442e73"  # Roxo escuro (cor principal)
light_purple = "#664983"  # Roxo mais claro
light_lilac = "#987FAB"  # Lilás claro
medium_lilac = "#A676B0"  # Lilás médio
white = "#FFFFFF"  # Branco puro

# -----------------------> CONFIGURAÇÃO DA JANELA PRINCIPAL <-----------------------

# Cria a janela principal com tema "cyborg" (escuro com elementos roxos)
window = ttk.Window(themename="cyborg")
window.title("Plataforma de Jogos")  # Título da janela
window.geometry("1600x700")  # Define tamanho fixo da janela
window.configure(background=purple)  # Cor de fundo principal
window.resizable(width=False, height=False)  # Impede redimensionamento

# -----------------------> CONFIGURAÇÃO DE ESTILOS <-----------------------

style = ttk.Style()  # Objeto para gerenciar estilos

# Estilo personalizado para botões principais
style.configure("Custom.TButton",
                background=light_purple,  # Cor de fundo
                foreground="white",  # Cor do texto
                font=("Segoe UI", 14, "bold"),  # Fonte
                padding=20,  # Espaçamento interno
                borderwidth=0)  # Remove borda
# Define comportamento ao passar mouse
style.map("Custom.TButton",
          background=[("active", "#573a87")])  # Cor mais clara quando ativo

# Segundo estilo de botão (pode ser usado para diferenciação)
style.configure("CustomTwo.TButton",
                background=light_purple,
                foreground="white",
                font=("Segoe UI", 14, "bold"),
                padding=20,
                borderwidth=0)
style.map("CustomTwo.TButton",
          background=[("active", "#573a87")])

# -----------------------> PREPARAÇÃO DE IMAGENS PARA OS BOTÕES <-----------------------

# Função auxiliar para carregar e preparar imagens
def load_image(path, size=(25, 25)):
    """Carrega uma imagem, redimensiona e converte para formato compatível com Tkinter"""
    image = Image.open(path)
    image = image.resize(size)
    return ImageTk.PhotoImage(image)

# Carrega ícones para os botões CRUD
select_image = load_image("select.png")  # Ícone para select
insert_image = load_image("insert.png")  # Ícone para insert
update_image = load_image("update.png")  # Ícone para update
delete_image = load_image("delete.png")  # Ícone para delete

# -----------------------> CRIAÇÃO DE FRAMES (ÁREAS DE CONTEÚDO) <-----------------------

# Frame para operações de SELECT
frame_select = SelectFrame(window)  # Frame para consulta de dados
frame_insert = InsertFrame(window)  # Frame para inserção de dados
frame_update = UpdateFrame(window)  # Frame para atualização de dados
frame_delete = DeleteFrame(window)  # Frame para exclusão de dados
frame_cart = CartFrame(window)      # Frame para o carrinho de compras

blablabla = tk.Entry(
        frame_insert,
        text="alala",
        width=20,
        bg="#442e73",  # Cor de fundo (roxo escuro)
        fg="#664983",  # Cor do texto
        font=("Calibri", 10)  # Fonte e tamanho
    )
blablabla.place(x=160, y=200)  # Posicionamento
blablabla.configure(background="#C6B4C9")  # Altera cor de fundo (lilás claro)

def blabla():

    print(blablabla.get())
# -----------------------> CONTROLE DE VISIBILIDADE DOS FRAMES <-----------------------

# Variáveis booleanas para rastrear qual frame está visível
frame_select_visible = False
frame_insert_visible = False
frame_update_visible = False
frame_delete_visible = False
frame_cart_visible = False

# -----------------------> FUNÇÕES PARA GERENCIAR FRAMES <-----------------------

def select_frame():
    """Controla a exibição do frame de seleção/consulta e do carrinho"""
    global frame_select_visible, frame_insert_visible, \
        frame_update_visible, frame_delete_visible, frame_cart_visible

    # Se o frame já está visível, esconde
    if frame_select_visible:
        frame_select.place_forget()
        frame_cart.place_forget()
        frame_select_visible = False
        frame_cart_visible = False
    else:
        # Mostra o frame de seleção e carrinho
        frame_select.place(x=250, y=15)
        frame_cart.place(x=1055, y=15)

        frame_select_visible = True
        frame_cart_visible = True

        # Esconde todos os outros frames
        frame_insert.place_forget()
        frame_update.place_forget()
        frame_delete.place_forget()

        frame_insert_visible = False
        frame_update_visible = False
        frame_delete_visible = False


def insert_frame():
    """Controla a exibição do frame de inserção de dados"""


    global frame_insert_visible, frame_select_visible, \
        frame_update_visible, frame_delete_visible, frame_cart_visible
    if frame_insert_visible:
        frame_insert.place_forget()
        frame_insert_visible = False
    else:
        frame_insert.place(x=250, y=15)
        frame_insert_visible = True
        # Esconde os outros frames
        frame_select.place_forget()
        frame_update.place_forget()
        frame_delete.place_forget()
        frame_cart.place_forget()

        frame_select_visible = False
        frame_update_visible = False
        frame_delete_visible = False
        frame_cart_visible = False

def update_frame():
    """Controla a exibição do frame de UPDATE"""
    global frame_update_visible, frame_select_visible, \
        frame_insert_visible, frame_delete_visible, frame_cart_visible
    if frame_update_visible:
        frame_update.place_forget()
        frame_update_visible = False
    else:
        frame_update.place(x=250, y=15)
        frame_update_visible = True
        # Esconde os outros frames
        frame_select.place_forget()
        frame_insert.place_forget()
        frame_delete.place_forget()
        frame_cart.place_forget()

        frame_select_visible = False
        frame_insert_visible = False
        frame_delete_visible = False
        frame_cart_visible = False


def delete_frame():
    """Controla a exibição do frame de DELETE"""
    global frame_delete_visible, frame_select_visible, \
        frame_insert_visible, frame_update_visible, frame_cart_visible
    if frame_delete_visible:
        frame_delete.place_forget()
        frame_delete_visible = False
    else:
        frame_delete.place(x=250, y=15)
        frame_delete_visible = True
        # Esconde os outros frames
        frame_select.place_forget()
        frame_insert.place_forget()
        frame_update.place_forget()
        frame_cart.place_forget()

        frame_select_visible = False
        frame_insert_visible = False
        frame_update_visible = False
        frame_cart_visible = False


# -----------------------> ELEMENTOS VISUAIS ADICIONAIS <-----------------------

# Cria uma linha vertical decorativa com extremidades arredondadas
canvas_line = tk.Canvas(
    window,
    width=10,
    height=520,
    bg=purple,
    highlightthickness=0,  # Remove destaque da borda
    borderwidth=0,  # Remove borda
    relief='flat'  # Estilo plano
)
canvas_line.place(x=200, y=100)     # Posiciona o canvas na janela
canvas_line.configure(bg=purple)    # Configura cor de fundo
canvas_line.config(bd=0, highlightthickness=0)  # Remove bordas

def draw_rounded_line(canvas, x, y1, y2, width, color):
    """Desenha uma linha vertical com extremidades arredondadas"""
    radius = width // 2
    # Desenha a linha vertical principal
    canvas.create_rectangle(
        x, y1 + radius,
           x + width, y2 - radius,
        fill=color,
        outline=color,
        width=0
    )
    # Desenha as extremidades arredondadas (semicírculos)
    canvas.create_oval(
        x, y1,
        x + width, y1 + width,
        fill=color,
        outline=color,
        width=0
    )
    canvas.create_oval(
        x, y2 - width,
           x + width, y2,
        fill=color,
        outline=color,
        width=0
    )

# Aplica a função para desenhar a linha
draw_rounded_line(canvas_line, 0, 0, 489, 10, light_lilac)

# Label para o campo de ID do Jogo
labelEntryIDGame = ttk.Label(frameInsert,
                             text="ID Jogo",
                             background=lightPurple,
                             font=("Calibri", 20))

# Campo de entrada para o ID do Jogo
entryIDGame = tk.Entry(frameInsert,width=20, bg=purple, fg=lightPurple, font=("Calibri",10))
entryIDGame.configure(bg="#C6B4C9")

# Label para o campo de Nome do Jogo
labelEntryNameGame = ttk.Label(frameInsert,
                             text="Nome",
                             background=lightPurple,
                             font=("Calibri", 20))

# Campo de entrada para o Nome do Jogo
entryNameGame = tk.Entry(frameInsert,width=20, bg=purple, fg=lightPurple, font=("Calibri",10))
entryNameGame.configure(bg="#C6B4C9")

# Label para o campo de Gênero do Jogo
labelEntryGenderGame = ttk.Label(frameInsert,
                             text="Gênero",
                             background=lightPurple,
                             font=("Calibri", 20))

# Campo de entrada para o Gênero do Jogo
entryGenderGame = tk.Entry(frame_insert,width=20, bg=purple, fg=lightPurple, font=("Calibri",10))
entryGenderGame.configure(bg="#C6B4C9")

# Label para o campo de Preço do Jogo
labelEntryPriceGame = ttk.Label(frameInsert,
                             text="Preço",
                             background=lightPurple,
                             font=("Calibri", 20))

# Campo de entrada para o Preço do Jogo
entryPriceGame = tk.Entry(frameInsert,width=20, bg=purple, fg=lightPurple, font=("Calibri",10))
entryPriceGame.configure(bg="#C6B4C9")

# Label para o campo de Produtora do Jogo
labelEntryProducerGame = ttk.Label(frameInsert,
                            text="Produtora",
                            background=lightPurple,
                            font=("Calibri", 20))

# Campo de entrada para a Produtora do Jogo
entryProducerGame = tk.Entry(frameInsert,width=20, bg=purple, fg=lightPurple, font=("Calibri",10))
entryProducerGame.configure(bg="#C6B4C9")

# Label para o campo de Idade indicada do Jogo
labelEntryAgeGame = ttk.Label(frameInsert,
                            text="Idade",
                            background=lightPurple,
                            font=("Calibri", 20))

# Campo de entrada para a Idade indicada do Jogo
entryAgeGame = tk.Entry(frameInsert,width=20, bg=purple, fg=lightPurple, font=("Calibri",10))
entryAgeGame.configure(bg="#C6B4C9")

# -----------------------> INÍCIA O LOOP PRINCIPAL DA INTERFACE <-----------------------

if __name__ == "__main__":
    # Cria uma instância da classe AppButton que gerencia todos os botões
    app_buttons = AppButton(
        window,  # Janela principal
        light_purple,  # Cor principal dos botões
        medium_lilac,  # Cor secundária
        frame_select,  # Frame de consulta
        frame_insert,  # Frame de inserção
        frame_cart,  # Frame do carrinho
        select_image,  # Ícone para consulta
        insert_image,  # Ícone para inserção
        update_image,  # Ícone para atualização
        delete_image,  # Ícone para exclusão
        select_frame,  # Função para mostrar frame de consulta
        insert_frame,  # Função para mostrar frame de inserção
        update_frame,  # Função para mostrar frame de atualização
        delete_frame  # Função para mostrar frame de exclusão
    )

    # Criação dos botões na interface
    app_buttons.create_main_buttons()  # Botões principais CRUD
    app_buttons.create_select_frame_buttons()  # Botões do frame de consulta
    app_buttons.create_cart_frame_buttons()  # Botões do frame do carrinho
    app_buttons.create_insert_frame_buttons()  # Botões do frame de inserção

    # Inicia o loop principal da interface gráfica
    window.mainloop()