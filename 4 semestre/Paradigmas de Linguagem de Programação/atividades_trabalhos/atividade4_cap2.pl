% +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+

% Paradigma de Linguagem de Programação 
% Professor: Felipe Batista da Silva
% Atividade 4 - Capítulo 2 - Prolog

% +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+

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


% +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+

% Exercício 3 

% +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+

% Base fornecida
aluno(joao, calculo).
aluno(maria, calculo).
aluno(joel, programacao).
aluno(joel, estrutura).

frequenta(joao, ufsm).
frequenta(maria, ufsm).
frequenta(joel, uri).

professor(carlos, calculo).
professor(ana_paula, estrutura).
professor(pedro, programacao).

funcionario(pedro, ufsm).
funcionario(ana_paula, ufsm).
funcionario(carlos, uri).

% (a) Quem são os alunos do professor X?
% Regra: um aluno A é de professor P se existe disciplina D
%        tal que aluno(A,D) e professor(P,D).
aluno_do_professor(P, A) :- professor(P, D), aluno(A, D).

% (b) Quem são as pessoas associadas à universidade X? (alunos e professores)
% Alunos associados: quem frequenta(X).
associado_a(Pessoa, Univ) :- frequenta(Pessoa, Univ).

% Professores associados: quem é professor e funcionário da mesma Univ.
associado_a(Prof, Univ) :- professor(Prof, _), funcionario(Prof, Univ).

% Consultas possíveis:
% ?- aluno_do_professor(carlos, A).
% ?- aluno_do_professor(pedro, A).
% ?- associado_a(Quem, ufsm).
% ?- associado_a(Quem, uri).


% +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+

% Exercício 4 

% +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+

% Base fornecida
nota(joao, 5.0).
nota(maria, 6.0).
nota(joana, 8.0).
nota(mariana, 9.0).
nota(cleuza, 8.5).
nota(jose, 6.5).
nota(joaquim, 4.5).
nota(mara, 4.0).
nota(mary,10.0).

% Regra: situacao(Aluno, Situacao).
situacao(Aluno, aprovado)    :- nota(Aluno, N), N >= 7.0.
situacao(Aluno, recuperacao) :- nota(Aluno, N), N >= 5.0, N < 7.0.
situacao(Aluno, reprovado)   :- nota(Aluno, N), N < 5.0.

% Consultas possíveis:
% ?- situacao(Quem, aprovado).
% ?- situacao(Quem, recuperacao).
% ?- situacao(Quem, reprovado).
% ?- situacao(maria, S).
