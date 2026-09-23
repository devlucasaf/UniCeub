-- Atividade Algebra Relacional
-- Banco de Dados II

group: empresa
description[[ 
Este é um exemplo de banco de dados do tipo "empresa" contendo 
uma única relação 'funcionario' com alguns dados fictícios.
]]
funcionario = {
pnome, minicial, unome, cpf, datanasc:date, endereco, sexo, salario:number, cpf_supervisor, dnr:number
Joao, B, Silva, 12345678966, 1965-01-09, 'Rua das Flores, 751, São Paulo, SP', M, 30000, 33344556587, 5
Fernando, T, Wong, 33344556587, 1955-12-08, 'Rua da Lapa, 34, São Paulo, SP', M, 40000, 8886655576, 5
Alice, J, Zelaya, 09088777767, 1968-01-19, 'Rua Souza Lima, 35, Curitiba, PR', F, 25000, 98765432168, 4
Jennifer, S, Souza, 98765432168, 1941-06-20, 'Av. Arthur de Lima, 54, Santo Andre, SP', F, 43000, 8886655576, 4
Ronaldo, K, Lima, 66688444476, 1962-09-15, 'Rua Rebouças, 65, Piracicaba, SP', M, 38000, 33344556587, 5
Joice, A, Leite, 4543545376, 1972-07-31, 'Av. Lucas Öbes, 74, São Paulo, SP', F, 25000, 33344556587, 5
Andre, V, Pereira, 98798798733, 1969-03-29, 'Rua Timbira, 35, São Paulo, SP', M, 25000, 98765432168, 4
Jorge, E, Brito, 88866555676, 1937-11-10, 'Rua do Horto, 35, São Paulo, SP', M, 55000, NULL, 1
}


-- +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

-- A) 10 Exercícios de Projeção (π)

-- Exercício P1:

π pnome, unome (funcionario)

-- Exercício P2:

π cpf, cpf_supervisor (funcionario)

-- Exercício P3:

π pnome, minicial, dnr (funcionario)

-- Exercício P4:

π endereco (funcionario)

-- Exercício P5:

π pnome, unome, salario (funcionario)

-- Exercício P6:

π datanasc, sexo, salario (funcionario)

-- Exercício P7:

π pnome, unome, cpf_supervisor (funcionario)

-- Exercício P8:

π pnome, minicial, unome, cpf, datanasc, endereco, sexo, salario, dnr (funcionario)

-- Exercício P9:

π unome, endereco (funcionario)

-- Exercício P10:

π minicial, sexo, dnr (funcionario)

-- +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

-- B) 10 Exercícios de Seleção (σ)

-- Exercício S1:

σ sexo = 'M' (funcionario)

-- Exercício S2:

σ cpf_supervisor = NULL (funcionario)

-- Exercício S3:

σ salario > 40000 (funcionario)

-- Exercício S4:

σ dnr = 1 (funcionario)

-- Exercício S5:

σ datanasc > date('1965-01-01') (funcionario)

-- Exercício S6:

σ salario >= 25000 and salario <= 30000 (funcionario)

-- Exercício S7:

σ datanasc < date('1960-01-01') (funcionario)

-- Exercício S8:

σ cpf_supervisor ≠ NULL (funcionario)

-- Exercício S9:

σ datanasc > date('1960-01-01') and salario >= 20000 and sexo = 'F' (funcionario)

-- Exercício S10:

σ salario = 25000 (funcionario)

-- +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

-- C) 10 Exercícios de Renomeação (ρ)

-- Exercício R1:

ρ F (funcionario)

-- Exercício R2:

ρ nome ← pnome (funcionario)

-- Exercício R3:

π sobrenome, cpf (ρ sobrenome ← unome (funcionario))

-- Exercício R4:

ρ genero ← sexo, vencimento ← salario (funcionario)

-- Exercício R5:

π pnome, unome, sexo (ρ emp (funcionario))

-- Exercício R6:

ρ data_de_nascimento ← datanasc (funcionario)

-- Exercício R7:

π mi, pnome, unome (ρ mi ← minicial (ρ func (funcionario)))

-- Exercício R8:

ρ f2 (ρ fi (funcionario))

-- Exercício R9:

π pnome, unome, sup (ρ sup ← cpf_supervisor (funcionario))

-- Exercício R10:

π local_residencia (ρ local_residencia ← endereco (funcionario))

-- +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

-- D) 10 Exercícios de Projeção + Seleção

-- Exercício PS1:

π pnome, unome, salario (σ dnr = 5 (funcionario))

-- Exercício PS2:

π pnome, endereco (σ salario >= 20000 and salario <= 30000 (funcionario))

-- Exercício PS3:

π cpf, salario (σ salario > 30000 (funcionario))

-- Exercício PS4:

π pnome, unome, datanasc (σ sexo = 'F' (funcionario))

-- Exercício PS5:

π pnome, cpf_supervisor (σ cpf_supervisor ≠ NULL (funcionario))

-- Exercício PS6:

π pnome, unome, dnr (σ dnr = 1 or dnr = 4 (funcionario))

-- Exercício PS7:

π pnome, unome, datanasc (σ datanasc > date('1965-01-01') (funcionario))

-- Exercício PS8:

π pnome, cpf, salario (σ salario <= 25000 (funcionario))

-- Exercício PS9:

π pnome, unome, dnr (σ unome = 'Souza' (funcionario))

-- Exercício PS10:

π pnome, salario, endereco (σ salario >= 38000 (funcionario))
