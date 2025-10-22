;-----------------------------------------
; EXERCÍCIO 1 - SEMÁFORO COMPLETO
;-----------------------------------------
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

;-----------------------------------------
; SUB-ROTINA DE ATRASO
;-----------------------------------------
ATRASO: MOV BL,0FFh
A1:     DEC BL
        JNZ A1
        RET
END

;-----------------------------------------
; EXERCÍCIO 2 - LETRAS C E E NO DISPLAY
;-----------------------------------------
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

;-----------------------------------------
ATRASO:  MOV BL,0FFh
ATR1:    DEC BL
         JNZ ATR1
         RET
END

;-----------------------------------------
; EXERCÍCIO 3 - SEMÁFORO CONTROLADO POR TECLAS
;-----------------------------------------
ORG 0
JMP INICIO

ORG 20
INICIO:  CLO

; Porta de entrada do teclado
TECLADO EQU 07h      ; Pode ser 00h em outra versão

; Etapa 1 - Vermelhas acesas
         MOV AL,01h
         OUT 01,AL

; Aguarda 1ª tecla
ESPERA1: IN  AL,TECLADO
         CMP AL,00h
         JZ  ESPERA1      ; Fica esperando até uma tecla ser pressionada

; Etapa 2 - Amarelas + vermelhas
         MOV AL,03h
         OUT 01,AL

; Aguarda 2ª tecla
ESPERA2: IN  AL,TECLADO
         CMP AL,00h
         JZ  ESPERA2

; Etapa 3 - Verdes + amarelas + vermelhas
         MOV AL,07h
         OUT 01,AL

FIM:     JMP FIM
END
