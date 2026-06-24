const express = require('express');

const app = express();
const port = process.env.PORT || 3000;

app.use(express.urlencoded({ extended: true }));

app.get('/', (req, res) => {
    res.send(`
        <html>
            <body>
                <h1>Login</h1>
                <form action="/login" method="POST">
                    <input 
                        type="text" 
                        name="username" 
                        placeholder="Usuário" 
                    />
                    <input 
                        type="password" 
                        name="password" 
                        placeholder="Senha" 
                    />
                    <button type="submit">Entrar</button>
                </form>
            </body>
        </html>
    `);
});

app.post('/login', (req, res) => {
    const { username, password } = req.body;

    if (username === 'aluno' && password === 'senha123') {
        return res.send('Bem-vindo!');
    }

    return res.status(401).send('Credenciais inválidas');
});

app.listen(port, () => console.log(`App rodando em http://localhost:${port}`));
