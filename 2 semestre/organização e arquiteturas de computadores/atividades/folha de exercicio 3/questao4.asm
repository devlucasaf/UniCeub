; +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

; EXERCÍCIO 4 - TRANSFERIR PARA A PILHA

; +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

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
