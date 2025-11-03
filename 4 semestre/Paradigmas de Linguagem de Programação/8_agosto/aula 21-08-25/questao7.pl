% +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+

% Paradigma de Linguagem de Programação 
% Atividade 5 - Capítulo 2 - Prolog
% Data da aula: 21-08-25
% Data de entrega: 27-08-25

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
