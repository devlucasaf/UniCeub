Teste a IA diretamente nos seus apps favoritos … Use o Gemini para criar rascunhos e editar conteúdo e acesse o Gemini Pro com a IA de última geração do Google
import customtkinter


class AppButton:
    def __init__(self, window,
                light_purple, medium_lilac,
                frame_select, frame_insert, frame_cart,
                select_image, insert_image, update_image, delete_image,
                select_frame, insert_frame, update_frame, delete_frame):
        """
        Construtor da classe AppButton.

        Parâmetros:
        window: Janela principal da aplicação
        light_purple, medium_lilac: Cores para os botões
        frame_select, frame_insert, frame_cart: Frames para diferentes seções
        select_image...delete_image: Imagens para os botões principais
        select_frame...game_frame: Funções de callback para os botões

        """

        # Atribui todos os parâmetros recebidos aos atributos da instância
        self.window         = window
        self.light_purple   = light_purple
        self.medium_lilac   = medium_lilac
        self.frame_select   = frame_select
        self.frame_insert   = frame_insert
        self.frame_cart     =   frame_cart
        self.select_image   = select_image
        self.insert_image   = insert_image
        self.update_image   = update_image
        self.delete_image   = delete_image
        self.select_frame   = select_frame
        self.insert_frame   = insert_frame
        self.update_frame   = update_frame
        self.delete_frame   = delete_frame

        # Chama os métodos para criar os grupos de botões
        self.create_main_buttons()  # Cria os botões principais na sidebar
        self.create_select_frame_buttons()  # Cria botões do frame de seleção
        self.create_cart_frame_buttons()  # Cria botões do frame do carrinho
        self.create_insert_frame_buttons()  # Cria botões do frame de inserção

    def mostrar_jogo(self):
        self.frame_insert.mostrar_campos_jogo()

    def mostrar_usuario(self):
        self.frame_insert.mostrar_campos_jogador()

    def create_main_buttons(self):
        """Cria os 4 botões principais localizados na sidebar esquerda"""

        # Botão Select - para acessar a tela de consulta
        self.button_select = customtkinter.CTkButton(
            master=self.window,  # Define a janela principal como pai
            text="Select",  # Texto do botão
            width=150,  # Largura fixa
            height=90,  # Altura fixa
            fg_color=self.light_purple,  # Cor principal
            hover_color=self.medium_lilac,  # Cor quando mouse está sobre
            text_color="white",  # Cor do texto
            font=("Segoe UI", 20, "bold"),  # Fonte do texto
            command=self.select_frame,  # Função chamada ao clicar
            image=self.select_image,  # Ícone do botão
            compound="left",  # Posição do ícone (esquerda)
            anchor="w"  # Alinhamento do conteúdo (oeste/esquerda)
        )
        self.button_select.place(x=20, y=120)  # Posiciona o botão

        # Botão Insert - para acessar a tela de inserção
        button_insert = customtkinter.CTkButton(
            master=self.window,
            text="Insert",
            width=150,
            height=90,
            fg_color=self.light_purple,
            hover_color=self.medium_lilac,
            text_color="white",
            font=("Segoe UI", 20, "bold"),
            command=self.insert_frame,
            image=self.insert_image,
            compound="left",
            anchor="w"
        )
        button_insert.place(x=20, y=240)  # Posiciona abaixo do botão Select

        # Botão Update - para acessar a tela de atualização
        button_update = customtkinter.CTkButton(
            master=self.window,
            text="Update",
            width=150,
            height=90,
            fg_color=self.light_purple,
            hover_color=self.medium_lilac,
            text_color="white",
            font=("Segoe UI", 20, "bold"),
            command=self.update_frame,
            image=self.update_image,
            compound="left",
            anchor="w"
        )
        button_update.place(x=20, y=360)  # Posiciona abaixo do botão Insert

        # Botão Delete - para acessar a tela de exclusão
        button_delete = customtkinter.CTkButton(
            master=self.window,
            text="Delete",
            width=150,
            height=90,
            fg_color=self.light_purple,
            hover_color=self.medium_lilac,
            text_color="white",
            font=("Segoe UI", 20, "bold"),
            command=self.delete_frame,
            image=self.delete_image,
            compound="left",
            anchor="w"
        )
        button_delete.place(x=20, y=480)  # Posiciona abaixo do botão Update
        return button_delete

    def create_select_frame_buttons(self):
        """Cria os botões do frame de seleção/consulta"""

        # Botão para buscar por ID do jogo
        self.button_search_id_game_select = customtkinter.CTkButton(
            master=self.frame_select,  # Frame pai
            text="ID do Jogo",  # Texto do botão
            width=50,  # Largura menor (texto curto)
            height=85,  # Altura padrão
            fg_color="#3d2e4c",  # Cor roxa escura
            hover_color="#a676b0",  # Cor roxa clara ao passar mouse
            text_color="white",  # Texto branco
            font=("Segoe UI", 20, "bold"),  # Fonte padrão
            command=self.select_frame  # Função de callback
        )
        self.button_search_id_game_select.place(x=20, y=160)  # Posicionamento

        # Botão para buscar por Nome do jogo (mesma estrutura dos demais)
        self.button_search_name_game_select = customtkinter.CTkButton(
            master=self.frame_select,
            text="Nome",
            width=100,  # Largura maior para texto maior
            height=85,
            fg_color="#3d2e4c",
            hover_color="#a676b0",
            text_color="white",
            font=("Segoe UI", 20, "bold"),
            command=self.select_frame
        )
        self.button_search_name_game_select.place(x=150, y=160)  # Ao lado do anterior

        # Os demais botões seguem o mesmo padrão com diferentes posições
        self.button_search_gender_game_select = customtkinter.CTkButton(
            master=self.frame_select,
            text="Gênero",
            width=100,
            height=85,
            fg_color="#3d2e4c",
            hover_color="#a676b0",
            text_color="white",
            font=("Segoe UI", 20, "bold"),
            command=self.select_frame
        )
        self.button_search_gender_game_select.place(x=280, y=160)

        self.button_search_price_game_select = customtkinter.CTkButton(
            master=self.frame_select,
            text="Preço",
            width=100,
            height=85,
            fg_color="#3d2e4c",
            hover_color="#a676b0",
            text_color="white",
            font=("Segoe UI", 20, "bold"),
            command=self.select_frame
        )
        self.button_search_price_game_select.place(x=410, y=160)

        self.button_search_producer_game_select = customtkinter.CTkButton(
            master=self.frame_select,
            text="Produtor",
            width=100,
            height=85,
            fg_color="#3d2e4c",
            hover_color="#a676b0",
            text_color="white",
            font=("Segoe UI", 20, "bold"),
            command=self.select_frame
        )
        self.button_search_producer_game_select.place(x=540, y=160)

        self.button_search_age_game_select = customtkinter.CTkButton(
            master=self.frame_select,
            text="Idade",
            width=100,
            height=85,
            fg_color="#3d2e4c",
            hover_color="#a676b0",
            text_color="white",
            font=("Segoe UI", 20, "bold"),
            command=self.select_frame
        )
        self.button_search_age_game_select.place(x=670, y=160)

        # Botões para busca de usuários (posicionados mais abaixo)
        self.button_search_name_player_select = customtkinter.CTkButton(
            master=self.frame_select,
            text="Nome",
            width=100,
            height=85,
            fg_color="#3d2e4c",
            hover_color="#a676b0",
            text_color="white",
            font=("Segoe UI", 20, "bold"),
            command=self.select_frame
        )
        self.button_search_name_player_select.place(x=20, y=500)

        self.button_search_id_player_select = customtkinter.CTkButton(
            master=self.frame_select,
            text="Id do Usuário",
            width=100,
            height=85,
            fg_color="#3d2e4c",
            hover_color="#a676b0",
            text_color="white",
            font=("Segoe UI", 20, "bold"),
            command=self.select_frame
        )
        self.button_search_id_player_select.place(x=150, y=500)

    def create_cart_frame_buttons(self):
        """Cria os botões específicos do frame do carrinho de compras"""

        # Botão para visualizar produtos
        self.button_search_product_cart = customtkinter.CTkButton(
            master=self.frame_cart,
            text="Produtos",
            width=200,  # Largura maior
            height=85,
            fg_color="#3d2e4c",
            hover_color="#a676b0",
            text_color="white",
            font=("Segoe UI", 20, "bold"),
            command=self.select_frame
        )
        self.button_search_product_cart.place(x=150, y=100)  # Posição centralizada

        # Botão para visualizar o carrinho
        self.button_view_cart = customtkinter.CTkButton(
            master=self.frame_cart,
            text="Ver o carrinho",
            width=200,
            height=85,
            fg_color="#3d2e4c",
            hover_color="#a676b0",
            text_color="white",
            font=("Segoe UI", 20, "bold"),
            command=self.select_frame
        )
        self.button_view_cart.place(x=20, y=460)  # Parte inferior esquerda

        # Botão para remover produtos do carrinho
        self.button_remove_product_cart = customtkinter.CTkButton(
            master=self.frame_cart,
            text="Remover Produto",
            width=200,
            height=85,
            fg_color="#3d2e4c",
            hover_color="#a676b0",
            text_color="white",
            font=("Segoe UI", 20, "bold"),
            command=self.select_frame
        )
        self.button_remove_product_cart.place(x=240, y=460)  # Parte inferior direita

    def create_insert_frame_buttons(self):
        """Cria os botões do frame de inserção de dados"""

        # Botão para inserir informações de jogos
        self.button_insert_information_game = customtkinter.CTkButton(
            master=self.frame_insert,
            text="Jogo",
            width=100,
            height=85,
            fg_color="#3d2e4c",
            hover_color="#a676b0",
            text_color="white",
            font=("Segoe UI", 20, "bold"),
            command=self.mostrar_jogo
        )
        self.button_insert_information_game.place(x=520, y=40)  # Lado direito superior

        # Botão para inserir informações de usuários
        self.button_insert_information_player = customtkinter.CTkButton(
            master=self.frame_insert,
            text="Usuário",
            width=100,
            height=85,
            fg_color="#3d2e4c",
            hover_color="#a676b0",
            text_color="white",
            font=("Segoe UI", 20, "bold"),
            command=self.mostrar_usuario
        )
        self.button_insert_information_player.place(x=640, y=40)  # Ao lado do anterior

        # Botão principal de inserção
        self.button_insert_from_game_and_player = customtkinter.CTkButton(
            master=self.frame_insert,
            text="Inserir",
            width=100,
            height=85,
            fg_color="#3d2e4c",
            hover_color="#a676b0",
            text_color="white",
            font=("Segoe UI", 20, "bold"),
            command=self.mostrar_jogo()
        )
        self.button_insert_from_game_and_player.place(x=580, y=500)  # Centro inferior