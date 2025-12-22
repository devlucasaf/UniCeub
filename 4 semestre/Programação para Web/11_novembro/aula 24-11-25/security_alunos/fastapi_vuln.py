# Vulnerabilidades demonstradas:
# - execução de SQL via concatenação (SQLite) → SQLi
# - templates Jinja2 renderizando dados sem escape
# - CORS configurado como allow_origins=['*'] (permissivo)
# Execução:
# uvicorn fastapi_vuln:app --reload
# POST form to /login with user=admin'-- and pwd=anything to see SQLi
# fastapi_vuln.py
from fastapi import FastAPI, Request, Form
from fastapi.responses import HTMLResponse
import sqlite3

app = FastAPI()
DB = 'demo_fast.db'

def get_db():
    conn = sqlite3.connect(DB)
    return conn

@app.get('/setup')
def setup():
    db = get_db()
    c = db.cursor()
    c.execute('CREATE TABLE IF NOT EXISTS users(id INTEGER PRIMARY KEY, username TEXT, password TEXT)')
    c.execute("INSERT INTO users(username,password) VALUES ('admin','secret')")
    db.commit()
    return {'ok': True}

@app.post('/login')
async def login(user: str = Form(...), pwd: str = Form(...)):
    # INSEGURANÇA: concatenação
    q = f"SELECT * FROM users WHERE username='{user}' AND password='{pwd}'"
    print('QUERY:', q)
    db = get_db()
    r = db.execute(q).fetchone()
    if r:
        return {'msg': f'Hello {user}'}
    return {'msg': 'Invalid'}

@app.post('/comment', response_class=HTMLResponse)
async def comment(text: str = Form(...)):
    # retorna sem escape => XSS
    return f'<p>Received: {text}</p>'

# permissive CORS would be set here in a real app