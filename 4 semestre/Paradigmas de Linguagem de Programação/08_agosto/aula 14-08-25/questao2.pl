% +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+

% Paradigma de Linguagem de Programação 
% Atividade 4 - Capítulo 2 - Prolog
% Data da aula: 14-08-25
% Data de entrega: 20-08-25

% +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+

% Exercício 2 

% +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+

% Gênero
homem(marcos).
homem(rodrigo).
homem(silvio).

mulher(ana).
mulher(maria).

% Atributos (fatos)
bonito(ana).
rico(marcos).
bonito(marcos).

rica(maria).   % manter também forma feminina se desejar consultas específicas
forte(maria).

forte(rodrigo).
bonito(rodrigo).

amavel(silvio).
forte(silvio).

% Normalização opcional para consultas "rico/1" e "rica/1"
rico(maria) :- rica(maria).

% Regras do enunciado
% 1) Todos os homens gostam de mulheres bonitas.
gosta(H, M) :- homem(H), mulher(M), bonito(M).

% 2) Todos os homens ricos são felizes.
feliz(H) :- homem(H), rico(H).

% 3) Homem que gosta de uma mulher que gosta dele é feliz.
feliz(H) :- homem(H), mulher(M), gosta(H, M), gosta(M, H).

% 4) Mulher que gosta de um homem que gosta dela é feliz.
feliz(M) :- mulher(M), homem(H), gosta(M, H), gosta(H, M).

% 5) Maria gosta de qualquer homem que gosta dela.
gosta(maria, H) :- homem(H), gosta(H, maria).

% 6) Ana gosta de qualquer homem que gosta dela,
%     desde que (rico e amável) OU (bonito e forte).
gosta(ana, H) :-
    homem(H),
    gosta(H, ana),
    ( (rico(H), amavel(H))
    ; (bonito(H), forte(H)) ).

% Consultas possíveis:
% ?- bonito(Quem).          % quem é bonito(a)?
% ?- rico(Quem).            % quem é rico(a)? (aceita maria via regra rico/1)
% ?- feliz(Quem).           % quem é feliz?
% ?- gosta(maria, Quem).    % de quem Maria gosta?
% ?- gosta(ana, Quem).      % de quem Ana gosta?

