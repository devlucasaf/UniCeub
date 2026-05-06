; +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=

; EXERCÍCIO 3 - SEMÁFORO CONTROLADO POR TECLAS

; +=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=+=
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
