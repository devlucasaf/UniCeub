import sqlite3
from flask import Flask, request, session, redirect, url_for, render_template_string

app = Flask(__name__)
app.secret_key = 'chave_super_secreta_insegura'

# Configuração do Banco de Dados (Recriado a cada execução para limpeza)
def init_db():
    conn = sqlite3.connect('vulnerable.db')
    c = conn.cursor()
    c.execute('DROP TABLE IF EXISTS users')
    c.execute('DROP TABLE IF EXISTS messages')
    c.execute('CREATE TABLE users (id INTEGER PRIMARY KEY, username TEXT, password TEXT, balance INTEGER)')
    c.execute('CREATE TABLE messages (id INTEGER PRIMARY KEY, user_id INTEGER, content TEXT)')
    
    # Dados iniciais
    c.execute("INSERT INTO users (username, password, balance) VALUES ('admin', 'admin123', 10000)")
    c.execute("INSERT INTO users (username, password, balance) VALUES ('alice', 'alice123', 50)")
    c.execute("INSERT INTO messages (user_id, content) VALUES (1, 'Senha do servidor: 123456')")
    c.execute("INSERT INTO messages (user_id, content) VALUES (2, 'Olá, sou a Alice!')")
    
    conn.commit()
    conn.close()

# --- VULNERABILIDADE 1: SQL INJECTION ---
@app.route('/', methods=['GET', 'POST'])
def login():
    error = None
    if request.method == 'POST':
        username = request.form['username']
        password = request.form['password']
        
        conn = sqlite3.connect('vulnerable.db')
        c = conn.cursor()
        
        # ERRO GRAVE: Concatenação direta de strings na query
        query = f"SELECT * FROM users WHERE username = '{username}' AND password = '{password}'"
        print(f"Query Executada: {query}") # Para mostrar no console da aula
        
        try:
            c.execute(query)
            user = c.fetchone()
            if user:
                session['user_id'] = user[0]
                session['username'] = user[1]
                return redirect(url_for('dashboard'))
            else:
                error = "Credenciais inválidas"
        except Exception as e:
            error = f"Erro de SQL: {e}"
        finally:
            conn.close()
            
    return render_template_string('''
        <h1>Login Vulnerável</h1>
        <p style="color:red">{{ error }}</p>
        <form method="post">
            Usuário: <input type="text" name="username"><br>
            Senha: <input type="password" name="password"><br>
            <input type="submit" value="Entrar">
        </form>
    ''', error=error)
    

@app.route('/dashboard')
def dashboard():
    if 'user_id' not in session: return redirect(url_for('login'))
    
    # --- VULNERABILIDADE 2: XSS REFLETIDO ---
    search = request.args.get('q', '')
    
    # ERRO GRAVE: Renderizando input do usuário sem escape (usando |safe ou format)
    template = f'''
        <h1>Bem-vindo, {session['username']}!</h1>
        <p>Seu ID de sessão: {session['user_id']}</p>
        <hr>
        <h3>Busca de Usuários</h3>
        <form>
            Buscar: <input name="q" value="{search}">
            <input type="submit" value="Buscar">
        </form>
        <p>Você buscou por: <b>{search}</b></p> 
        <hr>
        <h3>Suas Mensagens Privadas (IDOR Demo)</h3>
        <a href="/message/1">Ler mensagem #1</a> | 
        <a href="/message/2">Ler mensagem #2</a>
        <br><br>
        <a href="/logout">Sair</a>
    '''
    return render_template_string(template)

# --- VULNERABILIDADE 3: IDOR (Insecure Direct Object Reference) ---
@app.route('/message/<int:msg_id>')
def view_message(msg_id):
    if 'user_id' not in session: return redirect(url_for('login'))
    
    conn = sqlite3.connect('vulnerable.db')
    c = conn.cursor()
    
    # ERRO GRAVE: Busca a mensagem pelo ID sem verificar se pertence ao usuário logado
    c.execute("SELECT content FROM messages WHERE id = ?", (msg_id,))
    msg = c.fetchone()
    conn.close()
    
    if msg:
        return f"<h1>Mensagem Confidencial #{msg_id}</h1><p>{msg[0]}</p><a href='/dashboard'>Voltar</a>"
    return "Mensagem não encontrada"

@app.route('/logout')
def logout():
    session.clear()
    return redirect(url_for('login'))

if __name__ == '__main__':
    init_db()
    app.run(debug=True, port=5000)