% Código

gosta(joao, ler).
gosta(ana, caminhar).
gosta(lucas, jogar_xadrez).
gosta(carla, cozinhar).

tempo(chuvoso).
tempo(ensolarado).

atividade_indoor(ler).
atividade_indoor(jogar_xadrez).
atividade_indoor(cozinhar).
atividade_outdoor(caminhar).

recomenda(Pessoa, Atividade) :-
    gosta(Pessoa, Atividade),
    tempo(T),
    ((T = chuvoso, atividade_indoor(Atividade));
        (T = ensolarado, atividade_outdoor(Atividade))).

% Consultas:

% 5.1. 

?- gosta(carla, Atividade).

% Resposta: Atividade = cozinhar.

% 5.2. 

?- tempo(chuvoso), recomenda(lucas, Atividade).
?- tempo(chuvoso), recomenda(carla, Atividade).

% Resposta:

% Atividade = jogar_xadrez.  % para Lucas
% Atividade = cozinhar.      % para Carla

% 5.3. 

?- tempo(ensolarado), recomenda(Pessoa, Atividade).

% Resposta:

% Pessoa = ana, Atividade = caminhar.

% 5.4. 

atividade_recomendada_para_tempo(Atividade, chuvoso) :-
    atividade_indoor(Atividade).

atividade_recomendada_para_tempo(Atividade, ensolarado) :-
    atividade_outdoor(Atividade).

% 5.5.

?- atividade_recomendada_para_tempo(Atividade, Tempo).

% Resposta:

% Atividade = ler,           Tempo = chuvoso ;
% Atividade = jogar_xadrez,  Tempo = chuvoso ;
% Atividade = cozinhar,      Tempo = chuvoso ;
% Atividade = caminhar,      Tempo = ensolarado.
