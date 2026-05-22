# flask_vuln.py
# login usando concatenação de strings em SQL (SQLite) → SQL Injection
#rota que repassa nome diretamente em HTML → XSS
#formulário POST sem proteção CSRF
# Execução:
# python flask_vuln.py
# abrir http://127.0.0.1:5000/setup para criar tabela
# testar SQLi:
# http://127.0.0.1:5000/login?user=admin'--&pwd=qualquer
# testar XSS:
# http://127.0.0.1:5000/greet?name=<script>alert(1)</script>
from flask import Flask, request, g
import sqlite3

app = Flask(__name__)
DATABASE = 'demo.db'

def get_db():
    db = getattr(g, '_database', None)
    if db is None:
        db = g._database = sqlite3.connect(DATABASE)
    return db

@app.route('/setup')
def setup():
    db = get_db()
    cursor = db.cursor()
    cursor.execute('CREATE TABLE IF NOT EXISTS users(id INTEGER PRIMARY KEY, username TEXT, password TEXT)')
    cursor.execute("INSERT INTO users(username, password) VALUES ('admin','secret')")
    db.commit()
    return 'ok'

@app.route('/login')
def login():
    user = request.args.get('user','')
    pwd = request.args.get('pwd','')
    # INSEGURANÇA: concatenação direta → SQL Injection
    q = "SELECT * FROM users WHERE username='" + user + "' AND password='" + pwd + "'"
    print('QUERY:', q)
    cursor = get_db().cursor()
    res = cursor.execute(q).fetchone()
    if res:
        return 'Welcome %s' % user
    return 'Invalid'

@app.route('/greet')
def greet():
    # XSS: repassa nome sem escape
    name = request.args.get('name','guest')
    return f'<h1>Hello {name}</h1>'

@app.route('/comment', methods=['POST'])
def comment():
    # sem validação -> armazenaria comentário (ex: XSS persistente)
    text = request.form.get('text','')
    # apenas retorna
    return f'Received: {text}'

if __name__ == '__main__':
    app.run(debug=True)
    
