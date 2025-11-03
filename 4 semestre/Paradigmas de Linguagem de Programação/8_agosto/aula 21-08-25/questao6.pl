% +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+

% Paradigma de Linguagem de Programação 
% Atividade 5 - Capítulo 2 - Prolog
% Data da aula: 21-08-25
% Data de entrega: 27-08-25

% +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+

% EX 6 — Catálogo de filmes

% +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+

% filme(Titulo, Genero, Diretor, Ano, Minutos).

filme(amnesia,   suspense, nolan,      2000, 113).
filme(babel,     drama,    inarritu,   2006, 142).
filme(capote,    drama,    miller,     2005, 98).
filme(casablanca,romance,  curtiz,     1942, 102).
filme(matrix,    ficcao,   wachowski,  1999, 136).
filme(rebecca,   suspense, hitchcock,  1940, 130).
filme(shrek,     aventura, adamson,    2001, 90).
filme(sinais,    ficcao,   shyamalan,  2002, 106).
filme(spartacus, acao,     kubrik,     1960, 184).
filme(superman,  aventura, donner,     1978, 143).
filme(titanic,   romance,  cameron,    1997, 194).
filme(tubarao,   suspense, spielberg,  1975, 124).
filme(wolverine, drama,    almodovar,  2006, 121).

% --- “Consultas prontas” como regras auxiliares (opcional):
dirigiu_quem_titulo(Titulo, Diretor) :- filme(Titulo, _, Diretor, _, _).
filmes_de_genero(Genero, Titulo)     :- filme(Titulo, Genero, _, _, _).
filmes_de_diretor(Diretor, Titulo)   :- filme(Titulo, _, Diretor, _, _).
ano_de(Titulo, Ano)                  :- filme(Titulo, _, _, Ano, _).
duracao_menor_que(Titulo, Limite)    :- filme(Titulo, _, _, _, Min), Min < Limite.
entre_anos(Titulo, A, B)             :- filme(Titulo, _, _, Ano, _), Ano >= A, Ano =< B.
antes_de(Titulo, AnoCorte)           :- filme(Titulo, _, _, Ano, _), Ano < AnoCorte.

% Consultas possíveis:

% a) Quem dirigiu o filme Titanic?
% ?- filme(titanic, _, Diretor, _, _).

% b) Quais são os filmes de suspense?
% ?- filme(Titulo, suspense, _, _, _).

% c) Quais os filmes dirigidos por Donner?
% ?- filme(Titulo, _, donner, _, _).

% d) Em que ano foi lançado o filme Sinais?
% ?- filme(sinais, _, _, Ano, _).

% e) Quais os filmes com duração inferior a 100 minutos?
% ?- filme(Titulo, _, _, _, Min), Min < 100.

% f) Quais os filmes lançados entre 2000 e 2005?
% ?- filme(Titulo, _, _, Ano, _), Ano >= 2000, Ano =< 2005.

% g) Quais os filmes lançados antes de 1980?
% ?- filme(Titulo, _, _, Ano, _), Ano < 1980.