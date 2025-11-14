/*
                Linguagens Livres de Contexto
                Atividade - Gramática LL1

3. Eliminar o não determinismo das seguintes gram´aticas:

    Figura 5: (a)                       Figura 6: (b)                  
    S → bcD | Bcd                       C → V = exp | id(E)
    B → bcD | b                         V → id[E] | id
    D → dc | d                          E → exp + E | exp

    Figura 7: (c)                       Figura 8: (d)
    S → Abc | Bcc | baaD                S → 10A0 | 11B1
    A → acC | adC                       A → 11A0 | 100        
    B → dbCc | dc                       B → C10 | 01
    C → Acd | cd | cbA                  C → 10C | 11D    
    D → aaBC | abc | acC                D → 010 | 011

*/ 

use std::collections::{HashMap, HashSet};

pub fn compute_first(grammar: &Grammar) -> HashMap<String, HashSet<String>> {
    let mut first = HashMap::<String, HashSet<String>>::new();

    for p in &grammar.productions {
        first.entry(p.left.clone()).or_default();
    }

    let mut changed = true;
    while changed {
        changed = false;

        for prod in &grammar.productions {
            let left = &prod.left;

            if let Some(Symbol::Terminal(t)) = prod.right.first() {
                changed |= first.entry(left.clone()).or_default().insert(t.clone());
            } else if prod.right.first() == Some(&Symbol::Epsilon) {
                changed |= first.entry(left.clone()).or_default().insert("ε".into());
            }
        }
    }

    first
}
