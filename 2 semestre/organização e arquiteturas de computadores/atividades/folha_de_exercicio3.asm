;-----------------------------------------
; EXERCÍCIO 1 - LER TECLA E ARMAZENAR EM 90h
;-----------------------------------------
ORG 0
JMP INICIO

ORG 20
INICIO: CLO

         IN  AL,00h      ; Lê o valor da tecla (porta 00)
         MOV [90h],AL    ; Armazena o valor no endereço 90h

FIM:     JMP FIM         ; Loop infinito
END

;-----------------------------------------
; EXERCÍCIO 2 - ACEITAR SOMENTE LETRAS MINÚSCULAS
;-----------------------------------------
ORG 0
JMP INICIO

ORG 20
INICIO: CLO

LER:     IN  AL,00h          ; Lê uma tecla
         CMP AL,61h          ; Comparar com 'a'
         JC  LER             ; Se menor, lê novamente
         CMP AL,7Ah          ; Comparar com 'z'
         JNC LER             ; Se maior, lê novamente
         MOV [90h],AL        ; Guarda a tecla válida em 90h

FIM:     JMP FIM
END

;-----------------------------------------
; EXERCÍCIO 3 - PREENCHER E FAZER XOR
;-----------------------------------------
ORG 0
JMP INICIO

ORG 20
INICIO: CLO

         MOV DL,60h      ; Endereço inicial
         MOV BL,05h      ; Valor inicial
         MOV CL,10       ; 16 posições (de 60h a 6Fh)

PREENCHER:
         MOV [DL],BL     ; Guarda valor
         ADD BL,02h      ; Incrementa +2
         INC DL          ; Próximo endereço
         DEC CL
         JNZ PREENCHER

; XOR de todos os valores
         MOV DL,60h
         MOV AL,[DL]     ; AL = primeiro valor
         INC DL
         MOV CL,0Fh      ; restam 15 valores

XOR_LOOP:
         XOR AL,[DL]
         INC DL
         DEC CL
         JNZ XOR_LOOP

         MOV [70h],AL    ; Resultado final em 7016h

FIM:     JMP FIM
END

;-----------------------------------------
; EXERCÍCIO 4 - TRANSFERIR PARA A PILHA
;-----------------------------------------
ORG 0
JMP INICIO

ORG 20
INICIO: CLO

         MOV DL,60h      ; Início dos dados
         MOV CL,10       ; 16 valores

; PUSH dos valores na pilha
PUSH_LOOP:
         MOV AL,[DL]
         PUSH AL
         INC DL
         DEC CL
         JNZ PUSH_LOOP

; Agora POP para outra área da memória
         MOV DL,B0h      ; Endereço destino (BFh a B0h)
         MOV CL,10

POP_LOOP:
         POP  AL
         MOV [DL],AL
         INC DL
         DEC CL
         JNZ POP_LOOP

FIM:     JMP FIM
END

;-----------------------------------------
; EXERCÍCIO 5 - TESTE DE SHR E SHL
;-----------------------------------------
ORG 0
JMP INICIO

ORG 20
INICIO: CLO

         MOV DL,16h     ; Valor inicial = 00010110b

         SHR DL,1       ; Desloca 1 bit à direita → divide por 2
         ; DL = 00001011b = 0Bh

         SHR DL,1       ; Desloca novamente → divide por 2 de novo
         ; DL = 00000101b = 05h

         SHL DL,1       ; Desloca à esquerda → multiplica por 2
         ; DL = 00001010b = 0Ah

END
