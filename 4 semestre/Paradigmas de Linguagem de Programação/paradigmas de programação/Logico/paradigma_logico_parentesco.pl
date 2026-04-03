% ===== FATOS =====
pai(joao, maria).
pai(joao, pedro).
pai(pedro, lucas).

mae(ana, maria).
mae(ana, pedro).
mae(maria, julia).

homem(joao).
homem(pedro).
homem(lucas).

mulher(ana).
mulher(maria).
mulher(julia).

% ===== REGRAS =====
filho(X, Y) :- pai(Y, X).
filho(X, Y) :- mae(Y, X).

irmao(X, Y) :-
    pai(P, X), pai(P, Y),
    mae(M, X), mae(M, Y),
    X \= Y.

avo(X, Y) :-
    pai(X, Z), pai(Z, Y).

avo(X, Y) :-
    pai(X, Z), mae(Z, Y).

avo(X, Y) :-
    mae(X, Z), pai(Z, Y).

avo(X, Y) :-
    mae(X, Z), mae(Z, Y).

descendente(X, Y) :-
    filho(X, Y).

descendente(X, Y) :-
    filho(X, Z),
    descendente(Z, Y).