% +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+

% Paradigma de Linguagem de Programação 
% Atividade 4 - Capítulo 2 - Prolog
% Data da aula: 14-08-25
% Data de entrega: 20-08-25

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
