% Paradigmas de Linguagem de Programação
% Avaliação 1
% Data: 02-10-2025
% Questão 5. Dada a base de conhecimento:

pai(bruno, pedro).
pai(gabriel, bia).
pai(mateus, carla).
pai(carlos, ana).
pai(pedro, esther).
mae(catarina, maria).
mae(maria, pedro).
mae(ana, julia).
irmao(cosme, damiao).
irmao(adao, ada).

% Escreva as consultas de acordo com a linguagem Prolog e dê as respectivas saídas.

% 5.1. Quem são os pais de Pedro?

pai(X, pedro).
mae(X, pedro).

% Saída:
% X = bruno ;     % pai de Pedro
% X = maria.      % mãe de Pedro

% 5.2. De quem Catarina é mãe?

mae(catarina, X).

% Saída:
% X = maria

% 5.3. Quais são todas relações de irmandade nessa base de conhecimento?

irmao(X, Y).

% Saída:
% X = cosme, Y = damiao ;
% X = adao, Y = ada.

% 5.4. Crie uma regra, avo_paterno, que defina avô paterno.

avo_paterno(X, Y) :- pai(X, Z), pai(Z, Y).

% 5.5. Usando a regra avo_paterno, escolha um par (X, Y) tal que avo_paterno (X,Y) seja 
verdadeira

avo_paterno(X, Y).

% Saída:
% X = bruno,
% Y = esther.
