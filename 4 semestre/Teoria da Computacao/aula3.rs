use std::collections::HashMap;

/// Estrutura representando um AFD (Autômato Finito Determinístico)
#[derive(Debug)]
struct AFD {
    alfabeto: Vec<char>,
    estados: Vec<String>,
    estado_inicial: String,
    estados_finais: Vec<String>,
    transicoes: HashMap<(String, char), String>,
}

impl AFD {
    /// Cria um novo AFD
    fn new(
        alfabeto: Vec<char>,
        estados: Vec<String>,
        estado_inicial: &str,
        estados_finais: Vec<String>,
        transicoes: HashMap<(String, char), String>,
    ) -> Self {
        Self {
            alfabeto,
            estados,
            estado_inicial: estado_inicial.to_string(),
            estados_finais,
            transicoes,
        }
    }

    /// Simula o processamento de uma palavra e retorna se ela é aceita
    fn reconhece(&self, palavra: &str) -> bool {
        let mut estado_atual = self.estado_inicial.clone();

        for simbolo in palavra.chars() {
            if !self.alfabeto.contains(&simbolo) {
                println!("Símbolo '{}' não pertence ao alfabeto!", simbolo);
                return false;
            }

            match self.transicoes.get(&(estado_atual.clone(), simbolo)) {
                Some(proximo_estado) => {
                    estado_atual = proximo_estado.clone();
                }
                None => {
                    println!(
                        "Transição não definida para ({}, '{}')",
                        estado_atual, simbolo
                    );
                    return false;
                }
            }
        }

        self.estados_finais.contains(&estado_atual)
    }
}

fn main() {
    // Exemplo: Linguagem L = { w ∈ {a,b}* | w contém aa ou bb }
    let alfabeto = vec!['a', 'b'];
    let estados = vec![
        "q0".to_string(),
        "q1".to_string(),
        "q2".to_string(),
        "q3".to_string(),
    ];
    let estado_inicial = "q0";
    let estados_finais = vec!["q2".to_string(), "q3".to_string()];

    // Definição das transições δ
    // δ(q, símbolo) = próximo estado
    let mut transicoes = HashMap::new();
    transicoes.insert(("q0".to_string(), 'a'), "q1".to_string());
    transicoes.insert(("q0".to_string(), 'b'), "q0".to_string());
    transicoes.insert(("q1".to_string(), 'a'), "q2".to_string());
    transicoes.insert(("q1".to_string(), 'b'), "q0".to_string());
    transicoes.insert(("q2".to_string(), 'a'), "q2".to_string());
    transicoes.insert(("q2".to_string(), 'b'), "q3".to_string());
    transicoes.insert(("q3".to_string(), 'a'), "q2".to_string());
    transicoes.insert(("q3".to_string(), 'b'), "q3".to_string());

    let afd = AFD::new(alfabeto, estados, estado_inicial, estados_finais, transicoes);

    // Testes com palavras
    let palavras = vec!["aab", "abba", "abab", "baa", "bb", "aba"];
    for palavra in palavras {
        let resultado = afd.reconhece(palavra);
        println!(
            "Palavra {:<6} -> {}",
            palavra,
            if resultado { "ACEITA ✅" } else { "REJEITADA ❌" }
        );
    }
}
