% +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+

% Paradigma de Linguagem de Programação 
% Atividade 4 - Capítulo 2 - Prolog
% Data da aula: 14-08-25
% Data de entrega: 20-08-25

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