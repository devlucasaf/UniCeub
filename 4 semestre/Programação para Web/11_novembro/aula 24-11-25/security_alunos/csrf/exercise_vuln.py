import sqlite3
from flask import Flask, request, session, redirect, url_for, render_template_string

app = Flask(__name__)
app.secret_key = 'chave_super_fixa_ainda_insegura' # Em produção, isso deveria ser uma var de ambiente

def init_db():
    conn = sqlite3.connect('bank_v2.db')
    c = conn.cursor()
    c.execute('DROP TABLE IF EXISTS users')
    c.execute('CREATE TABLE users (id INTEGER PRIMARY KEY, username TEXT, password TEXT, balance INTEGER)')
    
    # Dados iniciais: Alice tem 1000, Hacker tem 0
    c.execute("INSERT INTO users (username, password, balance) VALUES ('alice', '123456', 1000)")
    c.execute("INSERT INTO users (username, password, balance) VALUES ('hacker', 'badguy', 0)")
    
    conn.commit()
    conn.close()

@app.route('/', methods=['GET', 'POST'])
def login():
    msg = ''
    if request.method == 'POST':
        username = request.form['username']
        password = request.form['password']
        
        conn = sqlite3.connect('bank_v2.db')
        c = conn.cursor()
        # CORREÇÃO DO SQL INJECTION (Uso de ?)
        c.execute("SELECT id, username, balance FROM users WHERE username = ? AND password = ?", (username, password))
        user = c.fetchone()
        conn.close()
        
        if user:
            session['user_id'] = user[0]
            session['username'] = user[1]
            return redirect(url_for('dashboard'))
        else:
            msg = "Login falhou!"
            
    return render_template_string('''
        <h2>Login Seguro v2.0</h2>
        <p style="color:red">{{msg}}</p>
        <form method="post">
            User: <input name="username"><br>
            Pass: <input type="password" name="password"><br>
            <input type="submit" value="Entrar">
        </form>
    ''', msg=msg)

@app.route('/dashboard')
def dashboard():
    if 'user_id' not in session: return redirect(url_for('login'))
    
    # Atualiza saldo para exibição
    conn = sqlite3.connect('bank_v2.db')
    c = conn.cursor()
    c.execute("SELECT balance FROM users WHERE id = ?", (session['user_id'],))
    balance = c.fetchone()[0]
    conn.close()

    return render_template_string('''
        <h1>Olá, {{ session['username'] }}</h1>
        <h3>Seu Saldo: R$ {{ balance }}</h3>
        <hr>
        <h3>Transferir Dinheiro</h3>
        <form action="/transfer" method="POST">
            Destinatário (User): <input name="to_user"><br>
            Valor: <input name="amount" type="number"><br>
            <input type="submit" value="Enviar PIX">
        </form>
        <br>
        <a href="/logout">Sair</a>
    ''', balance=balance, session=session)

@app.route('/transfer', methods=['POST'])
def transfer():
    if 'user_id' not in session: return redirect(url_for('login'))
    
    print('OI------------')
    
    to_user = request.form['to_user']
    amount = int(request.form['amount'])
    from_id = session['user_id']

    conn = sqlite3.connect('bank_v2.db')
    c = conn.cursor()
    
    # Verifica se o usuário tem saldo
    c.execute("SELECT balance FROM users WHERE id = ?", (from_id,))
    current_balance = c.fetchone()[0]
    
    if current_balance >= amount:
        # Realiza a transferência
        c.execute("UPDATE users SET balance = balance - ? WHERE id = ?", (amount, from_id))
        c.execute("UPDATE users SET balance = balance + ? WHERE username = ?", (amount, to_user))
        conn.commit()
        result = "Transferência realizada com sucesso!"
    else:
        result = "Saldo insuficiente!"
        
    conn.close()
    return render_template_string(f"<h1>{result}</h1><a href='/dashboard'>Voltar</a>")

@app.route('/logout')
def logout():
    session.clear()
    return redirect(url_for('login'))

if __name__ == '__main__':
    init_db()
    app.run(debug=True, port=5000)