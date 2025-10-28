use std::collections::{HashMap, HashSet};

type Var = String;
type Symbol = String; // terminal ou variável; convention: variáveis começam com letra maiúscula
type Production = Vec<Symbol>;

#[derive(Debug, Clone)]
struct Grammar {
    start: Var,
    prods: HashMap<Var, Vec<Production>>,
}

impl Grammar {
    fn new(start: &str) -> Self {
        Self { start: start.to_string(), prods: HashMap::new() }
    }

    fn add(&mut self, head: &str, rhs: Production) {
        self.prods.entry(head.to_string()).or_default().push(rhs);
    }

    fn pretty_print(&self) {
        println!("Start: {}", self.start);
        for (h, rhss) in &self.prods {
            for r in rhss {
                if r.is_empty() {
                    println!("{} -> ε", h);
                } else {
                    println!("{} -> {}", h, r.join(" "));
                }
            }
        }
    }

    // 1) remover símbolos não-geradores (que não derivam string de terminais)
    fn remove_non_generating(&mut self) {
        // encontre variáveis que geram terminalmente (fixpoint)
        let mut gen: HashSet<Var> = HashSet::new();
        loop {
            let mut changed = false;
            for (v, rhss) in &self.prods {
                if gen.contains(v) { continue; }
                for rhs in rhss {
                    // all symbols in rhs either terminals (lowercase) or already in gen
                    let mut ok = true;
                    for s in rhs {
                        if is_var(s) && !gen.contains(s) {
                            ok = false;
                            break;
                        }
                    }
                    if ok {
                        gen.insert(v.clone());
                        changed = true;
                        break;
                    }
                }
            }
            if !changed { break; }
        }

        // filtrar produções que contêm variáveis não-generating
        self.prods.retain(|v, _| gen.contains(v));
        for rhss in self.prods.values_mut() {
            rhss.retain(|rhs| {
                rhs.iter().all(|s| !is_var(s) || gen.contains(s))
            });
        }
    }

    // 2) remover símbolos inalcançáveis (a partir do start)
    fn remove_unreachable(&mut self) {
        let mut reach: HashSet<Var> = HashSet::new();
        let mut stack = vec![self.start.clone()];
        while let Some(v) = stack.pop() {
            if !reach.insert(v.clone()) { continue; }
            if let Some(rhss) = self.prods.get(&v) {
                for rhs in rhss {
                    for s in rhs {
                        if is_var(s) && !reach.contains(s) {
                            stack.push(s.clone());
                        }
                    }
                }
            }
        }
        self.prods.retain(|v, _| reach.contains(v));
    }

    // 3) eliminar produções vazias (ε)
    fn remove_epsilon(&mut self) {
        // achar variáveis anuláveis (nullable)
        let mut nullable: HashSet<Var> = HashSet::new();
        loop {
            let mut changed = false;
            for (v, rhss) in &self.prods {
                if nullable.contains(v) { continue; }
                for rhs in rhss {
                    if rhs.is_empty() {
                        nullable.insert(v.clone());
                        changed = true;
                        break;
                    }
                    if rhs.iter().all(|s| is_var(s) && nullable.contains(s)) {
                        nullable.insert(v.clone());
                        changed = true;
                        break;
                    }
                }
            }
            if !changed { break; }
        }

        // para cada produção, gerar combinações removendo variáveis anuláveis
        let mut new_prods: HashMap<Var, Vec<Production>> = HashMap::new();
        for (v, rhss) in &self.prods {
            let mut set: HashSet<Vec<Symbol>> = HashSet::new();
            for rhs in rhss {
                // indices das posições anuláveis
                let mut positions: Vec<usize> = Vec::new();
                for (i, s) in rhs.iter().enumerate() {
                    if is_var(s) && nullable.contains(s) {
                        positions.push(i);
                    }
                }
                // para cada subset de positions, remova ou mantenha
                let combos = 1usize << positions.len();
                for mask in 0..combos {
                    let mut new_rhs: Production = Vec::new();
                    for (i, s) in rhs.iter().enumerate() {
                        if let Some(pos_idx) = positions.iter().position(|&p| p == i) {
                            // essa posição é anulável; checar bit
                            if (mask >> pos_idx) & 1 == 1 {
                                // remove (interpreta como epsilon)
                                continue;
                            } else {
                                new_rhs.push(s.clone());
                            }
                        } else {
                            new_rhs.push(s.clone());
                        }
                    }
                    // se vazia, sinalizamos producão vazia com vetor vazio
                    set.insert(new_rhs);
                }
            }
            // remover a produção vazia se for a inicial? guardamos para posterior ajuste
            let vecs = set.into_iter().collect();
            new_prods.insert(v.clone(), vecs);
        }
        self.prods = new_prods;

        // se a gramática original gerava ε (start nullable), assegure que start contenha ε
        // (A decisão: já foi tratada acima porque combos geram vetor vazio)
        // entretanto, podemos querer permitir epsilon apenas se start era anulável originalmente.
        // Para simplicidade, mantendo o comportamento natural (se combos geraram empty, mantém).
    }

