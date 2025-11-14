/*

                Linguagens Livres de Contexto
                Atividade - Gramática LL1

1. [Eliminar a ambiguidade das seguintes gramáticas]
a)
    E → E + E | E − E | E ∗ E | E ÷ E | E ∗ ∗E | (E) | id | cte

b)
    S → S + S | S ∗ S | (S) | id

*/ 

#[derive(Debug, Clone, PartialEq, Eq, Hash)]
pub enum Symbol {
    Terminal(String),
    NonTerminal(String),
    Epsilon,
}

#[derive(Debug, Clone)]
pub struct Production {
    pub left: String,
    pub right: Vec<Symbol>,
}

#[derive(Debug)]
pub struct Grammar {
    pub start: String,
    pub productions: Vec<Production>,
}
