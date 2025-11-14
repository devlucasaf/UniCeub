/*

                Linguagens Livres de Contexto
                Atividade - Gramática LL1

2. Eliminar a recurs˜ao a esquerda das seguintes gramáticas:
    Figura 1: (a)                       Figura 2: (b)
    S → A0 | 0B1 | 11C                  S → Aab | Bc | ScAb
    A → 01 | 0BC | A0                   A → Aac | aA | ab
    B → B01 | 01A                       B → bc | aBb | Bab   
    C → 01 | 10                                   

    Figura 3: (c)                       Figura 4: (d)
    E → E + T | E − T | T               S → BaS | aB
    T → T ∗ F | T ÷ F | F               B → Bba | b
    F → F ∗ ∗P | P                      A → Aa | a
    P → (E) | id | cte

*/ 

pub fn remove_left_recursion(grammar: &Grammar) -> Grammar {
    let mut new_productions = vec![];

    for nt in grammar.productions.iter().map(|p| p.left.clone()).collect::<Vec<_>>() {
        let mut alpha = vec![];
        let mut beta = vec![];

        for p in grammar.productions.iter().filter(|p| p.left == nt) {
            if let Some(Symbol::NonTerminal(first)) = p.right.first() {
                if *first == nt {
                    alpha.push(p.right[1..].to_vec());
                } 
                else {
                    beta.push(p.right.clone());
                }
            } 
            else {
                beta.push(p.right.clone());
            }
        }

        if alpha.is_empty() {
            new_productions.extend(
                grammar.productions.iter()
                    .filter(|p| p.left == nt)
                    .cloned()
            );
        } else {
            let new_nt = format!("{}'", nt);
            for b in beta {
                let mut v = b.clone();
                v.push(Symbol::NonTerminal(new_nt.clone()));
                new_productions.push(Production { left: nt.clone(), right: v });
            }
            for a in alpha {
                let mut v = a.clone();
                v.push(Symbol::NonTerminal(new_nt.clone()));
                new_productions.push(Production { left: new_nt.clone(), right: v });
            }

            new_productions.push(Production {
                left: new_nt.clone(),
                right: vec![Symbol::Epsilon],
            });
        }
    }

    Grammar {
        start: grammar.start.clone(),
        productions: new_productions,
    }
}
