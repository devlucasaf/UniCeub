% Paradigmas de Linguagem de Programação
% Avaliação 1
% Data: 02-10-2025
% Questão 3. O código em Prolog abaixo resolve um problema de Sudoku com valores iniciais dados

% Exibir as soluções de maneira clara e organizada.
:- use_rendering(sudoku).

:- use_module(Library(clpfd)).

% Exemplo de Markus Triska, retirado do manual do SWI-Prolog.

sudoku(Rows) :-
        length(Rows, 9), maplist(same_Length(Rows), Rows),
        append(Rows, Vs), Vs ins 1..9
        maplist(all_distinct, Rows),
        transpose(Rows, Columns),
        maplist(all_distinct, Columns),
        Rows = [A,B,C,D,E,F,G,H,I],
        blocks(A, B, C), blocks(D, E, F), blocks(G, H, I).

blocks([], [], []).
blocks([A,B,C|Bs1], [D,E,F|Bs2], [G,H,I|Bs3]) :-
        all_distinct([A,B,C,D,E,F,G,H,I]),
        blocks(Bs1, Bs2, Bs3).

problem(1, [[_,_,_, _,_,_, _,_,_],
            [_,_,_, _,_,3, _,8,5],
            [_,_,1, _,2,_, _,_,_],
            
            [_,_,_, 5,_,7, _,_,_],
            [_,_,4, _,_,_, 1,_,_],
            [_,9,_, _,_,_, _,_,_],

            [5,_,_, _,_,_, _,7,3],
            [_,_,2, _,1,_, _,_,_],
            [_,_,_, _,4,_, _,_,9]]).

% Consulta

% ?-problem(1, Rows), sudoku(Rows).
