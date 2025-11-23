"""
Programação para Web
Aula: 10-11-2025
APIs em PYTHON
"""

# app.py
from flask import Flask, request, jsonify

# Inicializa a aplicação Flask
app = Flask(__name__)

# --- EXEMPLOS ABAIXO ---

# Rota principal para referência
@app.route("/")
def index():
    return "<h1>Exemplos de envio de parâmetros com Flask!</h1>"

# ... cole os códigos dos exemplos aqui ...

"""
Slide 33 - Flask - Requisição GET
Acesso a recursos GET
"""

# Exemplo 1: Parâmetros na URL
# o valor passado em <nome> na URL se torna um argumento da função

@app.route("/usuario/<string:nome>")
def saudar_usuario(nome):
    return f"<h1>Olá, {nome}!</h1><p>Este parâmetro foi recebido pela URL.</p>"

"""
No navegador, abrir e acessar as seguintes URLs:

http://127.0.0.1:5000/usuario/Maria
http://127.0.0.1:5000/usuario/Joao
"""

"""
Slide 34 - Flask - Requisição GET com parâmetro
Podemos passar parâmetros na URL após um ‘?’, no formato chave=valor.
Isto é muito usado para filtros, buscas e ordenação.
"""

# Exemplo 2: Argumento de Query String
# Usamos o objeto 'request' para acessar os argumentos da URL.
@app.route("/pesquisa")
def pesquisar():
    # request.args.get() é usado para pegar o valor de chave 'q'
    termo_busca = request.args.get('q')

    if termo_busca:
        return f"<h1>Você pesquisou por: '{termo_busca}'</h1>"
    else:
        return "<h1>Por favor, forneça um termo de busca. Ex:" \
        "/pesquisa?q=python</h1>"

# Teste: http://127.0.0.1:5000/pesquisa?q=Flask

"""
Slide 35 - Flask - Requisição POST
Os dados são enviados no corpo de uma requisição POST:
"""

# Exemplo 3: Recebendo dados de um formulário
# Esta rota aceita tanto GET (para mostrar o form) quanto POST (para receber os dados)
@app.route("/login", methods=['GET', 'POST'])
def login():
    # Se a requisição for POST, processamos os dados do formulário
    if request.method == 'POST':
        # request.form é um dicionário com os dados enviados pelo formulário
        usuario = request.form.get('usuario')
        senha = request.form.get('senha')
        return f"<h1>Login Tentado!</h1><p>Usuário: {usuario}</p>"

    # Se a requisição for GET, apenas mostramos o formulário
    return """
        <h1>Formulário de Login</h1>
        <form method="post">
            <label>Usuário:</label><br>
            <input type="text" name="usuario"><br><br>
            <label>Senha:</label><br>
            <input type="password" name="senha"><br><br>
            <input type="submit" value="Entrar>
        </form>
    """

"""
Slide 36 - Flask - Requisição POST em JSON
Os dados são enviados no formato JSON
"""

# Recebendo um corpo JSON (típico de API)
@app.route("/api/produtos", methods=['POST'])
def criar_produto():
    # request.get_json() parseia o corpo da requisição como um JSON
    dados = request.get_json()

    if not dados or 'nome' not in dados or 'preco' not in dados:
        return jsonify({"erro": "Dados incompletos"}), 400
    
    nome_produto = dados['nome']
    preco_produto = dados['preco']

    # Aqui você faria a lógica de salvar o produto no banco de dados...
    # Retornamos uma resposta em JSON confirmando o recebimento
    return jsonify({
        "mensagem": "Produto recebido com sucesso!",
        "produto_recebido": {
            "nome": nome_produto,
            "preco": preco_produto
        }
    }), 201 # 201 Created é um status HTTP apropriado

"""
O if __name__ == '__main__': não é necessário ao usar 'flask run',
mas é uma boa prática para rodar com 'python app.py'
"""
if __name__ == "__main__":
    app.run(debug=True)
