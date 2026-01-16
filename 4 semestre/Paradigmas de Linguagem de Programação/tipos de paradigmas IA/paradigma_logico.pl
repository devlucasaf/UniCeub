% Paradigmas de Linguagens de Programação
% Paradigma Funcional: Exemplo de Hospital
% Estudo prático sobre paradigma lógico 
% *CÓDIGO DESENVOLVIDO A FIM DE ESTUDOS! NÃO FOI PASSADO EM SALA!

paciente(joao, 25, gripe).
paciente(maria, 40, pneumonia).
paciente(carlos, 60, covid).
paciente(ana, 30, fratura).
paciente(lucas, 50, diabetes).
paciente(fernanda, 35, hipertensao).
paciente(pedro, 70, insuficiencia_cardiaca).
paciente(laura, 20, alergia).
paciente(bruno, 45, gastrite).
paciente(sofia, 55, cancer).


medico(dr_silva, cardiologia).
medico(dr_souza, ortopedia).
medico(dr_mendes, infectologia).
medico(dr_pereira, clinico_geral).
medico(dr_almeida, endocrinologia).
medico(dr_costa, pneumologia).
medico(dr_rocha, oncologia).
medico(dr_gomes, alergologia).
medico(dr_barbosa, gastroenterologia).
medico(dr_martins, pediatria).

internado(joao, enfermaria).
internado(maria, uti).
internado(carlos, isolamento).
internado(ana, ortopedia).
internado(lucas, endocrinologia).
internado(fernanda, clinica_medica).
internado(pedro, cardiologia).
internado(laura, enfermaria).
internado(bruno, clinica_medica).
internado(sofia, oncologia).

exame(joao, hemograma).
exame(maria, raio_x).
exame(carlos, tomografia).
exame(ana, raio_x).
exame(lucas, glicemia).
exame(fernanda, eletrocardiograma).
exame(pedro, ecocardiograma).
exame(laura, teste_alergia).
exame(bruno, endoscopia).
exame(sofia, biopsia).

% Encontrar médico responsável por paciente
responsavel(Paciente, Medico) :-
    paciente(Paciente, _, Doenca),
    medico(Medico, Especialidade),
    trata(Especialidade, Doenca).

% Relação entre especialidade e doença
trata(cardiologia, insuficiencia_cardiaca).
trata(cardiologia, hipertensao).
trata(ortopedia, fratura).
trata(infectologia, covid).
trata(infectologia, gripe).
trata(pneumologia, pneumonia).
trata(endocrinologia, diabetes).
trata(alergologia, alergia).
trata(gastroenterologia, gastrite).
trata(oncologia, cancer).
trata(clinico_geral, gripe).
trata(clinico_geral, gastrite).

% Pacientes em risco (idade > 60 ou doença grave)
risco(Paciente) :-
    paciente(Paciente, Idade, Doenca),
    (Idade > 60 ; grave(Doenca)).

grave(covid).
grave(pneumonia).
grave(cancer).
grave(insuficiencia_cardiaca).

% Pacientes que precisam de UTI
precisa_uti(Paciente) :-
    paciente(Paciente, _, Doenca),
    grave(Doenca).

% Pacientes atendidos por determinada especialidade
pacientes_por_especialidade(Especialidade, Lista) :-
    findall(Paciente, (paciente(Paciente, _, Doenca), trata(Especialidade, Doenca)), Lista).

% Médicos que atendem pacientes internados em uma ala
medicos_por_ala(Ala, Lista) :-
    findall(Medico, (internado(Paciente, Ala), responsavel(Paciente, Medico)), Lista).

exame_necessario(gripe, hemograma).
exame_necessario(pneumonia, raio_x).
exame_necessario(covid, tomografia).
exame_necessario(fratura, raio_x).
exame_necessario(diabetes, glicemia).
exame_necessario(hipertensao, eletrocardiograma).
exame_necessario(insuficiencia_cardiaca, ecocardiograma).
exame_necessario(alergia, teste_alergia).
exame_necessario(gastrite, endoscopia).
exame_necessario(cancer, biopsia).

% Verificar se paciente fez exame correto
exame_correto(Paciente) :-
    paciente(Paciente, _, Doenca),
    exame(Paciente, Exame),
    exame_necessario(Doenca, Exame).

% --- Consultas Exemplos ---
% ?- responsavel(joao, Medico).
% ?- risco(Paciente).
% ?- precisa_uti(Paciente).
% ?- pacientes_por_especialidade(cardiologia, Lista).
% ?- medicos_por_ala(uti, Lista).
% ?- exame_correto(maria).

% --- Regras adicionais para expandir ---
% Contar pacientes por ala
contar_pacientes(Ala, Total) :-
    findall(Paciente, internado(Paciente, Ala), Lista),
    length(Lista, Total).

% Contar pacientes por médico
contar_pacientes_medico(Medico, Total) :-
    findall(Paciente, responsavel(Paciente, Medico), Lista),
    length(Lista, Total).

% Listar pacientes graves
listar_graves(Lista) :-
    findall(Paciente, risco(Paciente), Lista).

% Pacientes que não fizeram exame correto
exame_incorreto(Paciente) :-
    paciente(Paciente, _, Doenca),
    exame(Paciente, Exame),
    exame_necessario(Doenca, ExameNec),
    Exame \= ExameNec.

% --- Funções recursivas ---
% Verificar lista de pacientes graves
todos_graves([]).
todos_graves([H|T]) :-
    risco(H),
    todos_graves(T).

% Verificar se todos pacientes de uma ala são graves
ala_grave(Ala) :-
    findall(Paciente, internado(Paciente, Ala), Lista),
    todos_graves(Lista).

% ============================================
% Fim do código hospitalar funcional em Prolog
% ============================================
