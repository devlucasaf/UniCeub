use std::fs::File;
use std::io::Write;
use std::path::Path;

fn main() -> std::io::Result<()> {
    // Cada string aqui é o conteúdo de um slide. Edite/adicione slides conforme quiser.
    let slides = vec![
        // 0 - título
        r#"<section>
            <h1>Teoria da Computação</h1>
            <h3>Introdução e Conceitos Básicos</h3>
            <p>Alessandra Hauck & Tiago Leite — FATECS</p>
            </section>"#,

        // 1 - Introdução
        r#"<section>
            <h2>Introdução</h2>
            <ul>
                <li>Linguagens formais — foco a partir de 1950</li>
                <li>Aplicações: análise léxica, análise sintática, linguagens de programação, circuitos digitais...</li>
            </ul>
            </section>"#,

        // 2 - Sintaxe
        r#"<section>
            <h2>Sintaxe</h2>
            <p>Sintaxe estuda a disposição das palavras e frases — regras formais de uma linguagem.</p>
            <p>Ex.: "José bebeu água." é sintaticamente correta.</p>
            </section>"#,

        // 3 - Semântica
        r#"<section>
            <h2>Semântica</h2>
            <p>Semântica estuda o significado. Erros semânticos mudam o sentido.</p>
            <p>Ex.: "xeque" vs "cheque".</p>
            </section>"#,

        // 4 - Sintaxe × Semântica
        r#"<section>
            <h2>Sintaxe × Semântica</h2>
            <ul>
                <li>Sintaxe: tratamento mais simples, construções bem definidas.</li>
                <li>Semântica: interpretação, subjetiva, mais elaborada.</li>
            </ul>
            </section>"#,

        // 5 - Conceitos básicos: Alfabeto e Palavra
        r#"<section>
            <h2>Conceitos Básicos</h2>
            <h3>Alfabeto (Σ)</h3>
            <p>Conjunto finito de símbolos (ex: {a, b}).</p>
            <h3>Palavra (w)</h3>
            <p>Sequência finita de símbolos; ε cadeia vazia.</p>
            </section>"#,

        // 6 - Concatenação e Σ*
        r#"<section>
            <h2>Operações</h2>
            <p>Concatenação: justaposição de palavras. Σ* = todas as palavras sobre Σ.</p>
            </section>"#,

        // 7 - Linguagem Formal
        r#"<section>
            <h2>Linguagem Formal (L)</h2>
            <p>L é subconjunto de Σ*. Exemplos: ∅, {ε}, Σ*, Σ+ e linguagens de programação.</p>
            </section>"#,

        // 8 - Gramática
        r#"<section>
            <h2>Gramática (G)</h2>
            <p>G = (V, T, P, S). Regras de produção geram palavras.</p>
            </section>"#,

        // 9 - Exemplo - grammar -> números
        r#"<section>
            <h2>Exemplo de Derivação</h2>
            <p>Ilustração: derivação do número 243 aplicando regras de produção (ver slides originais).</p>
            </section>"#,

        // 10 - Exercícios
        r#"<section>
            <h2>Exercícios</h2>
            <ol>
                <li>Exercício 1..</li>
                <li>Exercício 2..</li>
                <li>Gramáticas e derivações (veja arquivo original para enunciados).</li>
            </ol>
            </section>"#,

        // 11 - Encerramento
        r#"<section>
            <h2>Conclusão</h2>
            <p>Gramatica gera linguagens; L(G) denota linguagem gerada por G. Convenções: MAIÚSCULAS = variáveis; minúsculas = terminais.</p>
            </section>"#,
    ];

    // Template minimal com Reveal.js via CDN
    let html = format!(
        r#"<!doctype html>
<html lang="pt-BR">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Slides - Teoria da Computação</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reveal.js@4/dist/reveal.css">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/reveal.js@4/dist/theme/black.css">
    <style>
    body {{ font-family: system-ui, -apple-system, "Segoe UI", Roboto, "Helvetica Neue", Arial; }}
    h1 {{ font-size: 2.2rem; margin-bottom: 0.2rem; }}
    h2 {{ font-size: 1.6rem; }}
    section {{ padding: 1.2rem; }}
    </style>
</head>
<body>
    <div class="reveal">
    <div class="slides">
        {}
    </div>
</div>

    <script src="https://cdn.jsdelivr.net/npm/reveal.js@4/dist/reveal.js"></script>
<script>
    Reveal.initialize({{
        hash: true,
        slideNumber: true,
        controls: true,
        progress: true
    }});
</script>
</body>
</html>
"#,
        slides.join("\n")
    );

    let out_path = Path::new("slides.html");
    let mut file = File::create(&out_path)?;
    file.write_all(html.as_bytes())?;

    println!("Gerado: {}", out_path.display());
    println!("Abra {} no navegador para ver os slides.", out_path.display());
    Ok(())
}
