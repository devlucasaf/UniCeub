; +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

; EXERCÍCIO 2 - LETRAS C E E NO DISPLAY

; +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

ORG 0
JMP INICIO

ORG 20
INICIO:  CLO

; Máscaras para letras (ativo alto, comum-cátodo)
C_SEG   EQU 39h     ; C = 0b00111001
E_SEG   EQU 79h     ; E = 0b01111001

; Pinos de seleção dos displays
ESQ     EQU 01h     ; Display esquerdo
DIR     EQU 02h     ; Display direito

REP:     MOV AL,C_SEG    ; Exibe C
        OUT 02,AL
        OUT 03,ESQ      ; Habilita display esquerdo
        CALL ATRASO

        MOV AL,E_SEG    ; Exibe E
        OUT 02,AL
        OUT 03,DIR      ; Habilita display direito
        CALL ATRASO

        JMP REP         ; Repete continuamente

; +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

ATRASO:  MOV BL,0FFh
ATR1:    DEC BL
        JNZ ATR1
        RET
END
