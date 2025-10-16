% Paradigmas de Linguagens de Programação
% Trabalho Projeto Prolog - Teste de Einstein 
% Data de entrega: 11/09/2025

% Código em Prolog para o Teste de Einstein

% O quebra-cabeça será resolvido se a função "solucao(X)." for chamada.

solucao(Casas) :-
    Casas = [_,_,_,_,_],

    % Cada casa é representada por: casa(Cor, Nacionalidade, Animal, Bebida, Cigarro)

    % 1. O britânico vive na casa vermelha.
    member(casa(vermelha, britanico, _, _, _), Casas),

    % 2. O sueco tem cachorros como animais de estimação.
    member(casa(_, sueco, cachorro, _, _), Casas),

    % 3. O dinamarquês bebe chá.
    member(casa(_, dinamarques, _, cha, _), Casas),

    % 4. A casa verde está imediatamente à esquerda da casa branca.
    next_to(casa(verde, _, _, _, _), casa(branca, _, _, _, _), Casas),

    % 5. O dono da casa verde bebe café.
    member(casa(verde, _, _, cafe, _), Casas),

    % 6. A pessoa que fuma Pall Mall cria pássaros.
    member(casa(_, _, passaro, _, pall_mall), Casas),

    % 7. O dono da casa amarela fuma Dunhill.
    member(casa(amarela, _, _, _, dunhill), Casas),

    % 8. O homem que vive na casa do centro bebe leite.
    Casas = [_, _, casa(_, _, _, leite, _), _, _],

    % 9. O norueguês vive na primeira casa.
    Casas = [casa(_, noruegues, _, _, _) | _],

    % 10. O homem que fuma Blends vive ao lado do que tem gatos.
    next_to(casa(_, _, _, _, blends), casa(_, _, gato, _, _), Casas),

    % 11. O homem que tem cavalos vive ao lado do que fuma Dunhill.
    next_to(casa(_, _, cavalo, _, _), casa(_, _, _, _, dunhill), Casas),

    % 12. O homem que fuma BlueMaster bebe cerveja.
    member(casa(_, _, _, cerveja, blue_master), Casas),

    % 13. O alemão fuma Prince.
    member(casa(_, alemao, _, _, prince), Casas),

    % 14. O norueguês vive ao lado da casa azul.
    next_to(casa(_, noruegues, _, _, _), casa(azul, _, _, _, _), Casas),

    % 15. O homem que fuma Blends tem um vizinho que bebe água.
    next_to(casa(_, _, _, _, blends), casa(_, _, _, agua, _), Casas).

% Regras auxiliares
next_to(A, B, Lista) :- append(_, [A, B | _], Lista).
next_to(A, B, Lista) :- append(_, [B, A | _], Lista).
