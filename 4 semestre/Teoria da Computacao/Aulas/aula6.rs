use std::collections::{HashMap, HashSet, VecDeque};

/// Representa um AFN (Autômato Finito Não Determinístico)
#[derive(Debug)]
struct AFN {
    alfabeto: Vec<char>,
    estados: Vec<String>,
    estado_inicial: String,
    estados_finais: Vec<String>,
    transicoes: HashMap<(String, char), HashSet<String>>,
}

/// Representa o AFD resultante da determinização
#[derive(Debug)]
struct AFD {
    alfabeto: Vec<char>,
    estados: Vec<HashSet<String>>, // Conjuntos de estados do AFN
    estado_inicial: HashSet<String>,
    estados_finais: Vec<HashSet<String>>,
    transicoes: HashMap<(HashSet<String>, char), HashSet<String>>,
}

impl AFN {
    /// Cria um novo AFN
    fn new(
        alfabeto: Vec<char>,
        estados: Vec<String>,
        estado_inicial: &str,
        estados_finais: Vec<String>,
        transicoes: HashMap<(String, char), HashSet<String>>,
    ) -> Self {
        Self {
            alfabeto,
            estados,
            estado_inicial: estado_inicial.to_string(),
            estados_finais,
            transicoes,
        }
    }

    /// Algoritmo de determinização (AFN → AFD)
    fn determinizar(&self) -> AFD {
        let mut afd_transicoes = HashMap::new();
        let mut afd_estados = Vec::new();
        let mut fila = VecDeque::new();

        let estado_inicial: HashSet<String> = [self.estado_inicial.clone()].into_iter().collect();
        fila.push_back(estado_inicial.clone());
        afd_estados.push(estado_inicial.clone());

        while let Some(conjunto) = fila.pop_front() {
            for &simbolo in &self.alfabeto {
                let mut novo_conjunto = HashSet::new();

                // Para cada estado do conjunto atual, siga as transições possíveis
                for estado in &conjunto {
                    if let Some(destinos) = self.transicoes.get(&(estado.clone(), simbolo)) {
                        novo_conjunto.extend(destinos.clone());
                    }
                }

                // Registra a transição
                afd_transicoes.insert((conjunto.clone(), simbolo), novo_conjunto.clone());

                // Adiciona o novo conjunto se ainda não existir
                if !afd_estados.contains(&novo_conjunto) && !novo_conjunto.is_empty() {
                    fila.push_back(novo_conjunto.clone());
                    afd_estados.push(novo_conjunto);
                }
            }
        }

        // Identifica estados finais no novo AFD
        let afd_finais: Vec<HashSet<String>> = afd_estados
            .iter()
            .filter(|conj| conj.iter().any(|q| self.estados_finais.contains(q)))
            .cloned()
            .collect();

        AFD {
            alfabeto: self.alfabeto.clone(),
            estados: afd_estados,
            estado_inicial,
            estados_finais: afd_finais,
            transicoes: afd_transicoes,
        }
    }
}

impl AFD {
    /// Exibe a tabela de transições do AFD
    fn mostrar(&self) {
        println!("\n=== TABELA DE TRANSIÇÕES DO AFD ===");
        for (chave, destino) in &self.transicoes {
            let (origem, simbolo) = chave;
            println!(
                "{:?} -- {} --> {:?}",
                origem.iter().collect::<Vec<_>>(),
                simbolo,
                destino.iter().collect::<Vec<_>>()
            );
        }

        println!("\nEstados do AFD:");
        for estado in &self.estados {
            println!("{:?}", estado);
        }

        println!("\nEstado inicial: {:?}", self.estado_inicial);
        println!("Estados finais: {:?}", self.estados_finais);
    }
}

fn main() {
    // Exemplo de AFN (mesmo usado na Aula 6)
    // L = { w | w termina com '01' }
    let alfabeto = vec!['0', '1'];
    let estados = vec!["q0".to_string(), "q1".to_string(), "q2".to_string()];
    let estado_inicial = "q0";
    let estados_finais = vec!["q2".to_string()];

    let mut transicoes: HashMap<(String, char), HashSet<String>> = HashMap::new();

    // δ(q0, 0) = {q0}
    transicoes.insert(("q0".to_string(), '0'), ["q0".to_string()].into_iter().collect());
    // δ(q0, 1) = {q0, q1}
    transicoes.insert(
        ("q0".to_string(), '1'),
        ["q0".to_string(), "q1".to_string()].into_iter().collect(),
    );
    // δ(q1, 0) = {q2}
    transicoes.insert(("q1".to_string(), '0'), ["q2".to_string()].into_iter().collect());
    // δ(q1, 1) = {}
    transicoes.insert(("q1".to_string(), '1'), HashSet::new());
    // δ(q2, 0) = {}
    transicoes.insert(("q2".to_string(), '0'), HashSet::new());
    // δ(q2, 1) = {}
    transicoes.insert(("q2".to_string(), '1'), HashSet::new());

    let afn = AFN::new(alfabeto, estados, estado_inicial, estados_finais, transicoes);

    // Aplicar o algoritmo de determinização
    let afd = afn.determinizar();
    afd.mostrar();
}
