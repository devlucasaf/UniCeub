program ParadigmaEstruturado;

{ =======================================================================
  PROGRAMA: Explicacao Paradigma Estruturado
  OBJETIVO: Demonstrar e explicar os conceitos de Sequencia, Selecao,
            Iteracao e Modularizacao dentro do Pascal.
  ======================================================================= }

uses crt; // Biblioteca padrao para comandos de tela como ClrScr (limpar tela)

var
  opcao: integer;

{ =======================================================================
  1. MODULARIZACAO E SEQUENCIA
  A programacao estruturada divide o problema em "modulos" (procedures 
  e functions). Dentro de cada modulo, os comandos sao executados em 
  uma SEQUENCIA logica de cima para baixo.
  ======================================================================= }
procedure ExplicarSequencia;
begin
  writeln('--- 1. SEQUENCIA ---');
  writeln('No paradigma estruturado, as instrucoes sao executadas uma apos a outra.');
  writeln('Exemplo de execucao sequencial:');
  writeln('Passo A: Acordar');
  writeln('Passo B: Fazer cafe');
  writeln('Passo C: Comecar a programar');
  writeln;
  writeln('Pressione ENTER para continuar...');
  readln;
end;

{ =======================================================================
  2. SELECAO (Condicionais)
  Permite que o programa tome decisoes baseadas em condicoes, desviando
  o fluxo de execucao sem usar o perigoso comando GOTO.
  ======================================================================= }
procedure ExplicarSelecao;
var
  idade: integer;
begin
  writeln('--- 2. SELECAO (Decisao) ---');
  writeln('A selecao permite que o codigo siga caminhos diferentes.');
  writeln('Por favor, digite uma idade ficticia para o teste: ');
  readln(idade);

  // Exemplo de IF/THEN/ELSE
  if idade >= 18 then
  begin
    writeln('-> Estrutura IF: A condicao e VERDADEIRA (Maior de idade).');
  end
  else
  begin
    writeln('-> Estrutura ELSE: A condicao e FALSA (Menor de idade).');
  end;

  writeln;
  writeln('Pressione ENTER para continuar...');
  readln;
end;

{ =======================================================================
  3. ITERACAO (Repeticao / Loops)
  Permite repetir um bloco de codigo varias vezes de forma controlada.
  Isso evita a repeticao manual de linhas de codigo.
  ======================================================================= }
procedure ExplicarIteracao;
var
  i: integer;
begin
  writeln('--- 3. ITERACAO (Repeticao) ---');
  writeln('Existem varias formas de repetir um bloco de codigo.');
  
  writeln('a) Usando o loop FOR (quando sabemos o numero exato de repeticoes):');
  for i := 1 to 3 do
  begin
    writeln('   Execucao FOR numero: ', i);
  end;

  writeln;
  writeln('b) Usando o loop WHILE (repete enquanto a condicao for verdadeira):');
  i := 1;
  while i <= 3 do
  begin
    writeln('   Execucao WHILE numero: ', i);
    i := i + 1; // Precisamos incrementar para nao criar um loop infinito
  end;

  writeln;
  writeln('Pressione ENTER para continuar...');
  readln;
end;

{ =======================================================================
  MODULO PRINCIPAL (Menu)
  Este procedimento agrupa os outros modulos usando uma estrutura 
  de repeticao (REPEAT..UNTIL) e uma de selecao multipla (CASE).
  ======================================================================= }
procedure MenuPrincipal;
begin
  repeat
    // Limpa a tela (se suportado pelo terminal/compilador)
    writeln('=========================================');
    writeln('    O QUE E PROGRAMACAO ESTRUTURADA?     ');
    writeln('=========================================');
    writeln('Escolha um conceito para entender e ver na pratica:');
    writeln('1 - Sequencia');
    writeln('2 - Selecao (IF/ELSE)');
    writeln('3 - Iteracao (Loops)');
    writeln('4 - Sair do programa');
    writeln('=========================================');
    write('Digite a sua opcao: ');
    readln(opcao);

    writeln;

    // Estrutura de selecao multipla (CASE)
    case opcao of
      1: ExplicarSequencia;
      2: ExplicarSelecao;
      3: ExplicarIteracao;
      4: writeln('Encerrando o programa. Obrigado por aprender!');
    else
      writeln('Opcao invalida! Por favor, digite um numero de 1 a 4.');
      writeln('Pressione ENTER para tentar novamente...');
      readln;
    end;
    
    writeln;
  until opcao = 4; // Fim da iteracao REPEAT..UNTIL
end;

{ =======================================================================
  BLOCO DE EXECUCAO PRINCIPAL
  No paradigma estruturado (Top-Down), o programa comeca a rodar aqui,
  chamando o modulo principal que gerencia o resto do sistema.
  ======================================================================= }
begin
  // Chamada da procedure principal
  MenuPrincipal;
end.