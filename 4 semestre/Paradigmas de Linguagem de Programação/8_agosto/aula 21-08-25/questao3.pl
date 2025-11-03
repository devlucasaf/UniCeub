% +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+

% Paradigma de Linguagem de Programação 
% Atividade 4 - Capítulo 2 - Prolog
% Data da aula: 14-08-25
% Data de entrega: 20-08-25

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
