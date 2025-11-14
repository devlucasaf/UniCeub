/*

                Linguagens Livres de Contexto
                Atividade - Gramática LL1

4. Considere a seguinte Gram´atica Livre de Contexto G:

    A → (A) | A ∗ A | A + A | A ÷ A | A − A | a

    i. Verifique se a Gramática acima é LL(1) (ou seja, faça árvore(s) de
    derivação para cada w abaixo, se w possui mais de uma árvore de
    derivação, significa que G não é LL(1))
        a) w = a − a + a
        b) w = a + a ∗ a
        c) w = a ÷ (a + a) − a
    ii. Transforme G em uma Gramática LL(1). Ou seja G não pode possuir:
        • Ambiguidade
        • Recursão à esquerda, e
        • Não determinismo.
    iii. Teste se G é LL(1) (ou seja, faça árvore(s) de derivação para cada w
    abaixo, se w possui mais de uma árvore de derivação, significa que G
    não é LL(1) e é necessário refazer o processo até que cada w possua
    apenas UMA árvore de derivação)
        a) w = a − a + a
        b) w = a + a ∗ a
        c) w = a ÷ (a + a) − a

*/ 

pub type LL1Table = HashMap<(String, String), Vec<Symbol>>;

pub fn build_ll1_table(grammar: &Grammar) -> LL1Table {
    let first = compute_first(grammar);
    let mut table = LL1Table::new();

    for prod in &grammar.productions {
        if let Some(Symbol::Terminal(t)) = prod.right.first() {
            table.insert((prod.left.clone(), t.clone()), prod.right.clone());
        }
    }

    table
}
