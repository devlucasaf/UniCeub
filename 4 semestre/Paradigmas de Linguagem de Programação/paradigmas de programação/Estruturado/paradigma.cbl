*================================================================*
      * IDENTIFICATION DIVISION
      *================================================================*
       IDENTIFICATION DIVISION.
       PROGRAM-ID. PARADIGMA-ESTRUTURADO.
       AUTHOR. GEMINI-AI.
       DATE-WRITTEN. 25-FEB-2026.
      *----------------------------------------------------------------*
      * OBJETIVO: Este programa foi desenvolvido com o intuito de
      * ensinar e demonstrar os conceitos fundamentais do Paradigma de
      * Programacao Estruturada, utilizando a propria sintaxe do COBOL.
      *
      * O teorema de Bohm-Jacopini provou que qualquer programa pode
      * ser escrito usando apenas tres estruturas basicas:
      * 1. Sequencia
      * 2. Selecao (Decisao)
      * 3. Iteracao (Repeticao)
      *----------------------------------------------------------------*

      *================================================================*
      * ENVIRONMENT DIVISION
      *================================================================*
       ENVIRONMENT DIVISION.
       CONFIGURATION SECTION.
       SOURCE-COMPUTER. ANY-COMPUTER.
       OBJECT-COMPUTER. ANY-COMPUTER.

      *================================================================*
      * DATA DIVISION
      *================================================================*
       DATA DIVISION.
       WORKING-STORAGE SECTION.

      *----------------------------------------------------------------*
      * VARIAVEIS DE CONTROLE E DADOS PARA DEMONSTRACAO
      *----------------------------------------------------------------*
       01  WS-CONTROLE-DEMONSTRACAO.
           05  WS-IDADE              PIC 9(03) VALUE 22.
           05  WS-SITUACAO           PIC X(20) VALUE SPACES.
           05  WS-NOTA-ALUNO         PIC 9(03) VALUE 85.
           05  WS-NIVEL-ESTUDO       PIC X(15) VALUE "MEDIO".

      *----------------------------------------------------------------*
      * VARIAVEIS PARA ITERACAO
      *----------------------------------------------------------------*
       01  WS-CONTADORES.
           05  WS-INDICE-LOOP        PIC 9(02) VALUE ZERO.
           05  WS-MAXIMO-ITERACOES   PIC 9(02) VALUE 5.
           05  WS-SOMA-VALORES       PIC 9(04) VALUE ZERO.

      *----------------------------------------------------------------*
      * MENSAGENS FIXAS DO PROGRAMA
      *----------------------------------------------------------------*
       01  WS-MENSAGENS.
           05  WS-MSG-CABECALHO      PIC X(70) VALUE
               "=== BEM-VINDO AO TUTORIAL COBOL ESTRUTURADO ===".
           05  WS-MSG-RODAPE         PIC X(70) VALUE
               "=== FIM DO PROGRAMA DE DEMONSTRACAO ===".
           05  WS-LINHA-DIVISORIA    PIC X(70) VALUE
               "-------------------------------------------------------".

      *================================================================*
      * PROCEDURE DIVISION
      *================================================================*
       PROCEDURE DIVISION.

      *----------------------------------------------------------------*
      * 0000-MODULO-PRINCIPAL
      * Este é o ponto de entrada. Aqui mostramos a Modularidade, que
      * eh a base para programas estruturados, evitando o temido GO TO.
      * Cada PERFORM chama uma subrotina ou bloco logico distinto.
      *----------------------------------------------------------------*
       0000-MODULO-PRINCIPAL.
           DISPLAY WS-LINHA-DIVISORIA.
           DISPLAY WS-MSG-CABECALHO.
           DISPLAY WS-LINHA-DIVISORIA.

           DISPLAY "O Paradigma Estruturado mudou a historia "
                   "da computacao.".
           DISPLAY "Ele evita espaguete de codigo usando "
                   "tres bases simples:".
           DISPLAY " ".

           PERFORM 1000-ESTRUTURA-SEQUENCIAL.
           PERFORM 2000-ESTRUTURA-SELECAO.
           PERFORM 3000-ESTRUTURA-ITERACAO.
           PERFORM 4000-MODULARIDADE-E-GOTO.

           DISPLAY WS-LINHA-DIVISORIA.
           DISPLAY WS-MSG-RODAPE.
           DISPLAY WS-LINHA-DIVISORIA.

           STOP RUN.

      *----------------------------------------------------------------*
      * 1000-ESTRUTURA-SEQUENCIAL
      * Pilar 1: Instrucoes executadas uma apos a outra.
      *----------------------------------------------------------------*
       1000-ESTRUTURA-SEQUENCIAL.
           DISPLAY ">>> 1. ESTRUTURA SEQUENCIAL <<<".
           DISPLAY "A ordem das instrucoes importa. O computador".
           DISPLAY "executa o codigo linha por linha, de cima para".
           DISPLAY "baixo, manipulando o estado do programa.".
           DISPLAY " ".
           DISPLAY "[Passo 1] - Atribuindo variaveis...".
           MOVE 10 TO WS-INDICE-LOOP.
           DISPLAY "[Passo 2] - Executando calculos...".
           COMPUTE WS-SOMA-VALORES = WS-INDICE-LOOP * 5.
           DISPLAY "[Passo 3] - Resultado processado...".
           DISPLAY "Resultado Sequencial da Multiplicacao: " 
                   WS-SOMA-VALORES.
           DISPLAY " ".

      *----------------------------------------------------------------*
      * 2000-ESTRUTURA-SELECAO
      * Pilar 2: Tomada de decisao (IF/ELSE e EVALUATE).
      *----------------------------------------------------------------*
       2000-ESTRUTURA-SELECAO.
           DISPLAY ">>> 2. ESTRUTURA DE SELECAO (DECISAO) <<<".
           DISPLAY "Permite ao programa escolher caminhos diferentes".
           DISPLAY "baseando-se em condicoes logicas.".
           DISPLAY " ".

      * Exemplo com IF-ELSE
           DISPLAY "--- Exemplo com Condicional Simples (IF) ---".
           DISPLAY "Idade do usuario informada: " WS-IDADE.
           IF WS-IDADE >= 18
               MOVE "MAIOR DE IDADE" TO WS-SITUACAO
               DISPLAY "Avaliacao IF: Usuario " WS-SITUACAO
           ELSE
               MOVE "MENOR DE IDADE" TO WS-SITUACAO
               DISPLAY "Avaliacao IF: Usuario " WS-SITUACAO
           END-IF.
           DISPLAY " ".

      * Exemplo com EVALUATE (Equivalente ao Switch/Case)
           DISPLAY "--- Exemplo com Condicional Multipla (EVALUATE) ---".
           DISPLAY "Nota do Aluno (0 a 100): " WS-NOTA-ALUNO.
           EVALUATE TRUE
               WHEN WS-NOTA-ALUNO >= 90
                   DISPLAY "Avaliacao EVALUATE: Conceito A (Excelente)"
               WHEN WS-NOTA-ALUNO >= 70
                   DISPLAY "Avaliacao EVALUATE: Conceito B (Bom)"
               WHEN OTHER
                   DISPLAY "Avaliacao EVALUATE: Conceito C/D (Recuperacao)"
           END-EVALUATE.
           DISPLAY " ".

      *----------------------------------------------------------------*
      * 3000-ESTRUTURA-ITERACAO
      * Pilar 3: Repeticao de blocos de codigo (Loops).
      *----------------------------------------------------------------*
       3000-ESTRUTURA-ITERACAO.
           DISPLAY ">>> 3. ESTRUTURA DE ITERACAO (LOOP) <<<".
           DISPLAY "Permite que blocos de codigo sejam repetidos ate".
           DISPLAY "que uma condicao logica de saida seja satisfeita.".
           DISPLAY " ".

           MOVE 1 TO WS-INDICE-LOOP.

      * A repeticao ocorre chamando o paragrafo utilitario ate a
      * condicao ser verdadeira (PERFORM ... UNTIL).
           DISPLAY "Iniciando Loop de 1 a " WS-MAXIMO-ITERACOES ":".
           PERFORM 3100-PROCESSO-LOOP
              UNTIL WS-INDICE-LOOP > WS-MAXIMO-ITERACOES.
           DISPLAY "Loop Finalizado com Sucesso.".
           DISPLAY " ".

      *----------------------------------------------------------------*
      * 3100-PROCESSO-LOOP
      * Paragrafo utilitario invocado pelo processo de demonstracao.
      *----------------------------------------------------------------*
       3100-PROCESSO-LOOP.
           DISPLAY "    -> Processando iteracao numero: " WS-INDICE-LOOP.
           ADD 1 TO WS-INDICE-LOOP.

      *----------------------------------------------------------------*
      * 4000-MODULARIDADE-E-GOTO
      * Explicacao adicional sobre o design estruturado e manutencao.
      *----------------------------------------------------------------*
       4000-MODULARIDADE-E-GOTO.
           DISPLAY ">>> CONCEITO EXTRA: MODULARIDADE E O 'GO TO' <<<".
           DISPLAY "A programacao estruturada encapsula a complexidade".
           DISPLAY "em blocos funcionais menores, como fizemos ao".
           DISPLAY "dividir este programa em paragrafos (0000, 1000...).".
           DISPLAY "Isso limitou imensamente a necessidade do 'GO TO', ".
           DISPLAY "que criava codigos incontrolaveis e dificeis de ".
           DISPLAY "rastrear. O fluxo estruturado eh claro e confiavel.".
           DISPLAY " ".