use regex::Regex;

#[derive(Debug)]
struct Caso<'a> {
    descricao: &'a str,
    padrao: &'a str,
    exemplos: Vec<(&'a str, bool)>,
}

/// Helper: avalia um caso com âncoras opcionais.
/// Por padrão uso âncoras ^ $ para equivaler ao “aceitar a palavra inteira” (como AF faz).
fn testar_caso(c: &Caso) {
    // Se o padrão já tiver ^ ou $, uso do jeito que veio; senão, ancoro.
    let pattern_ancorado = if c.padrao.contains('^') || c.padrao.contains('$') {
        c.padrao.to_string()
    } else {
        format!("^{}$", c.padrao)
    };

    let re = Regex::new(&pattern_ancorado)
        .unwrap_or_else(|e| panic!("Falha compilando ER '{}': {}", c.padrao, e));

    println!("\n=== {} ===", c.descricao);
    println!("Padrão: /{}/", pattern_ancorado);

    for (w, esperado) in &c.exemplos {
        let ok = re.is_match(w);
        let status = if ok == *esperado { "OK ✅" } else { "ERRO ❌" };
        println!("  {:<12} → {:<9} {}", format!("\"{}\"", w), if ok { "ACEITA" } else { "REJEITA" }, status);
    }
}

fn main() {
    // 1) Começa e termina com 'a'   (Σ = {a, b})
    // Slide mostra conceitualmente "a b* a" (com zero ou mais b no meio).
    // Em regex com ancoragem: ^ab*a$
    let caso1 = Caso {
        descricao: "Σ={a,b}: começa e termina com 'a'",
        padrao: r"ab*a",
        exemplos: vec![
            ("aa", true),
            ("aba", true),
            ("abba", true),
            ("abbbbba", true),
            ("a", false),
            ("ba", false),
            ("abb", false),
        ],
    };

    // 2) Termina com "aa"   (Σ = {a, b})
    // Slide: (a|b)*aa
    let caso2 = Caso {
        descricao: "Σ={a,b}: termina com \"aa\"",
        padrao: r"(a|b)*aa",
        exemplos: vec![
            ("aa", true),
            ("aaaa", true),
            ("abaa", true),
            ("babaaabbbaa", true),
            ("bbaa", true),
            ("aba", false),
            ("bbb", false),
        ],
    };

    // 3) Começa com "bbb" e termina com ≥1 'a'   (Σ = {a, b})
    // Slide: b b b a+  (em regex: ^bbba+$)
    let caso3 = Caso {
        descricao: "Σ={a,b}: começa com \"bbb\" e termina com um ou mais 'a'",
        padrao: r"bbba+",
        exemplos: vec![
            ("bbba", true),
            ("bbbbaaa", true), // cuidado: precisa começar com 'bbb' exato
            ("bbb", false),
            ("bbbaaab", false),
            ("abba", false),
        ],
    };

    // 4) “Contém aa OU contém bb”  (Σ = {a,b})
    // Linguagem dos slides de AFD/AFN também aparece aqui. ER: (a|b)* (aa|bb) (a|b)*
    let caso4 = Caso {
        descricao: "Σ={a,b}: contém aa OU contém bb (subpalavra)",
        padrao: r"(a|b)*(aa|bb)(a|b)*",
        exemplos: vec![
            ("aab", true),
            ("abba", true),
            ("abab", false),
            ("baa", true),
            ("bb", true),
            ("aba", false),
        ],
    };

    // 5) “Entre dois 1s há sempre número par de 0s” (Σ = {0,1})
    // Exemplo alinhado a exercícios de AFN/AFε. ER é mais sofisticada; uma forma:
    // 1 ( 0{2} )* ( 1 (0{2})* )*   — mas para capturar todas cadeias possíveis, usamos construção por blocos:
    // Regex que aceita cadeias em que os 0s entre 1s vêm em pares (padrão conhecido):
    //  ^(0+)?(1(00)*1(00)*)*$   porém isso rejeita cadeia com único 1.
    // Vamos usar uma variante mais abrangente para Σ* com paridade entre 1s:
    //  ^(0*|(0*10*10*)+)$   (sequências de blocos “1 0* 1” onde 0* entre 1s pode ser vazio, mas precisamos pares -> usamos (00)*)
    //  ^(0*|(0*1(00)*0*1(00)*)+0*)$   -> permite blocos de 1 ... 1 com pares de 0s entre eles e 0s livres nas extremidades
    let caso5 = Caso {
        descricao: "Σ={0,1}: entre dois '1's há sempre número par de '0's",
        padrao: r"(0*|(0*1(00)*0*1(00)*)+0*)",
        exemplos: vec![
            ("", true),
            ("0", true),
            ("1", true),          // um único 1 não viola a regra
            ("10", true),         // sem outro 1 depois, ok
            ("1001", true),       // 2 zeros entre 1s
            ("101", false),       // 1 zero entre 1s (ímpar) -> rejeita
            ("110011", true),     // 2 zeros entre 1s e pares entre blocos
        ],
    };

    // 6) Dígitos/ponto flutuante simplificado (em espírito do AFε de números) — demonstrativo prático
    //   sinal opcional, dígitos, opcionalmente ponto com dígitos
    let caso6 = Caso {
        descricao: "Número decimal simples com sinal opcional",
        padrao: r"[+-]?(\d+(\.\d+)?|\.\d+)",
        exemplos: vec![
            ("+3.14", true),
            ("-0.5", true),
            (".25", true),
            ("42", true),
            ("+", false),
            ("3.", false),
        ],
    };

    for c in [&caso1, &caso2, &caso3, &caso4, &caso5, &caso6] {
        testar_caso(c);
    }
}
