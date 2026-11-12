; +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

; EXERCÍCIO 3 - PREENCHER E FAZER XOR

; +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

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
