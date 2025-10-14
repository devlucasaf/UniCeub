% +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+

% Paradigma de Linguagem de Programação 
% Atividade 5 - Capítulo 2 - Prolog
% Data: 27-08-25

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


% +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+

% Exercício 5  - Bairro, região, amizades e carona

% +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+

% Predicados do enunciado:
%   mora(Pessoa, Bairro).
%   pertence(Bairro, Regiao).
%   amigo(PessoaA, PessoaB).
%   tem_carro(Pessoa).

pertence(centro, centro).
pertence(jardim_america, zona_leste).
pertence(vila_nova, zona_sul).
pertence(parque_das_aguas, zona_leste).
pertence(bom_fim, zona_norte).
pertence(santa_marta, zona_sul).
pertence(sao_lucas, zona_norte).

% --- Fatos: quem mora onde
mora(ana, jardim_america).
mora(bruno, vila_nova).
mora(carla, centro).
mora(daniel, parque_das_aguas).
mora(erica, jardim_america).
mora(felipe, bom_fim).
mora(gabriel, santa_marta).
mora(helena, sao_lucas).

% --- Fatos: amizades (direção livre; se quiser bidirecional, declare os dois)
amigo(ana, erica).
amigo(erica, ana).
amigo(ana, daniel).
amigo(bruno, felipe).
amigo(gabriel, helena).

% --- Fatos: quem tem carro
tem_carro(ana).
tem_carro(daniel).
tem_carro(erica).
% (os demais não têm carro)

% --- Regra (b): pode dar carona se tem carro e mora na mesma região
pode_dar_carona(De, Para) :-
    tem_carro(De),
    mora(De, B1), mora(Para, B2),
    pertence(B1, R), pertence(B2, R),
    De \= Para.

% (Opcional) versão que restringe a carona só para amigos:
pode_dar_carona_para_amigo(De, Para) :-
    pode_dar_carona(De, Para),
    amigo(De, Para).

% Consultas possíveis:

% ?- pode_dar_carona(ana, erica).
% true.

% ?- pode_dar_carona(ana, bruno).
% false.

% ?- pode_dar_carona(daniel, ana).
% true.

% ?- pode_dar_carona_para_amigo(ana, erica).
% true.

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

% +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+

% EX 7 — Automóveis

% +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+

% Modelos
modelo(grand_livina).
modelo(spin).
modelo(fiesta_hatch).
modelo(fiesta_sedan).
modelo(gol).

% Marcas
marca(nissan).
marca(chevrolet).
marca(ford).
marca(volkswagen).

% Relação marca–modelo
fabrica(nissan,      grand_livina).
fabrica(chevrolet,   spin).
fabrica(ford,        fiesta_hatch).
fabrica(ford,        fiesta_sedan).
fabrica(volkswagen,  gol).

% Lugares
lugares(grand_livina, 7).
lugares(spin,         7).
lugares(fiesta_hatch, 5).
lugares(fiesta_sedan, 5).
lugares(gol,          5).

% Porta-malas (padronizado para 'portamalas/2')
portamalas(grand_livina, grande).
portamalas(spin,         grande).
portamalas(fiesta_sedan, medio).
portamalas(fiesta_hatch, pequeno).
portamalas(gol,          pequeno).

% --- Regras pedidas e úteis

% a) Modelos existentes -> já é 'modelo/1'
% b) Marcas existentes  -> já é 'marca/1'
% c) Qual marca fabrica qual modelo -> 'fabrica/2'

% d) Carros fabricados pela Ford
carro_ford(Modelo) :- fabrica(ford, Modelo).

% e) Quantos lugares tem cada modelo -> 'lugares/2'
% f) Quais carros têm 7 lugares
sete_lugares(Modelo) :- lugares(Modelo, 7).

% g) Tamanho do porta-malas de cada modelo -> 'portamalas/2'
% h) Quais têm porta-malas pequeno
porta_malas_pequeno(Modelo) :- portamalas(Modelo, pequeno).

% --- Regras extra do enunciado:
% (a) Modelo(s) indicado(s) para família grande (>=7 lugares)
familia_grande(Modelo) :- lugares(Modelo, L), L >= 7.

% (b) Entre 2 carros, qual tem mais lugares?
%     Resultado é o terceiro argumento.
mais_lugares(C1, C2, C1) :- lugares(C1, L1), lugares(C2, L2), L1 > L2.
mais_lugares(C1, C2, C2) :- lugares(C1, L1), lugares(C2, L2), L2 > L1.
mais_lugares(C1, C2, empate) :- lugares(C1, L), lugares(C2, L).

% Consultas possíveis:

% ?- modelo(M).
% ?- marca(Ma).
% ?- fabrica(Marca, Modelo).
% ?- carro_ford(M).
% ?- lugares(Modelo, L).
% ?- sete_lugares(M).
% ?- portamalas(Modelo, T).
% ?- porta_malas_pequeno(M).

% Regras finais:
% ?- familia_grande(M).
% ?- mais_lugares(grand_livina, gol, QuemTemMais).
