% +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+

% Paradigma de Linguagem de Programação 
% Atividade 5 - Capítulo 2 - Prolog
% Data da aula: 21-08-25
% Data de entrega: 27-08-25

% +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+

% Exercício 1 

% +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+

% Tipos/indivíduos
cachorro(doky).
gato(garfield).
peixe(nemo).
passaro(dudu).
pessoa(joao).
pessoa(maria).

% Propriedades
magro(doky).
gordo(garfield).

% Regras de "gosta"
% Gatos gostam de peixes, pássaros e pessoas
gosta(X, Y) :- gato(X), peixe(Y).
gosta(X, Y) :- gato(X), passaro(Y).
gosta(X, Y) :- gato(X), pessoa(Y).

% Cachorros gostam de pessoas
gosta(X, Y) :- cachorro(X), pessoa(Y).

% Os gatos comem tudo que gostam, exceto pessoas
come(X, Y) :- gato(X), gosta(X, Y), \+ pessoa(Y).

% Consultas possíveis:
% ?- cachorro(X).
% ?- gato(X).
% ?- peixe(X).
% ?- passaro(X).
% ?- pessoa(X).
% ?- magro(X).
% ?- gordo(X).
% ?- gosta(Quem, QuemOuQue).
% ?- come(garfield, OQue).