    // 4) eliminar produções unidade A -> B
    fn remove_unit_productions(&mut self) {
        // construir relação unitária (A =>* B via apenas unit productions)
        let vars: Vec<Var> = self.prods.keys().cloned().collect();
        let mut unit: HashMap<Var, HashSet<Var>> = HashMap::new();
        for v in &vars {
            let mut reach: HashSet<Var> = HashSet::new();
            // BFS apenas por produções unitárias
            let mut stack = vec![v.clone()];
            while let Some(x) = stack.pop() {
                if !reach.insert(x.clone()) { continue; }
                if let Some(rhss) = self.prods.get(&x) {
                    for rhs in rhss {
                        if rhs.len() == 1 && is_var(&rhs[0]) {
                            let y = rhs[0].clone();
                            if !reach.contains(&y) {
                                stack.push(y);
                            }
                        }
                    }
                }
            }
            unit.insert(v.clone(), reach);
        }

        // criar novas produções: para cada A, para cada B em unit[A], adicionar todas produções não-unitárias de B
        let mut new_prods: HashMap<Var, Vec<Production>> = HashMap::new();
        for a in &vars {
            let mut list: Vec<Production> = Vec::new();
            if let Some(set_b) = unit.get(a) {
                for b in set_b {
                    if let Some(rhss) = self.prods.get(b) {
                        for rhs in rhss {
                            // ignorar produções unitárias
                            if rhs.len() == 1 && is_var(&rhs[0]) { continue; }
                            list.push(rhs.clone());
                        }
                    }
                }
            }
            // dedup
            use std::collections::BTreeSet;
            let mut dedup = BTreeSet::new();
            let mut unique: Vec<Production> = Vec::new();
            for p in list {
                if dedup.insert(p.clone()) {
                    unique.push(p);
                }
            }
            if !unique.is_empty() {
                new_prods.insert(a.clone(), unique);
            } else {
                new_prods.insert(a.clone(), vec![]);
            }
        }
        // substituir
        self.prods = new_prods;
    }

    // rotina completa de simplificação em ordem recomendada:
    // 1. remover variáveis não-geradoras (produce terminals)
    // 2. remover inalcançáveis
    // 3. remover produções vazias (epsilon elimination)
    // 4. remover produções unidade
    // (Observação: algumas fontes usam ordem ligeiramente diferente; este é um fluxo válido.)
    fn simplify_all(&mut self) {
        // passos iniciais
        self.remove_non_generating();
        self.remove_unreachable();
        self.remove_epsilon();
        self.remove_unit_productions();
        // após remoções, pode restar símbolos inalcançáveis ou não-geradores; rodar limpeza final:
        self.remove_non_generating();
        self.remove_unreachable();
    }
}

// util: detecta se símbolo é variável (começa com maiúscula)
fn is_var(s: &str) -> bool {
    s.chars().next().map(|c| c.is_ascii_uppercase()).unwrap_or(false)
}

fn main() {
    // Exemplo retirado do slide (exercício): transformar este exemplo em gramática.
    // Exemplo: (do slide "Exercício 1")
    // S -> a A b B c
    // A -> B C
    // B -> b B | b | ε
    // C -> D | ε
    // D -> c
    //
    // Vamos representar terminais como "a","b","c"; variáveis: S,A,B,C,D
    let mut g = Grammar::new("S");
    g.add("S", vec!["a".into(), "A".into(), "b".into(), "B".into(), "c".into()]);
    g.add("A", vec!["B".into(), "C".into()]);
    g.add("B", vec!["b".into(), "B".into()]); // ambiguous: slide shows B -> bB | b | ε
    g.add("B", vec!["b".into()]);
    g.add("B", vec![]); // ε represented by empty vec
    g.add("C", vec!["D".into()]);
    g.add("C", vec![]); // ε
    g.add("D", vec!["c".into()]);

    println!("--- Gramática original ---");
    g.pretty_print();

    g.simplify_all();

    println!("\n--- Gramática simplificada ---");
    g.pretty_print();
}
