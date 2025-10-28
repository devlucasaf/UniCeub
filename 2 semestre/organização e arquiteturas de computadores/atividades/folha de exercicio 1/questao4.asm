; 4) Faça um programa que apresente no display de sete segmentos (porta 02) o valor 88.

ORG 0
JMP INICIO

ORG 20
INICIO:  CLO
; Ajuste EIGHT de acordo com seu hardware:
EIGHT   EQU 7Fh        ; se common-cathode
;EIGHT  EQU 80h        ; se common-anode (ativo baixo)

        MOV  AL,EIGHT
        OUT  02,AL     ; escreve '8' no 7-seg
        ; caso precise do segundo '8', repita a escrita / selecione o outro dígito conforme sua multiplexação
END