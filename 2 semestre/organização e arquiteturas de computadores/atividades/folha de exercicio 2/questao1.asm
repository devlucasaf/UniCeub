; +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

; EXERCÍCIO 1 - SEMÁFORO COMPLETO

; +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

ORG 0
JMP INICIO

ORG 20
INICIO:  CLO               ; Limpa flags

        MOV AL,01h        ; 0000 0001 → acende vermelhas
        OUT 01,AL

        CALL ATRASO       ; Pequeno delay
        
        MOV AL,03h        ; 0000 0011 → mantém vermelhas e acende amarelas
        OUT 01,AL

        CALL ATRASO

        MOV AL,07h        ; 0000 0111 → todas acesas (vermelha+amarela+verde)
        OUT 01,AL

FIM:     JMP FIM           ; Loop infinito

; +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

; SUB-ROTINA DE ATRASO

; +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

ATRASO: MOV BL,0FFh
A1:     DEC BL
        JNZ A1
        RET
END